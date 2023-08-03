package com.example.tradeinn.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
public class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_telegramUserId")
    private Customer customer;

    private String chosenService;
    private String chosenAccount;
    private String chosenSum;
    @Column(name = "date_column", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;
    private boolean finalise;

}
