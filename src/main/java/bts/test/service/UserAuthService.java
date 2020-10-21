package bts.test.service;

import bts.test.dto.AuthRequest;
import bts.test.dto.AuthResponse;
import bts.test.dto.RegistrationRequestDto;
import bts.test.exception.UserAlreadyExistException;
import bts.test.model.User;
import javassist.NotFoundException;

public interface UserAuthService {
    AuthResponse register(RegistrationRequestDto requestDto);

    AuthResponse login(AuthRequest request) throws NotFoundException;

    void saveUser(User user) throws UserAlreadyExistException;

    User findByEmailAndPassword(String login) throws NotFoundException;
}
