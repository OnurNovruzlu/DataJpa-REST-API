package com.example.controller;

import com.example.dto.request.BookRequest;
import com.example.dto.response.BookResponse;
import com.example.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse addBook(@RequestBody BookRequest request) {
        return bookService.addBook(request);
    }

    @GetMapping("/get/{id}")
    public BookResponse getBook(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/all")
    public List<BookResponse> getBooks() {
        return bookService.getBooks();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse deleteBook(@PathVariable("id") Long id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse editBook(@RequestBody BookRequest request,
                                 @PathVariable("id") Long id) {
        return bookService.editBook(id, request);
    }

    @PostMapping("/addCategory/{categoryId}/to/{bookId}")
    public BookResponse addCategory(@PathVariable("categoryId") Long categoryId,
                                    @PathVariable("bookId") Long bookId) {
        return bookService.addCategoryToBook(bookId, categoryId);
    }

    @PostMapping("/removeCategory/{categoryId}/from/{bookId}")
    public BookResponse removeCategory(@PathVariable("categoryId") Long categoryId,
                                       @PathVariable("bookId") Long bookId) {
        return bookService.removeCategoryFromBook(bookId, categoryId);
    }

    @PostMapping("/addAuthor/{authorId}/to/{bookId}")
    public BookResponse addAuthor(@PathVariable("authorId") Long authorId,
                                  @PathVariable("bookId") Long bookId) {
        return bookService.addAuthorToBook(bookId, authorId);
    }

    @PostMapping("/removeAuthor/{authorId}/from/{bookId}")
    public BookResponse removeAuthor(@PathVariable("authorId") Long authorId,
                                     @PathVariable("bookId") Long bookId) {
        return bookService.deleteAuthorFromBook(bookId, authorId);
    }
}

























