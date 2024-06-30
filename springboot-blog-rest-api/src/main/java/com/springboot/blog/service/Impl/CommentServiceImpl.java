/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.blog.service.Impl;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
   private CommentRepository commentRepository;
   private PostRepository postRepository;
   
   private ModelMapper mapper;

   public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
      this.commentRepository = commentRepository;
      this.postRepository = postRepository;
      this.mapper = mapper;
   }

   @Override
   public CommentDto createComment(Long postId, CommentDto commentDto) {
      // Mapeamos de DTO a Entity
      Comment comment = mapToEntity(commentDto);

      // Recuperar entidad Post por id, en caso que no exista arroja exception
      Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id", postId));

      // Le añadimos al comentario la entidad post que encuentra
      comment.setPost(post);

      // Añadimos al comentario el post encontrado
      Comment newComment = commentRepository.save(comment);

      return mapToDto(newComment);
   }

   // Mapeamos de Entity a Dto
   private CommentDto mapToDto(Comment comment){
       CommentDto commentDto = mapper.map(comment, CommentDto.class);
      return  commentDto;
   }

   // Mapeamos de Dto a Entity
   private Comment mapToEntity(CommentDto commentDto){
      Comment comment = mapper.map(commentDto, Comment.class);
      return comment;
   }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        // Obtener una lista de comentarios de un post en especifico id
        List<Comment> comments = commentRepository.findByPostId(postId);
        
        // Convertirmos los comentarios en una lista y los retornamos
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // Recuperar entidad Post por id, en caso que no exista arroja exception!
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post","id", postId));
        
        // Recuperar el comentario por id,
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment", "id", commentId));
        
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"El comentario no pertenece a ningun post");
        }
        
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentRequest) {
        // Verificamos que el post exista y Recuperar entidad Post por id, en caso que no exista arroja exception!
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post","id", postId));
        
        // Verificamos que el comentario exista y Recuperar el comentario por id,
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment", "id", commentId));
        
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"El comentario no pertenece a ningun post");
        }
        
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
        
        Comment updatedComment = commentRepository.save(comment);
        
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // Verificamos que el post exista y Recuperar entidad Post por id, en caso que no exista arroja exception!
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post","id", postId));
        
        // Verificamos que el comentario exista y Recuperar el comentario por id,
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment", "id", commentId));
        
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"El comentario no pertenece a ningun post");
        }
        
        commentRepository.delete(comment);
    }
}
