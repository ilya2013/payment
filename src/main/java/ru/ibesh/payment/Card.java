package ru.ibesh.payment;

import lombok.*;
import ru.ibesh.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance
@Table(name = "Cards")
public abstract class Card implements PaymentInstrument{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String cardNumber;
    protected int balance;
    @Enumerated(EnumType.STRING)
    protected Currency currency;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;



    public Card(String cardNumber, int balance, Currency currency){
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.currency = currency;
    }

    public Card(Long id, String cardNumber, int balance, Currency currency, User user) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.currency = currency;
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", user=" + ((user == null) ? "0" : "1") +
                '}';
    }
}
