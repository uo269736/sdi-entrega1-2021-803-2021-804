package com.uniovi.services;
import java.sql.Date;
import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
@Service
public class InsertSampleDataService {
	
	@Autowired
	private UserService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private OfertaService ofertaService;
	
	@PostConstruct
	public void init() {
		//Creacion de usuarios
		User user1 = new User("UO101010@uniovi.es", "Pedro", "Calvo");
		user1.setPassword("123456");
		user1.setRol(rolesService.getRoles()[0]);
		User user2 = new User("UO101011@uniovi.es", "Rodrígo", "González");
		user2.setPassword("123456");
		user2.setRol(rolesService.getRoles()[0]);
		User user3 = new User("UO101012@uniovi.es", "Sara", "Fernández");
		user3.setPassword("123456");
		user3.setRol(rolesService.getRoles()[0]);
		User user4 = new User("UO101013@uniovi.es", "Lara", "Peña");
		user4.setPassword("123456");
		user4.setRol(rolesService.getRoles()[0]);
		User user5 = new User("UO101014@uniovi.es", "Pelayo", "Martínez");
		user5.setPassword("123456");
		user5.setRol(rolesService.getRoles()[0]);
		User user6 = new User("admin@email.com", "Javier", "López");
		user6.setPassword("admin");
		user6.setRol(rolesService.getRoles()[1]);
		
		//Añadimos a los usuarios
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
		//Creacion de ofertas 
		//(teniendo en cuenta que el constructor Date empieza a contar los años en 1900 y que enero lo cuenta como el mes 0)
		Oferta u1a =new Oferta("Patinete", "Patinete de segunda mano marca 'Joi'. Una semana de uso", Date.valueOf(LocalDate.now()), 20, user1);
		Oferta u1b =new Oferta("Cesta picnic", "Ideal mochila para picnic. Dimensiones: 30x25", Date.valueOf(LocalDate.now()), 12, user1);
		Oferta u1c =new Oferta("Abrigo", "Ideal para el invierno, impermeable y de color rojo", Date.valueOf(LocalDate.now()), 25, user1);
		Oferta u2a =new Oferta("Camara", "Camara Nikon en perfecto estado", Date.valueOf(LocalDate.now()), 80, user2);
		Oferta u2b =new Oferta("Pendientes", "Pendientes de plata", Date.valueOf(LocalDate.now()), 17, user2);
		Oferta u2c =new Oferta("Collar", "Collar de oro", Date.valueOf(LocalDate.now()), 35, user2);
		Oferta u3a =new Oferta("Motocicleta", "Moto roja muy rápida", Date.valueOf(LocalDate.now()), 1000, user3);
		Oferta u3b =new Oferta("Libro: El Quijote", "El Quijote", Date.valueOf(LocalDate.now()), 18, user3);
		Oferta u3c =new Oferta("Maletas", "Maletas grandes para meter mucho equipaje", Date.valueOf(LocalDate.now()), 30, user3);
		Oferta u4a =new Oferta("Cargador", "Cargador para iphone, cable de 1 metro", Date.valueOf(LocalDate.now()), 13, user4);
		Oferta u4b =new Oferta("Taburete", "De madera, 4 patas", Date.valueOf(LocalDate.now()), 29, user4);
		Oferta u4c =new Oferta("Maquina de escribir", "Maquina recien comprada, 1 semana de uso", Date.valueOf(LocalDate.now()), 65, user4);
		Oferta u5a =new Oferta("Pantalones de chandal", "Patalones de chandal negro de adidas", Date.valueOf(LocalDate.now()), 20, user4);
		Oferta u5b =new Oferta("Calculadora", "Calculadora cientifica", Date.valueOf(LocalDate.now()), 34, user4);
		Oferta u5c =new Oferta("Bicicleta", "Bicicleta de montaña, de color rojo", Date.valueOf(LocalDate.now()), 102, user4);
		
		//Añadimos las ofertas
		ofertaService.addOferta(u1a);
		ofertaService.addOferta(u1b);
		ofertaService.addOferta(u1c);
		ofertaService.addOferta(u2a);
		ofertaService.addOferta(u2b);
		ofertaService.addOferta(u2c);
		ofertaService.addOferta(u3a);
		ofertaService.addOferta(u3b);
		ofertaService.addOferta(u3c);
		ofertaService.addOferta(u4a);
		ofertaService.addOferta(u4b);
		ofertaService.addOferta(u4c);
		ofertaService.addOferta(u5a);
		ofertaService.addOferta(u5b);
		ofertaService.addOferta(u5c);
	
		
		//Realizamos las compras
		ofertaService.realizaCompra(true,u2a, user1);
		ofertaService.realizaCompra(true,u2b, user1);
		ofertaService.realizaCompra(true,u1a, user2);
		ofertaService.realizaCompra(true,u5b, user2);
		ofertaService.realizaCompra(true,u4a, user3);
		ofertaService.realizaCompra(true,u5a, user3);
		ofertaService.realizaCompra(true,u2c, user4);
		ofertaService.realizaCompra(true,u3b, user4);
		ofertaService.realizaCompra(true,u4b, user5);
		ofertaService.realizaCompra(true,u4c, user5);
		
	}
}
