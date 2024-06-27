package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    List<PostDto> getAllPostsPaginated(int pageNo, int pageSize);

    PostResponse getAllPostsPaginatedMoreInformation(int pageNo, int pageSize);

    PostResponse getAllPostsPaginatedMoreInformationAndSort(int pageNo, int pageSize, String sortBy);

    PostResponse getAllPostsPaginatedMoreInformationAndSortOrderBy(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);
}
