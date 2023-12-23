package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDTO {
    private long id;
    private String content;
}
