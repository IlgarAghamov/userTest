// UserRepositoryTest.java
package com.example;

import org.example.Main;
import org.example.User;
import org.example.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindUser() {
        User user = new User();
        user.setName("Test User");
        userRepository.save(user);

        User foundUser = userRepository.findById(user.getId()).orElse(null);
        assert foundUser != null;
        assert foundUser.getName().equals("Test User");
    }
}
