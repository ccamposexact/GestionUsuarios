insert into permisos (nombre, descripcion) values ('ROLE_CREADOR_DE_CAMPAÑAS','CREAR CAMPAÑAS')

--debe estar por defecto activo a '1'11
insert into perfiles values (1,'ADMINISTRADOR DEL SISTEMA',GETDATE(),'ADMINISTRADOR')

--debe estar por defecto activo a '1'
--
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'ORLANDO','HEREDIA',12345698,'OHEREDIA','oheredia@exact.com.pe','oheredia','$2a$04$GKk2hQxmmUAyVqGQM3I7R.6Ua9av7/7XNz4kDE8ODzmVOFDnTxuEK')


insert into perfiles_permisos values (1,1)

insert into usuario_perfil values (1,GETDATE(),'',1,1)

insert into tipo_autenticacion (nombre, descripcion) values ('AUTENTICACION BASICA','INGRESO CON USUARIO Y CONTRASEÑA')


