package com.springboot.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.Data;

@Data
@Schema(
        description = "Información de Modelo de información"
)
public class PostDto {
    private long id;
    
    // El titulo no debe ser null o vacio
    // El titulo debe tener minimo dos caracteres
    @Schema(
            description = "Titulo del Post"
    )
    @NotEmpty
    @Size(min = 2, message = "Titulo debe tener minimo 2 caracteres")
    private String title;
    
    // La description no debe ser null o vacio
    // La description debe tener minimo 10 caracteres
    @Schema(
            description = "Descripción del post"
    )
    @NotEmpty
    @Size(min = 10, message = "La descripcion debe tener minimo 10 caracteres")
    private String description;
    
    // No debe ser null o vacio
    @Schema(
            description = "Contenido del post"
    )
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
