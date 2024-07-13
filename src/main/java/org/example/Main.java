package org.example;

import org.example.config.AppConfig;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws UserNotFoundException {
        System.out.print("laksdj");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = new User(null, "Ivanov");
        User currentUser = userService.createUser(user);
        System.out.println(currentUser.getId());
        User userFromDB = userService.getUserById(currentUser.getId());
        System.out.println(userFromDB.getUsername());
        userService.deleteUser(userFromDB.getId());
        userService.getUserById(currentUser.getId());
    }
}