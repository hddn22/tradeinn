package com.example.tradeinn.strategy;

import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.utils.ReplyKeyboardUtil;
import com.example.tradeinn.utils.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class PaymentButtonStrategy implements ButtonStrategy{

    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        String text = "Условия оплаты\n" +
                "\n" +
                "Оплата принимается в полном объеме с учетом комиссии перед оплаты нами заказа.";
        String path = "payment.jpg";
        return SendMessageUtil.sendPhotoUtil(update.getMessage().getChatId(), text, ReplyKeyboardUtil.WORK_BUTTONS, path);
    }
}
