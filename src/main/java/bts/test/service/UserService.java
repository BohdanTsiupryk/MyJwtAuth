package bts.test.service;

import bts.test.model.User;

public interface UserService {
    void saveUser(User user);

    User findByEmailAndPassword(String login, String password);
}
