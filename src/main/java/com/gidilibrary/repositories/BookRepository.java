package com.gidilibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gidilibrary.models.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	Book getById(long bookId);

}
