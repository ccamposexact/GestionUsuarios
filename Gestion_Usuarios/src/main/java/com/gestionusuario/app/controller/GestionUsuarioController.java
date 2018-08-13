package com.gestionusuario.app.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.gestionusuario.app.entity.Perfil;
import com.gestionusuario.app.entity.Permiso;
import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.enumerator.PermisosLista;
import com.gestionusuario.app.service.PerfilService;
import com.gestionusuario.app.service.UsuarioService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
			return "{\"RPTA\":\"NO SE REGISTRO PERFIL\"}";
		}
				
			
	}
	
	
	@RequestMapping(value = "/AsignarPermisosPerfiles", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String AsignarPermisosPerfiles(@RequestBody String request) throws Exception {
		
		int validar=0;
		JSONObject requestJson = new JSONObject(request);
		String idPerfil = requestJson.getString("idPerfil");
		String idPermiso = requestJson.getString("idPermiso");
		
		validar=this.getPerfilservice().ValidarAsignacionPermisos(Long.parseLong(idPerfil), Long.parseLong(idPermiso));
		
		if(validar==1) {
			try {
				this.getPerfilservice().AsignarPermisosAPerfiles(Long.parseLong(idPerfil), Long.parseLong(idPermiso));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "{\"RPTA\":\"SE AGREGARON LOS PERMISOS AL PERFIL\"}";
		}
		else
			
			return "{\"RPTA\":\"NO SE AGREGARON LOS PERMISOS\"}";
		
		
	}
	
	@RequestMapping(value = "/QuitarPermisosPerfiles", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String QuitarPermisosPerfiles(@RequestBody String request) throws Exception {
		
		int validar=0;
		JSONObject requestJson = new JSONObject(request);
		String idPerfil = requestJson.getString("idPerfil");
		String idPermiso = requestJson.getString("idPermiso");
		
		try {
			this.getPerfilservice().QuitarPermisosAPerfiles(Long.parseLong(idPerfil), Long.parseLong(idPermiso));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{\"RPTA\":\"SE QUITARON LOS PERMISOS AL PERFIL\"}";
	
	
	//validar=this.getPerfilservice().ValidarAsignacionPermisos(Long.parseLong(idPerfil), Long.parseLong(idPermiso));
	
	
	}
	
	
	
	@RequestMapping(value = "/ModificarPerfil", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String ModificarPerfil(@RequestBody String request) throws Exception{
		
		int rpta;
		int validar;
		Perfil perfil = new Perfil();
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		String idPerfil = requestJson.getString("idPerfil");
		String nombre = requestJson.getString("nombre");
		String descripcion = requestJson.getString("descripcion");
		
		
		perfil.setIdPerfil(Long.parseLong(idPerfil));
		perfil.setNombre(nombre);
		perfil.setDescripcion(descripcion);
		
		rpta=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario),PermisosLista.ModificadorPerfil);
		validar=this.getPerfilservice().ValidarFormatoPerfil(perfil.getNombre());
		
		if(rpta==1)
		{
			if(validar==1) {

				try {
					this.getPerfilservice().modificar(perfil);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return "{\"RPTA\":\"SE MODIFICO PERFIL\"}";
			}
			else
				return "{\"RPTA\":\"FORMATO INCORRECTO\"}";
			
		}else
			
			return "{\"RPTA\":\"NO SE MODIFICO PERFIL\"}";
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
			return "{\"RPTA\":\"NO SE DESACTIVO PERFIL\"}";
		
	}
	


}
