
package com.springboot.blog.service.Impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;
import com.springboot.blog.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository PostRepository) {
        this.postRepository = PostRepository;
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
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    // Convetir Dto a Entity
    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

}
