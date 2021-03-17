package com.uniovi.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	 * Sistema que permita realizar una búsqueda de ofertas por su título
	 * Si la cadena está vacía deberá mostrar un listado completo con todas las ofertas existentes en el sistema
	 */
	public Page<Oferta> searchOfertasByTitulo (Pageable pageable, String searchText, User user){
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
	
	public Page<Oferta> getOfertas(Pageable pageable){
		Page<Oferta> ofertas = ofertaRepository.findAll(pageable);
		return ofertas;
	}
}
