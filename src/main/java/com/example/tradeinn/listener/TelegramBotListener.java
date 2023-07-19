package com.example.tradeinn.listener;

import com.example.tradeinn.component.Step;
import com.example.tradeinn.config.BotConfig;
import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.handlers.*;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.validation.ValidationException;
import java.util.concurrent.CompletableFuture;


public class TelegramBotListener extends TelegramLongPollingBot {
    CustomerService customerService;
    OrderingService orderingService;


    public TelegramBotListener(String token, CustomerService customerService, OrderingService orderingService) {
        super(token);
        this.customerService = customerService;
        this.orderingService = orderingService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Customer customer = customerService.findByTelegramUserId(update.getMessage().getFrom().getId());
            if (customer == null) {
                executeAsync((SendPhoto) RegisterNewCustomerHandler.registerNewCustomer(update, customerService, orderingService));
            } else if (customer.getStep() == Step.INIT) {
                try  {
                    executeAsync(MainMenuHandler.mainMenuButton(update, customerService, orderingService));
                } catch (ValidationException e) {
                    executeAsync((SendPhoto) ExceptionHandler.exceptionMainMenuHandler(update.getMessage().getChatId(), e));
                }
            }else if (update.getMessage().getText().equals("☠️Отмена")) {
                PartialBotApiMethod<Message> message = CancelButtonHandlers.cancelButton(update, customerService, orderingService);
                if(message instanceof SendMessage msg) {
                    executeAsync(msg);
                } else if (message instanceof SendPhoto msg) {
                    executeAsync(msg);
                }
            } else if (!update.getMessage().getText().equals("☠️Отмена") && !customer.getStep().equals(Step.INIT)) {
                executeAsync((SendMessage) OrderMenuHandler.orderingButton(update, customerService, orderingService));
            }
        }
    }

    public CompletableFuture<Message> executeAsync(SendPhoto message) {
        return super.executeAsync(message);
    }

    public void executeAsync(SendMessage message) {
        try {
            super.executeAsync(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_NAME;
    }
}
