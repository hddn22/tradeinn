package com.example.tradeinn.entity;

import com.example.tradeinn.component.Step;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long telegramUserId;
    private String userName;
    private Step step;
    @Column(name = "date_column", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ordering> ordering;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Logs> logs;
}
