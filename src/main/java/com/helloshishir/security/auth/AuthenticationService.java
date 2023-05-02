package com.helloshishir.security.auth;

import com.helloshishir.security.config.JwtService;
import com.helloshishir.security.user.Role;
import com.helloshishir.security.user.User;
import com.helloshishir.security.user.UserRepository;
import com.helloshishir.security.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        User user = userService.findByEmail(request.getEmail());
        if( user !=null) {
            String jwtToken = jwtService.generateToken(user);
            return new AuthenticationResponse(jwtToken);
        } else {
            user = new User();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.USER);
            userService.save(user);
            String jwtToken = jwtService.generateToken(user);
            return new AuthenticationResponse(jwtToken);
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userService.findByEmail(request.getEmail());

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
