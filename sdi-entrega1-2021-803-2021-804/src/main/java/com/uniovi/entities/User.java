package com.uniovi.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String apellidos;
	private String email;
	
	private String rol;

	private String contraseña;
	@Transient // propiedad que no se almacena e la tabla.
	private String contraseñaRepetida;
	
	private double saldo;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<Oferta> ofertas;

	public User(Long id,String nombre ,String apellidos, String email) {
		super();
		this.id = id;
		this.nombre=nombre;
		this.apellidos = apellidos;
		this.email=email;
	}
	
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getContraseñaRepetida() {
		return contraseñaRepetida;
	}

	public void setContraseñaRepetida(String contraseñaRepetida) {
		this.contraseñaRepetida = contraseñaRepetida;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Set<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(Set<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", rol="
				+ rol + ", contraseña=" + contraseña + ", contraseñaRepetida=" + contraseñaRepetida + ", saldo=" + saldo
				+ ", ofertas=" + ofertas + "]";
	}
	
	

}
