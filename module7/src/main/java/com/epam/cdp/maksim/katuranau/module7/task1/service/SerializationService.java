package com.epam.cdp.maksim.katuranau.module7.task1.service;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public interface SerializationService <T> {

    void serializeObject(T object, String fileName);

    T deserializeObject(String fileName);
}
