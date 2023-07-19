package com.example.tradeinn.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BotConfig {
    public final static String TOKEN;
    public final static String CHAT_ID;
    public final static String BOT_NAME;

    static Properties property = new Properties();

    static {
        try(InputStream inputStream =  BotConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            property.load(inputStream);

            TOKEN = property.getProperty("bot.token");
            CHAT_ID = property.getProperty("bot.chatId");
            BOT_NAME = property.getProperty("bot.name");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
