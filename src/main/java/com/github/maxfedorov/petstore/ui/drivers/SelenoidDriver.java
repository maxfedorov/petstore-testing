package com.github.maxfedorov.petstore.ui.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SelenoidDriver extends DriverBase {
    public WebDriver getDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(driverConfig.browserName());
            capabilities.setVersion(driverConfig.browserVersion());
            capabilities.setCapability("enableVNC", driverConfig.enableVnc());
            WebDriver driver = new RemoteWebDriver(new URL(driverConfig.selenoidUrl()), capabilities);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, SECONDS);
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException();
        }
    }
}
