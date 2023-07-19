package com.example.tradeinn.handlers;

import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.strategy.*;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.validation.ValidationException;

public class MainMenuHandler {
    static ButtonHandler buttonHandler;

    public static SendPhoto mainMenuButton(Update update, CustomerService customerService, OrderingService orderingService) {

        switch (update.getMessage().getText()) {
            case "Я в деле!":
                buttonHandler = new ButtonHandler(new StartButtonStrategy());
                return (SendPhoto) buttonHandler.sendMessage(update, customerService, orderingService);
            case "\uD83D\uDEB4\uD83C\uDFFC\u200D♂️Сделать заказ":
                buttonHandler = new ButtonHandler(new OrderButtonStrategy());
                return (SendPhoto) buttonHandler.sendMessage(update, customerService, orderingService);
            case "\uD83D\uDCB8 Тарифы":
                buttonHandler = new ButtonHandler(new FareButtonStrategy());
                return (SendPhoto) buttonHandler.sendMessage(update, customerService, orderingService);
            case "\uD83D\uDCC4 Условия работы":
                buttonHandler = new ButtonHandler(new WorkButtonStrategy());
                return (SendPhoto) buttonHandler.sendMessage(update, customerService, orderingService);
            case "\uD83C\uDFE1 Moscow Gravel Cycling Club":
                buttonHandler = new ButtonHandler(new MGCCButtonStrategy());
                return (SendPhoto) buttonHandler.sendMessage(update, customerService, orderingService);
            case "☠️Отмена":
            case "⏪ Назад в меню":
                buttonHandler = new ButtonHandler(new DefaultCancelButtonStrategy());
                return (SendPhoto) buttonHandler.sendMessage(update, customerService, orderingService);
            case "\uD83D\uDCB5 Оплата":
                buttonHandler = new ButtonHandler(new PaymentButtonStrategy());
                return (SendPhoto) buttonHandler.sendMessage(update, customerService, orderingService);
            case "↩️ Возвраты":
                buttonHandler = new ButtonHandler(new ReturnButtonStrategy());
                return (SendPhoto) buttonHandler.sendMessage(update, customerService, orderingService);
            default:
//                buttonHandler = new ButtonHandler(new DefaultButtonStrategy());
//                return buttonHandler.buttonPhotoStrategy(update, channelId, customerEntity);
                throw new ValidationException("Ошибка валидации");
        }
    }
}
