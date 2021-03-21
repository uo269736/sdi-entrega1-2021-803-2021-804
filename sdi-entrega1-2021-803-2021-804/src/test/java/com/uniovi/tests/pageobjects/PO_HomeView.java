package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_HomeView extends PO_NavView {

	static public void checkWelcome(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout());
	}

	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(driver, locale1);
		// Cambiamos a segundo idioma
		PO_HomeView.changeIdiom(driver, textIdiom2);
		// COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_HomeView.checkWelcome(driver, locale2);
		// Volvemos a Español.
		PO_HomeView.changeIdiom(driver, textIdiom1);
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(driver, locale1);
	}
	
	static public void checkChangeIdiomOptions(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkOptionsUser(driver, locale1);
		// Cambiamos a segundo idioma
		PO_HomeView.changeIdiom(driver, textIdiom2);
		// COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_HomeView.checkOptionsUser(driver, locale2);
		// Volvemos a Español.
		PO_HomeView.changeIdiom(driver, textIdiom1);
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkOptionsUser(driver, locale1);
	}
	
	static public void checkOptionsUser(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id, 'users-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("gestion.ofertas.agregar", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("gestion.ofertas.ver", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("gestion.ofertas.ver.propias", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("gestion.ofertas.ver.compras", language), getTimeout());
	}
}
