package com.github.maxfedorov.petstore.ui.configs;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/${driver}.properties")
public interface DriverConfig extends Config {
    @Key("selenoid.url")
    @DefaultValue("http://localhost:4444/wd/hub/")
    String selenoidUrl();

    @Key("driver.path")
    @DefaultValue("/drivers/chromedriver")
    String driverPath();

    @Key("browser.name")
    @DefaultValue("chrome")
    String browserName();

    @Key("browser.version")
    @DefaultValue("91.0")
    String browserVersion();

    @Key("enable.vnc")
    @DefaultValue("true")
    boolean enableVnc();

    @Key("enable.video")
    @DefaultValue("false")
    boolean enableVideo();
}
