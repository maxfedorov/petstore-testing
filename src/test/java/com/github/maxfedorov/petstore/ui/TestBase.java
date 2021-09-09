package com.github.maxfedorov.petstore.ui;

import com.github.maxfedorov.petstore.ui.drivers.DriverFactory;
import com.github.maxfedorov.petstore.ui.helpers.AttachmentHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class TestBase {

    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new DriverFactory().getDriver();
    }

    @AfterEach
    void tearDown() {
        new AttachmentHelper(driver).asScreenshot("After test screenshot");
        driver.quit();
    }

}
