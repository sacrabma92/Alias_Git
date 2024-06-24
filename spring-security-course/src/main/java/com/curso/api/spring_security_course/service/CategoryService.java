package com.curso.api.spring_security_course.service;

import com.curso.api.spring_security_course.dto.SaveProduct;
import com.curso.api.spring_security_course.persistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

   Page<Category> findAll(Pageable pageable);

   Optional<Category> findOneById(Long categoryId);

   Category createOne(SaveProduct saveProduct);

   Category updateOneById(Long categoryId, SaveProduct saveProduct);

   Category disableOneById(Long categoryId);
}
