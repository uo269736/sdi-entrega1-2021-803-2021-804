package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;

public interface OfertaRepository extends CrudRepository<Oferta, Long>{
	@Query("SELECT r FROM Oferta r WHERE (LOWER(r.titulo) LIKE LOWER(?1)")
	Page<Oferta> searchByTitulo(Pageable pageable, String searchtext);
	
	@Query("SELECT r FROM Oferta r WHERE (LOWER(r.titulo) LIKE LOWER(?1) OR LOWER(r.user.nombre) LIKE LOWER(?1)) AND r.user = ?2")
	Page<Oferta> searchByTituloAndUser(Pageable pageable, String searchtext);
	
	@Query("SELECT r FROM Oferta r WHERE (LOWER(r.descripcion) LIKE LOWER(?1) OR LOWER(r.user.nombre) LIKE LOWER(?1))")
	Page<Oferta> searchByDescriptionAndName(Pageable pageable, String searchtext);
	
	@Query("SELECT r FROM Oferta r WHERE (LOWER(r.descripcion) LIKE LOWER(?1) OR LOWER(r.user.nombre) LIKE LOWER(?1)) AND r.user = ?2")
	Page<Oferta> searchByDescriptionNameAndUser(Pageable pageable, String searchtext, User user);
	
	@Query("SELECT r FROM Oferta r WHERE r.user = ?1 ORDER BY r.id ASC")
	Page<Oferta> findAllByUser(Pageable pageable, User user);
	
	Page<Oferta> findAll(Pageable pageable);
}
