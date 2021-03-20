package com.uniovi.services;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.repositories.UserRepository;
@Service
public class InsertSampleDataService {
	
	@Autowired
	private UserService usersService;
	
	@Autowired
	private RolesService rolesService;
	
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
		
		//Creacion de ofertas 
		//(teniendo en cuenta que el constructor Date empieza a contar los años en 1900 y que enero lo cuenta como el mes 0)
		Oferta u1a =new Oferta("Patinete", "Patinete de segunda mano marca 'Joi'. Una semana de uso", new Date(121, 2, 12), 20, user1, user2);
		Oferta u1b =new Oferta("Cesta picnic", "Ideal mochila para picnic. Dimensiones: 30x25", new Date(121, 2, 12), 12, user1);
		Oferta u1c =new Oferta("Abrigo", "Ideal para el invierno, impermeable y de color rojo", new Date(121, 2, 12), 25, user1);
		Set user1Ofertas = new HashSet<Oferta>() {
			{
				add(u1a);
				add(u1b);
				add(u1c);
			}
		};
		user1.setOfertas(user1Ofertas);
		
		Oferta u2a =new Oferta("Camara", "Camara Nikon en perfecto estado", new Date(121, 2, 12), 80, user2, user1);
		Oferta u2b =new Oferta("Pendientes", "Pendientes de plata", new Date(121, 2, 12), 17, user2, user1);
		Oferta u2c =new Oferta("Collar", "Collar de oro", new Date(121, 2, 12), 35, user2, user4);
		Set user2Ofertas = new HashSet<Oferta>() {
			{
				add(u2a);
				add(u2b);
				add(u2c);
			}
		};
		user2.setOfertas(user2Ofertas);
		
		Oferta u3a =new Oferta("Motocicleta", "Moto roja muy rápida", new Date(121, 2, 12), 1000, user3);
		Oferta u3b =new Oferta("Libro: El Quijote", "El Quijote", new Date(121, 2, 12), 18, user3, user4);
		Oferta u3c =new Oferta("Maletas", "Maletas grandes para meter mucho equipaje", new Date(121, 2, 12), 30, user3);
		Set user3Ofertas = new HashSet<Oferta>() {
			{
				add(u3a);
				add(u3b);
				add(u3c);
			}
		};
		user3.setOfertas(user3Ofertas);
		
		Oferta u4a =new Oferta("Cargador", "Cargador para iphone, cable de 1 metro", new Date(121, 2, 12), 13, user4, user3);
		Oferta u4b =new Oferta("Taburete", "De madera, 4 patas", new Date(121, 2, 12), 29, user4, user5);
		Oferta u4c =new Oferta("Maquina de escribir", "Maquina recien comprada, 1 semana de uso", new Date(121, 2, 12), 65, user4, user5);
		Set user4Ofertas = new HashSet<Oferta>() {
			{
				add(u4a);
				add(u4b);
				add(u4c);
			}
		};
		user4.setOfertas(user4Ofertas);
		
		Oferta u5a =new Oferta("Pantalones de chandal", "Patalones de chandal negro de adidas", new Date(121, 2, 12), 20, user4, user3);
		Oferta u5b =new Oferta("Calculadora", "Calculadora cientifica", new Date(121, 2, 12), 34, user4, user2);
		Oferta u5c =new Oferta("Bicicleta", "Bicicleta de montaña, de color rojo", new Date(121, 2, 12), 102, user4);
		Set user5Ofertas = new HashSet<Oferta>() {
			{
				add(u5a);
				add(u5b);
				add(u5c);
			}
		};
		user5.setOfertas(user5Ofertas);
		
		//Agregamos las compras
		Set user1Compras = new HashSet<Oferta>() {
			{
				add(u2a);
				add(u2b);
			}
		};
		user1.setOfertasCompradas(user1Compras);
		user1.setSaldo(100-(u2a.getCantidad()+u2b.getCantidad()));
		
		Set user2Compras = new HashSet<Oferta>() {
			{
				add(u1a);
				add(u5b);
			}
		};
		user2.setOfertasCompradas(user2Compras);
		user2.setSaldo(100-(u1a.getCantidad()+u5b.getCantidad()));
		
		Set user3Compras = new HashSet<Oferta>() {
			{
				add(u4a);
				add(u5b);
			}
		};
		user3.setOfertasCompradas(user3Compras);
		user3.setSaldo(100-(u4a.getCantidad()+u5b.getCantidad()));
		
		Set user4Compras = new HashSet<Oferta>() {
			{
				add(u2c);
				add(u3b);
			}
		};
		user4.setOfertasCompradas(user4Compras);
		user4.setSaldo(100-(u2c.getCantidad()+u3b.getCantidad()));
		
		Set user5Compras = new HashSet<Oferta>() {
			{
				add(u4b);
				add(u4c);
			}
		};
		user5.setOfertasCompradas(user5Compras);
		user5.setSaldo(100-(u2a.getCantidad()+u2b.getCantidad()));
		
		
		//Add usuarios
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
//		//Realizamos las compras
//		usersService.comprarOferta(u2a, user1);
//		usersService.comprarOferta(u2b, user1);
//		
//		usersService.comprarOferta(u1a, user2);
//		usersService.comprarOferta(u5b, user2);
//		
//		usersService.comprarOferta(u4a, user3);
//		usersService.comprarOferta(u5a, user3);
//		
//		usersService.comprarOferta(u2c, user4);
//		usersService.comprarOferta(u3b, user4);
//		
//		usersService.comprarOferta(u4b, user5);
//		usersService.comprarOferta(u4c, user5);
//		
	}
}
