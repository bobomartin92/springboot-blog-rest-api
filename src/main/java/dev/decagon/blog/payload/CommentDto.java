package dev.decagon.blog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Body Should Not Be Empty or Null")
    @Size(min = 10, message = "Comment Body Length Should Be Longer Than 9")
    private String body;

    @NotEmpty(message = "Name Should Not Be Empty or Null")
    private String name;

    @NotEmpty(message = "Email Should Not Be Empty or Null")
    @Email
    private String email;
}
