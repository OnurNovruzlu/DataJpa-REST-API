package com.example.service;

import com.example.dto.Mapper;
import com.example.dto.request.BookRequest;
import com.example.dto.response.BookResponse;
import com.example.model.Author;
import com.example.model.Book;
import com.example.model.Category;
import com.example.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public BookResponse addBook(BookRequest request) {
        Book book = new Book();
        book.setName(request.getName());
        if (request.getAuthorIds().isEmpty()) {
            throw new IllegalArgumentException("you need at least on author");
        } else {
            List<Author> authors = new ArrayList();
            for (Long authorId: request.getAuthorIds()) {
                Author author = authorService.getAuthorById(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);
        }
        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("book at least on category");
        }
        Category category = categoryService.getCategory(request.getCategoryId());
        book.setCategory(category);

        Book savedBook = bookRepository.save(book);
        return Mapper.bookToBookResponse(savedBook);
    }

    @Override
    public BookResponse getBookById(Long bookId) {
        Book book = getBook(bookId);
        return Mapper.bookToBookResponse(book);
    }

    @Override
    public Book getBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalArgumentException("cannot find book with id: " + bookId));
        return book;
    }

    @Override
    public List<BookResponse> getBooks() {
        List<Book> books = bookRepository.findAll();
        return Mapper.booksToBookResponseList(books);
    }

    @Override
    public BookResponse deleteBook(Long bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);
        return Mapper.bookToBookResponse(book);
    }

    @Transactional
    @Override
    public BookResponse editBook(Long bookId, BookRequest bookRequestDto) {
        Book bookToEdit = getBook(bookId);
        bookToEdit.setName(bookRequestDto.getName());
        if (!bookRequestDto.getAuthorIds().isEmpty()){
            List<Author> authors = new ArrayList<>();
            for (Long authorId: bookRequestDto.getAuthorIds()) {
                Author author = authorService.getAuthorById(authorId);
                authors.add(author);
            }
            bookToEdit.setAuthors(authors);
        }
        if (bookRequestDto.getCategoryId() != null) {
            Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
            bookToEdit.setCategory(category);
        }
        return Mapper.bookToBookResponse(bookToEdit);
    }

    @Override
    public BookResponse addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthorById(authorId);
        if (author.getBooks().contains(author)) {
            throw new IllegalArgumentException("this author is already assigned to this book");
        }
        book.addAuthor(author);
        author.addBook(book);
        return Mapper.bookToBookResponse(book);
    }

    @Override
    public BookResponse deleteAuthorFromBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthorById(authorId);
        if (!(author.getBooks().contains(book))){
            throw new IllegalArgumentException("book does not have this author");
        }
        author.removeBook(book);
        book.removeAuthor(author);
        return Mapper.bookToBookResponse(book);
    }

    @Override
    public BookResponse addCategoryToBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (Objects.nonNull(book.getCategory())){
            throw new IllegalArgumentException("book already has a catogory");
        }
        book.setCategory(category);
        category.addBook(book);
        return Mapper.bookToBookResponse(book);
    }

    @Override
    public BookResponse removeCategoryFromBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (!(Objects.nonNull(book.getCategory()))){
            throw new IllegalArgumentException("book does not have a category to delete");
        }
        book.setCategory(null);
        category.removeBook(book);
        return Mapper.bookToBookResponse(book);
    }
}
