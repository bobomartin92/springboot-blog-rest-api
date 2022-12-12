package dev.decagon.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Comment model information")
@Data
public class CommentDto {

    @ApiModelProperty(value = "Blog Comment ID")
    private Long id;

    @ApiModelProperty(value = "Blog Comment Body")
    @NotEmpty(message = "Body Should Not Be Empty or Null")
    @Size(min = 10, message = "Comment Body Length Should Be Longer Than 9")
    private String body;

    @ApiModelProperty(value = "Blog Comment User Name")
    @NotEmpty(message = "Name Should Not Be Empty or Null")
    private String name;

    @ApiModelProperty(value = "Blog Comment User Email")
    @NotEmpty(message = "Email Should Not Be Empty or Null")
    @Email
    private String email;
}
