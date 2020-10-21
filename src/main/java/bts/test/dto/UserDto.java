package bts.test.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(builderMethodName = "of")
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @JsonView(View.HIDDEN.class)
    private Long id;

    @JsonView(View.HIDDEN.class)
    private String email;

    @JsonView(View.HIDDEN.class)
    private String firstName;

    @JsonView(View.HIDDEN.class)
    private String lastName;

    @JsonView(View.SHOW.class)
    private String password;

    @JsonView(View.HIDDEN.class)
    private Date createdAt;
}
