package com.example.handy.services.servicesImpl;

import com.example.handy.adapters.cloudinary.CloudinaryAdapter;
import com.example.handy.dtos.UserInfoDto;
import com.example.handy.exceptions.EmptyFillInfoException;
import com.example.handy.model.User;
import com.example.handy.model.UserInfo;
import com.example.handy.repositories.UserInfoRepository;
import com.example.handy.repositories.UserRepository;
import com.example.handy.services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;
    private final CloudinaryAdapter cloudinaryAdapter;


    @Override
    public void fillInfo(UserInfoDto userInfoDto, Integer id) throws Exception {
        if (userInfoDto.getName().equals("") || userInfoDto.getSurname().equals("") || userInfoDto.getCity().equals("") ||
                userInfoDto.getNumber().equals("")) throw new EmptyFillInfoException();


        UserInfo userInfo1 = new UserInfo();
        userInfo1.setName(userInfoDto.getName());
        userInfo1.setSurname(userInfoDto.getSurname());
        userInfo1.setCity(userInfoDto.getCity());
        userInfo1.setPhoneNumber(userInfoDto.getNumber());
        User user = userRepository.getById(id);


        MultipartFile multipartFile = userInfoDto.getFile();
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        Map<String, String> uploadImage = cloudinaryAdapter.uploadImageToCloudinary(file);
        file.delete();


        String url = uploadImage.get("url");

        userInfo1.setProfilePhoto(url);
        userInfo1.setUser(user);

        userInfoRepository.save(userInfo1);
    }

    @Override
    public void updateUser(UserInfoDto userInfoDto, Integer id) throws Exception {
        if (userInfoDto.getName().equals("") || userInfoDto.getSurname().equals("") || userInfoDto.getCity().equals("") ||
                userInfoDto.getNumber().equals("")) throw new EmptyFillInfoException();


        UserInfo userInfo1 = userInfoRepository.getById(userRepository.getById(id).getUserInfo().getId());
        userInfo1.setName(userInfoDto.getName());
        userInfo1.setSurname(userInfoDto.getSurname());
        userInfo1.setCity(userInfoDto.getCity());
        userInfo1.setPhoneNumber(userInfoDto.getNumber());


        MultipartFile multipartFile = userInfoDto.getFile();
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        Map<String, String> uploadImage = cloudinaryAdapter.uploadImageToCloudinary(file);
        file.delete();


        String url = uploadImage.get("url");

        userInfo1.setProfilePhoto(url);

        userInfoRepository.save(userInfo1);

    }

    @Override
    public UserInfo viewUser(Integer id) {
        User user = userRepository.getById(id);
        return userInfoRepository.findByUser(user);
    }
}
