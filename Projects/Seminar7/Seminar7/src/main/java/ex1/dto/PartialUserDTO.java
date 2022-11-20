package ex1.dto;

import javax.validation.constraints.NotNull;

import ex1.validator.OnlyLetters;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PartialUserDTO {

    @NotNull
    @OnlyLetters
    private String firstname;

    @NotNull
    @OnlyLetters
    private String lastname;

}
