package dev.decagon.blog.controller;

import dev.decagon.blog.payload.CommentDto;
import dev.decagon.blog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/posts/{post_id}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    public ResponseEntity<CommentDto> createComment(@PathVariable Long post_id, @Valid @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(post_id, commentDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<CommentDto>> getAllPostComments(@PathVariable Long post_id){

        return new ResponseEntity<>(commentService.getAllCommentsByPostId(post_id), HttpStatus.OK);
    }

    @GetMapping("{comment_id}")
    public ResponseEntity<CommentDto> getPostComment(@PathVariable Long post_id, @PathVariable Long comment_id){

        return new ResponseEntity<>(commentService.getCommentById(post_id, comment_id), HttpStatus.OK);
    }

    @PutMapping("{comment_id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable Long post_id, @PathVariable Long comment_id, @Valid @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.updateComment(post_id, comment_id, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("{comment_id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long post_id, @PathVariable Long comment_id){

        commentService.deleteComment(post_id, comment_id);

        return new ResponseEntity<>("Comment deleted Successfully", HttpStatus.OK);
    }
}
