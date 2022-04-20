package com.example.handy.services.servicesImpl;

import com.example.handy.adapters.cloudinary.CloudinaryAdapter;
import com.example.handy.dtos.PostDto;
import com.example.handy.model.Post;
import com.example.handy.model.WishList;
import com.example.handy.repositories.PostRepository;
import com.example.handy.repositories.WishedRepository;
import com.example.handy.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CloudinaryAdapter cloudinaryAdapter;
    private final WishedRepository wishedRepository;


    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post addPost(PostDto postDto) throws Exception {
        Post post = new Post();
        post.setCategory(postDto.getCategory());
        post.setCity(post.getCity());
        LocalDate date = LocalDate.parse(postDto.getExpiryDate());
        post.setExpiryDate(date);
        post.setName(postDto.getName());


        MultipartFile multipartFile = postDto.getImage();
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        Map<String, String> uploadImage = cloudinaryAdapter.uploadImageToCloudinary(file);

        String url = uploadImage.get("url");

        post.setImageUrl(url);
        return this.postRepository.save(post);
    }

    @Override
    public void deletePost(Integer id) {
        this.postRepository.deleteById(id);
    }

    @Override
    public PostDto updatePost(Integer id, PostDto postDto) {
        return null;
    }

    @Override
    public Page<Post> findPage(Integer currentPage) {
        Pageable pageable = PageRequest.of(1, 10);
        return postRepository.findAll(pageable);
    }


    @Override
    public void addWishList(PostDto postDto) {
        Post post = postRepository.getById(postDto.getId());
        WishList wishList = new WishList();
        wishList.setPost(post);
        wishedRepository.save(wishList);
    }

    @Override
    public Post getById(Integer id) {
        return postRepository.getById(id);
    }
}
