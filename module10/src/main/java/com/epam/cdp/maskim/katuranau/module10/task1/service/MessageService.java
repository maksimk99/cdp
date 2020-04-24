package com.epam.cdp.maskim.katuranau.module10.task1.service;

import com.epam.cdp.maskim.katuranau.module10.task1.exception.ValidationException;

import java.util.List;

public interface MessageService {

    List<String> getMessages();

    void updateMessage(int messageId, String message) throws ValidationException;

    void addMessage(String message) throws ValidationException;

    void deleteMessage(int messageId) throws ValidationException;
}
