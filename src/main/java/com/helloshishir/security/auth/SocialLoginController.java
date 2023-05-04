package com.helloshishir.security.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SocialLoginController {

    @GetMapping("/socialLogin")
    public String googleLogin() {
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        return "success";
    }
}
