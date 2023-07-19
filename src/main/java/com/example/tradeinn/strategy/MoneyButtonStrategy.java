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

import java.util.List;

public class MoneyButtonStrategy implements ButtonStrategy{
    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        if(ReplyKeyboardUtil.validateMessage(update.getMessage().getText(), ReplyKeyboardUtil.SUM_BUTTONS)) {
            Customer customer = customerService.findByTelegramUserId(update.getMessage().getFrom().getId());
            Ordering ordering = orderingService.findOrderingByCustomer(customer);
            ordering.setChosenSum(update.getMessage().getText());
            customer.setStep(Step.CONFIRM);
            customerService.saveCustomer(customer);
            orderingService.save(ordering);
            String message = "Записал ✅\nПожалуйста, проверьте правильно ли оформлен ваш заказ:\n\n\n\n\uD83D\uDC8E Сервис: " + ordering.getChosenService()
                    + "\n\n\uD83E\uDD77\uD83C\uDFFB Аккаунт: " + ordering.getChosenAccount()
                    + "\n\n\uD83D\uDCB8 Сумма: " + ordering.getChosenSum();
            return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), message, ReplyKeyboardUtil.CONFIRM_BUTTONS);
        } else {
            return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Ой, я не знаю такой команды. \uD83D\uDC7E \nВыберите сумму с помощью кнопок ниже:", ReplyKeyboardUtil.SUM_BUTTONS);
        }
    }
}
