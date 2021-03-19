package com.uniovi.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE User SET saldo = saldo - ?2 WHERE id = ?1")
	void updateSaldoOfertaComprada(Long id, double cantidad);
	
	@Modifying
	@Transactional
	@Query("UPDATE User SET saldo = saldo + ?2 WHERE id = ?1")
	void updateSaldoOfertaVendida(Long id, double cantidad);
}
