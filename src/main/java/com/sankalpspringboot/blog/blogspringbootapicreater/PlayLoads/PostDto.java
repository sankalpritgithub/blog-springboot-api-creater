package com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Category;
import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Comment;
import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    
    private Integer postId; 
    
    private String title;

    private String content;

    private String imageName;
  
    private Date addedDate;


    // yaha per recursion ho rha h kyo me category me fir se post mil ja rha to isko thick karege 
   // private Category category;
   
    private CategoryDto category;

    private UserDto user;
    
   private Set<CommentDto> comments = new HashSet<>();
}
