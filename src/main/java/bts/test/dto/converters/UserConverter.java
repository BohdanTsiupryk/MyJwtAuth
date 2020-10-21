package bts.test.dto.converters;

import bts.test.dto.RegistrationRequestDto;
import bts.test.dto.UserDto;
import bts.test.model.User;
import bts.test.util.DateUtil;

public class UserConverter {
    public static User mapFromRegistrationRequest(RegistrationRequestDto request, String encriptedPass) {
        return User.of()
                .email(request.getLogin())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .createdAt(DateUtil.getNowDate())
                .password(encriptedPass)
                .build();
    }

    public static User mapToEntity(UserDto userDto) {
        return User.of()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .createdAt(userDto.getCreatedAt())
                .build();
    }

    public static UserDto mapToDto(User user) {
        return UserDto.of()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
