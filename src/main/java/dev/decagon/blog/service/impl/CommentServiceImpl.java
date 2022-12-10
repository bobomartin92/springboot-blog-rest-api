package dev.decagon.blog.service.impl;

import dev.decagon.blog.entity.Comment;
import dev.decagon.blog.entity.Post;
import dev.decagon.blog.exception.BlogAPIException;
import dev.decagon.blog.exception.ResourceNotFoundException;
import dev.decagon.blog.payload.CommentDto;
import dev.decagon.blog.repository.CommentRepository;
import dev.decagon.blog.repository.PostRepository;
import dev.decagon.blog.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper mapper;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(Long post_id) {
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", post_id));

        List<Comment> comments = commentRepository.findAllByPost(post);

        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long post_id, Long comment_id) {

        Comment comment = validateComment(post_id, comment_id);

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long post_id, Long comment_id, CommentDto commentDto) {

        Comment comment = validateComment(post_id, comment_id);

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long post_id, Long comment_id) {


        Comment comment = validateComment(post_id, comment_id);

        commentRepository.delete(comment);
    }

    private Comment validateComment(Long post_id, Long comment_id) {
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", post_id));

        Comment comment = commentRepository.findById(comment_id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", comment_id));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Does Not Belong To Post");
        }

        return comment;
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//
//        comment.setId(commentDto.getId());
//        comment.setBody(commentDto.getBody());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());

        return comment;
    }
}
