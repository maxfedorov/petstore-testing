package com.github.maxfedorov.petstore.ui.drivers;

import com.github.maxfedorov.petstore.ui.configs.DriverConfig;
import org.aeonbits.owner.ConfigFactory;

public class DriverBase {
    public static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);
}
