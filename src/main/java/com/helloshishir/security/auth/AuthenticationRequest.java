package com.helloshishir.security.auth;


import lombok.*;

@Setter
@Getter
@ToString
public class AuthenticationRequest {
    String email;
    String password;
}
