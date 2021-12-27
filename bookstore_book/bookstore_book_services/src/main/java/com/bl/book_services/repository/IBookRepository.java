package com.bl.book_services.repository;

/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : BookRepository
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bl.book_services.model.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
	

}
