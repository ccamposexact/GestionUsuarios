package com.gestionusuario.app.entity;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;





@NamedStoredProcedureQueries(
		{
				@NamedStoredProcedureQuery(
						name="usuario.validarCrear", 
						procedureName="ValidarCrearPerfil",
						resultClasses= Usuario.class,
						parameters={
								@StoredProcedureParameter(mode=ParameterMode.IN,name="id",type=Long.class)
							}					
				)
				
		}
	)

@Entity
@Table(name="Usuarios")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;
	
	private String nombre;
	private String apellido;
	private String correo;
	protected String activo;
	
	
	
	public Usuario() {
		this.setActivo("1");
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public Usuario(int idUsuario, String nombre, String apellido, String correo, String activo) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.activo= activo;
	}
	
	
	
	
	
	
	
}
