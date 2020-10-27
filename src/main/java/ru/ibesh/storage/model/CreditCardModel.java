package ru.ibesh.storage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditCardModel extends CardModel {
    @Column(name = "limit_")
    private int limit;
    private int usedLimit = 0;
}
