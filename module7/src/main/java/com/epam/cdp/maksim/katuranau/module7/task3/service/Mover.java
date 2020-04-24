package com.epam.cdp.maksim.katuranau.module7.task3.service;

import java.io.IOException;

public interface Mover {

    void move(String sourcePath, String destinationPath, String fileName) throws IOException;
}
