package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.PostDTO;
import org.example.model.Post;
import org.example.service.PostDTOService;
import org.example.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private static final Logger myLogger = LogManager.getLogger(PostController.class);

    private final PostService myService;

    private final PostDTOService myDTOService;

    @GetMapping
    public List<PostDTO> all() {
        return myService.all().stream().map(myDTOService::convertPostToPostDTO).toList();
    }

    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable long id) {
        return myDTOService.convertPostToPostDTO(myService.getById(id));
    }

    @PostMapping
    public PostDTO save(@RequestBody PostDTO post) {
        return myDTOService.convertPostToPostDTO(
                myService.save(
                        myDTOService.convertPostDTOToPost(post)
                )
        );
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        myService.removeById(id);
    }

    @GetMapping("/admin")
    public List<Post> allAdmin() {
        return myService.all();
    }
}