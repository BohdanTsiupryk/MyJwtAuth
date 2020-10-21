package bts.test.service;

import bts.test.dto.UserDto;
import bts.test.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User getUserById(Long id);

    void deleteUserById(Long id);

    User saveNewUser(UserDto userDto);

    User updateUser(Long id, UserDto userDto);
}
