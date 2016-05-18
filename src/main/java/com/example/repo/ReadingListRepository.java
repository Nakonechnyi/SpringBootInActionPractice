package com.example.repo;

import com.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @autor A_Nakonechnyi
 * @date 18.05.2016.
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);
}
