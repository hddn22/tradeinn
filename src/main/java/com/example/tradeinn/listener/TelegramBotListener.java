package com.example.tradeinn.listener;

import com.example.tradeinn.component.Step;
import com.example.tradeinn.config.BotConfig;
import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.handlers.*;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.LogsService;
import com.example.tradeinn.service.OrderingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.validation.ValidationException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;

@Service
public class TelegramBotListener extends TelegramLongPollingBot {
    CustomerService customerService;
    OrderingService orderingService;
    LogsService logsService;


    public TelegramBotListener(CustomerService customerService, OrderingService orderingService, LogsService logsService) {
        super(BotConfig.TOKEN);
        this.customerService = customerService;
        this.orderingService = orderingService;
        this.logsService = logsService;
    }



    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            logsService.saveCustomerMessage(update);
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
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(update.getMessage().getText());

    }

    public CompletableFuture<Message> executeAsync(SendPhoto message) {
//        System.out.println(message.toString());
        logsService.saveBotMessage(message);
        return super.executeAsync(message);
    }

    public void executeAsync(SendMessage message) {
        try {
            super.executeAsync(message);
            logsService.saveBotMessage(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_NAME;
    }
}
