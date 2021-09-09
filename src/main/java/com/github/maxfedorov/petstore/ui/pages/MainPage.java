package com.github.maxfedorov.petstore.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final String EXPAND_REQUEST_BLOCK_BUTTON = ".//button[./span[@class='opblock-summary-method' and text()='%s'] and ./span[@class='opblock-summary-path' and @data-path='%s']]";
    private final String REQUEST_BLOCK_PANEL = EXPAND_REQUEST_BLOCK_BUTTON + "/ancestor::*[contains(@class, 'opblock opblock-')]";

    @Step("Open main page")
    public MainPage open() {
        driver.get(getBaseUrl());
        return this;
    }

    @Step("Expand block {method}:{path}")
    public RequestBlock expandBlock(String method, String path) {
        driver.findElement(xpath(format(EXPAND_REQUEST_BLOCK_BUTTON, method, path))).click();
        return new RequestBlock(driver, xpath(format(REQUEST_BLOCK_PANEL, method, path)));
    }

}
