package ru.ibesh.storage;

import org.springframework.data.repository.CrudRepository;
import ru.ibesh.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
