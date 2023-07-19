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

public class ServiceButtonStrategy implements ButtonStrategy{

    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        if(ReplyKeyboardUtil.validateMessage(update.getMessage().getText(), ReplyKeyboardUtil.SERVICE_BUTTONS)) {
            Customer customer = customerService.findByTelegramUserId(update.getMessage().getFrom().getId());
            Ordering ordering = orderingService.findOrderingByCustomer(customer);
            ordering.setChosenService(update.getMessage().getText());
            customer.setStep(Step.ACCOUNT);
            customerService.saveCustomer(customer);
            orderingService.save(ordering);
//            OrderEntity orderEntity = new OrderEntity();
//            orderEntity.setCustomerEntity(customerEntity);
//            orderEntity.setChosenService(update.getMessage().getText());
//            customerEntity.setOrderEntity(List.of(orderEntity));
//            customerEntity.setStep(Step.ACCOUNT);
//            CustomerDAO.getCustomerDAO().save(customerEntity);
            return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Принято✅\nНа чей аккаунт заказ?", ReplyKeyboardUtil.ACCOUNT_BUTTONS);
        } else {
            return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Ой, я не знаю такой команды. \uD83D\uDC7E \nВыберите сервис с помощью кнопок ниже:", ReplyKeyboardUtil.SERVICE_BUTTONS);
        }

    }
}
