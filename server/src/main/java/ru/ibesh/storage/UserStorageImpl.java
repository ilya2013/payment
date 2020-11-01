package ru.ibesh.storage;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;
import ru.ibesh.User;
import ru.ibesh.payment.Currency;
import ru.ibesh.payment.Status;
import ru.ibesh.storage.persistent.entity.Card;
import ru.ibesh.storage.persistent.entity.CreditCard;
import ru.ibesh.storage.persistent.entity.Payment;
import ru.ibesh.storage.persistent.entity.UserEntity;
import ru.ibesh.storage.persistent.repository.PaymentRepository;
import ru.ibesh.storage.persistent.repository.UserRepository;

import java.util.Optional;
@AllArgsConstructor
@Log4j2
public class UserStorageImpl implements UserStorage {
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public User addUser(User user) {

//        user1 = userRepository.save(user1);
//        Card card = new CreditCard(null, "34543465234", 100, Currency.RUB, user1, 10, 0);
//        user1.getPaymentInstruments().add(card);
//        Payment payment = new Payment(null, user1, card.getCardNumber(), 10, Status.SUCCESS, "q123");
//        user1.getPayments().add(payment);
//        user1 = userRepository.save(user1);
        return user;
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id).map(this::mapUserEntity2User);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
         return (userRepository.findByLogin(login).map(this::mapUserEntity2User));
    }

    private User mapUserEntity2User(UserEntity userEntity){
        log.info("Map UserEntity -> User with login {}", userEntity.getLogin());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userEntity, User.class);
    }
}
