package dev.decagon.blog.service;

import dev.decagon.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long PostId, CommentDto commentDto);

    List<CommentDto> getAllCommentsByPostId(Long post_id);

    CommentDto getCommentById(Long post_id, Long comment_id);

    CommentDto updateComment(Long post_id, Long comment_id, CommentDto commentDto);

    void deleteComment(Long post_id, Long comment_id);
}
