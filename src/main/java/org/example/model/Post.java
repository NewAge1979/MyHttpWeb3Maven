package org.example.model;

import lombok.Data;

@Data
public class Post {
    private long id;
    private String content;

    public Post() {
    }

    public Post(long id, String content) {
        this.id = id;
        this.content = content;
    }

}