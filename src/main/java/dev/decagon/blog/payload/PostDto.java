package dev.decagon.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@ApiModel(description = "Post model information")
@Data
public class PostDto {

    @ApiModelProperty(value = "Blog Post ID")
    private Long id;

    @ApiModelProperty(value = "Blog Post Title")
    @NotEmpty
    @Size(min = 2, message = "Post Title Length Should Be Longer Than 1")
    private String title;

    @ApiModelProperty(value = "Blog Post Description")
    @NotEmpty
    @Size(min = 10, message = "Post Description Length Should Be Longer Than 9")
    private String description;

    @ApiModelProperty(value = "Blog Post Content")
    @NotEmpty
    private String content;

    @ApiModelProperty(value = "Blog Post Comments")
    private Set<CommentDto> comments = new HashSet<>();
}
