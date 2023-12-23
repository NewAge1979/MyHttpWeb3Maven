package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.example.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository myRepository;

    public List<Post> all() {
        return myRepository.all();
    }

    public Post getById(long id) {
        return myRepository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) throws NotFoundException {
        return myRepository.save(post);
    }

    public void removeById(long id) throws NotFoundException {
        myRepository.removeById(id);
    }
}