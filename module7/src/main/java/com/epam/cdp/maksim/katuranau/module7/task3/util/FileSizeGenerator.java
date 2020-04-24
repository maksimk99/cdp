package com.epam.cdp.maksim.katuranau.module7.task3.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileSizeGenerator {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void createDirectory(String path) {
        new File(path).mkdirs();
    }

    public void generate(String path, String name, long sizeMultiplier) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(path + name, "rw");
        randomAccessFile.setLength(sizeMultiplier);
        randomAccessFile.close();
    }

    public void deleteDirectory(String path) {
        new File(path).deleteOnExit();
    }
}
