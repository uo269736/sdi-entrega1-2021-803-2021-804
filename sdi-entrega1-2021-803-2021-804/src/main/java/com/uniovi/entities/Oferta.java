package com.uniovi.entities;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Oferta {
	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private String descripcion;
	private Date fechaAlta;
	private double cantidad;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Oferta(Long id,String titulo ,String descripcion, Date fechaAlta, double cantidad) {
		super();
		this.id = id;
		this.titulo=titulo;
		this.descripcion = descripcion;
		this.fechaAlta=fechaAlta;
		this.cantidad = cantidad;
	}
	
	public Oferta(String titulo ,String descripcion, Date fechaAlta, double cantidad, Usuario usuario) {
		super();
		this.titulo=titulo;
		this.descripcion = descripcion;
		this.fechaAlta=fechaAlta;
		this.cantidad = cantidad;
		this.usuario=usuario;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Oferta [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fechaAlta=" + fechaAlta
				+ ", cantidad=" + cantidad + ", usuario=" + usuario + "]";
	}
	
	


}
