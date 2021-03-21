package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.Oferta;
import com.uniovi.services.OfertaService;

@Controller
public class HomeController {
	
	@Autowired
	private OfertaService ofertaService;
	
	@RequestMapping("/" )
	public String index(Model model, Pageable pageable) {
		Page<Oferta> ofertasDestacadas = ofertaService.getOfertasDestacadas(pageable);
		model.addAttribute("ofertaList", ofertasDestacadas.getContent());
		model.addAttribute("page", ofertasDestacadas);
		return "index";
	}
}
