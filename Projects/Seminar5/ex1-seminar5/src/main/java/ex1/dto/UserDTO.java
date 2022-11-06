package ex1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO { //DTO (DataTransferObject) -> an object that encapsulates data. The most common usage is to
                        // transfer data between the app and UI

    private String username;
    private String email;
    private String accountInformation;

}
