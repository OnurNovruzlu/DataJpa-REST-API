package com.example.controller;

import com.example.dto.Mapper;
import com.example.dto.request.AuthorRequest;
import com.example.dto.response.AuthorResponse;
import com.example.model.Author;
import com.example.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponse addAuthor(@RequestBody AuthorRequest request) {
        return authorService.insert(request);
    }

    @GetMapping("/get/{id}")
    public AuthorResponse getAuthor(@PathVariable("id") Long id) {
        Author author = authorService.getAuthorById(id);
        return Mapper.authorToAuthorResponse(author);
    }

    @GetMapping("/all")
    public List<AuthorResponse> getAuthors() {
        return authorService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorResponse deleteAuthor(@PathVariable("id") Long id) {
        return authorService.delete(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    private AuthorResponse editAuthor(@PathVariable("id") Long id,
                                      @RequestBody  AuthorRequest request) {
        return authorService.updatePut(id, request);
    }

    @PostMapping("/addZipcode/{zipcodeId}/to/{authorId}")
    private AuthorResponse addZipcode(@PathVariable("zipcodeId") Long zipcodeId,
                                      @PathVariable("authorId") Long authorId) {
        return authorService.addZipcodeToAuthor(authorId, zipcodeId);
    }

    @PostMapping("/removeZipcode/{id}")
    private AuthorResponse removeZipcode(@PathVariable("id") Long id) {
        return authorService.removeZipcodeFromAuthor(id);
    }
}
























