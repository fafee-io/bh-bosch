package hu.bosch.bomple.auth;

import lombok.Data;

@Data
public class UserEntity {

    private Long id;

    private String username;
    private String password;

}
