package bts.test.service.impl;

import bts.test.config.jwt.JwtProvider;
import bts.test.dto.AuthRequest;
import bts.test.dto.AuthResponse;
import bts.test.dto.RegistrationRequestDto;
import bts.test.dto.converters.UserConverter;
import bts.test.exception.BadPasswordException;
import bts.test.exception.UserAlreadyExistException;
import bts.test.model.User;
import bts.test.repository.UserRepo;
import bts.test.service.UserAuthService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public AuthResponse register(RegistrationRequestDto requestDto) {
        String encode = passwordEncoder.encode(requestDto.getPassword());
        User user = UserConverter.mapFromRegistrationRequest(requestDto, encode);
        saveUser(user);
        return new AuthResponse(jwtProvider.generateToken(user));
    }

    @Override
    public AuthResponse login(AuthRequest request) throws NotFoundException {
        User user = findByEmailAndPassword(request.getLogin());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadPasswordException(request.getLogin());
        }

        String token = jwtProvider.generateToken(user);
        return new AuthResponse(token);
    }

    @Override
    public void saveUser(User user) throws UserAlreadyExistException {
        String email = user.getEmail();
        log.debug("Save new user with email {}", email);
        Optional<User> byEmail = userRepo.findByEmail(email);

        if (byEmail.isPresent()) {
            throw new UserAlreadyExistException("User with email %s already exist", email);
        }

        userRepo.save(user);
    }

    @Override
    public User findByEmailAndPassword(String login) throws NotFoundException {
       return userRepo.findByEmail(login)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
