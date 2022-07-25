package com.ey.test.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponseDTO {
    private String id;
    private LocalDateTime created;
    private LocalDateTime modified;
    @JsonProperty(namespace = "last_login")
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
}
