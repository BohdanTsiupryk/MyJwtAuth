package bts.test.controler;

import bts.test.dto.UserDto;
import bts.test.dto.converters.UserConverter;
import bts.test.model.User;
import bts.test.dto.View;
import bts.test.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @JsonView(View.HIDDEN.class)
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> userDtos = userService.getAllUser()
                .stream()
                .map(UserConverter::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @JsonView(View.HIDDEN.class)
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable Long id) {
        User userById = userService.getUserById(id);

        return ResponseEntity.ok(UserConverter.mapToDto(userById));
    }

    @JsonView(View.HIDDEN.class)
    @PostMapping
    public ResponseEntity<UserDto> saveNewUser(@RequestBody UserDto userDto) {
        User newUser = userService.saveNewUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserConverter.mapToDto(newUser));
    }

    @JsonView(View.HIDDEN.class)
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userService.updateUser(id, userDto);
        return ResponseEntity
                .ok(UserConverter.mapToDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
