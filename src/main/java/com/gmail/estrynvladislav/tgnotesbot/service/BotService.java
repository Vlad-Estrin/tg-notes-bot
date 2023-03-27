package com.gmail.estrynvladislav.tgnotesbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface BotService {
    SendMessage getStartMsg(final long chatId);
    SendMessage getStopMsg(final long chatId);
    SendMessage getDefaultMessage(final long chatId);
}