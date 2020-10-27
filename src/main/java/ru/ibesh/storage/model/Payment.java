package ru.ibesh.storage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.ibesh.payment.User;
import ru.ibesh.payment.Status;

import javax.persistence.*;

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
}
