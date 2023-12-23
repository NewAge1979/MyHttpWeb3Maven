package org.example.service;

import org.example.dto.PostDTO;
import org.example.model.Post;
import org.springframework.stereotype.Service;

@Service
public class PostDTOServiceImpl implements PostDTOService {
    @Override
    public PostDTO convertPostToPostDTO(Post post) {
        return PostDTO.builder().id(post.getId()).content(post.getContent()).build();
    }

    @Override
    public Post convertPostDTOToPost(PostDTO post) {
        return Post.builder().id(post.getId()).content(post.getContent()).build();
    }
}
