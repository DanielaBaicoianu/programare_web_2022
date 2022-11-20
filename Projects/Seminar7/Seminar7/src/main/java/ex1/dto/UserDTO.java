package ex1.dto;

import javax.validation.constraints.NotNull;

import ex1.validator.OnlyLetters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "Firstname should not be null")
    @OnlyLetters()
    private String firstname;

    @NotNull(message = "Lastname should not be null")
    @OnlyLetters()
    private String lastname;

    @NotNull(message = "Email should not be null")
    private String email;

    @NotNull(message = "User information should not be null")
    private String info;

}
