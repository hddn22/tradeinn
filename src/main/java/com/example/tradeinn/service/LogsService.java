package com.example.tradeinn.service;

import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.entity.Logs;
import com.example.tradeinn.repository.LogsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Date;

@Service
@AllArgsConstructor
public class LogsService {
    LogsRepository logsRepository;
    CustomerService customerService;

    public void saveCustomerMessage(Update update) {
        Logs logs = new Logs();
        logs.setCustomer(customerService.findByTelegramUserId(update.getMessage().getFrom().getId()));
        logs.setDate(new Date());
        logs.setMessage(update.getMessage().getText());
        logs.setName("\uD83E\uDD11" + update.getMessage().getFrom().getFirstName());
        logsRepository.save(logs);
    }

    public void saveBotMessage(SendPhoto msg) {
        Logs logs = new Logs();
        logs.setCustomer(customerService.findByTelegramUserId(Long.valueOf(msg.getChatId())));
        logs.setDate(new Date());
        logs.setMessage(msg.getCaption());
        logs.setName("\uD83E\uDD16Telegram BOT");
        logsRepository.save(logs);
    }

    public void saveBotMessage(SendMessage msg) {
        Logs logs = new Logs();
        logs.setCustomer(customerService.findByTelegramUserId(Long.valueOf(msg.getChatId())));
        logs.setDate(new Date());
        logs.setMessage(msg.getText());
        logs.setName("\uD83E\uDD16Telegram BOT");
        logsRepository.save(logs);
    }

    @Transactional
    public void deleteLogHistoryByCustomerId(Long id) {
        Customer customer = customerService.findByTelegramUserId(id);
        logsRepository.deleteAllByCustomer(customer);
    }
}
