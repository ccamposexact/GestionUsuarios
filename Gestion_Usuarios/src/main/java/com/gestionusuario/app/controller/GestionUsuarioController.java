package com.gestionusuario.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.gestionusuario.app.entity.Perfil;
import com.gestionusuario.app.enumerator.PermisosLista;
import com.gestionusuario.app.service.PerfilService;
import com.gestionusuario.app.service.UsuarioService;

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



	@RequestMapping(value = "/agregarPerfil", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String agregarRequestBody(@RequestParam("perfil") Perfil perfil, @RequestParam("idUsuario") Long idUsuario) throws Exception{
		int rpta=0;
		
		rpta=this.getPerfilservice().ValidarPermisos(1,PermisosLista.CreadorPerfil);
		
		if(rpta==1) {
			
			try {
				this.getPerfilservice().insertar(perfil);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "{\"ret\":\"Se registro Perfil\"}";
			
		}else
			
			return "{\"ret\":\"No se registro Perfil\"}";
	}
	
	
	@RequestMapping(value = "/ModificarPerfil", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String ModificarBody(@RequestBody Perfil perfil) throws Exception{
		int rpta=0;
		
		rpta=this.getPerfilservice().ValidarPermisos(1,PermisosLista.ModificadorPerfil);
		
		if(rpta==1) {
			
			try {
				this.getPerfilservice().modificar(perfil);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "{\"ret\":\"Se registro Perfil\"}";
			
		}else
			
			return "{\"ret\":\"No se registro Perfil\"}";
	}
	
	
	@RequestMapping(value = "/DesactivarPerfil/{id}", produces = "application/json; charset=utf-8", method = RequestMethod.DELETE)
	public @ResponseBody String DesactivarPerfil(@PathVariable("id") String id) throws Exception {

		Perfil perfil = new Perfil();
		perfil.setIdPerfil(Long.valueOf(id));
		int rpta=0;
		
		rpta=this.getPerfilservice().ValidarPermisos(2,PermisosLista.DesactivadorPerfil);
		
		if(rpta==1)
		{
			try {
				this.getPerfilservice().eliminar(perfil);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "{\"ret\":\"Se desactivo Perfil\"}";
		}
		else
			return "{\"ret\":\"No Se desactivo Perfil\"}";
		
	}
	
	

	

}
