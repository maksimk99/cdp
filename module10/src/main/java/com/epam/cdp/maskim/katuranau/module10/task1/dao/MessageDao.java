package com.epam.cdp.maskim.katuranau.module10.task1.dao;

import java.util.List;

public interface MessageDao {

    List<String> getMessages();

    boolean existsMessage(int messageId);

    boolean existsMessage(String message);

    void updateMessage(int messageId, String message);

    void addMessage(String message);

    void deleteMessage(int messageId);
}
