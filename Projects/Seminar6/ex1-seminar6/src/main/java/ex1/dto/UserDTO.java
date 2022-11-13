package ex1.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull(message = "User username should not be null!")
    @NotBlank
    @NotEmpty
    private String name;

    @NotNull(message = "Email should not be null")
    @NotBlank
    @NotEmpty
    private String email;

    @NotNull(message = "Information should not be null")
    @Size(min = 5, max = 20, message = "Information about the user should be between 5 and 20")
    private String info;

}
