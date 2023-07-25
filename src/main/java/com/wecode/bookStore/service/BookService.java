package com.wecode.bookStore.service;

import com.wecode.bookStore.dto.BookDto;
import com.wecode.bookStore.model.Book;
import com.wecode.bookStore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<BookDto> getBooks(){

        Iterable<Book> all = bookRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .map(convertBookModelToBookDto())
                .collect(Collectors.toList());
    }

    private Function<Book, BookDto> convertBookModelToBookDto() {
        return book -> modelMapper.map(book, BookDto.class);
    }

    public List<BookDto> getBooksByTitle(String bookTitle) {
        List<Book> booksByTitle = bookRepository.findBooksByTitleIgnoreCase(bookTitle);

        return booksByTitle.stream()
                .map(convertBookModelToBookDto())
                .collect(Collectors.toList());
    }

}
