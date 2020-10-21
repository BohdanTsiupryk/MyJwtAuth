package bts.test.service;

import bts.test.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void saveUser(User user) {

    }

    @Override
    public User findByEmailAndPassword(String login, String password) {
        return User.of().email(login).password(password).build();
    }
}
