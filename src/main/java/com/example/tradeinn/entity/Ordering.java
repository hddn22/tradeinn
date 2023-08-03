package com.example.tradeinn.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity(name = "order_entity")
public class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_entity_telegramuserid")
    private Customer customer;

    @Column(name = "chosenservice")
    private String chosenService;
    @Column(name = "chosenaccount")
    private String chosenAccount;
    @Column(name = "chosensum")
    private String chosenSum;
    @Column(name = "date_column", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;
    @Column(name = "isfinalise")
    private boolean finalise;

}
