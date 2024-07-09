
package com.springboot.blog.controller;

import com.springboot.blog.Utils.PaginationConstants;
import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@Tag(
        name = "POST CRUD"
)
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    // Crear el metodo POST
    @Operation(
            summary = "Crear un nuevo POST",
            description = "Creamos un nuevo post, es usado para almacenar un post dentro de la BD"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREADO"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    
    // Obtener todos los post API
    @Operation(
            summary = "Obtener todos los POST",
            description = "Traemos todos los post de la BD"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 EXITOSO"
    )
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    // Obtener todos los post pero con paginación
    @Operation(
            summary = "Obtener los Post Paginados",
            description = "Opcional - pageNo, pageSize"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 EXITOSO"
    )
    @GetMapping("paginated")
    public List<PostDto> getAllPostsPaginated(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return postService.getAllPostsPaginated(pageNo, pageSize);
    }

    // Obtener todos los post pero con paginación y datos adicionales
    @Operation(
            summary = "Obtener los Post Paginados",
            description = "Opcional - pageNo, pageSize, trae un poco mas de información como numero de paginas, cantidad de registros etc..."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 EXITOSO"
    )
    @GetMapping("paginateandinformation")
    public PostResponse getAllPostsPaginatedMoreInformation(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "7", required = false) int pageSize
    ){
        return postService.getAllPostsPaginatedMoreInformation(pageNo, pageSize);
    }

    // Obtener todos los post pero con paginación y datos adicionales y Ordenamiento
    @Operation(
            summary = "Obtener los Post Paginados",
            description = "Opcional - pageNo, pageSize, sortBy trae un poco mas de información como numero de paginas, cantidad de registros etc..., mas la opcion de ordenamiento ASC o DESC"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 EXITOSO"
    )
    @GetMapping("paginateandinformationandsort")
    public PostResponse getAllPostsPaginatedMoreInformationAndSort(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "7", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ){
        return postService.getAllPostsPaginatedMoreInformationAndSort(pageNo, pageSize, sortBy);
    }

    // Obtener todos los post pero con paginación y datos adicionales, Ordenamiento y Forma de Ordenar
    @GetMapping("paginateandinformationandsortOrderBy")
    public PostResponse getAllPostsPaginatedMoreInformationAndSortOrderBy(
            @RequestParam(value = "pageNo", defaultValue = PaginationConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPostsPaginatedMoreInformationAndSortOrderBy(pageNo, pageSize, sortBy, sortDir);
    }

    // Obtener Post por Id
    @Operation(
            summary = "Obtener el post por ID",
            description = "Debemos ingresar el ID del post que queremos consultar en la BD"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 EXITOSO"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // Actualizar Post por Id
    @Operation(
            summary = "Actualizar un post",
            description = "Actualizar un post, debemos pasarle el id y el formato JSON con los datos."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 EXITOSO"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //Eliminar Post por Id
    @Operation(
            summary = "Eliminar un post",
            description = "Elimina un post, debemos proporcionar el id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 EXITOSO"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted succesfully.", HttpStatus.OK);
    }
}
