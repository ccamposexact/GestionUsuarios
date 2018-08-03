package com.gestionusuario.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Sesion implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSesion;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date iSesion;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fSesion;
	
	@ManyToOne
	private Usuario usuario;	
	
	@ManyToOne
	private Tipo_Autenticacion tipo_autenticacion;

	public Long getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Long idSesion) {
		this.idSesion = idSesion;
	}

	public Date getiSesion() {
		return iSesion;
	}

	public void setiSesion(Date iSesion) {
		this.iSesion = iSesion;
	}

	public Date getfSesion() {
		return fSesion;
	}

	public void setfSesion(Date fSesion) {
		this.fSesion = fSesion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tipo_Autenticacion getTipo_autenticacion() {
		return tipo_autenticacion;
	}

	public void setTipo_autenticacion(Tipo_Autenticacion tipo_autenticacion) {
		this.tipo_autenticacion = tipo_autenticacion;
	}
	
	
	
	
}
