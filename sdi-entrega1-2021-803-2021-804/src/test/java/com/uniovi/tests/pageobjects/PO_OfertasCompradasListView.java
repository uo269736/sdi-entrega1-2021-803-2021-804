package com.uniovi.tests.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.services.OfertaService;
import com.uniovi.services.UserService;
import com.uniovi.tests.util.SeleniumUtils;

public class PO_OfertasCompradasListView extends PO_NavView {

	static public void comprobarOfertasCompradas(WebDriver driver, OfertaService ofertaService, String email) {
		// Sacamos todos los elemetos oferta
		List<Oferta> ofertas =ofertaService.getListaOfertas();
		List<Oferta> comprasUser =new ArrayList<Oferta>();
		//Sacamos a otra lista los elementos que haya comprado el usuario usuario
		for(Oferta oferta: ofertas) {
			if(oferta.getComprador()!=null)
				if(oferta.getComprador().getEmail().equals(email))
					comprasUser.add(oferta);
		}
		//Comprobamos si las ofertas compradas están en la vista
		for(Oferta oferta: comprasUser) {
			SeleniumUtils.textoPresentePagina(driver, oferta.getTitulo());
			SeleniumUtils.textoPresentePagina(driver, oferta.getId()+"");
		}
		
	}
	
}
