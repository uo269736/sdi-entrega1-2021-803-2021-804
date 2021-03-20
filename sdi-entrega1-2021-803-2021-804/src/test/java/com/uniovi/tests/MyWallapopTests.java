package com.uniovi.tests;

import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.repositories.UserRepository;
import com.uniovi.services.OfertaService;
import com.uniovi.services.RolesService;
import com.uniovi.services.UserService;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MyWallapopTests {
	// Reinicio de base de datos
	@Autowired
	private UserService usersService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private OfertaService ofertaService;

	public void initdb() {
		// Borramos todas las entidades
		usersRepository.deleteAll();
		// Ahora las volvemos a crear
		// Creacion de usuarios
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

		// Añadimos a los usuarios
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);

		// Creacion de ofertas
		// (teniendo en cuenta que el constructor Date empieza a contar los años en 1900
		// y que enero lo cuenta como el mes 0)
		Oferta u1a = new Oferta("Patinete", "Patinete de segunda mano marca 'Joi'. Una semana de uso",
				new Date(121, 2, 12), 20, user1);
		Oferta u1b = new Oferta("Cesta picnic", "Ideal mochila para picnic. Dimensiones: 30x25", new Date(121, 2, 12),
				12, user1);
		Oferta u1c = new Oferta("Abrigo", "Ideal para el invierno, impermeable y de color rojo", new Date(121, 2, 12),
				25, user1);
		Oferta u2a = new Oferta("Camara", "Camara Nikon en perfecto estado", new Date(121, 2, 12), 80, user2);
		Oferta u2b = new Oferta("Pendientes", "Pendientes de plata", new Date(121, 2, 12), 17, user2);
		Oferta u2c = new Oferta("Collar", "Collar de oro", new Date(121, 2, 12), 35, user2);
		Oferta u3a = new Oferta("Motocicleta", "Moto roja muy rápida", new Date(121, 2, 12), 1000, user3);
		Oferta u3b = new Oferta("Libro: El Quijote", "El Quijote", new Date(121, 2, 12), 18, user3);
		Oferta u3c = new Oferta("Maletas", "Maletas grandes para meter mucho equipaje", new Date(121, 2, 12), 30,
				user3);
		Oferta u4a = new Oferta("Cargador", "Cargador para iphone, cable de 1 metro", new Date(121, 2, 12), 13, user4);
		Oferta u4b = new Oferta("Taburete", "De madera, 4 patas", new Date(121, 2, 12), 29, user4);
		Oferta u4c = new Oferta("Maquina de escribir", "Maquina recien comprada, 1 semana de uso", new Date(121, 2, 12),
				65, user4);
		Oferta u5a = new Oferta("Pantalones de chandal", "Patalones de chandal negro de adidas", new Date(121, 2, 12),
				20, user4);
		Oferta u5b = new Oferta("Calculadora", "Calculadora cientifica", new Date(121, 2, 12), 34, user4);
		Oferta u5c = new Oferta("Bicicleta", "Bicicleta de montaña, de color rojo", new Date(121, 2, 12), 102, user4);

		// Añadimos las ofertas
		ofertaService.addOferta(u1a);
		ofertaService.addOferta(u1b);
		ofertaService.addOferta(u1c);
		ofertaService.addOferta(u2a);
		ofertaService.addOferta(u2b);
		ofertaService.addOferta(u2c);
		ofertaService.addOferta(u3a);
		ofertaService.addOferta(u3b);
		ofertaService.addOferta(u3c);
		ofertaService.addOferta(u4a);
		ofertaService.addOferta(u4b);
		ofertaService.addOferta(u4c);
		ofertaService.addOferta(u5a);
		ofertaService.addOferta(u5b);
		ofertaService.addOferta(u5c);

		// Realizamos las compras
		ofertaService.realizaCompra(true, u2a, user1);
		ofertaService.realizaCompra(true, u2b, user1);
		ofertaService.realizaCompra(true, u1a, user2);
		ofertaService.realizaCompra(true, u5b, user2);
		ofertaService.realizaCompra(true, u4a, user3);
		ofertaService.realizaCompra(true, u5a, user3);
		ofertaService.realizaCompra(true, u2c, user4);
		ofertaService.realizaCompra(true, u3b, user4);
		ofertaService.realizaCompra(true, u4b, user5);
		ofertaService.realizaCompra(true, u4c, user5);
	}

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):

	// Miguel
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\MiguelUni\\Desktop\\TrabajoUniversidadMiguel\\Tercero\\SDI\\Sesion 5\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	// Alex
//	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
//  static String Geckdriver024 = "C:\\Users\\MiguelUni\\Desktop\\TrabajoUniversidadMiguel\\Tercero\\SDI\\Sesion 5\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8080";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		initdb();
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// [Prueba1] Registro de Usuario con datos válidos.
	@Test
	public void Prueba1() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "a@email.com", "Josefo", "Perez", "123456", "123456");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "a@email.com");
	}

	// [Prueba2] Registro de Usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos).
	@Test
	public void Prueba2() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "", "", "", "123456", "123456");
		// Comprobamos que no cambiamos de página y que no sale mensaje de error
		PO_View.checkElement(driver, "text", "Regístrate como usuario");
		SeleniumUtils.textoNoPresentePagina(driver, "debe");
	}

	// [Prueba3] Registro de Usuario con datos inválidos (repetición de contraseña
	// inválida).
	@Test
	public void Prueba3() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "a@email.com", "Josefo", "Perez", "123456", "456");
		// Comprobamos que no cambiamos de página y que sale el mensaje de error de las
		// contraseñas
		PO_View.checkElement(driver, "text", "Las contraseñas no coinciden");
	}

	// [Prueba4] Registro de Usuario con datos inválidos (email existente).
	@Test
	public void Prueba4() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "admin@email.com", "Josefo", "Perez", "123456", "456");
		// Comprobamos que no cambiamos de página y que sale el mensaje de error de las
		// contraseñas
		PO_View.checkElement(driver, "text", "Este email ya existe");
	}

	// [Prueba5] Inicio de sesión con datos válidos (administrador).
	@Test
	public void Prueba5() {

	}

	// [Prueba6] Inicio de sesión con datos válidos (usuario estándar).
	@Test
	public void Prueba6() {

	}

	// [Prueba7] Inicio de sesión con datos inválidos (usuario estándar, campo email
	// y contraseña vacíos).
	@Test
	public void Prueba7() {

	}

	// [Prueba8] Inicio de sesión con datos válidos (usuario estándar, email
	// existente, pero contraseña
	// incorrecta).
	@Test
	public void Prueba8() {

	}

	// [Prueba9] Inicio de sesión con datos inválidos (usuario estándar, email no
	// existente en la aplicación).
	@Test
	public void Prueba9() {

	}

	// [Prueba10] Hacer click en la opción de salir de sesión y comprobar que se
	// redirige a la página de inicio
	// de sesión (Login).
	@Test
	public void Prueba10() {

	}

	// [Prueba11] Comprobar que el botón cerrar sesión no está visible si el usuario
	// no está autenticado
	@Test
	public void Prueba11() {

	}

	// [Prueba12] Mostrar el listado de usuarios y comprobar que se muestran todos
	// los que existen en el
	// sistema.
	@Test
	public void Prueba12() {

	}

	// [Prueba13] Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualiza
	// y que el usuario desaparece.
	@Test
	public void Prueba13() {

	}

	// [Prueba14] Ir a la lista de usuarios, borrar el último usuario de la lista,
	// comprobar que la lista se actualiza
	// y que el usuario desaparece.
	@Test
	public void Prueba14() {

	}

	// [Prueba15] Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la
	// lista se actualiza y que los
	// usuarios desaparecen
	@Test
	public void Prueba15() {

	}

	// [Prueba16] Ir al formulario de alta de oferta, rellenarla con datos válidos y
	// pulsar el botón Submit.
	// Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
	@Test
	public void Prueba16() {

	}

	// [Prueba17] Ir al formulario de alta de oferta, rellenarla con datos inválidos
	// (campo título vacío) y pulsar
	// el botón Submit. Comprobar que se muestra el mensaje de campo obligatorio.
	@Test
	public void Prueba17() {

	}

	// [Prueba18] Mostrar el listado de ofertas para dicho usuario y comprobar que
	// se muestran todas los que
	// existen para este usuario.
	@Test
	public void Prueba18() {

	}

	// [Prueba19] Ir a la lista de ofertas, borrar la primera oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void Prueba19() {

	}

	// Prueba20] Ir a la lista de ofertas, borrar la última oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void Prueba20() {

	}

	// [Prueba21] Hacer una búsqueda con el campo vacío y comprobar que se muestra
	// la página que
	// corresponde con el listado de las ofertas existentes en el sistema
	@Test
	public void Prueba21() {

	}

	// [Prueba22] Hacer una búsqueda escribiendo en el campo un texto que no exista
	// y comprobar que se
	// muestra la página que corresponde, con la lista de ofertas vacía.
	@Test
	public void Prueba22() {

	}

	// Prueba23] Sobre una búsqueda determinada (a elección del desarrollador),
	// comprar una oferta que deja
	// un saldo positivo en el contador del comprador. Comprobar que el contador se
	// actualiza correctamente
	// en la vista del comprador
	@Test
	public void Prueba23() {

	}

	// [Prueba24] Sobre una búsqueda determinada (a elección del desarrollador),
	// comprar una oferta que deja
	// un saldo 0 en el contador del comprador. Comprobar que el contador se
	// actualiza correctamente en la
	// vista del comprador.
	@Test
	public void Prueba24() {

	}

	// [Prueba25] Sobre una búsqueda determinada (a elección del desarrollador),
	// intentar comprar una oferta
	// que esté por encima de saldo disponible del comprador. Y comprobar que se
	// muestra el mensaje de
	// saldo no suficiente.
	@Test
	public void Prueba25() {

	}

	// [Prueba26] Ir a la opción de ofertas compradas del usuario y mostrar la
	// lista. Comprobar que aparecen
	// las ofertas que deben aparecer.
	@Test
	public void Prueba26() {

	}

	// [Prueba27] Visualizar al menos cuatro páginas haciendo el cambio
	// español/inglés/español
	// (comprobando que algunas de las etiquetas cambian al idioma correspondiente).
	// Página
	// principal/Opciones principales de usuario/Listado de usuarios /Vista de alta
	// de oferta.
	@Test
	public void Prueba27() {

	}

	// [Prueba28] Intentar acceder sin estar autenticado a la opción de listado de
	// usuarios del administrador. Se
	// deberá volver al formulario de login.
	@Test
	public void Prueba28() {

	}

	// [Prueba29] Intentar acceder sin estar autenticado a la opción de listado de
	// ofertas propias de un usuario
	// estándar. Se deberá volver al formulario de login.
	@Test
	public void Prueba29() {

	}

	// [Prueba30] Estando autenticado como usuario estándar intentar acceder a la
	// opción de listado de
	// usuarios del administrador. Se deberá indicar un mensaje de acción prohibida.
	@Test
	public void Prueba30() {

	}

}