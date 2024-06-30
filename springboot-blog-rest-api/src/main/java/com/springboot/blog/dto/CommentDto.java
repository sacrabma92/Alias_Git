
package com.springboot.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotEmpty(message = "El nombre no puede ser null o vacio")
    private String name;
    @NotEmpty(message = "El email no puede ser null o vacio")
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10, message = "Minimo 10 caracteres")
    private String body;

}
