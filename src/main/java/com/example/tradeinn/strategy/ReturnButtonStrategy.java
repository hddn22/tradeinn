package com.example.tradeinn.strategy;

import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.utils.ReplyKeyboardUtil;
import com.example.tradeinn.utils.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ReturnButtonStrategy implements ButtonStrategy{
    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        String text = "Условия возвратов \n" +
                "Если по каким-то причинам в ходе обработки со стороны TradeInn заказа произошли изменения (товара нет, товар доставляется до TradeInn слишком долго), то Вы можете удалить данный товар из заказа и в момент когда денежные средства поступят к нам обратно на счет, вы можете использовать данные денежные средства в качестве депозита на следующие заказы. Данный депозит не облагается комиссией. \n" +
                "\n" +
                "Если Вам нужно вернуть товар, то если Вы заказывали товар через свой аккаунт, то Вы сами напрямую пишете представителям TradeInn и выясняете обстоятельства с ними самостоятельно.\n" +
                "Если Вы заказывали через наш аккаунт, то мы ведем общение с представителями TradeInn за Вас. От Вас требуется только лишь запрашиваемая от представителей TradeInn информация (фотографии и прочее).";
        String path = "return.jpg";
        return SendMessageUtil.sendPhotoUtil(update.getMessage().getChatId(), text, ReplyKeyboardUtil.WORK_BUTTONS, path);
    }
}
