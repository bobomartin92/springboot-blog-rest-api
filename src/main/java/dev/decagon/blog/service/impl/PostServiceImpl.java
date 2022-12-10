package dev.decagon.blog.service.impl;

import dev.decagon.blog.entity.Post;
import dev.decagon.blog.payload.PostResponse;
import dev.decagon.blog.exception.ResourceNotFoundException;
import dev.decagon.blog.payload.PostDto;
import dev.decagon.blog.repository.PostRepository;
import dev.decagon.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;
    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        return mapToDto(newPost);
    }


    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> postList = posts.getContent();

        List<PostDto> content =  postList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse(
                content,
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.isLast()
        );

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));

        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));

        postRepository.delete(post);
    }

    private Post mapToEntity(PostDto postDto) {

        Post post = mapper.map(postDto, Post.class);
//        Post post = new Post();
//        post.setContent(postDto.getContent());
//        post.setDescription(postDto.getDescription());
//        post.setTitle(postDto.getTitle());
        return post;
    }

    private PostDto mapToDto(Post post) {

        PostDto postDto = mapper.map(post, PostDto.class);

//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }
}
