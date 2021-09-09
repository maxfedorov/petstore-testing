package com.github.maxfedorov.petstore.ui.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.util.concurrent.TimeUnit.SECONDS;

public class LocalDriver extends DriverBase {
    public WebDriver getDriver() {
        String driverPath = getClass().getResource(driverConfig.driverPath()).getPath();
        WebDriver driver;
        switch (driverConfig.browserName()) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver();
                break;
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", driverPath);
                driver = new FirefoxDriver();
                break;
            }
            default:
                throw new RuntimeException("Supported browsers: chrome, firefox");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        return driver;
    }
}
