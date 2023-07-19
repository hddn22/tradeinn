package com.example.tradeinn.strategy;

import com.example.tradeinn.component.Step;
import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.entity.Ordering;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.utils.ReplyKeyboardUtil;
import com.example.tradeinn.utils.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ChangeButtonStrategy implements ButtonStrategy{
    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        if (ReplyKeyboardUtil.CHANGE_BUTTONS.getKeyboard()
                .stream()
                .anyMatch(keyboardButtons -> keyboardButtons.contains(update.getMessage().getText()))) {
            Customer customer = customerService.findByTelegramUserId(update.getMessage().getFrom().getId());
            Ordering ordering = orderingService.findOrderingByCustomer(customer);
            switch (update.getMessage().getText()) {
                case "Сервис":
                    customer.setStep(Step.CHANGE_SERVICE);
                    customerService.saveCustomer(customer);
                    return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Выберите сервис через который будет оформляться заказ", ReplyKeyboardUtil.SERVICE_BUTTONS);
                case "Аккаунт":
                    customer.setStep(Step.CHANGE_ACCOUNT);
                    customerService.saveCustomer(customer);
                    return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "На чей аккаунт будет оформлен заказ?", ReplyKeyboardUtil.ACCOUNT_BUTTONS);
                case "Сумма":
                    customer.setStep(Step.CHANGE_MONEY);
                    customerService.saveCustomer(customer);
                    return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Какая сумма товаров в корзине?", ReplyKeyboardUtil.SUM_BUTTONS);
            }
        } else {
            return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Ой, я не знаю таких комманд.\uD83D\uDC7E \nКак из пунктов вы хотите изменить?", ReplyKeyboardUtil.CHANGE_BUTTONS);
        }
        return null;
    }
}
