package com.example.tradeinn.config;

import com.example.tradeinn.listener.TelegramBotListener;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class ConfigBotListener {

    @Bean
    public TelegramBotsApi telegramBotListener(CustomerService customerService, OrderingService orderingService) throws TelegramApiException {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBotListener(BotConfig.TOKEN, customerService, orderingService));
            return telegramBotsApi;
    }
}
