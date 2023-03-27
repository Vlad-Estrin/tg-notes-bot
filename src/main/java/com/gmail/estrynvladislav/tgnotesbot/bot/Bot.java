package com.gmail.estrynvladislav.tgnotesbot.bot;

import com.gmail.estrynvladislav.tgnotesbot.service.BotServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.gmail.estrynvladislav.tgnotesbot.bot.Requests.START;
import static com.gmail.estrynvladislav.tgnotesbot.bot.Requests.STOP;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final BotServiceImpl service;

    @Value("${telegram.bot.name}")
    private String name;

    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String requestMsg = update.getMessage().getText();
        final var chatId = update.getMessage().getChatId();
        SendMessage response;

        switch (requestMsg) {
            case START -> response = service.getStartMsg(chatId);
            case STOP -> response = service.getStopMsg(chatId);
            default -> response  = service.getDefaultMessage(chatId);
        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Cant' send message");
        }
    }

}