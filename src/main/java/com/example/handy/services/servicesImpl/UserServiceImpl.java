package com.example.handy.services.servicesImpl;

import com.example.handy.dtos.UserDto;
import com.example.handy.exceptions.UserAlreadyExistException;
import com.example.handy.exceptions.WrongPasswordException;
import com.example.handy.model.User;
import com.example.handy.repositories.UserRepository;
import com.example.handy.services.RoleService;
import com.example.handy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public User register(UserDto userDto) throws UserAlreadyExistException {
        User user;
        if (this.userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            user = this.userRepository.findByEmail(userDto.getEmail()).get();

            if (user != null) {
                throw new UserAlreadyExistException("emailnotunique");
            }
        }
        User user1 = new User();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user1.setEmail(userDto.getEmail());
        user1.setPassword(encodedPassword);

        roleService.addRoleToUser(user1, "USER");
        return this.userRepository.save(user1);
    }

    public void login(String email, String password) throws WrongPasswordException {
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
            if (user.getPassword().equals(password)) {
                throw new WrongPasswordException();
            }
        }
    }

    @Override
    public Optional<User> findUserForLogin(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getById(Integer id) {
        return userRepository.getById(id);
    }
}
