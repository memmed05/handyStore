package com.example.handy.services.servicesImpl;

import com.example.handy.configs.Security.token.ConfirmationToken;
import com.example.handy.configs.Security.token.ConfirmationTokenService;
import com.example.handy.dtos.UserDto;
import com.example.handy.exceptions.UserAlreadyExistException;
import com.example.handy.model.User;
import com.example.handy.repositories.UserRepository;
import com.example.handy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    @Override
    public User register(UserDto userDto) throws UserAlreadyExistException {
        User user = this.userRepository.findByEmail(userDto.getEmail());
        if (user!=null) {
            throw new UserAlreadyExistException("emailnotunique");
        }
        User user1=new User();
        String encodedPassword=bCryptPasswordEncoder.encode(userDto.getPassword());
        user1.setEmail(userDto.getEmail());
        user1.setPassword(encodedPassword);

        String token= UUID.randomUUID().toString();
        ConfirmationToken confirmationToken=new ConfirmationToken(token);

        confirmationTokenService.save(confirmationToken);
        confirmationToken.setUser(user1);

        return this.userRepository.save(user1);
    }
}
