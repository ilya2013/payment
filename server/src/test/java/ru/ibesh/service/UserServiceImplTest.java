package ru.ibesh.service;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AllArgsConstructor
class UserServiceImplTest {
private final UserService userService;

    @Test
    void findByLogin() {
        System.out.println(userService.findByLogin("login1").get());
    }

}