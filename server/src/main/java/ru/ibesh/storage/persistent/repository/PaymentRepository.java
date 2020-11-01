package ru.ibesh.storage.persistent.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ibesh.storage.persistent.entity.Payment;

import java.util.Optional;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
    Optional<Payment> findByPaymentUID(String paymentUID);
}
