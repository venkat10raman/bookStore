package com.wecode.bookStore.service;

import com.wecode.bookStore.dto.BookDto;
import com.wecode.bookStore.model.Book;
import com.wecode.bookStore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    void getBooksTest() {
        List<Book> books = new ArrayList<>();
        Book book = getBook();
        books.add(book);
        BookDto bookDto = getBookDto();
        when(bookRepository.findAll()).thenReturn(books);
        when(mapper.map(book, BookDto.class)).thenReturn(bookDto);
        List<BookDto> bookDtos = bookService.getBooks();
        assertThat(1).isEqualTo(bookDtos.size());
        assertThat(bookDtos.get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("title","test title")
                .hasFieldOrPropertyWithValue("description","test description")
                .hasFieldOrPropertyWithValue("releaseYear",2020);
    }

    @Test
    void getBooksByTitleTest() {
        List<Book> books = new ArrayList<>();
        Book book = getBook();
        books.add(book);
        BookDto bookDto = getBookDto();
        when(bookRepository.findBooksByTitleIgnoreCase(anyString())).thenReturn(books);
        when(mapper.map(book, BookDto.class)).thenReturn(bookDto);

        List<BookDto> bookDtoList = bookService.getBooksByTitle("test title");

        assertThat(bookDtoList.size()).isEqualTo(1);
    }

    private Book getBook() {
        return Book.builder()
                .title("test title")
                .description("test description")
                .id(UUID.randomUUID())
                .releaseYear(2020)
                .build();
    }

    private BookDto getBookDto() {
        return BookDto.builder()
                .title("test title")
                .description("test description")
                .id(UUID.randomUUID())
                .releaseYear(2020)
                .build();
    }
}
