package hu.bosch.bomple.auth.model;

import lombok.Data;

import java.time.Instant;

@Data
public class RefreshEntity {

    private String _id;

    private Long userId;
    private String token;
    private String generation;
    private Instant created; // vagy expiration
    private String sensitiveInformation;

}
