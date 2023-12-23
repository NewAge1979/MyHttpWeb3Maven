package org.example.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryImpl implements PostRepository{
    private static final Logger myLogger = LogManager.getLogger(PostRepositoryImpl.class);
    private final ConcurrentHashMap<Long, Post> allPosts = new ConcurrentHashMap<>();
    private final static AtomicLong lastId = new AtomicLong(0);

    public List<Post> all() {
        return allPosts.values().stream().filter(x->!x.isRemoved()).toList();
    }

    public Optional<Post> getById(long id) {
        return allPosts.values().stream()
                        .filter(x->!x.isRemoved())
                        .filter(x->x.getId() == id)
                        .findFirst();
    }

    public Post save(Post post) {
        var id = post.getId();
        if (id == 0 || getById(id).isPresent()) {
            if (id == 0) {
                id = lastId.incrementAndGet();
                post.setId(id);
                post.setRemoved(false);
            }
            allPosts.put(id, post);
            myLogger.info(String.format("Add/Edit post: %d", id));
        } else {
            throw new NotFoundException();
        }
        return post;
    }

    public void removeById(long id) {
        if (getById(id).isPresent()) {
            allPosts.get(id).setRemoved(true);
            myLogger.info(String.format("Delete post: %d", id));
        } else {
            throw new NotFoundException();
        }
    }
}