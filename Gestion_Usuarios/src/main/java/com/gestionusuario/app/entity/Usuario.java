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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;





@NamedStoredProcedureQueries(
		{
			
			@NamedStoredProcedureQuery(
					name="usuario.InsertarUsuario", 
					procedureName="InsertarUsuario",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="nombre",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="apellido",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="correo",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="dni",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="matricula",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="idUsuario",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="usuario.ModificarUsuario", 
					procedureName="ModificarUsuario",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="nombre",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="apellido",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="dni",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="matricula",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="correo",type=String.class)
							
						}
				),
			
			@NamedStoredProcedureQuery(
					name="usuario.ValidarUsuarioExistente", 
					procedureName="ValidarUsuarioExistente",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="rpta",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="usuario.ModificarPerfilUsuario", 
					procedureName="ModificarPerfilUsuario",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idPerfil",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="usuario.ValidarUsuarioActivo", 
					procedureName="ValidarUsuarioActivo",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="rpta",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="usuario.ValidarDatosExistentes", 
					procedureName="ValidarDatosExistentes",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="dni",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="correo",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="matricula",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="rpta",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(

					name="usuario.ValidarSiActivaDesactiva", 
					procedureName="ValidarSiActivaDesactiva",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuarioDest",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="activo",type=Integer.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="rpta",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="usuario.AsignarPerfilAUsuario", 
					procedureName="AsignarPerfilAUsuario",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idPerfil",type=Long.class)
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
	private Long idUsuario;
	
	private String nombre;
	private String apellido;
	protected int activo;
	private String dni;
	private String matricula;
	
	
	private String correo;
	
	
	public Usuario() {
		
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Usuario(Long idUsuario, String nombre, String apellido, int activo, String dni, String matricula,
			String correo) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.activo = activo;
		this.dni = dni;
		this.matricula = matricula;
		this.correo = correo;
	}

	
	
}
