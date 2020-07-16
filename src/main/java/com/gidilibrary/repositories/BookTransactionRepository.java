package com.gidilibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidilibrary.models.Book;
import com.gidilibrary.models.BookTransaction;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction, Long>{


}
