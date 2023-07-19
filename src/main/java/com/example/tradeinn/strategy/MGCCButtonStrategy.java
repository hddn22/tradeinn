package com.example.tradeinn.strategy;

import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.utils.ReplyKeyboardUtil;
import com.example.tradeinn.utils.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MGCCButtonStrategy implements ButtonStrategy{
    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        String text = "Философия\n" +
                "Московский гревел – это как гонки с препятствиями. Только вместо того чтобы преодолевать препятствия надо в буквальном смысле их создавать. В таких гонках могут участвовать только те, кто готов к тому, что с каждой минутой темп будет все быстрее и быстрее. И тут не важно какая у вас физическая форма, выносливость или физическая подготовка. Главное чтобы было желание. Если не готовы ехать на скорость по пересеченной местности, то лучше не стоит этим заниматься.\n" +
                "\n" +
                "ВСТУПАЙ В КЛУБ — ТЕБЯ ЖДЕТ:\n" +
                "Невероятно клевое сообщество\n" +
                "Участие в городских заездах\n" +
                "Участие в клубных турах\n" +
                "Доступ к клубному мерчу\n" +
                "Скидки у друзей и партнеров клуба\n" +
                "\n" +
                "Ссылка для вступления: https://t.me/+3pDMR0-3rgU1M2Qy";
        String path = "mgcc.jpg";
        return SendMessageUtil.sendPhotoUtil(update.getMessage().getChatId(), text, ReplyKeyboardUtil.MGCC_BUTTONS, path);
    }
}
