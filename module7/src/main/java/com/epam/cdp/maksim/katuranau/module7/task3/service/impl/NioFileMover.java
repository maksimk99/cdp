package com.epam.cdp.maksim.katuranau.module7.task3.service.impl;

import com.epam.cdp.maksim.katuranau.module7.task3.service.Mover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NioFileMover implements Mover {

    private static final Logger LOGGER = LoggerFactory.getLogger(NioFileMover.class);

    @Override
    public void move(String sourcePath, String destinationPath, String fileName) throws IOException {
        Path source = Paths.get(sourcePath + fileName);
        if (Files.exists(source)) {
            Path destination = Paths.get(destinationPath + fileName);
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } else {
            LOGGER.error("File not exist path: {}", sourcePath);
        }
    }
}
