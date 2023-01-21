package com.helloshishir.security.auth;

import lombok.*;

@Setter
@Getter
@ToString
public class AuthenticationResponse {
    public AuthenticationResponse(String token) {
        this.token = token;
    }

    private String token;
}
