package dev.decagon.blog.repository;

import dev.decagon.blog.entity.Comment;
import dev.decagon.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);
}
