package com.lise.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesData {
    private Properties propertiesData;
    private String accessToken;
    private String serverPort;
    private String baseUri;

    public PropertiesData() throws IOException {
        propertiesData = readFilePropertiesFile("C:\\Users\\Lise infotech\\IdeaProjects\\SmallProject\\src\\test\\java\\resources\\application.properties");

    }

    public String getBaseUri(){
        baseUri  = propertiesData.getProperty("url");
        return  baseUri;
    }

    public String getAccessToken(){
        accessToken = propertiesData.getProperty("tokens");
        System.out.println(accessToken);
        return   accessToken;
    }

    public String getServerPort(){
        return    serverPort = propertiesData.getProperty("serverPort");
    }
    public static Properties readFilePropertiesFile(String filename) throws IOException {
        FileInputStream fileInputStream = null;
        Properties propertiesData = null;
        fileInputStream = new FileInputStream(filename);
        propertiesData = new Properties();
        propertiesData.load(fileInputStream);
        return propertiesData;
    }
}