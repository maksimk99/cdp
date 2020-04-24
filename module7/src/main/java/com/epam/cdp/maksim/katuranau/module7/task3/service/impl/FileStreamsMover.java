package com.epam.cdp.maksim.katuranau.module7.task3.service.impl;

import com.epam.cdp.maksim.katuranau.module7.task3.service.Mover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileStreamsMover implements Mover {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileStreamsMover.class);

    @Override
    public void move(String sourcePath, String destinationPath, String fileName) throws IOException {
        File sourceFile = new File(sourcePath + fileName);
        if (sourceFile.exists()) {
            File destFile = new File(destinationPath + fileName);
            try (InputStream is = new FileInputStream(sourceFile);
                 OutputStream os = new FileOutputStream(destFile)) {
                int readByte;
                while ((readByte = is.read()) != -1) {
                    os.write(readByte);
                }
                sourceFile.delete();
            }
        } else {
            LOGGER.error("File not exist path: {}", sourcePath);
        }
    }
}
