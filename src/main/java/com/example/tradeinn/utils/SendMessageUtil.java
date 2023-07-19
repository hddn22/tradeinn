package com.example.tradeinn.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.io.InputStream;

public class SendMessageUtil {
    static ReplyKeyboardMarkup prevKeyboardMarkup = new ReplyKeyboardMarkup();
    public static SendMessage sendMessageUtil(Long channelId, String text, ReplyKeyboardMarkup keyboardMarkup) {
        if (keyboardMarkup != null) {
            prevKeyboardMarkup = keyboardMarkup;
        }

        return SendMessage.builder()
                .chatId(channelId)
                .parseMode("Markdown")
                .replyMarkup(prevKeyboardMarkup)
                .text(text)
                .build();
    }

    public static SendPhoto sendPhotoUtil(Long channelId, String text, ReplyKeyboardMarkup keyboardMarkup, String path) {
        if (keyboardMarkup != null) {
            prevKeyboardMarkup = keyboardMarkup;
        }
        if (path == null) {
            path = "tradeinn.jpg";
        }

        InputStream inputStream = SendMessageUtil.class.getClassLoader().getResourceAsStream("img/" + path);

        return SendPhoto.builder()
                .chatId(channelId)
                .photo(new InputFile(inputStream, "tradeinn.jpg"))
                .caption(text)
                .replyMarkup(prevKeyboardMarkup)
                .parseMode("HTML")
                .build();
    }
}
