package br.com.morgado.digitalaccount.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.morgado.digitalaccount.api.dto.request.UserRequest;
import br.com.morgado.digitalaccount.api.dto.response.UserResponse;
import br.com.morgado.digitalaccount.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<UserResponse> accounts = userService.findAllUsers();
        return ResponseEntity.ok().body(accounts);
    }

    @GetMapping("/{idUser}/user")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long idUser) {
        UserResponse account = userService.findUserById(idUser);
        return ResponseEntity.ok().body(account);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createUser(@RequestBody @Valid UserRequest UserRequest) {
        Long idAccount = userService.createUser(UserRequest);
        return ResponseEntity.ok().body(idAccount);
    }

    @PutMapping("/{idUser}/account")
    public ResponseEntity<Void> updateUserDetails(@PathVariable Long idUser,
            @RequestBody @Valid UserRequest UserRequest) {
        userService.updateUserDetails(idUser, UserRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long idUser) {
        userService.deleteUser(idUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verify-account")
    public ResponseEntity<String> verifyEmail(@RequestParam String code){
        userService.verifyEmail(code);
        return ResponseEntity.ok().body(null);
    }
}
