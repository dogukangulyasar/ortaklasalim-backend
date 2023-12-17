package com.dtech.Ortaklasalim.controller;

import com.dtech.Ortaklasalim.model.Post;
import com.dtech.Ortaklasalim.service.PostRepository;
import com.dtech.Ortaklasalim.model.User;
import com.dtech.Ortaklasalim.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path="/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all posts
    @GetMapping("/")
    public @ResponseBody Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get Owner Of Post
    @GetMapping("/{post_id}/owner")
    public @ResponseBody User getOwnerOfPost(@PathVariable Integer post_id) {
        Optional<Post> post = postRepository.findById(post_id);
        if(!post.isEmpty()) {
            Optional<User> user =userRepository.findById(post.get().getOwner_id());
            if(!user.isEmpty()) {
                return user.get();
            } else {
                return null;
            }
        }
        return null;
    }

    // Get specific post
    @GetMapping("/{post_id}")
    public @ResponseBody Post getSpecificPost(@PathVariable Integer post_id) {
        Optional<Post> post = postRepository.findById(post_id);

        if(!post.isEmpty()) {
            return post.get();
        }

        return null;
    }

    // Update post
    @PutMapping("/update/{post_id}")
    public @ResponseBody HttpStatus updatePost(@RequestBody Post post) {
        Optional<Post> current_post = postRepository.findById(post.getId());

        if(!current_post.isEmpty()) {
            current_post.get().setTitle(post.getTitle());
            current_post.get().setDescription(post.getDescription());
            current_post.get().setPrice(post.getPrice());
            current_post.get().setCommission(post.getCommission());
            current_post.get().setKilometer(post.getKilometer());
            current_post.get().setBrand(post.getBrand());
            current_post.get().setYear(post.getYear());
            current_post.get().setRecord(post.getRecord());
            current_post.get().setPost_date(post.getPost_date());

            try {
                postRepository.save(current_post.get());
            } catch (Exception e) {
                return HttpStatus.BAD_REQUEST;
            }

        } else {
            return HttpStatus.NO_CONTENT;
        }

        return HttpStatus.OK;


    }

    // Add post, json format without date & id
    @PostMapping("/add")
    public @ResponseBody HttpStatus addPost(@RequestBody Post post) {
        System.out.println(post);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        post.setPost_date(formatter.format(date));

        try {
            postRepository.save(post);
        }catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.OK;
    }
}
