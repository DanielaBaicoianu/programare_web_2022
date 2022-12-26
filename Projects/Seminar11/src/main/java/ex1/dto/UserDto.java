package ex1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String username;
    private String fullName;
    private String userType;
    private String cnp;
    private Integer age;
    private String otherInformation;

}
