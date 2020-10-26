package ru.ibesh;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import ru.ibesh.payment.Card;
import ru.ibesh.payment.Payment;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Log4j2
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String phoneNumber;
    @OneToMany(mappedBy = "user")
    private Set<Card> cards;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<Payment> payments;


    public void savePayment(Payment payment) {
        log.debug("Сохранение платежа: {}; пользователю {}", payment, this);
        payments.add(payment);
        log.debug("Платеж: \"{}\" сохранён пользователю c логином \"{}\"", payment.getPaymentUID(), this.login);

    }

    public Optional<Card> findCardByCardNumber(String cardNumber) {
        return cards.stream()
                .filter(a -> a.getCardNumber().equals(cardNumber))
                .findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
