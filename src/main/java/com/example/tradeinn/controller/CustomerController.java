package com.example.tradeinn.controller;

import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.entity.Ordering;
import com.example.tradeinn.listener.TelegramBotListener;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import com.example.tradeinn.utils.ReplyKeyboardUtil;
import com.example.tradeinn.utils.SendMessageUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerController {
    CustomerService customerService;
    OrderingService orderingService;
    TelegramBotListener tgBot;

    @GetMapping("customer")
    String getCustomerList(Model model) {
        List<Customer> customers = customerService.getCustomerList();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("customer/{id}/")
    public String getCustomer(@PathVariable Long id, Model model) {
        Ordering orderEdit = new Ordering();
        Customer customer = customerService.findByTelegramUserId(id);
        Customer customerChangeStep = customerService.findByTelegramUserId(id);
        List<Ordering> ordering = orderingService.getListOrderingByCustomer(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("ordering", ordering);
        model.addAttribute("orderEdit", orderEdit);
        model.addAttribute("customerChangeStep", customerChangeStep);
        return "customer";
    }

    @PostMapping("customer/{id}/")
    public String editCustomerStep(@PathVariable Long id, @ModelAttribute Customer customerChangeStep) {
        Customer customer = customerService.findByTelegramUserId(id);
        customer.setStep(customerChangeStep.getStep());
        customerService.saveCustomer(customer);
        return "redirect:/customer/" + id + "/";
    }

    @PostMapping("customer/{id}/send")
    public String sendTelegramMessage(@PathVariable Long id, @RequestParam("message") String message) {
        System.out.println(message);
        tgBot.executeAsync(SendMessageUtil.sendMessageUtil(id, message, ReplyKeyboardUtil.MAIN_MENU_BUTTONS));
        return "redirect:/customer/" + id + "/";
    }
}
