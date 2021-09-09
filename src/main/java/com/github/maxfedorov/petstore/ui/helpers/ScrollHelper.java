package com.github.maxfedorov.petstore.ui.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollHelper {
    public ScrollHelper(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
