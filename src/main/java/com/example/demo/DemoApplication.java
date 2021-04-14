package com.example.demo;

import com.example.demo.dao.ScrapedDocketDataRepository;
import com.example.demo.dao.ScrapedDocketDataTXElPasoRepository;
import com.example.demo.entity.ScrapedDocketData;
import com.example.demo.handler.TXHarrisDistrictHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static File[] files;
    public static int count = -1;
    public static String fileName;
    private int amountOfDockets = 0;
    private final ScrapedDocketDataTXElPasoRepository scrapedDocketDataTXElPasoRepository;
    private final ScrapedDocketDataRepository scrapedDocketDataRepository;

    public DemoApplication(ScrapedDocketDataTXElPasoRepository scrapedDocketDataTXElPasoRepository,
                           ScrapedDocketDataRepository scrapedDocketDataRepository) {
        this.scrapedDocketDataTXElPasoRepository = scrapedDocketDataTXElPasoRepository;
        this.scrapedDocketDataRepository = scrapedDocketDataRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            File folder = new File("");//insert path for files to be loaded here
            files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    fileName = file.getName();
                    TXHarrisDistrictHandler handler = new TXHarrisDistrictHandler();
                    saxParser.parse(file, handler);
                    System.out.println("File " + file.getName() + " has been parsed ");
                    List<ScrapedDocketData> scrapedDocketDataList = handler.getScrapedDocketDataList();
                    if (!scrapedDocketDataList.isEmpty()) {
                        System.out.println("Dockets from " + file.getName() + " have been added to the list");
                        int filesNumber = scrapedDocketDataList.size();
                        AtomicInteger counter = new AtomicInteger();
                        scrapedDocketDataList.forEach(i -> {
                            scrapedDocketDataRepository.save(i);
                            System.out.printf("Processing docket (legacy id = %s) number %d out of %d%n", i.getLegacyId(), counter.getAndIncrement(), filesNumber);
                            amountOfDockets++;
                        });
                        System.out.println("Amount of dockets processed: " + amountOfDockets);
                    }
                }
            } else {
                throw new Exception("There are no files to persist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
/*        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            File folder = new File("C:\\Users\\C270954\\Documents\\El_Paso\\files");
            files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    count++;
                    TXElPasoHandler handler = new TXElPasoHandler();
                    saxParser.parse(file, handler);
                    List<ScrapedDocketDataTXElPaso> scrapedDocketDataList = handler.getTXElPasoDataList();
                    int filesNumber = scrapedDocketDataList.size();
                    AtomicInteger counter = new AtomicInteger();
                    scrapedDocketDataList.forEach(i -> {
                        scrapedDocketDataTXElPasoRepository.save(i);
                        System.out.printf("%dProcessing file %s number %d out of %d%n", count, i.getLastSourceFilename(), counter.getAndIncrement(), filesNumber);
                        amountOfDockets++;
                    });
                    System.out.println(amountOfDockets);
                }
            } else {
                throw new Exception("There are no files to persist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
