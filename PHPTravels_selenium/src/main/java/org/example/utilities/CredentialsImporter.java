package org.example.utilities;

import org.example.pojo.CredentialsData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class CredentialsImporter {

    private String credentialsLocation;

    public CredentialsImporter(String credentialsLocation) {
        this.credentialsLocation = credentialsLocation;
    }

    public CredentialsData getCredentials() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(this.credentialsLocation));
        JSONObject jsonObject = (JSONObject) object;
        String url = (String) jsonObject.get("URL");
        String username = (String) jsonObject.get("username");
        String password = (String) jsonObject.get("password");
        return new CredentialsData(username, password, url);
    }

}
