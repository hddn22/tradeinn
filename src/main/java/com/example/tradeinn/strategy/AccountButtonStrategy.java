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

public class AccountButtonStrategy implements ButtonStrategy{
    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        if (ReplyKeyboardUtil.validateMessage(update.getMessage().getText(), ReplyKeyboardUtil.ACCOUNT_BUTTONS)) {
            Customer customer = customerService.findByTelegramUserId(update.getMessage().getFrom().getId());
            Ordering ordering = orderingService.findOrderingByCustomer(customer);
            ordering.setChosenAccount(update.getMessage().getText());
            orderingService.save(ordering);
            customer.setStep(Step.MONEY);
            customerService.saveCustomer(customer);
//            OrderEntity orderEntity = OrderDAO.getOrderDAO().getOrderByCustomer(customerEntity);
//            orderEntity.setChosenAccount(update.getMessage().getText());
//            customerEntity.setOrderEntity(List.of(orderEntity));
//            customerEntity.setStep(Step.MONEY);
//            CustomerDAO.getCustomerDAO().save(customerEntity);
            return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Принято✅\nКакая сумма товаров в корзине", ReplyKeyboardUtil.SUM_BUTTONS);
        } else {
            return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Ой, я не знаю такой команды. \uD83D\uDC7E \nВыберите аккаунт с помощью кнопок ниже:", ReplyKeyboardUtil.ACCOUNT_BUTTONS);
        }
    }
}
