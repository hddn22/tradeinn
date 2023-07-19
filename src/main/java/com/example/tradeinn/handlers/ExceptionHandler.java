package com.example.tradeinn.handlers;

import com.example.tradeinn.utils.ReplyKeyboardUtil;
import com.example.tradeinn.utils.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.validation.ValidationException;

public class ExceptionHandler {
    public static PartialBotApiMethod<Message> exceptionMainMenuHandler(Long channelId, ValidationException e) {
        String text = "Ой, я ещё не знаю таких команд";
        String path = "tradeinn.jpg";
        return SendMessageUtil.sendPhotoUtil(channelId, text, ReplyKeyboardUtil.MAIN_MENU_BUTTONS, path);
    }
}
