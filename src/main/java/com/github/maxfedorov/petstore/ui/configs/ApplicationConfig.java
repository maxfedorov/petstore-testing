package com.github.maxfedorov.petstore.ui.configs;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/application.properties")
public interface ApplicationConfig extends Config {
    @Key("base.url")
    @DefaultValue("https://petstore.swagger.io")
    String baseUrl();
}
