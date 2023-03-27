package com.gmail.estrynvladislav.tgnotesbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class BotServiceImpl implements BotService {

    private static final String START_TEXT = "Hey! Nice to see you!";
    private static final String STOP_TEXT = "See you!";
    private static final String ERROR_TEXT = "Try again...";

    public SendMessage getStartMsg(final long chatId) {
        return generateMessage(chatId, START_TEXT);
    }

    public SendMessage getStopMsg(final long chatId) {
        return generateMessage(chatId, STOP_TEXT);
    }

    public SendMessage getDefaultMessage(final long chatId) {
        return generateMessage(chatId, ERROR_TEXT);
    }

    private SendMessage generateMessage(final long chatId, final String text) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
    }

}