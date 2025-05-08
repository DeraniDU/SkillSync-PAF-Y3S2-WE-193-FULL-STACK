// src/main/java/com/example/post_creating/dto/PostDto.java
package com.example.post_creating.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String caption;
    private String prerequisites;
    private String tags;
    private String skills;
}
