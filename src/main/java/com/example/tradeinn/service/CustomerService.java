package com.example.tradeinn.service;

import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    CustomerRepository customerRepository;

    public Customer findByTelegramUserId(Long id) {
        Customer customerFromDb = customerRepository.findByTelegramUserId(id);
        return customerFromDb;
    }



    public boolean saveCustomer(Customer customer) {
        if(customerRepository.findByTelegramUserId(customer.getId()) == null) {
            customerRepository.save(customer);
        } else {
            return false;
        }
        return true;
    }

    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }
}
