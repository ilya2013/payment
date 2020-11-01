package ru.ibesh.storage.persistent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ibesh.payment.Currency;

import javax.persistence.*;

@Data
@Entity
@Inheritance
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cards")
public abstract class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String cardNumber;
    protected int balance;
    @Enumerated(EnumType.STRING)
    protected Currency currency;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    protected UserEntity userEntity;
}


