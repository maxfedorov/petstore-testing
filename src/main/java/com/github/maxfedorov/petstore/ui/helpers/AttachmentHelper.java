package com.github.maxfedorov.petstore.ui.helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AttachmentHelper {
    public AttachmentHelper(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    @Attachment(value = "{name}", type = "image/png")
    public byte[] asScreenshot(String name) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
