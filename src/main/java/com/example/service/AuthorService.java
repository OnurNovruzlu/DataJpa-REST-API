package com.example.service;

import com.example.dto.request.AuthorRequest;
import com.example.dto.response.AuthorResponse;
import com.example.model.Author;

import java.util.List;

public interface AuthorService {
    AuthorResponse insert(AuthorRequest request);
    List<AuthorResponse> getAll();
    AuthorResponse getAuthorResponseById(Long id);
    Author getAuthorById(Long id);
    AuthorResponse delete(Long id);
    AuthorResponse updatePut(Long id,AuthorRequest request);
    AuthorResponse addZipcodeToAuthor(Long authorId,Long zipcodeId);
    AuthorResponse removeZipcodeFromAuthor(Long id);
}
