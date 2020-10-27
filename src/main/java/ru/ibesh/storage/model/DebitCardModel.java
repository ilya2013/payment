package ru.ibesh.storage.model;

import lombok.*;
import ru.ibesh.payment.Card;
import ru.ibesh.payment.Currency;
import ru.ibesh.payment.NotEnoughMoney;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@Entity
@ToString
public class DebitCardModel extends CardModel {


}
