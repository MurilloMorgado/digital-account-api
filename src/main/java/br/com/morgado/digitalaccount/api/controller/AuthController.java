package br.com.morgado.digitalaccount.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.morgado.digitalaccount.api.config.security.JwtUtil;
import br.com.morgado.digitalaccount.api.dto.request.LoginRequest;
import br.com.morgado.digitalaccount.api.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        String token = jwtUtil.generateToken(request.getUserName());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
