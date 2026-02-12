package br.com.morgado.digitalaccount.api.domain.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.morgado.digitalaccount.api.dto.request.UserRequest;
import br.com.morgado.digitalaccount.api.dto.response.UserResponse;
import br.com.morgado.digitalaccount.api.exception.BusinessRuleException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "VERIFIED_EMAIL")
    private boolean verifiedEmail;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "TOKEN_EXPIRATION")
    private LocalDateTime tokenExpiration;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserResponse toResponse() {
        return new UserResponse(
                id,
                fullName,
                userName,
                email,
                password,
                verifiedEmail
            );
    }

    public UserModel(UserRequest userRequest) {

        this.fullName = userRequest.getFullName();
        this.userName = userRequest.getFullName();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
        this.verifiedEmail = false;
        this.token = UUID.randomUUID().toString();
        this.tokenExpiration = LocalDateTime.now().plusMinutes(30);
    }

    public void verify(){

        if(tokenExpiration.isBefore(LocalDateTime.now())){
            throw new BusinessRuleException("Verification link has expired");
        }

        this.verifiedEmail = true;
        this.token = null;
        this.tokenExpiration = null;
    }
}
