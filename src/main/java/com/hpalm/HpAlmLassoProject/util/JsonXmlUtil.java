package com.hpalm.HpAlmLassoProject.util;

import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

@Component
public class JsonXmlUtil {

    private static final Logger log = LogManager.getLogger(JsonXmlUtil.class);

    public String generateXML(String jsonString) {
        log.info("Generating XML from JSON..");
        JSONObject json = new JSONObject(jsonString);
        return XML.toString(json);
    }

    public String generateJson(String xmlString) {
        log.info("Generating JSON from XML..");
        JSONObject json = XML.toJSONObject(xmlString);
        return json.toString();
    }

    public String generateJsonString(Object reqObj) throws JsonProcessingException {
        ObjectMapper objectWriter = new ObjectMapper();
        return objectWriter.writeValueAsString(reqObj);
    }

    public String genearteXMLString(Object reqObj) throws JsonProcessingException {
        return generateXML(generateJsonString(reqObj));
    }
}
