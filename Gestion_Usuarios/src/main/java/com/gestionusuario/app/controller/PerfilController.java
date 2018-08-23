																																																																																																																																																																																																																																																																																																																																																																																																																																																													package com.gestionusuario.app.controller;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionusuario.app.entity.Perfil;
import com.gestionusuario.app.enumerator.PermisosLista;
import com.gestionusuario.app.service.PerfilService;
import com.gestionusuario.app.service.UsuarioService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
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
		int existe;
		int uactivo;
		
		ObjectMapper mapper = new ObjectMapper();
		JSONObject requestJson = new JSONObject(request);		
		String idUsuario = requestJson.getString("idUsuario");
		Perfil perfil = mapper.readValue(requestJson.getString("Perfil"), Perfil.class);
	
		existe=this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));
		
		if(existe==1) {
			
			uactivo = this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			
			if(uactivo==1) {
				
				rpta=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario),PermisosLista.CreadorPerfil);
				
				if(rpta==1) 
				{	
					validar=this.getPerfilservice().ValidarFormatoPerfil(perfil.getNombre());
					
					switch(validar) {
					case 1: return "{\"RPTA\":\"NOMBRE DE PERFIL INCORRECTO\"}";
					case 2: return "{\"RPTA\":\"FORMATO DE NOMBRE INCORRECTO (SOLO LETRAS)\"}";
					default:
						
							try {
								this.getPerfilservice().insertar(perfil);
								} catch (Exception e) {
								e.printStackTrace();
							}
							return "{\"RPTA\":\"SE REGISTRO PERFIL\"}";
					}
				}
				else 
				{
					return "{\"RPTA\":\"EL USUARIO NO TIENE EL PERMISO PARA REALIZAR ESTA ACCIÓN\"}";
				}
			}
			else
			{
			return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION ESTA DESACTIVADO\"}";
			}
		}
		return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION NO EXISTE\"}";
	}
	
	@RequestMapping(value = "/AsignarPermisosPerfiles", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String AsignarPermisosPerfiles(@RequestBody String request) throws Exception {
		
		int existe=0;
		int vperm=0;
		int uexiste;
		int uactivo=0;
		boolean perm=false;
		ArrayList<Integer> Lista = new ArrayList<Integer>();
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		String idPerfil = requestJson.getString("idPerfil");
		JSONArray lstPermisos = requestJson.getJSONArray("Permisos");
		perm=lstPermisos.isNull(0);
		
		
		
		uexiste=this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));
		
		if(uexiste==1) {
			uactivo = this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			if(uactivo==1) {
				vperm=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario) ,PermisosLista.AsignadorPermisos);
				if(vperm==1) 
				{
					if(perm) 
					{
						this.getPerfilservice().BorrarPermisosAPerfil(Long.parseLong(idPerfil));
						return "{\"RPTA\":\"SE ELIMINARON LOS PERMISOS \"}";
					}
					else
					{
						for(int i = 0; i < lstPermisos.length(); ++i) {
							JSONObject rec = lstPermisos.getJSONObject(i);
							Long id =rec.getLong("idPermiso");
							existe=getPerfilservice().ValidarPermisosExistentes(id);
							Lista.add(existe);
						}
						if(Lista.contains(0)) 
						{
							return "{\"RPTA\":\"EL PERMISO QUE INTENTA AGREGAR NO EXISTE\"}";
						}
						else 
						{
							this.getPerfilservice().BorrarPermisosAPerfil(Long.parseLong(idPerfil));
							for (int i = 0; i < lstPermisos.length(); ++i) {
								JSONObject rec = lstPermisos.getJSONObject(i);
								Long id =rec.getLong("idPermiso");
								this.getPerfilservice().AsignarPermisosAPerfil(Long.parseLong(idPerfil),id);
							}
							return "{\"RPTA\":\"EL PERFIL HA SIDO ACTUALIZADO CORRECTAMENTE\"}";
						}
					}
				}
				else 
				{
					return "{\"RPTA\":\"EL USUARIO NO TIENE EL PERMISO PARA REALIZAR ESTA ACCIÓN\"}";
				}
			}
			else 
			{
				return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION ESTA DESACTIVADO\"}";
			}
		}
		return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION NO EXISTE\"}";
		
	}
	
	
	@RequestMapping(value = "/ModificarPerfil", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.PATCH)
	public @ResponseBody String ModificarPerfil(@RequestBody String request) throws Exception{
		
		int rpta;
		int validar;
		int existe;
		int uexiste;
		int uactivo;
		Perfil perfil = new Perfil();
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		String idPerfil = requestJson.getString("idPerfil");
		String nombre = requestJson.getString("nombre");
		String descripcion = requestJson.getString("descripcion");
		
		perfil.setIdPerfil(Long.parseLong(idPerfil));
		perfil.setNombre(nombre);
		perfil.setDescripcion(descripcion);
		
		uexiste=this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));
		
		if(uexiste==1) {
			
			uactivo = this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			
			if(uactivo==1) 
			{
				rpta=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario) ,PermisosLista.ModificadorPerfil);
				
				if(rpta==1)
				{
					validar=this.getPerfilservice().ValidarFormatoPerfil(perfil.getNombre());
					
					switch(validar) {
					case 1: return "{\"RPTA\":\"NOMBRE DE PERFIL INCORRECTO\"}";
					case 2: return "{\"RPTA\":\"FORMATO DE NOMBRE INCORRECTO (SOLO LETRAS)\"}";
					default:
						try {
							existe=this.perfilservice.modificar(perfil);
							if(existe==0) {
								return "{\"RPTA\":\"NO EXISTE PERFIL\"}";
							}
						}catch (Exception e){
							e.printStackTrace();
						}
						return "{\"RPTA\":\"REGISTROS ACTUALIZADOS\"}";
					}
				}
				else 
				{
					return "{\"RPTA\":\"EL USUARIO NO TIENE EL PERMISO PARA REALIZAR ESTA ACCIÓN\"}";
				}
			}
			else 
			{
				return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION ESTA DESACTIVADO\"}";
			}
		}
		return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION NO EXISTE\"}";
		
	}
	
	@RequestMapping(value = "/DesactivarPerfil", produces = "application/json; charset=utf-8", method = RequestMethod.DELETE)
	public @ResponseBody String DesactivarPerfil(@RequestBody String request) throws Exception {

		int rpta;
		int existe;
		int uactivo;
		Perfil perfil = new Perfil();
		JSONObject requestJson = new JSONObject(request);		
		String idUsuario = requestJson.getString("idUsuario");
		String idPerfil = requestJson.getString("idPerfil");
		perfil.setIdPerfil(Long.parseLong(idPerfil));
		
		existe=this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));
		
		if(existe==1) {
			
			uactivo = this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			
			if(uactivo==1) 
			{
				rpta=this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario),PermisosLista.DesactivadorPerfil);
				
				if(rpta==1)
				{
					try {
						this.getPerfilservice().eliminar(perfil);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return "{\"RPTA\":\"PERFIL DESACTIVADO\"}";
				}
				else
					return "{\"RPTA\":\"EL USUARIO NO TIENE EL PERMISO PARA REALIZAR ESTA ACCIÓN\"}";
				
			}else
			{
				return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION ESTA DESACTIVADO\"}";
			}
			
		}
		
		return "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION NO EXISTE\"}";
		
	}
	
}
