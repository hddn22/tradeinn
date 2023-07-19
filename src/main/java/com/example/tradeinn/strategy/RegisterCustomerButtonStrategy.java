package com.example.tradeinn.strategy;

import com.example.tradeinn.component.Step;
import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.utils.ReplyKeyboardUtil;
import com.example.tradeinn.utils.SendMessageUtil;
import lombok.RequiredArgsConstructor;
import org.glassfish.jersey.internal.inject.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service

public class RegisterCustomerButtonStrategy implements ButtonStrategy{


    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        Customer customer = new Customer();
        customer.setUserName(update.getMessage().getFrom().getUserName());
        customer.setTelegramUserId(update.getMessage().getFrom().getId());
        customer.setStep(Step.INIT);
        customerService.saveCustomer(customer);
        String path = "tradeinn.jpg";
        return SendMessageUtil.sendPhotoUtil(update.getMessage().getFrom().getId(), "Привет! \n" +
                "Добро пожаловать в бот по заказу товаров с TradeInn от MGCC.\n" +
                "В данном боте ты можешь:\n" +
                "— Оставить заявку на заказ; \n" +
                "— Узнать условия заказа;\n" +
                "— Узнать особенности заказа;\n" +
                "Чтобы продолжить нажмина кнопку внизу!", ReplyKeyboardUtil.START_MENU_BUTTONS, path);
    }
}
