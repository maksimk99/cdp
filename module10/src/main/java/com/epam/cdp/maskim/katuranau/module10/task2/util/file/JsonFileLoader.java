package com.epam.cdp.maskim.katuranau.module10.task2.util.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class JsonFileLoader {

    public static String loadFile(String fileName) {
        try {
            File file = Paths.get(JsonFileLoader.class.getClassLoader().getResource(fileName).toURI()).toFile();
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return "Can't read json file";
        }
    }
}
