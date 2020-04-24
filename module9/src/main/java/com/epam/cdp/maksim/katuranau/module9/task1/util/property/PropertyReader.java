package com.epam.cdp.maksim.katuranau.module9.task1.util.property;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
public class PropertyReader {

    private static final Logger LOGGER = LogManager.getLogger();

    public static Properties getProperties(String path) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return properties;
    }
}
