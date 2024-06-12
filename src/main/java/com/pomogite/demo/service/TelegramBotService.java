package com.pomogite.demo.service;

import com.pengrad.telegrambot.TelegramBot;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.jdbc.support.JdbcUtils.isNumeric;


@Service

public class TelegramBotService {
    private final TelegramBot telegramBot;
    private final jokeService jokeService;

    @Autowired
    public TelegramBotService(TelegramBot telegramBot, jokeService jokeService) {
        this.telegramBot = telegramBot;
        this.jokeService = jokeService;
        this.telegramBot.setUpdatesListener(updates -> {
            updates.forEach(this::buttonClickReact);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, Throwable::printStackTrace);
    }

    private void sendSelectJokeByIdMessage(long chatId) {
        SendMessage request = new SendMessage(chatId, "Введите id шутки:")
                .parseMode(ParseMode.HTML)  // Определение способа форматирования текста
                .disableWebPagePreview(true)  // Отключение предпросмотра веб-ссылок
                .disableNotification(true);  // Отключение уведомлений для сообщения
        telegramBot.execute(request);  // Выполнение отправки сообщения
    }

    private void buttonClickReact(Update update) {
        if (update.message() != null && update.message().text() != null) {
            String messageText = update.message().text();
            long chatId = update.message().chat().id();

            switch (messageText) {
                case "/start":
                    sendStartMessage(chatId);
                    break;
                case "Выбрать все шутки":
                    jokeService.getAllJokes();
                    break;
                case "Выбрать шутку по id":
                    sendSelectJokeByIdMessage(chatId);
                    break;
                default:
                    if (isNumeric(Integer.parseInt(messageText))) {
                        long jokeId = Long.parseLong(messageText);
                        jokeService.getJokeById(jokeId);
                    } else {
                        sendUnknownCommand(chatId);
                    }
                    break;
            }
        }
    }

    private void sendUnknownCommand(long chatId) {
        SendMessage request = new SendMessage(chatId, "Неизвестная команда.")
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
        telegramBot.execute(request);
    }

    private void sendStartMessage(long chatId) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{"Выбрать все шутки"},
                new String[]{"Выбрать шутку по id"}
        )
                .oneTimeKeyboard(true)
                .resizeKeyboard(true);

        SendMessage request = new SendMessage(chatId, "Выберите действие:")
                .replyMarkup(keyboardMarkup);

        telegramBot.execute(request);
    }
}