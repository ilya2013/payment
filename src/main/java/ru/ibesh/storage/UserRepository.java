package ru.ibesh.storage;

import org.springframework.data.repository.CrudRepository;
import ru.ibesh.storage.model.UserModel;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserModel, Long> {
    public Optional<UserModel> findUserModelByLogin();
}
