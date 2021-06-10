package com.example.spring_boot_registration_and_login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("hacker@gmail.com");
        user.setPassword("123456");
        user.setFirstName("cris");
        user.setLastName("phan");

        User savedUser = repo.save(user);
        User exitsUser = entityManager.find(User.class, savedUser.getId());

        assertEquals(exitsUser.getEmail(),user.getEmail());


    }
    @Test
    public void testFindUserByEmail(){
        String email = "hacker748485@gmail.com";
        User user = repo.findByEmail(email);
        assertThat(user).isNotNull();
    }

}