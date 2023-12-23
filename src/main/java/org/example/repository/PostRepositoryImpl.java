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
        return allPosts.values().stream().toList();
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(allPosts.get(id));
    }

    public Post save(Post post) {
        var id = post.getId();
        if (id == 0 || allPosts.get(id) != null) {
            if (id == 0) {
                id = lastId.incrementAndGet();
                post.setId(id);
            }
            allPosts.put(id, post);
            myLogger.info(String.format("Add/Edit post: %d", id));
        } else {
            throw new NotFoundException();
        }
        return post;
    }

    public void removeById(long id) {
        if (allPosts.get(id) != null) {
            allPosts.remove(id);
            myLogger.info(String.format("Delete post: %d", id));
        } else {
            throw new NotFoundException();
        }
    }
}