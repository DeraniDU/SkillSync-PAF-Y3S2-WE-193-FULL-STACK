// src/main/java/com/example/post_creating/model/Post.java
package com.example.post_creating.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String caption;
    private String prerequisites;
    private String tags;
    private String skills;
}
