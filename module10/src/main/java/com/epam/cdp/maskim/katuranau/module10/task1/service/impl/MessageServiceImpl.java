package com.epam.cdp.maskim.katuranau.module10.task1.service.impl;

import com.epam.cdp.maskim.katuranau.module10.task1.dao.MessageDao;
import com.epam.cdp.maskim.katuranau.module10.task1.dao.impl.MessageDaoImpl;
import com.epam.cdp.maskim.katuranau.module10.task1.exception.ValidationException;
import com.epam.cdp.maskim.katuranau.module10.task1.service.MessageService;
import com.epam.cdp.maskim.katuranau.module10.task1.validator.MessageValidator;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    private static final String USER_ALREADY_EXISTS = "Message already exists";
    private static final String USER_DOES_NOT_EXIST = "Message does not exist";
    private MessageDao messageDao;
    private MessageValidator messageValidator;

    public MessageServiceImpl() {
        this.messageDao = new MessageDaoImpl();
        this.messageValidator = new MessageValidator();
    }

    @Override
    public List<String> getMessages() {
        return messageDao.getMessages();
    }

    @Override
    public void updateMessage(int messageId, String message) throws ValidationException {
        messageValidator.validateName(message);
        if (messageDao.existsMessage(messageId)) {
            messageDao.updateMessage(messageId, message);
            return;
        }
        throw new ValidationException(USER_DOES_NOT_EXIST);
    }

    @Override
    public void addMessage(String message) throws ValidationException {
        messageValidator.validateName(message);
        if (!messageDao.existsMessage(message)) {
            messageDao.addMessage(message);
            return;
        }
        throw new ValidationException(USER_ALREADY_EXISTS);
    }

    @Override
    public void deleteMessage(int messageId) throws ValidationException {
        if (messageDao.existsMessage(messageId)) {
            messageDao.deleteMessage(messageId);
            return;
        }
        throw new ValidationException(USER_DOES_NOT_EXIST);
    }
}
