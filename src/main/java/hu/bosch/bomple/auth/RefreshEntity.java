package hu.bosch.bomple.auth;

import lombok.Data;

import java.time.Instant;

@Data
public class RefreshEntity {

    private Long userId;
    private String token;
    private String generation;
    private Instant created; // vagy expiration

}
