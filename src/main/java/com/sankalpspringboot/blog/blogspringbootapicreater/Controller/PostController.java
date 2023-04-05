package com.sankalpspringboot.blog.blogspringbootapicreater.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.coyote.http11.HttpOutputBuffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.DeleteExchange;

import com.sankalpspringboot.blog.blogspringbootapicreater.Config.AppConstant;
import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Post;
import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.ApiResponse;
import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.PostDto;
import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.PostResponse;
import com.sankalpspringboot.blog.blogspringbootapicreater.Services.FileService;
import com.sankalpspringboot.blog.blogspringbootapicreater.Services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
     
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    
    @Value("${project.image}")
    private String path;

    // create 
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
        @RequestBody PostDto postDto,
        @PathVariable Integer userId, 
        @PathVariable Integer categoryId
        ){

       PostDto  createPost = this.postService.createPost(postDto, userId, categoryId);
      return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);

    }

    //get by user 
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(
        @PathVariable Integer userId
    ){
     List<PostDto> posts = this.postService.getPostsByUser(userId);
    return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

   // get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(
        @PathVariable Integer categoryId
    ){
     List<PostDto> posts = this.postService.getPostsByCategory(categoryId);   
    return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //get all posts 
   @GetMapping("/posts")
   public ResponseEntity<PostResponse> getAllPost(
    @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, //page number select nhi kiya to by default ye rahega or required nhi h 
    @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
    @RequestParam(value ="sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
    @RequestParam(value ="sortDir", defaultValue = AppConstant.SORT_DIR,required = false) String sortDir)
  {
     
    PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
     return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);

   }


   @GetMapping("/posts/{postId}")
   public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
     
    PostDto postDto = this.postService.getPostById(postId);
     return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);

   }

   //delete post 
   @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is Successfully Deleted !!",true); 

    }

  //update post 
  @PutMapping("/posts/{postId}")
  public ResponseEntity<PostDto> updatePost(@RequestBody PostDto  posrDto, @PathVariable Integer postId){
      
    PostDto updatePost = this.postService.updatePost(posrDto, postId);
    return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

  }

  //Page Shoring 6:30:01


  //7:14:20
   // search method
   @GetMapping("/posts/serach/{keywords}")
   public ResponseEntity<List<PostDto>> serachPostByTitle(
       @PathVariable("keywords") String keywords
   ){
        
   List<PostDto> result =  this.postService.searchPosts(keywords);
     return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
   }  


    // Good pratise in our project 7:26:01
    // avoid hardcorded Value and create a new contact and then constant use in our code
   //----------------------
   
   //post Image upload 
  @PostMapping("/post/image/upload/{postId}")// kis post ki images ko hum upload kar rhe h
   public ResponseEntity<PostDto> uploadPostImage(
    @RequestParam("image") MultipartFile image,
    @PathVariable Integer postId) throws IOException  {
      PostDto postDto = this.postService.getPostById(postId);
      String fileName = this.fileService.uploadImage(path, image);
   
      postDto.setImageName(fileName);
     PostDto updatePost = this.postService.updatePost(postDto, postId);

     return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);

   }
/* 
  @GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
  public void downloadImage(
     @PathVariable("imageName") String imageName,
     HttpServletResponse response
  )throws IOException{
    InputStream resource = this.fileService.getResource(path, imageName);
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    //StreamUtils.setContentType(resource,response.getOutputStream());
    Object streamUtils = StreamUtils;(resource,response.getOutputStream());
  }
   */

   

  } 
