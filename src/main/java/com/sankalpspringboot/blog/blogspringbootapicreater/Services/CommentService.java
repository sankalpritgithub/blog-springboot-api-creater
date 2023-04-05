package com.sankalpspringboot.blog.blogspringbootapicreater.Services;

import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.CommentDto;

public interface CommentService {
      
    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
