package com.example.service;

import com.example.dto.request.BookRequest;
import com.example.dto.response.BookResponse;
import com.example.model.Book;

import java.util.List;

public interface BookService {
    BookResponse addBook(BookRequest bookRequestDto);
    BookResponse getBookById(Long bookId);
    Book getBook(Long bookId);
    List<BookResponse> getBooks();
    BookResponse deleteBook(Long bookId);
    BookResponse editBook(Long bookId, BookRequest bookRequestDto);
    BookResponse addAuthorToBook(Long bookId, Long authorId);
    BookResponse deleteAuthorFromBook(Long bookId, Long authorId);
    BookResponse addCategoryToBook(Long bookId, Long categoryId);
    BookResponse removeCategoryFromBook(Long bookId, Long categoryId);
}
