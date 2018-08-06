package com.gestionusuario.app.service;

public interface GenericService<T> {

	public boolean insertar(T objeto) throws Exception;
	public boolean modificar(T objeto) throws Exception;
	public boolean eliminar (T objeto) throws Exception;
	
}
