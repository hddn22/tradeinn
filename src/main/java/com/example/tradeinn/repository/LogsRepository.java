package com.example.tradeinn.repository;

import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Long> {
    void deleteAllByCustomer(Customer customer);
}
