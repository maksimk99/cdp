package com.epam.cdp.maksim.katuranau.module8.task2.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final Logger LOGGER = LogManager.getLogger(PropertyReader.class);

    public static Properties getProperties(String propertiesPath) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream =
                     new FileInputStream(PropertyReader.class.getClassLoader().getResource(propertiesPath).getFile())) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return properties;
    }
}
