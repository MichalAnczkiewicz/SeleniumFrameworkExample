package org.example.pojo;

public class EnvironmentData {
    private String mode;
    private String chromeDriverLocation;

    public EnvironmentData(String mode, String chromeDriverLocation) {
        this.mode = mode;
        this.chromeDriverLocation = chromeDriverLocation;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getChromeDriverLocation() {
        return chromeDriverLocation;
    }

    public void setChromeDriverLocation(String chromeDriverLocation) {
        this.chromeDriverLocation = chromeDriverLocation;
    }
}
