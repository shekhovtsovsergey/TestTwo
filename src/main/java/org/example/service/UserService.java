package org.example.service;


import org.example.model.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);
    public User getUserById(Long id);
    public List<User> getAllUsers();
    public void deleteUser(Long id);

}
