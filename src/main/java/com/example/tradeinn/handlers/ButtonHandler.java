package com.example.tradeinn.handlers;

import com.example.tradeinn.listener.TelegramBotListener;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.strategy.ButtonStrategy;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ButtonHandler {
    private ButtonStrategy strategy;

    public ButtonHandler(ButtonStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(ButtonStrategy strategy) {
        this.strategy = strategy;
    }


    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        return strategy.sendMessage(update, customerService, orderingService);
    }

}
