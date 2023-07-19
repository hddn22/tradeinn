package com.example.tradeinn.controller;

import com.example.tradeinn.entity.Ordering;
import com.example.tradeinn.service.CustomerService;
import com.example.tradeinn.service.OrderingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderingController {
    CustomerService customerService;
    OrderingService orderingService;

    @GetMapping("/ordering")
    public String orderList(Model model) {
        List<Ordering> orders = orderingService.getListOrdering();
        model.addAttribute("orders", orders);
        return "ordering";
    }

    @DeleteMapping("/order/{orderId}/{customerId}")
    public String orderDelete(@PathVariable Long orderId, @PathVariable Long customerId) {
        orderingService.deleteOrderById(orderId);
        return "redirect:/customer/" + customerId + "/";
    }

    @PostMapping("order/{id}/")
    public String editCustomerOrder(@PathVariable Long id, @ModelAttribute Ordering orderEdit) {
        orderingService.editOrder(orderEdit);
        return "redirect:/customer/" + id + "/";
    }

}
