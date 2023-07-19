package com.example.tradeinn.strategy;

import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ButtonStrategy {
    PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService);
}
