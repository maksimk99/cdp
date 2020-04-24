package com.epam.cdp.maskim.katuranau.module10.task1.dao.impl;

import com.epam.cdp.maskim.katuranau.module10.task1.dao.MessageDao;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class MessageDaoImpl implements MessageDao {

    private static final int AMOUNT_OF_MESSAGES = 30;
    private Faker faker;
    private List<String> messages;

    public MessageDaoImpl() {
        this.faker = new Faker();
        this.messages = Collections.synchronizedList(new ArrayList<>());
        populateUsers();
    }

    private void populateUsers() {
        Stream.generate(() -> faker.book().title()).limit(AMOUNT_OF_MESSAGES).forEach(messages::add);
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public boolean existsMessage(int messageId) {
        return messageId >= 0 && messageId < messages.size();
    }

    @Override
    public boolean existsMessage(String message) {
        return messages.contains(message);
    }

    @Override
    public void updateMessage(int messageId, String message) {
        messages.set(messageId, message);
    }

    @Override
    public void addMessage(String message) {
        messages.add(message);
    }

    @Override
    public void deleteMessage(int messageId) {
        messages.remove(messageId);
    }
}
