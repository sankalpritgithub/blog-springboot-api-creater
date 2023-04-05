package com.sankalpspringboot.blog.blogspringbootapicreater.Services;

import java.util.List;

import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Post;
import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.PostDto;
import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.PostResponse;

public interface PostService {
    
    //create posdt 

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
   
    //update 

    PostDto updatePost(PostDto postDto, Integer postId );

   //delete 
    void  deletePost(Integer postId);
    
    // get all posts
    // yaha per hum pagination ke leye change karege 

   // List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
   PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy, String sortDir);

    // get a single post 
     PostDto  getPostById(Integer postId);

     //get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

   // get all posts by User 
   List<PostDto> getPostsByUser(Integer userId);
    
   // search Posts
   List<PostDto> searchPosts(String keyword);



    
}
