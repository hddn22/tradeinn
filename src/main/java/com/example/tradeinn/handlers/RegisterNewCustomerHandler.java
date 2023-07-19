package com.example.tradeinn.handlers;

import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.strategy.RegisterCustomerButtonStrategy;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RegisterNewCustomerHandler {
    static ButtonHandler buttonHandler;


    public static PartialBotApiMethod<Message> registerNewCustomer(Update update, CustomerService customerService, OrderingService orderingService){
        buttonHandler = new ButtonHandler(new RegisterCustomerButtonStrategy());
        return buttonHandler.sendMessage(update, customerService, orderingService);
    }
}
