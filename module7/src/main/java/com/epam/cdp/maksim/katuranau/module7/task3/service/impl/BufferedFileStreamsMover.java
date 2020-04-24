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

public class BufferedFileStreamsMover implements Mover {

    private static final Logger LOGGER = LoggerFactory.getLogger(BufferedFileStreamsMover.class);

    @Override
    public void move(String sourcePath, String destinationPath, String fileName) throws IOException {
        File sourceFile = new File(sourcePath + fileName);
        if (sourceFile.exists()) {
            File destFile = new File(destinationPath + fileName);
            try (InputStream inputStream = new FileInputStream(sourceFile);
                 OutputStream outputStream = new FileOutputStream(destFile)) {
                byte[] buffer = new byte[102400];
                int readBytes;
                while ((readBytes = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, readBytes);
                }
                sourceFile.delete();
            }
        } else {
            LOGGER.error("File not exist path: {}", sourcePath);
        }
    }
}
