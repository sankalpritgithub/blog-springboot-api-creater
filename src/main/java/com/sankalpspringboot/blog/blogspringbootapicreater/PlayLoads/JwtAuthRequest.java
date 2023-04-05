package com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads;

import lombok.Data;

@Data
public class JwtAuthRequest {
    
    private String username;

    private String password;
}
