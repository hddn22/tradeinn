package com.example.tradeinn.handlers;

import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.listener.TelegramBotListener;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.strategy.*;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class OrderMenuHandler {
    static ButtonHandler buttonHandler;

    public static PartialBotApiMethod<Message> orderingButton(Update update, CustomerService customerService, OrderingService orderingService, TelegramBotListener tgBot) {
        Customer customer = customerService.findByTelegramUserId(update.getMessage().getFrom().getId());
        switch (customer.getStep()) {
            case SERVICE -> {
                buttonHandler = new ButtonHandler(new ServiceButtonStrategy());
                return buttonHandler.sendMessage(update, customerService, orderingService);
            } case ACCOUNT -> {
                buttonHandler = new ButtonHandler(new AccountButtonStrategy());
                return buttonHandler.sendMessage(update, customerService, orderingService);
            } case MONEY -> {
                buttonHandler = new ButtonHandler(new MoneyButtonStrategy());
                return buttonHandler.sendMessage(update, customerService, orderingService);
            } case CONFIRM -> {
                buttonHandler = new ButtonHandler(new ConfirmButtonStrategy());
                return buttonHandler.sendMessage(update, customerService, orderingService);
            } case CHANGE -> {
                buttonHandler = new ButtonHandler(new ChangeButtonStrategy());
                return buttonHandler.sendMessage(update, customerService, orderingService);
            } case CHANGE_SERVICE -> {
                buttonHandler = new ButtonHandler(new ChangeServiceButtonStrategy());
                return buttonHandler.sendMessage(update, customerService, orderingService);
            } case CHANGE_ACCOUNT -> {
                buttonHandler = new ButtonHandler(new ChangeAccountButtonsStrategy());
                return buttonHandler.sendMessage(update, customerService, orderingService);
            } case CHANGE_MONEY -> {
                buttonHandler = new ButtonHandler(new ChangeMoneyButtonStrategy());
                return buttonHandler.sendMessage(update, customerService, orderingService);
            }
        }
        return null;
    }
}
