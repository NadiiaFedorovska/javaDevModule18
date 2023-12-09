package com.example.JavaDevModule18.security;

import com.example.JavaDevModule18.security.register.RegisterUserRequest;
import com.example.JavaDevModule18.security.register.RegisterUserResponse;
import com.example.JavaDevModule18.security.register.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SecurityController {
    private final RegisterUserService registerUserService;

    @PostMapping("/note/user/register")
    public RegisterUserResponse register(@RequestBody RegisterUserRequest request) {
        return registerUserService.register(request);
    }
}
