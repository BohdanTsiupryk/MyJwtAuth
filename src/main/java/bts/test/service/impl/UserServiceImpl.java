package bts.test.service.impl;

import bts.test.dto.UserDto;
import bts.test.dto.converters.UserConverter;
import bts.test.exception.UserNotFoundException;
import bts.test.model.User;
import bts.test.repository.UserRepo;
import bts.test.service.UserService;
import bts.test.util.DateUtil;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        log.debug("Get user by email {}", id);
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteUserById(Long id) {
        log.debug("Delete user by id {}", id);
        userRepo.delete(getUserById(id));
    }

    @Override
    public User saveNewUser(UserDto userDto) {
        log.debug("Save new user {}", userDto);
        User user = UserConverter.mapToEntity(userDto);
        String encode = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encode);
        user.setCreatedAt(DateUtil.getNowDate());
        return userRepo.save(user);
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        log.debug("Update user by id {}", id);
        String encode = passwordEncoder.encode(userDto.getPassword());
        User user = UserConverter.mapToEntity(userDto);
        user.setId(id);
        user.setPassword(encode);
        return userRepo.save(user);
    }
}
