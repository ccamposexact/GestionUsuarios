package com.gestionusuario.app.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="perfil",namespace="com.gestionusuario.app.entity.Perfil")
public class PerfilBean {

	private Long idPerfil;
	private String nombre;
	private Date fCreacion;
	private String descripcion;
	protected String activo;
	
	public PerfilBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	@XmlElement
	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public Date getfCreacion() {
		return fCreacion;
	}
	public void setfCreacion(Date fCreacion) {
		this.fCreacion = fCreacion;
	}
	@XmlElement
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	public PerfilBean(Long idPerfil, String nombre, Date fCreacion, String descripcion, String activo) {
		super();
		this.idPerfil = idPerfil;
		this.nombre = nombre;
		this.fCreacion = fCreacion;
		this.descripcion = descripcion;
		this.activo = activo;
	}
	
	
	
	
}
