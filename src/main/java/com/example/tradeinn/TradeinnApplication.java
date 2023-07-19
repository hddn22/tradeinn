package com.example.tradeinn;

import com.example.tradeinn.config.BotConfig;
import com.example.tradeinn.listener.TelegramBotListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TradeinnApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeinnApplication.class, args);

//		try {
//			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
//			telegramBotsApi.registerBot(new TelegramBotListener(BotConfig.TOKEN));
//		} catch (TelegramApiException e) {
//			e.printStackTrace();
//		}

	}

}
