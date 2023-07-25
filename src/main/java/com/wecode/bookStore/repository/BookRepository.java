package com.wecode.bookStore.repository;

import com.wecode.bookStore.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {

    List<Book> findBooksByTitle(String title);

    List<Book> findBooksByTitleIgnoreCase(String title);
}
