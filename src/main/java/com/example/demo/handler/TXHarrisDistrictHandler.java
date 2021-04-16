package com.example.demo.handler;

import com.example.demo.entity.ScrapedDocketData;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.DemoApplication.fileName;

public class TXHarrisDistrictHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger("com.example.demo.handler.TXHarrisDistrictHandler");
    private boolean timeToSave;
    private StringBuilder builder;
    private List<ScrapedDocketData> scrapedDocketDataList = new ArrayList<>();

    public List<ScrapedDocketData> getScrapedDocketDataList() {
        return scrapedDocketDataList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("n-document".equals(qName)) {
            timeToSave = true;
            builder = new StringBuilder();
        }
        if (timeToSave) {
            builder.append("<").append(qName).append(">");
        }
    }

    public void characters(char[] ch, int start, int length) {
        if (timeToSave) {
            builder.append(ch, start, length);
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (timeToSave) {
            builder.append("</").append(qName).append(">");
        }
        if ("n-document".equals(qName)) {
            timeToSave = false;
            String oneDocket = builder.toString();
            if (StringUtils.isNotBlank(oneDocket)) {
                processDocket(oneDocket);
            }
            builder = null;
        }
    }

    private void processDocket(String oneDocket) {
        Document doc = Jsoup.parse(oneDocket);
        String docketNum = removeMetaData(doc.getElementsByTag("md.docketnum").toString());
        String vendorName = "";
        long vendorId;
        Elements updateBlock = doc.getElementsByTag("update.link.block");
        if (!updateBlock.isEmpty()) {
            Elements parameterValue = updateBlock.get(0).getElementsByTag("parameter.value");
            for (Element vendorElem : parameterValue) {
                if (vendorElem.text().matches("(?i)[a-z]+")) {
                    vendorName = vendorElem.text();
                }
            }
            if ("district".equalsIgnoreCase(vendorName)) {
                vendorId = 149L;
            } else if ("county".equalsIgnoreCase(vendorName)) {
                vendorId = 150L;
            } else {
                logger.error("Docket with number: " + docketNum + " has an unknown vendor: " + vendorName);
                return;
            }
        } else {
            return;
        }

        ScrapedDocketData scrapedDocketData = new ScrapedDocketData();
        scrapedDocketData.setDocketNumber(docketNum);
        String caseType = doc.getElementsByTag("md.case.type").toString()
                .replaceAll("(?i)<[a-z]+\\.[a-z]+\\.[a-z]+>", "")
                .replaceAll("(?i)</[a-z]+\\.[a-z]+\\.[a-z]+>", "")
                .replaceAll("\\n", "")
                .replaceAll(" ", "");
        scrapedDocketData.setCaseType(caseType);
        String lastScrapedDate = removeMetaData(doc.getElementsByTag("scrape.date").toString());
        LocalDateTime localDateTimeForScrapedDate;
        String initialScrapedDatePattern = "yyyyMMddHHmm";
        DateTimeFormatter scrapedDateFormatter = DateTimeFormatter.ofPattern(initialScrapedDatePattern);
        if (StringUtils.isNotBlank(lastScrapedDate)) {
            String timestampAsStringForScrapedDate = lastScrapedDate + "0000";
            localDateTimeForScrapedDate = LocalDateTime.from(scrapedDateFormatter.parse(timestampAsStringForScrapedDate));
            Timestamp timestampForScrapedDate = Timestamp.valueOf(localDateTimeForScrapedDate);
            scrapedDocketData.setLastScrapeDate(timestampForScrapedDate);
        } else {
            localDateTimeForScrapedDate = LocalDateTime.of(2020, 12, 22, 0, 0);
            Timestamp timestampForScrapedDate = Timestamp.valueOf(localDateTimeForScrapedDate);
            scrapedDocketData.setLastScrapeDate(timestampForScrapedDate);
        }
        String yearInDate = removeMetaData(doc.getElementsByTag("filing.date").toString());
        if (StringUtils.isNotBlank(yearInDate)) {
            String year = yearInDate.substring(0, 4);
            scrapedDocketData.setYear(year);
        }
        String legacyId = removeMetaData(doc.getElementsByTag("legacy.id").toString());
        scrapedDocketData.setLegacyId(legacyId);
        scrapedDocketData.setLastSourceFilename(fileName);
        scrapedDocketData.setWestlawClusterName("N_DTXHARRIS");
        scrapedDocketData.setVendor(vendorId);
        scrapedDocketData.setScrapeType("N_EXTRACT");
        scrapedDocketData.setSubdivisionName("Harris");
        scrapedDocketData.setDocketDataFields(oneDocket);
        scrapedDocketData.setDeleteFlag("N");
        scrapedDocketData.setIndexNumber("TXHarris01");
        scrapedDocketDataList.add(scrapedDocketData);
    }

    private String removeMetaData(String dataToBeCleaned) {
        return dataToBeCleaned
                .replaceAll("(?i)<[a-z]+\\.[a-z]+>", "")
                .replaceAll("(?i)</[a-z]+\\.[a-z]+>", "")
                .replaceAll("\\n", "")
                .replaceAll(" ", "");
    }
}
