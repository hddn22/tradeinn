package com.example.tradeinn.strategy;

import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.utils.ReplyKeyboardUtil;
import com.example.tradeinn.utils.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class WorkButtonStrategy implements ButtonStrategy{
    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        String text = "Условия работы\n" +
                "\n" +
                "Мы предоставляем услугу оплаты Вашего заказа на зарубежных интернет-магазинах. \n" +
                "Вы предоставляете список товаров и информацию, необходимую для заказа: \n" +
                "— Имя и фамилию\n" +
                "— Адрес доставки с индексом\n" +
                "— Номер телефона\n" +
                "\n" +
                "Мы либо с Вашего аккаунта, либо с нашего осуществляет заказ данных товаров по указанным Вами данным. \n" +
                "Если мы заказываем с Вашего аккаунта, то услугой считается выполненной в момент получения подтверждения Вашего заказа. \n" +
                "Если мы заказываем с Нашего аккаунта, то услуга считается выполненной в момент получения нами трек-номера Вашего заказа и передачи его Вам.";
        String path = "work.jpg";

        return SendMessageUtil.sendPhotoUtil(update.getMessage().getChatId(), text, ReplyKeyboardUtil.WORK_BUTTONS, path);
    }
}
