package com.example.tradeinn.strategy;

import com.example.tradeinn.component.Step;
import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.utils.ReplyKeyboardUtil;
import com.example.tradeinn.utils.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DefaultCancelButtonStrategy implements ButtonStrategy{
    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        Customer customer = customerService.findByTelegramUserId(update.getMessage().getFrom().getId());
        customer.setStep(Step.INIT);
        customerService.saveCustomer(customer);
        orderingService.deleteOrderIfFinaliseFalse(customer);
        String path = "tradeinn.jpg";
        return SendMessageUtil.sendPhotoUtil(update.getMessage().getChatId(), "Выбери интересующий раздел ниже:", ReplyKeyboardUtil.MAIN_MENU_BUTTONS, path);
    }
}
