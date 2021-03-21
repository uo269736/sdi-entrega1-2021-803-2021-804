package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_OfertaAddView extends PO_NavView {
	
	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id, 'users-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		elementos.get(0).click();
		// Pinchamos en la opción de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'oferta/add')]");
		elementos.get(0).click();
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_OfertaAddView.checkañadirOfertas(driver, locale1);
		// Cambiamos a segundo idioma
		PO_OfertaAddView.changeIdiom(driver, textIdiom2);
		// Comprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_OfertaAddView.checkañadirOfertas(driver, locale2);
		// Volvemos a Español.
		PO_OfertaAddView.changeIdiom(driver, textIdiom1);
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_OfertaAddView.checkañadirOfertas(driver, locale1);
	}
	
	static public void checkañadirOfertas(WebDriver driver, int language) {
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("oferta.add", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("oferta.titulo", language), getTimeout());
	}
	
}
