package com.example.tradeinn.config;

import com.example.tradeinn.listener.TelegramBotListener;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.LogsService;
import com.example.tradeinn.service.OrderingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class ConfigBotListener {

    @Bean
    public TelegramBotsApi telegramBotsApi(CustomerService customerService, OrderingService orderingService, LogsService logsService) throws TelegramApiException {
            String token = BotConfig.TOKEN;
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBotListener(customerService, orderingService, logsService));
            return telegramBotsApi;
    }
}
