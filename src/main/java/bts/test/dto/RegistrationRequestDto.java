package bts.test.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequestDto {

    @NotEmpty
    @Email
    private String login;

    @NotEmpty
    private String password;

    private String firstName;

    private String lastName;
}
