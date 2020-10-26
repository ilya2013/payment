package ru.ibesh.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.ibesh.User;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "Payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String cardNumber;
    private int paySum;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String paymentUID;

    public Payment(User user, Card card, int paySum) {
        this.user = user;
        this.cardNumber = card.getCardNumber();
        this.paySum = paySum;
        this.status = Status.WAIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
