
package com.springboot.blog.service.Impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.springboot.blog.entity.Post;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

@Service
public class PostServiceImpl implements PostService{
    
    private PostRepository postRepository;
    
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository PostRepository, ModelMapper mapper) {
        this.postRepository = PostRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        
        // Convertir DTO to entity
        Post post = mapToEntity(postDto);
        
        Post newPost = postRepository.save(post);
        
        // Convertir entity a DTO
        PostDto postResponse = mapToDto(newPost);
        
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsPaginated(int pageNo, int pageSize) {

        // Crear un objeto Pageable con el número de página y el tamaño de página especificados
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // Obtener una página de posts del repositorio usando el objeto Pageable
        Page<Post> posts = postRepository.findAll(pageable);

        // Extraer la lista de posts de la página obtenida
        List<Post> listOfPost = posts.getContent();

        // Convertir la lista de entidades Post a una lista de PostDto usando un stream
        return listOfPost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostResponse getAllPostsPaginatedMoreInformation(int pageNo, int pageSize) {
        // Crear un objeto Pageable con el número de página y el tamaño de página especificados
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // Obtener una página de posts del repositorio usando el objeto Pageable
        Page<Post> posts = postRepository.findAll(pageable);

        // Extraer la lista de posts de la página obtenida
        List<Post> listOfPost = posts.getContent();

        List<PostDto> content =  listOfPost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getAllPostsPaginatedMoreInformationAndSort(int pageNo, int pageSize, String sortBy) {
        // Crear un objeto Pageable con el número de página y el tamaño de página especificados
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        // Obtener una página de posts del repositorio usando el objeto Pageable
        Page<Post> posts = postRepository.findAll(pageable);

        // Extraer la lista de posts de la página obtenida
        List<Post> listOfPost = posts.getContent();

        List<PostDto> content =  listOfPost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getAllPostsPaginatedMoreInformationAndSortOrderBy(int pageNo, int pageSize, String sortBy, String sortDir) {
        // esta línea de código establece una ordenación para una colección de elementos basada en el valor de sortDir. Si sortDir es igual a ASC,
        // se ordena de forma ascendente; de lo contrario, se ordena de forma descendente.
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // Crear un objeto Pageable con el número de página y el tamaño de página especificados
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Obtener una página de posts del repositorio usando el objeto Pageable
        Page<Post> posts = postRepository.findAll(pageable);

        // Extraer la lista de posts de la página obtenida
        List<Post> listOfPost = posts.getContent();

        List<PostDto> content =  listOfPost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        // Obtenemos el post por Id de la base de datos en caso que no exista lanzamos una exception
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id", id));

        // Actualizamos en memoria los datos de Post en BD con los valores ingresados
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        // Guardamos el objeto nuevo en la base de datos
        Post updatePost = postRepository.save(post);

        // Convertimos el post actualizado a un DTO y lo devolvemos
        return mapToDto(updatePost);
    }

    @Override
    public void deletePostById(Long id) {
        // Obtenemos el post por Id de la base de datos en caso que no exista lanzamos una exception
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id", id));
        postRepository.delete(post);
    }

    // Convertir Entity a Dto
    private PostDto mapToDto(Post post){
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;
    }

    // Convetir Dto a Entity
    private Post mapToEntity(PostDto postDto){
        Post post = mapper.map(postDto, Post.class);
        return post;
    }

}
