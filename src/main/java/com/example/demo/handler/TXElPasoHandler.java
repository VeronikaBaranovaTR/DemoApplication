package com.example.demo.handler;

import com.example.demo.entity.ScrapedDocketDataTXElPaso;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.demo.DemoApplication.count;
import static com.example.demo.DemoApplication.files;

public class TXElPasoHandler extends DefaultHandler {

    private List<ScrapedDocketDataTXElPaso> TXElPasoDataList = null;
    private ScrapedDocketDataTXElPaso scrapedDocketDataTXElPaso = null;
    private StringBuilder data = null;
    private boolean bPage = false;

    public List<ScrapedDocketDataTXElPaso> getTXElPasoDataList() {
        return TXElPasoDataList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("docket")) {
            scrapedDocketDataTXElPaso = new ScrapedDocketDataTXElPaso();
            if (TXElPasoDataList == null)
                TXElPasoDataList = new ArrayList<>();
            scrapedDocketDataTXElPaso.setDocketNumber(attributes.getValue(0));
            String initialScrapedDatePattern = "yyyyMMddHHmmss";
            DateTimeFormatter scrapedDateFormatter = DateTimeFormatter.ofPattern(initialScrapedDatePattern);
            String timestampAsStringForScrapedDate = attributes.getValue(2);
            LocalDateTime localDateTimeForScrapedDate = LocalDateTime.from(scrapedDateFormatter.parse(timestampAsStringForScrapedDate));
            Timestamp timestampForScrapedDate = Timestamp.valueOf(localDateTimeForScrapedDate);
            scrapedDocketDataTXElPaso.setLastScrapeDate(timestampForScrapedDate);
            scrapedDocketDataTXElPaso.setFilingDate(attributes.getValue(8));
            scrapedDocketDataTXElPaso.setOffense("NOVUS_EXTRACT");
            scrapedDocketDataTXElPaso.setFullCaseType(UUID.randomUUID().toString());
            scrapedDocketDataTXElPaso.setCaseType(attributes.getValue(5));
            scrapedDocketDataTXElPaso.setLastSourceFilename(/*getFileName().replace("xml", "gz")*/
                    files[count].getName().replace("xml", "gz"));
        } else if (qName.equalsIgnoreCase("page")) {
            bPage = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (bPage) {
            scrapedDocketDataTXElPaso.setDocketDataFields(data.toString());
            bPage = false;
        }
        if (qName.equalsIgnoreCase("docket")) {
            TXElPasoDataList.add(scrapedDocketDataTXElPaso);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }

}
