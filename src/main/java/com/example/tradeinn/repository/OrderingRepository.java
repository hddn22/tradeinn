package com.example.tradeinn.repository;

import com.example.tradeinn.entity.Customer;
import com.example.tradeinn.entity.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderingRepository extends JpaRepository<Ordering, Long> {
    List<Ordering> findOrderingByCustomer(Customer customer);
}
