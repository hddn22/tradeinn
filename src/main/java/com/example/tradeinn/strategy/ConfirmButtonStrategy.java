package com.example.tradeinn.strategy;

import com.example.tradeinn.component.Step;
import com.example.tradeinn.config.BotConfig;
import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.entity.Ordering;
import com.example.tradeinn.listener.TelegramBotListener;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.utils.ReplyKeyboardUtil;
import com.example.tradeinn.utils.SendMessageUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ConfirmButtonStrategy implements ButtonStrategy {
    String userName;
    String link;
    SimpleDateFormat formater;
    String date;
    String time;
    Date globalDate;

//    TelegramBotListener tgBot;

    @Override
    public PartialBotApiMethod<Message> sendMessage(Update update, CustomerService customerService, OrderingService orderingService) {
        if (ReplyKeyboardUtil.validateMessage(update.getMessage().getText(), ReplyKeyboardUtil.CONFIRM_BUTTONS)) {
            Customer customer = customerService.findByTelegramUserId(update.getMessage().getFrom().getId());
            Ordering ordering = orderingService.findOrderingByCustomer(customer);
            switch (update.getMessage().getText()) {
                case "Отправить ✅":
                    customer.setStep(Step.INIT);
                    ordering.setFinalise(true);
                    customerService.saveCustomer(customer);
                    orderingService.save(ordering);

                    userName = update.getMessage().getFrom().getUserName() == null ? update.getMessage().getFrom().getFirstName() : update.getMessage().getFrom().getUserName();
                    formater = new SimpleDateFormat("dd.MM.yyyy");
                    globalDate = new Date();
                    date = formater.format(globalDate);
                    formater = new SimpleDateFormat("HH:mm:ss");
                    String time = formater.format(globalDate);
                    link = "<a href=\"tg://user?id=" + customer.getTelegramUserId() + "\">" + userName + "</a>"
                            + " в " + time + ", " + date;
                    String message = "Принятый заказ ✅\n\n "
                            + link + "\n\n"
                            + "\uD83D\uDC8E Сервис: " + ordering.getChosenService()
                            + "\n\n\uD83E\uDD77\uD83C\uDFFB Аккаунт: " + ordering.getChosenAccount()
                            + "\n\n\uD83D\uDCB8 Сумма: " + ordering.getChosenSum();
//                    tgBot.executeAsync(SendMessageUtil.sendPhotoUtil(Long.parseLong(BotConfig.CHAT_ID), message, null, null));
                    return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Ваш заказ отправлен✅\nC вами скоро свяжутся.\n", ReplyKeyboardUtil.MAIN_MENU_BUTTONS);
                case "Удалить ❌":
                    customer.setStep(Step.INIT);
                    orderingService.deleteOrderIfFinaliseFalse(customer);
                    customerService.saveCustomer(customer);

                    SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
                    Date globalDate = new Date();
                    date = formater.format(globalDate);
                    formater = new SimpleDateFormat("HH:mm:ss");
                    time = formater.format(globalDate);

                    userName = update.getMessage().getFrom().getUserName() == null? update.getMessage().getFrom().getFirstName() : update.getMessage().getFrom().getUserName();
                    link = "<a href=\"tg://user?id=" + customer.getTelegramUserId() + "\">" + userName + "</a>"
                            + " в " + time + ", " + date;

                    message = "Отменённый заказ ❌\n\n "
                            + link + "\n\n"
                            + "\uD83D\uDC8E Сервис: " + ordering.getChosenService()
                            + "\n\n\uD83E\uDD77\uD83C\uDFFB Аккаунт: " + ordering.getChosenAccount()
                            + "\n\n\uD83D\uDCB8 Сумма: " + ordering.getChosenSum();
//                    tgBot.executeAsync(SendMessageUtil.sendPhotoUtil(Long.parseLong(BotConfig.CHAT_ID), message, null, null));

                    return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Ваш заказ удалён ❌\n", ReplyKeyboardUtil.MAIN_MENU_BUTTONS);
                case "Изменить \uD83D\uDCDD":
                    customer.setStep(Step.CHANGE);
                    customerService.saveCustomer(customer);
                    return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Как из пунктов вы хотите изменить?", ReplyKeyboardUtil.CHANGE_BUTTONS);
            }
        } else {
            return SendMessageUtil.sendMessageUtil(update.getMessage().getChatId(), "Ой, я не знаю такой команды.\uD83D\uDC7E \nВыберите пункт с помощью кнопок ниже:", ReplyKeyboardUtil.CONFIRM_BUTTONS);
        }
        return null;
    }
}

