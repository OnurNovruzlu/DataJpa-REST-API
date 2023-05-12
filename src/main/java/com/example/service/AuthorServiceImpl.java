package com.example.service;

import com.example.dto.Mapper;
import com.example.dto.request.AuthorRequest;
import com.example.dto.response.AuthorResponse;
import com.example.model.Author;
import com.example.model.ZipCode;
import com.example.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;
    private final ZipCodeService zipCodeService;

    @Transactional
    @Override
    public AuthorResponse insert(AuthorRequest request) {
        Author response = new Author();
        response.setName(request.getName());
        if(request.getZipcodeId()==null){
            throw new IllegalArgumentException("Author need a zipcode,zipcode is empty!");
        }
        ZipCode zipCode=zipCodeService.getZipCode(request.getZipcodeId());
        response.setZipcode(zipCode);
        authorRepository.save(response);
        return Mapper.authorToAuthorResponse(response);
    }

    @Override
    public List<AuthorResponse> getAll() {
        List<Author> authors = authorRepository.findAll();
        return Mapper.authorsToAuthorResponseList(authors);
    }

    @Override
    public AuthorResponse getAuthorResponseById(Long id) {
        Author author = getAuthorById(id);
        return Mapper.authorToAuthorResponse(author);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Author by authorId:"+id+" could not be found"));
    }

    @Override
    public AuthorResponse delete(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
        return Mapper.authorToAuthorResponse(author);
    }

    @Transactional
    @Override
    public AuthorResponse updatePut(Long id, AuthorRequest request) {
        Author author = getAuthorById(id);
        author.setName(request.getName());
        if(request.getZipcodeId()!=null){
            ZipCode zipCode = zipCodeService.getZipCode(request.getZipcodeId());
            author.setZipcode(zipCode);
        }
        return Mapper.authorToAuthorResponse(author);
    }

    @Transactional
    @Override
    public AuthorResponse addZipcodeToAuthor(Long authorId, Long zipcodeId) {
        Author author = getAuthorById(authorId);
        ZipCode zipCode = zipCodeService.getZipCode(zipcodeId);
        if(author.getZipcode()!=null){
            throw new RuntimeException("author already has a zipcode");
        }
        author.setZipcode(zipCode);
        return Mapper.authorToAuthorResponse(author);
    }

    @Transactional
    @Override
    public AuthorResponse removeZipcodeFromAuthor(Long id) {
        Author author = getAuthorById(id);
        author.setZipcode(null);
        return Mapper.authorToAuthorResponse(author);
    }
}
