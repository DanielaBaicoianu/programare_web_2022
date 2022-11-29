package ex1.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {

    private Long id;
    private String username;
    private String fullName;
    private String cnp;
    private Integer age;
    private String userType;
    private String otherInformation;


}
