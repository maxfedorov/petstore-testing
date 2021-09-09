package com.github.maxfedorov.petstore.ui.pages;

import com.github.maxfedorov.petstore.ui.configs.ApplicationConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private final ApplicationConfig appConfig = ConfigFactory.create(ApplicationConfig.class);
    protected WebDriver driver;

    protected String getBaseUrl() {
        String property = System.getProperty("base.url");
        return property == null ? appConfig.baseUrl() : property;
    }

    protected WebDriverWait webDriverWait() {
        return new WebDriverWait(driver, 10);
    }

}
