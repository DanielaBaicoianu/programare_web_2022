package ex1.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String fullName;
    private String userType;
    private String cnp;
    private Integer age;
    private String otherInformation;

}
