package com.example.handy.services.servicesImpl;

import com.example.handy.dtos.UserDto;
import com.example.handy.exceptions.UserAlreadyExistException;
import com.example.handy.model.User;
import com.example.handy.repositories.UserRepository;
import com.example.handy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User register(UserDto userDto) throws UserAlreadyExistException {
        User user = this.userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            throw new UserAlreadyExistException("Email has been taken");
        }
        user.setUserId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return this.userRepository.save(user);
    }
}
