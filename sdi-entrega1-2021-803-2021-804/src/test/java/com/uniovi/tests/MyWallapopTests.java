package com.uniovi.tests;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.repositories.UserRepository;
import com.uniovi.services.OfertaService;
import com.uniovi.services.RolesService;
import com.uniovi.services.UserService;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_OfertaAddView;
import com.uniovi.tests.pageobjects.PO_OfertaUserList;
import com.uniovi.tests.pageobjects.PO_OfertasCompradasListView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_UserListView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
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
				20, user5);
		Oferta u5b = new Oferta("Calculadora", "Calculadora cientifica", new Date(121, 2, 12), 34, user5);
		Oferta u5c = new Oferta("Bicicleta", "Bicicleta de montaña, de color rojo", new Date(121, 2, 12), 102, user5);

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
	//static String Geckdriver024 = "C:\\Users\\MiguelUni\\Desktop\\TrabajoUniversidadMiguel\\Tercero\\SDI\\Sesion 5\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	// Alex
	static String Geckdriver024 = "C:\\Users\\Usuario\\Desktop\\CallateYa\\SDI\\Sesion5\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

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
	public void Prueba01() {
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
	public void Prueba02() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "", "", "", "123456", "123456");
		// Comprobamos que no cambiamos de página y que no sale mensaje de error
		SeleniumUtils.textoPresentePagina(driver, "Regístrate como usuario");
		SeleniumUtils.textoNoPresentePagina(driver, "debe");
	}

	// [Prueba3] Registro de Usuario con datos inválidos (repetición de contraseña
	// inválida).
	@Test
	public void Prueba03() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "a@email.com", "Josefo", "Perez", "123456", "456");
		// Comprobamos que no cambiamos de página y que sale el mensaje de error de las
		// contraseñas
		SeleniumUtils.textoPresentePagina(driver, "Las contraseñas no coinciden");
		
	}

	// [Prueba4] Registro de Usuario con datos inválidos (email existente).
	@Test
	public void Prueba04() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "admin@email.com", "Josefo", "Perez", "123456", "456");
		// Comprobamos que no cambiamos de página y que sale el mensaje de error de las
		// contraseñas
		PO_View.checkKey(driver,"Error.signup.email.duplicate",PO_Properties.getSPANISH());
	}

	// [Prueba5] Inicio de sesión con datos válidos (administrador).
	@Test
	public void Prueba05() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Comprobamos que entramos en la pagina privada del Admin
		PO_View.checkElement(driver, "text", "admin@email.com");
		SeleniumUtils.textoPresentePagina(driver, "Gestión de Usuarios");
	}

	// [Prueba6] Inicio de sesión con datos válidos (usuario estándar).
	@Test
	public void Prueba06() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "UO101010@uniovi.es", "123456");
		// Comprobamos que entramos en la pagina privada del Usuario Estandar
		PO_View.checkElement(driver, "text", "UO101010@uniovi.es");
		PO_View.checkElement(driver, "text", "Gestión de ofertas");
		SeleniumUtils.textoNoPresentePagina(driver, "Gestión de Usuarios");
	}

	// [Prueba7] Inicio de sesión con datos inválidos (usuario estándar, campo email
	// y contraseña vacíos).
	@Test
	public void Prueba07() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "", "");
		// Comprobamos que no nos movemos y que no sale ningún error
		PO_View.checkElement(driver, "text", "Identifícate");
		PO_View.checkElement(driver, "text", "Email");
		SeleniumUtils.textoNoPresentePagina(driver, "Desconectar");
	}

	// [Prueba8] Inicio de sesión con datos válidos (usuario estándar, email
	// existente, pero contraseña
	// incorrecta).
	@Test
	public void Prueba08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "UO101010@uniovi.es", "111111");
		// Comprobamos que nos sale el error de contraseña o usuario incorrecto
		SeleniumUtils.textoPresentePagina(driver, "El usuario o la contraseña son incorrectos");
	}

	// [Prueba9] Inicio de sesión con datos inválidos (usuario estándar, email no
	// existente en la aplicación).
	@Test
	public void Prueba09() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "123@email.com", "111111");
		// Comprobamos que nos sale el error de contraseña o usuario incorrecto
		SeleniumUtils.textoPresentePagina(driver, "El usuario o la contraseña son incorrectos");
	}

	// [Prueba10] Hacer click en la opción de salir de sesión y comprobar que se
	// redirige a la página de inicio
	// de sesión (Login).
	@Test
	public void Prueba10() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "UO101010@uniovi.es", "123456");
		// Comprobamos que entramos en la pagina privada del Usuario Estandar
		PO_View.checkElement(driver, "text", "UO101010@uniovi.es");
		PO_View.checkElement(driver, "text", "Gestión de ofertas");
		SeleniumUtils.textoNoPresentePagina(driver, "Gestión de Usuarios");
		// Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		//Comprobamos que sale la página de inicar sesión
		SeleniumUtils.textoPresentePagina(driver, "Identifícate");
		SeleniumUtils.textoPresentePagina(driver, "Email:");
	}

	// [Prueba11] Comprobar que el botón cerrar sesión no está visible si el usuario
	// no está autenticado
	@Test
	public void Prueba11() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Probamos si esta el botón desconectar antes de entrar
		SeleniumUtils.textoNoPresentePagina(driver, "Desconectar");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "UO101010@uniovi.es", "123456");
		// Comprobamos que entramos en la pagina privada del Usuario Estandar
		PO_View.checkElement(driver, "text", "UO101010@uniovi.es");
		PO_View.checkElement(driver, "text", "Gestión de ofertas");
		SeleniumUtils.textoNoPresentePagina(driver, "Gestión de Usuarios");
		// Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		//Ahora tras desconectarnos comprobamos si esta el botón desconectarse
		SeleniumUtils.textoNoPresentePagina(driver, "Desconectar");
	}

	// [Prueba12] Mostrar el listado de usuarios y comprobar que se muestran todos
	// los que existen en el sistema.
	@Test
	public void Prueba12() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id, 'users-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		// Pinchamos en la opción de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		PO_UserListView.checkAllUsers(driver, usersService);
	}

	// [Prueba13] Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualiza y que el usuario desaparece.
	@Test
	public void Prueba13() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id, 'users-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		// Pinchamos en la opción de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		//Sacamos la lista de usuarios
		List<User> usuarios=usersService.getUsers();
		//Comprobamos que está el primer usuario
		PO_UserListView.comprobarElementoDeLista(driver, 0, usuarios,true);
		//Seleccionamos los usuarios que queremos eliminar
		PO_UserListView.seleccionarUsuario(driver, 0);
		//Le damos a eliminar 
		elementos = PO_View.checkElement(driver, "free", "//button[contains(@type,'submit')]");
		elementos.get(0).click();
		//Comprobamos que no está el primer elemento de antes
		PO_UserListView.comprobarElementoDeLista(driver, 0, usuarios,false);
	}

	// [Prueba14] Ir a la lista de usuarios, borrar el último usuario de la lista,
	// comprobar que la lista se actualiza y que el usuario desaparece.
	@Test
	public void Prueba14() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id, 'users-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		// Pinchamos en la opción de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		//Sacamos la lista de usuarios
		List<User> usuarios=usersService.getUsers();
		//Comprobamos que está el último usuario (en este caso como es un admin es el antepenultimo)
		PO_UserListView.comprobarElementoDeLista(driver, usuarios.size()-2, usuarios,true);
		//Seleccionamos los usuarios que queremos eliminar
		PO_UserListView.seleccionarUsuario(driver, usuarios.size()-2);
		//Le damos a eliminar 
		elementos = PO_View.checkElement(driver, "free", "//button[contains(@type,'submit')]");
		elementos.get(0).click();
		//Comprobamos que no está el último elemento (penúltimo)
		PO_UserListView.comprobarElementoDeLista(driver, usuarios.size()-2, usuarios,false);
	}

	// [Prueba15] Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la
	// lista se actualiza y que los usuarios desaparecen
	@Test
	public void Prueba15() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id, 'users-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		// Pinchamos en la opción de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		//Sacamos la lista de usuarios
		List<User> usuarios=usersService.getUsers();
		//Comprobamos que están los tres primeros usuarios
		PO_UserListView.comprobarElementoDeLista(driver, usuarios.size()-2, usuarios,true);
		//Seleccionamos los usuarios que queremos eliminar
		PO_UserListView.seleccionarUsuario(driver, 0);
		PO_UserListView.seleccionarUsuario(driver, 1);
		PO_UserListView.seleccionarUsuario(driver, 2);
		//Le damos a eliminar 
		elementos = PO_View.checkElement(driver, "free", "//button[contains(@type,'submit')]");
		elementos.get(0).click();
		//Comprobamos que no están los tres primeros de antes
		PO_UserListView.comprobarElementoDeLista(driver, 0, usuarios,false);
		PO_UserListView.comprobarElementoDeLista(driver, 1, usuarios,false);
		PO_UserListView.comprobarElementoDeLista(driver, 2, usuarios,false);
	}

	// [Prueba16] Ir al formulario de alta de oferta, rellenarla con datos válidos y
	// pulsar el botón Submit.
	// Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
	@Test
	public void Prueba16() {
		// Vamos al formulario de login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "UO101014@uniovi.es", "123456");
		// Creamos la oferta
		PO_OfertaAddView.creaOferta(driver, "Pato de goma", "Vendo el pato de goma que me regalo mi tia cuando era pequeño. Por cierto, es de goma", 4);
		// Vamos al listado de ofertas personales
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/userlist')]");
		elementos.get(0).click();
		// Comprobamos que la oferta creada esta presente en la ultima posicion
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		elementos.get(elementos.size()-1).click();
		SeleniumUtils.textoPresentePagina(driver, "Pato de goma");		
		//Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// [Prueba17] Ir al formulario de alta de oferta, rellenarla con datos inválidos
	// (campo título vacío) y pulsar
	// el botón Submit. Comprobar que se muestra el mensaje de campo obligatorio.
	@Test
	public void Prueba17() {
		// Vamos al formulario de login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "UO101014@uniovi.es", "123456");
		//Creamos oferta no valida
		PO_OfertaAddView.creaOferta(driver, "Pato", "Vendo el pato de goma que me regalo mi tia cuando era pequeño. Por cierto, es de goma", 4);
		PO_OfertaAddView.checkKey(driver, "Error.oferta.titulo.length", PO_Properties.getSPANISH() );
		
		//Rellenamos el formulario con descripcion no valida
		PO_OfertaAddView.fillForm(driver, "Pato de goma", "Vendo pato", String.valueOf(4));
		PO_OfertaAddView.checkKey(driver, "Error.oferta.descripcion.length", PO_Properties.getSPANISH() );
		
		//Rellenamos el formulario con precio no valido
		PO_OfertaAddView.fillForm(driver, "Pato", "Vendo el pato de goma que me regalo mi tia cuando era pequeño. Por cierto, es de goma", String.valueOf(-1));
		PO_OfertaAddView.checkKey(driver, "Error.oferta.cantidad.negativa", PO_Properties.getSPANISH() );
	
		//Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// [Prueba18] Mostrar el listado de ofertas para dicho usuario y comprobar que
	// se muestran todas los que existen para este usuario.
	@Test
	public void Prueba18() {
		// Vamos al formulario de login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "UO101014@uniovi.es", "123456");
		// Seleccionamos el menu ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		// Accedemos a Ver Mis Ofertas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/userlist')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Comprobamos que las ofertas de este usuario esten presentes
		List<Oferta> ofertas = ofertaService.getListaOfertas();
		User usuario = usersService.getUserByEmail("UO101014@uniovi.es");
		List<Oferta> userOfertas = new ArrayList<Oferta>();
		//Eliminamos las ofertas que no sean del usuario
		for(Oferta o : ofertas){
			if(o.getUser().getEmail()==usuario.getEmail())
				userOfertas.add(o);
		}
		//Realizamos las comprobaciones en cada pagina
		PO_OfertaUserList.comprobarAllOfertas(driver, usuario, userOfertas, elementos);
		
		//Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// [Prueba19] Ir a la lista de ofertas, borrar la primera oferta de la lista,
	// comprobar que la lista se actualiza y que la oferta desaparece.
	@Test
	public void Prueba19() {
		// Vamos al formulario de login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "UO101014@uniovi.es", "123456");
		User usuario = usersService.getUserByEmail("UO101014@uniovi.es");
		// Seleccionamos el menu ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		// Accedemos a Ver Mis Ofertas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/userlist')]");
		elementos.get(0).click();
		// Comprobamos que este la primera oferta
		List<Oferta> misOfertas = new ArrayList<Oferta>();
		for(Oferta o : ofertaService.getListaOfertas())
			if(usuario.getEmail().equals(o.getUser().getEmail()))
				misOfertas.add(o);
		int posicion = 0;
		String titulo = misOfertas.get(posicion).getTitulo();
		SeleniumUtils.textoPresentePagina(driver, titulo);
		// Borramos la primera oferta
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/delete')]");
		elementos.get(0).click();
		// Comprobamos que no este la primera oferta
		SeleniumUtils.textoNoPresentePagina(driver, titulo);
		// Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// Prueba20] Ir a la lista de ofertas, borrar la última oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void Prueba20() {
		// Vamos al formulario de login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "UO101014@uniovi.es", "123456");
		User usuario = usersService.getUserByEmail("UO101014@uniovi.es");
		// Seleccionamos el menu ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		// Accedemos a Ver Mis Ofertas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/userlist')]");
		elementos.get(0).click();
		// Comprobamos que este la ultima oferta
		List<Oferta> misOfertas = new ArrayList<Oferta>();
		for(Oferta o : ofertaService.getListaOfertas())
			if(usuario.getEmail().equals(o.getUser().getEmail()))
				misOfertas.add(o);
		int posicion = misOfertas.size()-1;
		String titulo = misOfertas.get(posicion).getTitulo();
		PO_OfertaUserList.comprobarElementoDeLista(driver, posicion, misOfertas, true);
		// Esperamos a que se muestren los enlaces de paginación la lista de notas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nos vamos a la última página
		elementos.get(posicion).click();
		// Borramos la ultima oferta
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/delete')]");
		elementos.get(posicion).click();
		// Comprobamos que no este la primera oferta
		SeleniumUtils.textoNoPresentePagina(driver, titulo);
		// Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// [Prueba21] Hacer una búsqueda con el campo vacío y comprobar que se muestra
	// la página que corresponde con el listado de las ofertas existentes en el sistema
	@Test
	public void Prueba21() {
		// Vamos al formulario de login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "UO101014@uniovi.es", "123456");
		// Seleccionamos el menu ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		// Accedemos a Ver Ofertas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/list')]");
		elementos.get(0).click();
		// Clickamos en Buscar
		elementos = PO_View.checkElement(driver, "free", "//button[contains(@type, 'submit')]");
		elementos.get(0).click();
		// Comprobamos que las ofertas de este usuario esten presentes
		List<Oferta> ofertas = ofertaService.getListaOfertas();
		User usuario = usersService.getUserByEmail("UO101014@uniovi.es");
		//Realizamos las comprobaciones en cada pagina
		PO_OfertaUserList.comprobarAllOfertasPaginaPorPagina(driver, usuario, ofertas, elementos);
		
		//Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// [Prueba22] Hacer una búsqueda escribiendo en el campo un texto que no exista
	// y comprobar que se muestra la página que corresponde, con la lista de ofertas vacía.
	@Test
	public void Prueba22() {
		// Vamos al formulario de login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "UO101014@uniovi.es", "123456");
		// Seleccionamos el menu ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		// Accedemos a Ver Ofertas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/list')]");
		elementos.get(0).click();
		// Realizamos una busqueda de algo inexistente
		elementos = PO_View.checkElement(driver, "free", "//input[contains(@name, 'searchText')]");
		elementos.get(0).click();
		elementos.get(0).clear();
		elementos.get(0).click();
		elementos.get(0).sendKeys("nada");
		// Clickamos en Buscar
		By boton = By.className("btn");
		driver.findElement(boton).click();
		// Comprobamos que no se muestran ofertas
		List<Oferta> ofertas = ofertaService.getListaOfertas();
		for(Oferta o : ofertas)
			SeleniumUtils.textoNoPresentePagina(driver, o.getTitulo());
		
		//Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// [Prueba23] Sobre una búsqueda determinada (a elección del desarrollador),
	// comprar una oferta que deja
	// un saldo positivo en el contador del comprador. Comprobar que el contador se
	// actualiza correctamente
	// en la vista del comprador
	@Test
	public void Prueba23() {
		// Vamos al formulario de login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario -> usuario con saldo muy elevado.
		PO_LoginView.fillForm(driver, "UO101011@uniovi.es", "123456");
		User usuario = usersService.getUserByEmail("UO101011@uniovi.es");
		double saldoInicial = usuario.getSaldo();
		List<Oferta> ofertas = ofertaService.getListaOfertas();
		Oferta oferta = null;
		for(int i=0; i<ofertas.size(); i++)
			if(!ofertas.get(i).isComprada()) {
				oferta = ofertas.get(i);
				break;
			}
		// Seleccionamos el menu ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		// Accedemos a Ver Ofertas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/list')]");
		elementos.get(0).click();
		// Realizamos una compra
		elementos = PO_View.checkElement(driver, "free", "//button[contains(@id, 'buyButton')]");
		elementos.get(0).click();
		// Comprobamos el saldo
		SeleniumUtils.textoPresentePagina(driver, "Saldo: "+(saldoInicial-oferta.getCantidad()));
		
		//Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// [Prueba24] Sobre una búsqueda determinada (a elección del desarrollador),
	// comprar una oferta que deja un saldo 0 en el contador del comprador. 
	// Comprobar que el contador se  actualiza correctamente en la
	// vista del comprador.
	@Test
	public void Prueba24() {
		// Vamos al formulario de login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "UO101014@uniovi.es", "123456");
		//Creamos una oferta que comprara un nuevo usuario
		PO_OfertaAddView.creaOferta(driver, "Balon de baloncesto", "Pelota de baloncesto de maxima calidad, sin estrenar.", 100);
		//Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");	
		//Nos registramos
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "prueba24@email.com", "El Pepe", "Perez", "123456", "123456");
		// Seleccionamos el menu ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		// Accedemos a Ver Ofertas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/list')]");
		elementos.get(0).click();
		// Realizamos la busqueda
		elementos = PO_View.checkElement(driver, "free", "//input[contains(@name, 'searchText')]");
		elementos.get(0).click();
		elementos.get(0).clear();
		elementos.get(0).click();
		elementos.get(0).sendKeys("Balon de baloncesto");
		// Clickamos en Buscar
		By boton = By.className("btn");
		driver.findElement(boton).click();
		// Realizamos la compra que nos deje el saldo a 0
		elementos = PO_View.checkElement(driver, "free", "//button[contains(@id, 'buyButton')]");
		elementos.get(0).click();
		//Comprobamos el saldo a 0
		SeleniumUtils.textoPresentePagina(driver, "Saldo: 0");
		//Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// [Prueba25] Sobre una búsqueda determinada (a elección del desarrollador),
	// intentar comprar una oferta que esté por encima de saldo disponible del 
	// comprador. Y comprobar que se muestra el mensaje de saldo no suficiente.
	@Test
	public void Prueba25() {
		// Vamos al formulario de login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "UO101014@uniovi.es", "123456");
		User usuario = usersService.getUserByEmail("UO101014@uniovi.es");
		// Seleccionamos el menu ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		// Accedemos a Ver Ofertas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/list')]");
		elementos.get(0).click();
		//Buscamos la oferta mas cara "Motocicleta"
		elementos = PO_View.checkElement(driver, "free", "//input[contains(@name, 'searchText')]");
		elementos.get(0).click();
		elementos.get(0).clear();
		elementos.get(0).click();
		elementos.get(0).sendKeys("Motocicleta");
		
		// Clickamos en Buscar
		By boton = By.className("btn");
		driver.findElement(boton).click();
		String saldo = String.valueOf(usuario.getSaldo());
		SeleniumUtils.textoPresentePagina(driver, saldo);
		
		//Intentamos realizar la compra
		elementos = PO_View.checkElement(driver, "free", "//button[contains(@id, 'buyButton')]");
		elementos.get(0).click();
		//No pasa nada y no podemos capturar el error ya que hacemos uso del metodo alert para notificarlo
		driver.navigate().to("http://localhost:8080/");
		//El saldo no ha variado, la compra no se ha realizado
		SeleniumUtils.textoPresentePagina(driver, saldo); 
		//Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// [Prueba26] Ir a la opción de ofertas compradas del usuario y mostrar la
	// lista. Comprobar que aparecen las ofertas que deben aparecer.
	@Test
	public void Prueba26() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "UO101010@uniovi.es", "123456");
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id, 'users-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'ofertas-menu')]/a");
		elementos.get(0).click();
		// Pinchamos en la opción de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'oferta/userlistcompradas')]");
		elementos.get(0).click();
		//Comprobamos que entramos en las lista de compras
		SeleniumUtils.textoPresentePagina(driver, "Mis Compras");
		//Seleccionamos los usuarios que queremos eliminar
		PO_OfertasCompradasListView.comprobarOfertasCompradas(driver,ofertaService, "UO101010@uniovi.es");
		
	}

	// [Prueba27] Visualizar al menos cuatro páginas haciendo el cambio
	// español/inglés/español
	// (comprobando que algunas de las etiquetas cambian al idioma correspondiente).
	// Página  principal/Opciones principales de usuario/Listado de usuarios /Vista de alta
	// de oferta.
	@Test
	public void Prueba27() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "UO101010@uniovi.es", "123456");
		//Comprobamos que cambia de idioma en la página principal
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish",PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		//Comprobamos que cambia de idioma en el menu de opciones de usuario
		PO_HomeView.checkChangeIdiomOptions(driver, "btnSpanish", "btnEnglish",PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		//Comprobamos que cambia de idioma en añadir oferta
		PO_OfertaAddView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish",PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		// Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Comprobamos que cambia de idioma en el listado de usuaios
		PO_UserListView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish",PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
	}

	// [Prueba28] Intentar acceder sin estar autenticado a la opción de listado de
	// usuarios del administrador. Se
	// deberá volver al formulario de login.
	@Test
	public void Prueba28() {
        //Intentamos acceder a lista de usuarios sin identificarnos
		driver.navigate().to("http://localhost:8080/user/list");
		//Comprobamos que vamos a la vista de iniciar sesión
		SeleniumUtils.textoPresentePagina(driver, "Identifícate");
		SeleniumUtils.textoPresentePagina(driver, "Email:");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña:");
	}

	// [Prueba29] Intentar acceder sin estar autenticado a la opción de listado de
	// ofertas propias de un usuario
	// estándar. Se deberá volver al formulario de login.
	@Test
	public void Prueba29() {
		//Intentamos acceder al listado de ofertas
		driver.navigate().to("http://localhost:8080/oferta/userlist");
		//Comprobamos que vamos a la vista de iniciar sesión
		SeleniumUtils.textoPresentePagina(driver, "Identifícate");
		SeleniumUtils.textoPresentePagina(driver, "Email:");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña:");
	}

	// [Prueba30] Estando autenticado como usuario estándar intentar acceder a la
	// opción de listado de
	// usuarios del administrador. Se deberá indicar un mensaje de acción prohibida.
	@Test
	public void Prueba30() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "UO101010@uniovi.es", "123456");
		//Intentamos acceder a lista de usuarios identificados como usuario estandar
		driver.navigate().to("http://localhost:8080/user/list");
		//Comprobamos que aparece el texto que nos prohibe entrar
		SeleniumUtils.textoPresentePagina(driver, "HTTP Status 403 – Forbidden");
		
	}

}