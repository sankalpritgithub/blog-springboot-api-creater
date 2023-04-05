package com.sankalpspringboot.blog.blogspringbootapicreater.Imples;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Category;
import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Post;
import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.User;
import com.sankalpspringboot.blog.blogspringbootapicreater.Exception.ResourceNotFoundException;
import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.PostDto;
import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.PostResponse;
import com.sankalpspringboot.blog.blogspringbootapicreater.Repositories.CategoryRepo;
import com.sankalpspringboot.blog.blogspringbootapicreater.Repositories.PostRepo;
import com.sankalpspringboot.blog.blogspringbootapicreater.Repositories.UserRepo;
import com.sankalpspringboot.blog.blogspringbootapicreater.Services.PostService;

@Service 
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
   
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        // TODO Auto-generated method stub
      
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id", userId));
        
       Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category id", categoryId));

     Post post = this.modelMapper.map(postDto, Post.class);
     post.setImageName("default.png");
     post.setAddedDate(new Date());
     post.setUser(user);
     post.setCategory(category);
    // post.setCategory(categoryRepo);
    

     Post newPost = this.postRepo.save(post);

     return this.modelMapper.map(newPost,PostDto.class);
        
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
         

       post.setTitle(postDto.getTitle());
       post.setContent(postDto.getContent());
       post.setImageName(postDto.getImageName());

       Post updatedPost = this.postRepo.save(post);
       return this.modelMapper.map(updatedPost, PostDto.class);

    }

    @Override
    public void deletePost(Integer postId) {
       
      Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id", postId));
      
        this.postRepo.delete(post);
    }

    @Override
   // public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {

    //6:46:28
   // 06:30:20  pagination work karege 
     //   List<Post> allPost = this.postRepo.findAll();
    // List<Post> allPosts = this.postRepo.findAll();
    // List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post , PostDto.class)).collect(Collectors.toList());
    //return postDtos;
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy,String sortDir) {
        
   // create a pageable object 
    Sort sort=null;
    if(sortDir.equalsIgnoreCase("asc")){
       sort=Sort.by(sortBy).ascending();
    }else{
        sort=Sort.by(sortBy).descending();
    }

     Pageable p = PageRequest.of(pageNumber, pageSize, sort);

    Page<Post> pagePost = this.postRepo.findAll(p);
      //get content se hame mil jayega listofPost
       List<Post> allPosts = pagePost.getContent();
       List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post , PostDto.class)).collect(Collectors.toList());
       
       PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
       return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {

     Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
        // TODO Auto-generated method stub
       return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        // yaha per actual category de rhe h 
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
        // yaha per travers karege List of post or convert karege post to postDto
      List<PostDto> postDtos =  posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

       return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        // TODO Auto-generated method stub
      User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id", userId));
       List<Post> posts = this.postRepo.findByUser(user);  
        
     List<PostDto> postDtos =   posts.stream().map((post) ->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
       
       return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        // TODO Auto-generated method stub  

      List<Post> posts =  this.postRepo.findByTitleContaining(keyword);
      // we have to change post to postDto 
   List<PostDto>  postDto = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

    return postDto;
       
    }
    
}
