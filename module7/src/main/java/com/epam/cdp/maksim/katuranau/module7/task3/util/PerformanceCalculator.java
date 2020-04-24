package com.epam.cdp.maksim.katuranau.module7.task3.util;

import com.epam.cdp.maksim.katuranau.module7.task3.service.Mover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class PerformanceCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceCalculator.class);
    private static final int REPETITION_AMOUNT = 120;
    private static final long KILO = 1024;
    private static final long[] SIZES = new long[]{KILO, KILO * 100, KILO * KILO};
    private final FileSizeGenerator fileSizeGenerator;
    private final String sourcePath;
    private final String destinationPath;
    private final String fileName;

    public PerformanceCalculator(FileSizeGenerator fileSizeGenerator, String sourcePath,
                                 String destinationPath, String fileName) {
        this.fileSizeGenerator = fileSizeGenerator;
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
        this.fileName = fileName;
    }

    public void logTimings(Mover mover) {
        LOGGER.info(mover.getClass().getSimpleName());
        try {
            for (long size : SIZES) {
                long resultTime = 0;
                for (int i = 0; i < REPETITION_AMOUNT; i++) {
                    fileSizeGenerator.generate(sourcePath, fileName, size);
                    long startTime = System.nanoTime();
                    mover.move(sourcePath, destinationPath, fileName);
                    long endTime = System.nanoTime();
                    resultTime += endTime - startTime;
                }
                long averageTime = resultTime / REPETITION_AMOUNT;
                LOGGER.info("{} nanoseconds. File size = {} KB.", averageTime, size / KILO);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            deleteTempFiles();
        }
    }

    private void deleteTempFiles() {
        while (new File(sourcePath + fileName).delete());
        while (new File(destinationPath + fileName).delete());
    }
}
