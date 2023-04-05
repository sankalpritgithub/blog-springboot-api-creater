package com.sankalpspringboot.blog.blogspringbootapicreater.Imples;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Comment;
import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Post;
import com.sankalpspringboot.blog.blogspringbootapicreater.Exception.ResourceNotFoundException;
import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.CommentDto;
import com.sankalpspringboot.blog.blogspringbootapicreater.Repositories.CommentRepo;
import com.sankalpspringboot.blog.blogspringbootapicreater.Repositories.PostRepo;
import com.sankalpspringboot.blog.blogspringbootapicreater.Services.CommentService;

@Service
public class CommentServiceImp implements CommentService{
   
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
      Comment comment =  this.modelMapper.map(commentDto, Comment.class);
           comment.setPost(post);
      Comment savedComment  = this.commentRepo.save(comment);
       return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
       
        Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment id", commentId));
      this.commentRepo.delete(com);
    }
    
}
