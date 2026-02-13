package br.com.morgado.digitalaccount.api.service.implementacao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.morgado.digitalaccount.api.domain.model.UserModel;
import br.com.morgado.digitalaccount.api.dto.request.UserRequest;
import br.com.morgado.digitalaccount.api.dto.response.UserResponse;
import br.com.morgado.digitalaccount.api.exception.AlreadyExistsException;
import br.com.morgado.digitalaccount.api.exception.DatabaseException;
import br.com.morgado.digitalaccount.api.exception.ResourceNotFoundException;
import br.com.morgado.digitalaccount.api.repository.UserRepository;
import br.com.morgado.digitalaccount.api.service.UserService;
import br.com.morgado.digitalaccount.api.service.utils.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public List<UserResponse> findAllUsers() {

        try {
            List<UserResponse> users = userRepository.findAll().stream().map(UserModel::toResponse)
                    .collect(Collectors.toList());

            if (users.isEmpty()) {
                throw new ResourceNotFoundException("No accounts found");
            }

            return users;

        } catch (DatabaseException e) {
            throw new DatabaseException("Error accessing the database.", e);
        }

    }

    @Override
    public UserResponse findUserById(Long idUser) {

        try {
            UserModel userDB = userRepository.findById(idUser)
                    .orElseThrow(() -> new ResourceNotFoundException("No user found by ID: " + idUser));

            return userDB.toResponse();

        } catch (DataAccessException e) {
            throw new DatabaseException("Error accessing the database.", e);
        }

    }

    
    @Transactional
    @Override
    public Long createUser(UserRequest userRequest) {

        String email = userRequest.getEmail();
        Optional<UserModel> userDB = userRepository.findByEmailIgnoreCaseAndVerifiedEmailTrue(email);

        if (userDB.isPresent()) {
            throw new AlreadyExistsException("User already exists for the given email: " + email);
        }

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserModel user = new UserModel(userRequest);

        emailService.sendVerificationEmail(user);
        return userRepository.save(user).getId();

    }

    @Override
    public void updateUserDetails(Long idUser, UserRequest userRequest) {

        UserModel userDB = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("No user found by ID: " + idUser));

        UserModel userModel = new UserModel(userRequest);

        BeanUtils.copyProperties(userDB, userModel, "idAccount");

        userRepository.save(userDB);

    }

    @Override
    public void deleteUser(Long idUser) {

        findUserById(idUser);

        userRepository.deleteById(idUser);

    }

    @Transactional
    @Override
    public void verifyEmail(String code) {
        
        UserModel userModel = userRepository.findByToken(code).orElseThrow();
        userModel.verify();
    }

}
