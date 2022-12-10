package dev.decagon.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
public class PostDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post Title Length Should Be Longer Than 1")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post Description Length Should Be Longer Than 9")
    private String description;

    @NotEmpty
    private String content;

    private Set<CommentDto> comments = new HashSet<>();
}
