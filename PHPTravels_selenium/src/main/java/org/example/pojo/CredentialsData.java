package org.example.pojo;

public class CredentialsData {
    String username;
    String password;
    String url;

    public CredentialsData(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }
}
