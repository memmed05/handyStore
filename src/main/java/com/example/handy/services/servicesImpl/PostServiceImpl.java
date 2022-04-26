package com.example.handy.services.servicesImpl;

import com.example.handy.adapters.cloudinary.CloudinaryAdapter;
import com.example.handy.dtos.PostDto;
import com.example.handy.model.Post;
import com.example.handy.model.User;
import com.example.handy.model.WishList;
import com.example.handy.repositories.PostRepository;
import com.example.handy.repositories.UserRepository;
import com.example.handy.repositories.WishedRepository;
import com.example.handy.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CloudinaryAdapter cloudinaryAdapter;
    private final WishedRepository wishedRepository;
    private final UserRepository userRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post addPost(PostDto postDto, Optional<User> user) throws Exception {
        Post post = new Post();
        post.setCategory(postDto.getCategory());
        post.setCity(post.getCity());
        LocalDate date = LocalDate.parse(postDto.getExpiryDate());
        post.setExpiryDate(date);
        post.setName(postDto.getName());
        user.ifPresent(post::setUser);
        MultipartFile multipartFile = postDto.getImage();
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        Map<String, String> uploadImage = cloudinaryAdapter.uploadImageToCloudinary(file);
        file.delete();


        String url = uploadImage.get("url");

        post.setImageUrl(url);
        return this.postRepository.save(post);
    }

    @Override
    public void deletePost(Integer id) {
        this.postRepository.deleteById(id);
    }

    @Override
    public Post updatePost(Integer id, PostDto postDto) throws Exception {
        Post post = postRepository.getById(id);
        post.setName(postDto.getName());
        post.setCity(postDto.getCity());
        post.setCategory(postDto.getCategory());
        post.setExpiryDate(LocalDate.parse(postDto.getExpiryDate()));

        MultipartFile multipartFileupdate = postDto.getImage();

        File file = new File(multipartFileupdate.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFileupdate.getBytes());
        fos.close();

        Map<String, String> uploadImage = cloudinaryAdapter.uploadImageToCloudinary(file);
        file.delete();


        String url = uploadImage.get("url");

        post.setImageUrl(url);
        return this.postRepository.save(post);
    }

    @Override
    public Page<Post> findPage(Integer currentPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, 10);
        return postRepository.findAll(pageable);
    }


    @Override
    public WishList addWishList(Integer id, Integer userId) {
        Post post = postRepository.getById(id);
        User user = userRepository.getById(userId);
        WishList wishList = new WishList();
        wishList.setPost(post);
        wishList.setUser(user);
        return wishedRepository.save(wishList);
    }

    @Override
    public List<WishList> getAllWished() {

        return wishedRepository.findAll();
    }

    @Override
    public Post getById(Integer id) {
        return postRepository.getById(id);
    }

    @Override
    public List<Post> getUsersPosts(Integer id) {
        User user = userRepository.getById(id);
        return postRepository.findByUser(user);
    }
}
