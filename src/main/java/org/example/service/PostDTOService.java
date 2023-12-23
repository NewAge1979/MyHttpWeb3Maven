package org.example.service;

import org.example.dto.PostDTO;
import org.example.model.Post;

public interface PostDTOService {
    PostDTO convertPostToPostDTO(Post post);
    Post convertPostDTOToPost(PostDTO post);
}
