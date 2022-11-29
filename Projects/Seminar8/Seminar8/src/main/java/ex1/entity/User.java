package ex1.entity;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class User {

    private Long id;
    private String username;
    private String fullName;
    private String userType;
    private UserDetails userDetails;

}
