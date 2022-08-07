package com.hpalm.HpAlmLassoProject.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class SessionXMLReader {

    private static final Logger log = LogManager.getLogger(SessionXMLReader.class);

    private static final String xmlPath = "src/main/resources/sessionXML.xml";

    public String readSessionXML() {
        StringBuilder sb = new StringBuilder();
        try {
            Path filePath = Paths.get(xmlPath);
            BufferedReader reader = Files.newBufferedReader(filePath);
            String nextLine;
            while((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (Exception e) {
            System.out.println("Error while Reading XML file!!!");
            e.printStackTrace();
        }
        return sb.toString();
    }

}
