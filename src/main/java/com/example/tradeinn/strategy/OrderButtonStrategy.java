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

public class OrderButtonStrategy implements ButtonStrategy{
    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        Customer customer = customerService.findByTelegramUserId(update.getMessage().getFrom().getId());
        customer.setStep(Step.SERVICE);
        customerService.saveCustomer(customer);
        Ordering ordering = new Ordering();
        ordering.setCustomer(customer);
        orderingService.save(ordering);

        return SendMessageUtil.sendPhotoUtil(update.getMessage().getChatId(), "Поздравляю, что решился!\n" +
                "Теперь, нам нужно немного информации о заказе:", ReplyKeyboardUtil.SERVICE_BUTTONS, null);
    }
}
