package api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String login;
    private String id;
    private String name;
    private String type;

    public UserDTO(String login) {
        this.login = login;
    }

    public UserDTO(String login, String name) {
        this.login = login;
        this.name = name;
    }
}
