package com.uniovi.controllers;

import java.security.Principal;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.services.OfertaService;
import com.uniovi.services.UserService;
import com.uniovi.validators.OfertaAddFormValidator;

@Controller
public class OfertaController {
	@Autowired
	private HttpSession httpSession;
	
	@Autowired //Inyectar el servicio
	private OfertaService ofertaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OfertaAddFormValidator ofertaAddFormValidator;
	
	@RequestMapping("/oferta/list")
	public String getList(Model model, Pageable pageable, Principal principal, @RequestParam(value = "", required=false) String searchText){
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		if (searchText != null && !searchText.isEmpty()) {
			ofertas = ofertaService.searchOfertasByTitulo(pageable, searchText);
		} else {
			ofertas = ofertaService.getOfertas(pageable) ;
		}
		model.addAttribute("ofertaList", ofertas.getContent());
		model.addAttribute("page", ofertas);
		return "oferta/list";

	}
	
	@RequestMapping("/oferta/userlist")
	public String getUserList(Model model, Pageable pageable, Principal principal, @RequestParam(value = "", required=false) String searchText){
		String email = principal.getName(); // email es el name de la autenticación
		User user = userService.getUserByEmail(email);
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		if (searchText != null && !searchText.isEmpty()) {
			ofertas = ofertaService.searchOfertasByTituloForUser(pageable, searchText, user);
		} else {
			ofertas = ofertaService.getOfertasForUser(pageable, user) ;
		}
		model.addAttribute("ofertaList", ofertas.getContent());
		model.addAttribute("page", ofertas);
		return "oferta/userlist";

	}
	
	@RequestMapping(value="/oferta/add", method=RequestMethod.GET )
	public String getOferta(Model model){
		model.addAttribute("oferta", new Oferta());
		return "oferta/add";
	}
	
	@RequestMapping(value="/oferta/add", method=RequestMethod.POST )
	public String setOferta(Model model,@Validated Oferta oferta, BindingResult result){
		ofertaAddFormValidator.validate(oferta, result);
		model.addAttribute("usersList", userService.getUsers());
		if(result.hasErrors()) {
			return "oferta/add";
		}
		oferta.setFechaAlta(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		oferta.setUser(userService.getUserAuthenticated());
		ofertaService.addOferta(oferta);
		return "redirect:/oferta/list";
	}
	
	@RequestMapping("/oferta/details/{id}")
	public String getDetail(Model model, @PathVariable Long id){
		model.addAttribute("oferta", ofertaService.getOferta(id));
		return "oferta/details";
	}
	
	@RequestMapping("/oferta/delete/{id}" )
	public String deleteOferta(@PathVariable Long id){
		ofertaService.deleteOferta(id);
		return "redirect:/oferta/list";
	}

	@RequestMapping("/oferta/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal){
		//Metodo de actualizacion para la lista que muestra todas las ofertas
		//Por ello, usamos getOfertas. Queremos TODAS las ofertas
		Page<Oferta> ofertas = ofertaService.getOfertas(pageable);
		model.addAttribute("ofertaList", ofertas.getContent());
		return "oferta/list :: tableOfertas";
	}
	
	@RequestMapping(value="/oferta/{id}/comprar", method=RequestMethod.GET)
	public String setResendTrue(Model model, @PathVariable Long id){
		ofertaService.setOfertaComprada(true, id);
		return "redirect:/oferta/list";
	}
}
