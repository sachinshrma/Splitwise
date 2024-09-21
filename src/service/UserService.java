package service;

import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {

    Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public User createUser(String name, String email) {
        User user = new User(UUID.randomUUID().toString(), name, email);
        users.put(user.getId(), user);
        return user;
    }
}
