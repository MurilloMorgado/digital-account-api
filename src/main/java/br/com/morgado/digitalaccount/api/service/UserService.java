package br.com.morgado.digitalaccount.api.service;

import java.util.List;

import br.com.morgado.digitalaccount.api.dto.request.UserRequest;
import br.com.morgado.digitalaccount.api.dto.response.UserResponse;

public interface UserService {

    List<UserResponse> findAllUsers();

    UserResponse findUserById(Long idUser);

    Long createUser(UserRequest userRequest);

    void updateUserDetails(Long idUser, UserRequest userRequest);

    void deleteUser(Long idUser);

    void verifyEmail(String code);
}
