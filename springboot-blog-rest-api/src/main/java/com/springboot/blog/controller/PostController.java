
package com.springboot.blog.controller;

import com.springboot.blog.Utils.PaginationConstants;
import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    // Crear el metodo POST
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    
    // Obtener todos los post API
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    // Obtener todos los post pero con paginación
    @GetMapping("paginated")
    public List<PostDto> getAllPostsPaginated(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return postService.getAllPostsPaginated(pageNo, pageSize);
    }

    // Obtener todos los post pero con paginación y datos adicionales
    @GetMapping("paginateandinformation")
    public PostResponse getAllPostsPaginatedMoreInformation(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "7", required = false) int pageSize
    ){
        return postService.getAllPostsPaginatedMoreInformation(pageNo, pageSize);
    }

    // Obtener todos los post pero con paginación y datos adicionales y Ordenamiento
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
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // Actualizar Post por Id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //Eliminar Post por Id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted succesfully.", HttpStatus.OK);
    }
}
