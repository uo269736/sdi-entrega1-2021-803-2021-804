package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_OfertaAddView extends PO_NavView{
	static public void fillForm(WebDriver driver, String ptitulo, String pdescripcion, String pcantidad) {
		WebElement titulo = driver.findElement(By.name("titulo"));
		titulo.click();
		titulo.clear();
		titulo.sendKeys(ptitulo);
		WebElement descripcion = driver.findElement(By.name("descripcion"));
		descripcion.click();
		descripcion.clear();
		descripcion.sendKeys(pdescripcion);		
		WebElement cantidad = driver.findElement(By.name("cantidad"));
		cantidad.click();
		cantidad.clear();
		cantidad.sendKeys(pcantidad);	
		// Pulsar el boton de Enviar.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	static public void creaOferta(WebDriver driver, String titulo, String descripcion, double cantidad) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		// Accedemos a Agregar Ofertas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/add')]");
		elementos.get(0).click();
		// Rellenamos el formulario y lo enviamos
		PO_OfertaAddView.fillForm(driver, titulo, descripcion, String.valueOf(cantidad));
	}
}
