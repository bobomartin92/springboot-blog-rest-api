package dev.decagon.blog.controller;

import dev.decagon.blog.payload.CommentDto;
import dev.decagon.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD REST API for Comment Resources")
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/posts/{post_id}/comments")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "Create Comment REST API")
    @PostMapping()
    public ResponseEntity<CommentDto> createComment(@PathVariable Long post_id, @Valid @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(post_id, commentDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Comments By Post ID REST API")
    @GetMapping()
    public ResponseEntity<List<CommentDto>> getAllPostComments(@PathVariable Long post_id){

        return new ResponseEntity<>(commentService.getAllCommentsByPostId(post_id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Comment By ID REST API")
    @GetMapping("{comment_id}")
    public ResponseEntity<CommentDto> getPostComment(@PathVariable Long post_id, @PathVariable Long comment_id){

        return new ResponseEntity<>(commentService.getCommentById(post_id, comment_id), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Comment By ID REST API")
    @PutMapping("{comment_id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable Long post_id, @PathVariable Long comment_id, @Valid @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.updateComment(post_id, comment_id, commentDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Comment By ID REST API")
    @DeleteMapping("{comment_id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long post_id, @PathVariable Long comment_id){

        commentService.deleteComment(post_id, comment_id);

        return new ResponseEntity<>("Comment deleted Successfully", HttpStatus.OK);
    }
}
