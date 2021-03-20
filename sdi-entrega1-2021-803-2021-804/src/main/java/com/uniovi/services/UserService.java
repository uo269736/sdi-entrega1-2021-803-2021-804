package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private HttpSession httpSession;

	@PostConstruct
	public void init() {
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public void realizaPago(double cantidad, Long idComprador, Long idVendedor) {
		// Actualizamos el saldo tanto del comprador como del vendedor
		usersRepository.updateSaldoOfertaComprada(idComprador, cantidad);
		usersRepository.updateSaldoOfertaVendida(idVendedor, cantidad);
	}
	
	public User getUserAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=getUserByEmail(auth.getName());
        return user;
    }
	
	public void comprarOferta(Oferta oferta , User usuario) {
		usuario.getOfertasCompradas().add(oferta);
		usersRepository.save(usuario);
	}

}
