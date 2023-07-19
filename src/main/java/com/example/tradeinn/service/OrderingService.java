package com.example.tradeinn.service;

import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.entity.Ordering;
import com.example.tradeinn.repository.OrderingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderingService {
    OrderingRepository orderingRepository;

    public Ordering findOrderingByCustomer(Customer customer) {
        List<Ordering> ordering = orderingRepository.findOrderingByCustomer(customer);
        for(Ordering order : ordering) {
            if(!order.isFinalise()) {
                return order;
            }
        }
        return null;
    }

    public List<Ordering> getListOrderingByCustomer(Customer customer) {
        return orderingRepository.findOrderingByCustomer(customer);
    }

    public boolean editOrder(Ordering editOrder) {
        Optional<Ordering> optionalOrdering = orderingRepository.findById(editOrder.getId());
        Ordering ordering = optionalOrdering.get();
        ordering.setChosenSum(editOrder.getChosenSum());
        ordering.setChosenAccount(editOrder.getChosenAccount());
        ordering.setChosenService(editOrder.getChosenService());
        ordering.setFinalise(editOrder.isFinalise());
        orderingRepository.save(ordering);
        return true;
    }

    public boolean deleteOrderById(Long id) {
        orderingRepository.deleteById(id);
        return true;
    }

    public List<Ordering> getListOrdering() {
        return orderingRepository.findAll();
    }


    public boolean save(Ordering ordering) {
        orderingRepository.save(ordering);
        return true;
    }

    public void deleteOrderIfFinaliseFalse(Customer customer) {
        List<Ordering> ordering = orderingRepository.findOrderingByCustomer(customer);
        for (Ordering order : ordering) {
            if (!order.isFinalise()) {
                orderingRepository.delete(order);
            }
        }
    }
}
