package ru.ibesh.storage.model;

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
public class UserModel {
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

}
