package ex1.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private String firstname;
    private String lastname;
    private String email;
    private String information;

}
