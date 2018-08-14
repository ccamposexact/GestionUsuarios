package com.gestionusuario.app.controller;







import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.gestionusuario.app.entity.Perfil;

import com.gestionusuario.app.enumerator.PermisosLista;
import com.gestionusuario.app.service.PerfilService;
import com.gestionusuario.app.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/gestionusuario")
public class GestionUsuarioController {
	
	@Autowired
	private PerfilService perfilservice;
	
	@Autowired
	private UsuarioService usuarioservice;
	
	
	public UsuarioService getUsuarioservice() {
		return usuarioservice;
	}



	public void setUsuarioservice(UsuarioService usuarioservice) {
		this.usuarioservice = usuarioservice;
	}



	public PerfilService getPerfilservice() {
		return perfilservice;
	}



	public void setPerfilservice(PerfilService perfilservice) {
		this.perfilservice = perfilservice;
	}


	
	@RequestMapping(value = "/CrearPerfil", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String crearPerfil(@RequestBody String request) throws Exception {
		
		int rpta;
		int validar;
		ObjectMapper mapper = new ObjectMapper();
		JSONObject requestJson = new JSONObject(request);		
		String idUsuario = requestJson.getString("idUsuario");
		Perfil perfil = mapper.readValue(requestJson.getString("Perfil"), Perfil.class);
		String permisos = requestJson.getString("Permisos");
		System.out.println(permisos);
		
		 
		rpta=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario),PermisosLista.CreadorPerfil);
		validar=this.getPerfilservice().ValidarFormatoPerfil(perfil.getNombre());
		
		
		if(rpta==1) 
		{	
			if(validar==1)
			{
				try {
					this.getPerfilservice().insertar(perfil);
					} catch (Exception e) {
					e.printStackTrace();
				}
				
				return "{\"RPTA\":\"SE REGISTRO PERFIL\"}";
			}
			else
				
				return "{\"RPTA\":\"FORMATO INCORRECTO\"}";
		}
		else 
		{
			return "{\"RPTA\":\"USUARIO NO AUTORIZADO\"}";
		}
				
			
	}
	
	
	@RequestMapping(value = "/AsignarPermisosPerfiles", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String AsignarPermisosPerfiles(@RequestBody String request) throws Exception {
		
		int vasig=0;
		int vperm=0;
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		String idPerfil = requestJson.getString("idPerfil");
		String idPermiso = requestJson.getString("idPermiso");
		
		vperm=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario) ,PermisosLista.AsignadorPermisos);
		vasig=this.getPerfilservice().ValidarAsignacionPermisos(Long.parseLong(idPerfil), Long.parseLong(idPermiso));
		
		if(vperm==1) {
			
			if(vasig==1) {
				try {
					this.getPerfilservice().AsignarPermisosAPerfiles(Long.parseLong(idPerfil), Long.parseLong(idPermiso));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "{\"RPTA\":\"SE AGREGARON LOS PERMISOS AL PERFIL\"}";
			}
			else
				return "{\"RPTA\":\"PERMISOS NO VALIDO\"}";
		}
		
		return "{\"RPTA\":\"USUARIO NO AUTORIZADO\"}";
		
	}
	
	@RequestMapping(value = "/QuitarPermisosPerfiles", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.DELETE)
	public @ResponseBody String QuitarPermisosPerfiles(@RequestBody String request) throws Exception {
		
		int vasig=0;
		int vperm=0;
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		String idPerfil = requestJson.getString("idPerfil");
		String idPermiso = requestJson.getString("idPermiso");
		
		
		vperm=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario) ,PermisosLista.QuitarPermisos);
		vasig=this.getPerfilservice().ValidarAsignacionPermisos(Long.parseLong(idPerfil), Long.parseLong(idPermiso));
		
		if(vperm==1)
		{
			
			if(vasig==1)
			{
				try {
					this.getPerfilservice().QuitarPermisosAPerfiles(Long.parseLong(idPerfil), Long.parseLong(idPermiso));
				} catch (Exception e) {
					e.printStackTrace();
				}
					return "{\"RPTA\":\"SE QUITARON LOS PERMISOS AL PERFIL\"}";
			}
			else
				return "{\"RPTA\":\"PERMISO NO VALIDO \"}";
			
		}
			return "{\"RPTA\":\"USUARIO NO AUTORIZADO\"}";
			
		
		}
	
	@RequestMapping(value = "/ModificarPerfil", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.PATCH)
	public @ResponseBody String ModificarPerfil(@RequestBody String request) throws Exception{
		
		int rpta;
		int validar;
		int existe;
		Perfil perfil = new Perfil();
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		String idPerfil = requestJson.getString("idPerfil");
		String nombre = requestJson.getString("nombre");
		String descripcion = requestJson.getString("descripcion");
		System.out.println(idPerfil);
		
		perfil.setIdPerfil(Long.parseLong(idPerfil));
		perfil.setNombre(nombre);
		perfil.setDescripcion(descripcion);
		
		
		rpta=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario) ,PermisosLista.ModificadorPerfil);
		validar=this.getPerfilservice().ValidarFormatoPerfil(perfil.getNombre());
		
		if(rpta==1)
		{
			if(validar==1) {
				try {
					existe=this.perfilservice.modificar(perfil);
					if(existe==0) {
						return "{\"RPTA\":\"NO EXISTE PERFIL\"}";
					}
				}catch (Exception e){
					e.printStackTrace();
				}
				
				return "{\"RPTA\":\"PERFIL MODIFICADO\"}";
				
			}
			else 
				return "{\"RPTA\":\"FORMATO INCORRECTO\"}";
			}
		
		return "{\"RPTA\":\"USUARIO NO AUTORIZADO\"}";
		
	}
	
	
	@RequestMapping(value = "/DesactivarPerfil", produces = "application/json; charset=utf-8", method = RequestMethod.DELETE)
	public @ResponseBody String DesactivarPerfil(@RequestBody String request) throws Exception {

		int rpta;
		Perfil perfil = new Perfil();
		JSONObject requestJson = new JSONObject(request);		
		String idUsuario = requestJson.getString("idUsuario");
		String idPerfil = requestJson.getString("idPerfil");
		perfil.setIdPerfil(Long.parseLong(idPerfil));
		
		rpta=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario),PermisosLista.DesactivadorPerfil);
		
		if(rpta==1)
		{
			try {
				this.getPerfilservice().eliminar(perfil);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return "{\"RPTA\":\"SE DESACTIVO PERFIL\"}";
		}
		else
			return "{\"RPTA\":\"USUARIO NO AUTORIZADO\"}";
		
	}
	


}
