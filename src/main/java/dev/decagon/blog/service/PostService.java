package dev.decagon.blog.service;

import dev.decagon.blog.payload.PostResponse;
import dev.decagon.blog.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long postId);

    PostDto updatePost(PostDto postDto, Long postId);

    void deletePost(Long postId);
}
