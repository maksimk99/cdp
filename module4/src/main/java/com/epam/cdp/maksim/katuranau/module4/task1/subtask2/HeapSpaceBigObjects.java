package com.epam.cdp.maksim.katuranau.module4.task1.subtask2;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class HeapSpaceBigObjects {

    public static void main(String[] args) throws IOException {
        File file = new File("testFile");
        createBigFile(file);
        readBigFile(file);
    }

    private static void createBigFile(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.setLength(1024 * 1024 * 1024);
        randomAccessFile.close();
    }

    private static void readBigFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String value = br.readLine();
        } catch (IOException | OutOfMemoryError e) {
            e.printStackTrace();
        }
    }
}
