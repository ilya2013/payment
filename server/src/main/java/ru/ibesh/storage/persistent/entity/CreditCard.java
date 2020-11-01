package ru.ibesh.storage.persistent.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ibesh.payment.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditCard extends Card {
    @Column(name = "limit_")
    private int limit;
    private int usedLimit = 0;

    public CreditCard(Long id, String cardNumber, int balance, Currency currency, UserEntity userEntity, int limit, int usedLimit){
        super(id, cardNumber, balance, currency, userEntity);
        this.limit = limit;
        this.usedLimit = usedLimit;
    }
}
