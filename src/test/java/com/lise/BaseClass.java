package com.lise;
import com.lise.Test.PropertiesData;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.util.Strings;

import java.io.IOException;


public class BaseClass {
    public String accessToken;
    public String serverPort;

    @BeforeSuite
    public void beforeSuite() throws IOException {
        System.out.println("Before suite");
        PropertiesData properties = new PropertiesData();
        RestAssured.baseURI = properties.getBaseUri();
        System.out.println(" RestAssured.baseURI=>>>" +RestAssured.baseURI);
        accessToken = properties.getAccessToken();
        serverPort = properties.getServerPort();
    }
    @AfterSuite
    void afterGetUser() {
        System.out.println("After suite");
    }
}
