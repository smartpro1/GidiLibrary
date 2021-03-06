package com.gidilibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidilibrary.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	Book getById(long bookId);

}
