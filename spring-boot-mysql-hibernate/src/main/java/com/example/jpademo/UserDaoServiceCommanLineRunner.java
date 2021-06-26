package com.example.jpademo;

import com.example.jpademo.model.User;
import com.example.jpademo.repository.UserDaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class UserDaoServiceCommanLineRunner implements CommandLineRunner {

    @Autowired
    private UserDaoService userDaoService;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Jack", "jack@yahoo.com");

        int id = userDaoService.insert(user);
        log.info("New user is created: " + user);

//        user = new User("Jill", "jill@yahoo.com");
//        userDaoService.insert(user);
//        log.info("New user is created: " + user);
    }
}
