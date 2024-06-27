
package com.springboot.blog.service.Impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;
import com.springboot.blog.entity.Post;

@Service
public class PostServiceImpl implements PostService{
    
    private PostRepository PostRepository;

    public PostServiceImpl(PostRepository PostRepository) {
        this.PostRepository = PostRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        
        // Convertir DTO to entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        
        Post newPost = PostRepository.save(post);
        
        // Convertir entity a DTO
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setDescription(newPost.getContent());
        
        return postResponse;
    }
    
}
