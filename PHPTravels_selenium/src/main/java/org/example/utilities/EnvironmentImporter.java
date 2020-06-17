package org.example.utilities;

import org.example.pojo.EnvironmentData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class EnvironmentImporter {

    private String environmentLocation;


    public EnvironmentImporter(String environmentLocation) {
        this.environmentLocation = environmentLocation;
    }

    public EnvironmentData getEnvironment() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(this.environmentLocation));
        JSONObject jsonObject = (JSONObject) object;
        String mode = (String) jsonObject.get("mode");
        String chromedriverLocation = (String) jsonObject.get("chromedriverLocation");
        return new EnvironmentData(mode, chromedriverLocation);
    }

}

