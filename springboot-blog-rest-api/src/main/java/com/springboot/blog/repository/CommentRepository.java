
package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    // Buscar todos los comentarios de un Post
    List<Comment> findByPostId(Long postId);
}
