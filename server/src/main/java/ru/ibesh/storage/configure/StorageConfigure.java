package ru.ibesh.storage.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ibesh.storage.UserStorage;
import ru.ibesh.storage.UserStorageImpl;
import ru.ibesh.storage.persistent.repository.PaymentRepository;
import ru.ibesh.storage.persistent.repository.UserRepository;

@Configuration
public class StorageConfigure {

    @Bean
    public UserStorage configureUserStorage(UserRepository userRepository, PaymentRepository paymentRepository){
        return new UserStorageImpl(userRepository, paymentRepository);
    }
}
