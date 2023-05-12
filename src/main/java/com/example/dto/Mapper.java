package com.example.dto;

import com.example.dto.response.AuthorResponse;
import com.example.dto.response.BookResponse;
import com.example.dto.response.CategoryResponse;
import com.example.model.Author;
import com.example.model.Book;
import com.example.model.Category;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static BookResponse bookToBookResponse(Book book){
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setName(book.getName());
        response.setCategoryName(book.getCategory().getName());
        List<String> authorNames = new ArrayList<>();
        List<Author> authors = book.getAuthors();
        for(Author author: authors){
            authorNames.add(author.getName());
        }
        response.setAuthorNames(authorNames);
        return response;
    }
    public static List<BookResponse> booksToBookResponseList(List<Book> books){
        List<BookResponse> responses = new ArrayList<>();
        for(Book book: books){
            responses.add(bookToBookResponse(book));
        }
        return responses;
    }
    public static AuthorResponse authorToAuthorResponse(Author author){
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setZipcodeName(author.getZipcode().getName());
        List<String> names = new ArrayList<>();
        List<Book> books = author.getBooks();
        for(Book book: books){
            names.add(book.getName());
        }
        response.setBookNames(names);
        return response;
    }
    public static List<AuthorResponse> authorsToAuthorResponseList(List<Author> authors){
        List<AuthorResponse> responses = new ArrayList<>();
        for(Author author: authors){
            responses.add(authorToAuthorResponse(author));
        }
        return responses;
    }

    public static CategoryResponse categoryToCategoryResponse(Category category){
        CategoryResponse response=new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        List<String> names = new ArrayList<>();
        List<Book> books = category.getBooks();
        for(Book book: books){
            names.add(book.getName());
        }
        response.setBookNames(names);
        return response;
    }
    public static List<CategoryResponse> categoriesToCategoryResponseList(List<Category> categories){
        List<CategoryResponse> responses = new ArrayList<>();
        for(Category category: categories){
            responses.add(categoryToCategoryResponse(category));
        }
        return responses;
    }

}
