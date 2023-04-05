package com.sankalpspringboot.blog.blogspringbootapicreater.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Category;
import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Post;
import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.User;

import jakarta.validation.constraints.PastOrPresent;

public interface PostRepo extends JpaRepository<Post , Integer> {
    

    //kisi user ke sare post chahiye to hum method create kar sakte h 
    List<Post> findByUser(User user);
    
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String  title);
}
