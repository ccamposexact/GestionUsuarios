package com.gestionusuario.app.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="permiso", namespace="com.gestionusuario.app.entity.Permiso")
public class PermisoBean {
	
	
	private Long idPermiso;
	private String nombre;
	private String descripcion;
	
	@XmlElement
	public Long getIdPermiso() {
		return idPermiso;
	}
	public void setIdPermiso(Long idPermiso) {
		this.idPermiso = idPermiso;
	}
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public PermisoBean(Long idPermiso, String nombre, String descripcion) {
		super();
		this.idPermiso = idPermiso;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	
	
	
	

}
