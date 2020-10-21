package bts.test.controler;

import bts.test.config.jwt.JwtProvider;
import bts.test.dto.AuthRequest;
import bts.test.dto.AuthResponse;
import bts.test.dto.RegistrationRequestDto;
import bts.test.exception.UserAlreadyExistException;
import bts.test.service.UserAuthService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserAuthService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/registration")
    public ResponseEntity<AuthResponse> registration(@RequestBody @Valid RegistrationRequestDto request) throws UserAlreadyExistException {
        AuthResponse token = userService.register(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) throws NotFoundException {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest httpRequest) {
        jwtProvider.invalidateToken(httpRequest);
        return ResponseEntity.ok("Successfully logout");
    }
}
