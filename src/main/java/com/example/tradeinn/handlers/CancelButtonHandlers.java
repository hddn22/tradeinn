package com.example.tradeinn.handlers;

import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.strategy.CancelButtonStrategy;
import com.example.tradeinn.strategy.DefaultCancelButtonStrategy;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CancelButtonHandlers {

    static ButtonHandler buttonHandler;

    public static PartialBotApiMethod<Message> cancelButton(Update update, CustomerService customerService, OrderingService orderingService) {
        Customer customer = customerService.findByTelegramUserId(update.getMessage().getFrom().getId());
        switch (customer.getStep()) {
            case CHANGE:
            case CHANGE_MONEY:
            case CHANGE_ACCOUNT:
            case CHANGE_SERVICE:
                buttonHandler = new ButtonHandler(new CancelButtonStrategy());
                return buttonHandler.sendMessage(update, customerService, orderingService);
            default:
                buttonHandler = new ButtonHandler(new DefaultCancelButtonStrategy());
                return buttonHandler.sendMessage(update, customerService, orderingService);
        }
    }
}
