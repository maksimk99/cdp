package com.epam.cdp.maksim.katuranau.module7.task3.service.impl;

import com.epam.cdp.maksim.katuranau.module7.task3.service.Mover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileChannelMover implements Mover {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileChannelMover.class);

    @Override
    public void move(String sourcePath, String destinationPath, String fileName) throws IOException {
        File sourceFile = new File(sourcePath + fileName);
        if (sourceFile.exists()) {
            File destFile = new File(destinationPath + fileName);
            try (FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
                 FileChannel destChannel = new FileOutputStream(destFile).getChannel()) {
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            }
        } else {
            LOGGER.error("File not exist path: {}", sourcePath);
        }
    }
}
