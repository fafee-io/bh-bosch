package hu.bosch.bomple.auth.model;

import lombok.Data;

@Data
public class UserEntity {

    private Long id;

    private String username;
    private String password;

}
