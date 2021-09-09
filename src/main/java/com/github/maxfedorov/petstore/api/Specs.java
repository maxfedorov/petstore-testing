package com.github.maxfedorov.petstore.api;

import com.github.maxfedorov.petstore.ui.configs.ApplicationConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;

public class Specs {
    private final ApplicationConfig appConfig = ConfigFactory.create(ApplicationConfig.class);
    protected WebDriver driver;

    public RequestSpecification requestSpecification() {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(getBaseUrl())
                .basePath("/v2")
                .accept(ContentType.JSON);
    }

    private String getBaseUrl() {
        String property = System.getProperty("base.url");
        return property == null ? appConfig.baseUrl() : property;
    }
}
