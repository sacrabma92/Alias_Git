/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.blog.service.Impl;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
   private CommentRepository commentRepository;
   private PostRepository postRepository;

   public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
      this.commentRepository = commentRepository;
      this.postRepository = postRepository;
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
      CommentDto commentDto = new CommentDto();
      commentDto.setId(comment.getId());
      commentDto.setName(comment.getName());
      commentDto.setEmail(comment.getEmail());
      commentDto.setBody(comment.getBody());
      return  commentDto;
   }

   // Mapeamos de Dto a Entity
   private Comment mapToEntity(CommentDto commentDto){
      Comment comment = new Comment();
      comment.setId(commentDto.getId());
      comment.setName(commentDto.getName());
      comment.setEmail(commentDto.getEmail());
      comment.setBody(commentDto.getBody());
      return comment;
   }
}
