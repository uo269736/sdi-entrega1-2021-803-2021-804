package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.repositories.OfertaRepository;

@Service
public class OfertaService {
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private OfertaRepository ofertaRepository; 
	
	@Autowired
	private UserService userService;
	
	private Logger log = Logger.getLogger("WallapopLogger");
	
	public void addOferta(Oferta oferta){
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		ofertaRepository.save(oferta);
	}
	public void deleteOferta(Long id){
		ofertaRepository.deleteById(id);
	}
	
	public Oferta getOferta(Long id){
		Set<Oferta> consultedList = (Set<Oferta>) httpSession.getAttribute("consultedList");
		if ( consultedList == null ) {
			consultedList = new HashSet<Oferta>();
		}
		Oferta obtainedoferta = ofertaRepository.findById(id).get();
		consultedList.add(obtainedoferta);
		httpSession.setAttribute("consultedList", consultedList);
		return obtainedoferta;
	}
	
	public void setOfertaComprada(boolean revised,Long id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Oferta oferta = ofertaRepository.findById(id).get();
		User usuario = userService.getUserByEmail(email);

		realizaCompra(revised, oferta, usuario);
	}
	
	public void realizaCompra(boolean revised,Oferta oferta, User usuario){
		if(!oferta.isComprada()) {
			if(oferta.getCantidad() <= usuario.getSaldo()) {
				oferta.setComprador(usuario);
				ofertaRepository.save(oferta);
				ofertaRepository.updateComprada(revised, oferta.getId());
				userService.realizaPago(oferta.getCantidad(), usuario.getId(), oferta.getUser().getId());
				log.log(Level.INFO, "El usuario con id "+usuario.getId()+" e email "+usuario.getEmail()+ " ha comprado la oferta con id "+oferta.getId());
			}
			else
				log.log(Level.INFO, "El usuario con id "+usuario.getId()+" e email "+usuario.getEmail()+ " no pudo realizar la compra de la oferta con id "+oferta.getId());
		}	else
			log.log(Level.INFO, "El usuario con id "+usuario.getId()+" e email "+usuario.getEmail()+ " no pudo realizar la compra de la oferta con id "+oferta.getId());
	}

	public Page<Oferta> getOfertasForUser (Pageable pageable, User user){
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		ofertas = ofertaRepository.findAllByUser(pageable, user);
		return ofertas;
	}

	public Page<Oferta> searchOfertasByDescriptionAndNameForUser (Pageable pageable, String searchText, User user){
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		searchText = "%"+searchText+"%";
		ofertas = ofertaRepository.searchByDescriptionNameAndUser(pageable, searchText, user);
		return ofertas;
	}
	
	/*
	 * Sistema que permita realizar una búsqueda de ofertas por su título PARA EL USUARIO DE SUS PROPIAS OFERTAS
	 * Si la cadena está vacía deberá mostrar un listado completo con todas las ofertas existentes en el sistema
	 */
	public Page<Oferta> searchOfertasByTituloForUser (Pageable pageable, String searchText, User user){
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		if(searchText.isEmpty()) {
			ofertas = ofertaRepository.findAll(pageable);
		}
		else {
			searchText = "%"+searchText+"%";
			ofertas = ofertaRepository.searchByDescriptionNameAndUser(pageable, searchText, user);
		}
		return ofertas;
	}
	
	/*
	 * Sistema que permita realizar una búsqueda de ofertas por su título
	 * Si la cadena está vacía deberá mostrar un listado completo con todas las ofertas existentes en el sistema
	 */
	public Page<Oferta> searchOfertasByTitulo(Pageable pageable, String searchText){
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		if(searchText.isEmpty()) {
			ofertas = ofertaRepository.findAll(pageable);
		}
		else {
			searchText = "%"+searchText+"%";
			ofertas = ofertaRepository.searchByTitulo(pageable, searchText);
		}
		return ofertas;
	}
	
	public Page<Oferta> getOfertas(Pageable pageable){
		Page<Oferta> ofertas = ofertaRepository.findAll(pageable);
		return ofertas;
	}
	
	public List<Oferta> getListaOfertas() {
        List<Oferta> ofertas = new ArrayList<Oferta>();
        ofertaRepository.findAll().forEach(ofertas::add);
        return ofertas;
    }
}
