package ex1.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class UserDetails {

    private Long id;
    private String cnp;
    private Integer age;
    private String otherInformation;

}
