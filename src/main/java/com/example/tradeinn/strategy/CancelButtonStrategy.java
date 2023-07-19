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

public class CancelButtonStrategy implements ButtonStrategy{
    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        Customer customer = customerService.findByTelegramUserId(update.getMessage().getFrom().getId());
        Ordering ordering = orderingService.findOrderingByCustomer(customer);
        customer.setStep(Step.CONFIRM);
        customerService.saveCustomer(customer);
        String message = "Возвращаемся назад...\nПожалуйста, проверьте правильно ли оформлен ваш заказ:\n\n\n\n\uD83D\uDC8E Сервис: " + ordering.getChosenService()
                + "\n\n\uD83E\uDD77\uD83C\uDFFB Аккаунт: " + ordering.getChosenAccount()
                + "\n\n\uD83D\uDCB8 Сумма: " + ordering.getChosenSum();
        return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), message, ReplyKeyboardUtil.CONFIRM_BUTTONS);
    }
}
