package dev.decagon.blog.controller;

import dev.decagon.blog.payload.PostResponse;
import dev.decagon.blog.payload.PostDto;
import dev.decagon.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static dev.decagon.blog.util.APPConstant.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(name = "pageNumber", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){

        return new ResponseEntity<>(postService.getAllPost(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long postId){

        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("id") Long postId){

        PostDto postResponse = postService.updatePost(postDto, postId);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long postId){

        postService.deletePost(postId);

        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}
