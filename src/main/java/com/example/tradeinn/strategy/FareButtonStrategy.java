package com.example.tradeinn.strategy;

import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.utils.ReplyKeyboardUtil;
import com.example.tradeinn.utils.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class FareButtonStrategy implements ButtonStrategy{
    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        String text = "Базовая ставка — 8%. \n" +
                "\n" +
                "Так же предусмотрена прогрессивная ставка: \n" +
                "Заказ до 20 тыс. — базовая ставка 8%\n" +
                "Заказ от 20 до 40 тыс. — 7%\n" +
                "Свыше 40 тыс. — 6%\n" +
                "\n" +
                "Если будете разбивать заказ на несколько (для избежания пошлины, например), то учитывается общая сумма единоразового заказа.";
        String path = "fare.jpg";
        return SendMessageUtil.sendPhotoUtil(update.getMessage().getChatId(), text, ReplyKeyboardUtil.FARE_BUTTONS, path);
    }
}
