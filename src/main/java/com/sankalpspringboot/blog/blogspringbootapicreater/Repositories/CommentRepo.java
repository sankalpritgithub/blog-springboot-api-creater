package com.sankalpspringboot.blog.blogspringbootapicreater.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{
    
}
