package ex1.entity;

import java.text.DateFormat;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private String username;
    private String email;
    private String additionalInfo;


}
