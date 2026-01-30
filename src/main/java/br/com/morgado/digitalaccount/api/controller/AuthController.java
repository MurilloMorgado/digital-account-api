package br.com.morgado.digitalaccount.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.morgado.digitalaccount.api.config.security.TokenManager;
import br.com.morgado.digitalaccount.api.config.security.TokenUpdateRecord;
import br.com.morgado.digitalaccount.api.domain.authentication.TokenService;
import br.com.morgado.digitalaccount.api.domain.model.UserModel;
import br.com.morgado.digitalaccount.api.dto.request.LoginRequest;
import br.com.morgado.digitalaccount.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    
    @PostMapping("/login")
    public ResponseEntity<TokenManager> login(@RequestBody LoginRequest request) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String tokenAcess = tokenService.generateToken((UserModel)authentication.getPrincipal());
        String refreshToken = tokenService.generateRefreshToken((UserModel)authentication.getPrincipal());

        return ResponseEntity.ok(new TokenManager(tokenAcess, refreshToken));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenManager> refreshToken(@RequestBody TokenUpdateRecord token){

        var refreshToken = token.refreshToken();
        Long idUser = Long.valueOf(tokenService.verifyToken(refreshToken));
        UserModel user = userRepository.findById(idUser).orElseThrow();

        String tokenAcess = tokenService.generateToken(user);
        String updateToken = tokenService.generateRefreshToken(user);

        return ResponseEntity.ok(new TokenManager(tokenAcess, updateToken));
    }
}
