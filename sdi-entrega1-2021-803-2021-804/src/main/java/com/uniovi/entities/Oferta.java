package com.uniovi.entities;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Oferta {
	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private String descripcion;
	private Date fechaAlta;
	private double cantidad;
	private boolean comprada;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "comprador_id", nullable=true)
	private User comprador;

	public Oferta(Long id,String titulo ,String descripcion, Date fechaAlta, double cantidad) {
		super();
		this.id = id;
		this.titulo=titulo;
		this.descripcion = descripcion;
		this.fechaAlta=fechaAlta;
		this.cantidad = cantidad;
		setComprada(false);
	}
	
	public Oferta(String titulo ,String descripcion, Date fechaAlta, double cantidad, User usuario) {
		super();
		this.titulo=titulo;
		this.descripcion = descripcion;
		this.fechaAlta=fechaAlta;
		this.cantidad = cantidad;
		this.user=usuario;
		setComprada(false);
	}
	
	public Oferta() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public boolean isComprada() {
		return comprada;
	}

	public void setComprada(boolean comprada) {
		this.comprada = comprada;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getComprador() {
		return comprador;
	}

	public void setComprador(User user) {
		this.comprador = user;
	}

	@Override
	public String toString() {
		return "Oferta [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fechaAlta=" + fechaAlta
				+ ", cantidad=" + cantidad + ", comprada=" + comprada + ", user=" + user + ", userComprador="
				+ comprador + "]";
	}
	
}
