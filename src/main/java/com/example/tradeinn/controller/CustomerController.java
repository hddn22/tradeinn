package com.example.tradeinn.controller;

import com.example.tradeinn.component.Step;
import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.entity.Ordering;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerController {
    CustomerService customerService;
    OrderingService orderingService;

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


}
