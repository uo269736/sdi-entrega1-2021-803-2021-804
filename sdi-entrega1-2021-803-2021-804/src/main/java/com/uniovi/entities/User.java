package com.uniovi.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String apellidos;
	@Column(unique=true)
	private String email;
	
	private String rol;

	private String password;
	@Transient // propiedad que no se almacena e la tabla.
	private String passwordConfirm;
	
	private double saldo;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Oferta> ofertas;

	public User(String email,String nombre ,String apellidos) {
		super();
		this.nombre=nombre;
		this.apellidos = apellidos;
		this.email=email;
		setSaldo(100);
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
				+ rol + ", contraseña=" + getPassword() + ", contraseñaRepetida=" + getPasswordConfirm() + ", saldo=" + saldo
				+ ", ofertas=" + ofertas + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String repeatedPassword) {
		this.passwordConfirm = repeatedPassword;
	}

}
