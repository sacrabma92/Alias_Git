package com.springboot.blog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    
    // El titulo no debe ser null o vacio
    // El titulo debe tener minimo dos caracteres
    @NotEmpty
    @Size(min = 2, message = "Titulo debe tener minimo 2 caracteres")
    private String title;
    
    // La description no debe ser null o vacio
    // La description debe tener minimo 10 caracteres
    @NotEmpty
    @Size(min = 10, message = "La descripcion debe tener minimo 10 caracteres")
    private String description;
    
    // No debe ser null o vacio
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
