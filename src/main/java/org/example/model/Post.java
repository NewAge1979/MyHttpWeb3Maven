package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private long id;
    private String content;
    @Builder.Default
    private boolean removed = false;
}