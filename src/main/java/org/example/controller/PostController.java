package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Post;
import org.example.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private static final Logger myLogger = LogManager.getLogger(PostController.class);

    private final PostService myService;

    @GetMapping
    public List<Post> all() {
        return myService.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) {
        return myService.getById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return myService.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        myService.removeById(id);
    }
}