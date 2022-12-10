package dev.decagon.blog.payload;

import dev.decagon.blog.payload.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponse {
    private List<PostDto> contents;
    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;
}
