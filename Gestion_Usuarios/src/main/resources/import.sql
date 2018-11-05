insert into permisos (nombre, descripcion) values ('ROLE_CREADOR_DE_PERFILES','CREAR PERFILES DE USUARIO')
insert into permisos (nombre, descripcion) values ('ROLE_MODIFICADOR_DE_PERFILES','MODIFICAR PERFILES DE USUARIO')
insert into permisos (nombre, descripcion) values ('ROLE_MODIFICADOR_DE_ESTADO_DE_PERFILES','MODIFICA EL ESTADO DE LOS PERFILES')
insert into permisos (nombre, descripcion) values ('ROLE_ASIGNADOR_DE_PERFILES','ASIGNAR PERFILES A UN USUARIO')
insert into permisos (nombre, descripcion) values ('ROLE_CREADOR_DE_PERMISOS','CREAR PERMISOS DE UN USUARIO')
insert into permisos (nombre, descripcion) values ('ROLE_MODIFICADOR_DE_PERMISOS','MODIFICAR PERMISOS DE USUARIO')
insert into permisos (nombre, descripcion) values ('ROLE_ASIGNADOR_DE_PERMISOS','ASIGNAR PERMISOS A UN PERFIL')
insert into permisos (nombre, descripcion) values ('ROLE_QUITAR_PERMISOS','QUITAR PERMISOS A UN PERFIL')
insert into permisos (nombre, descripcion) values ('ROLE_CREAR_USUARIOS','CREA USUARIOS CON UN PERFIL')
insert into permisos (nombre, descripcion) values ('ROLE_MODIFICADOR_DE_ESTADO_DE_USUARIOS','MODIFICA EL ESTADO')
insert into permisos (nombre, descripcion) values ('ROLE_MODIFICADOR_USUARIOS','MODIFICA LOS DATOS DEL USUARIO')


--debe estar por defecto activo a '1'11
insert into perfiles values (1,'ESTA A CARGO DE UN AREA ASIGNADA',GETDATE(),'JEFE')
insert into perfiles values (1,'ES EL RESPONSABLE DEL CONTROL DE ACTIVIDADES DIARIAS Y MANEJO DE LAS OPERACIONES',GETDATE(),'SUPERVISOR')
insert into perfiles values (1,'ES EL ENCARGADO DE REALIZAR LAS ACTIVIDADES DIARIAS DE LA EMPRESA',GETDATE(),'OPERATIVO')
insert into perfiles values (1,'ES EL ENCARGADO DE GUARDAR TARJETAS DE CREDITO',GETDATE(),'ASISTENTE')

--debe estar por defecto activo a '1'
--
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'ORLANDO','HEREDIA',12345698,'OHEREDIA','oheredia@exact.com.pe','oheredia','$2a$04$GKk2hQxmmUAyVqGQM3I7R.6Ua9av7/7XNz4kDE8ODzmVOFDnTxuEK')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'CHRISTIAN','CAMPOS',75435710,'CCAMPOS','ccampos@exact.com.pe','ccampos','$2a$04$NvvJE0k5kV2g3gIMxzLa0ungVBERTwH3psJ57SbFK51qDBbbAfM7q')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'RONALD','SANTOS',46059112,'RSANTOS','rsantos@exact.com.pe','rsantos','$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'CESAR','BALTAZAR',58963258,'CBALTAZAR','cbaltazar@exact.com.pe','cbaltazar','$2a$04$wqth2G7pFDu1yku6YkDFOu7MJH.T5LjBZpaElTo5BjSwMRTYME8Li')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'KATHERINE','VEGA',79461382,'KVEGA','kvega@exact.com.pe','kvega','$2a$04$VXlH1WjopIud3AagK45V1.lyv9Jpd8.WiCLEEzRR7hppuIpkOvDR2')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'KATHELEEN','MACEDO',23434322,'KMACEDO','kmacedo@exact.com.pe','kmacedo','$2a$04$VXlH1WjopIud3AagK45V1.lyv9Jpd8.WiCLEEzRR7hppuIpkOvDR2')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'KRYSTEL','ARRUE',53684524,'KARRUE','karrue@exact.com.pe','karrue','$2a$04$VXlH1WjopIud3AagK45V1.lyv9Jpd8.WiCLEEzRR7hppuIpkOvDR2')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'MEDALIT','CEVALLOS',95785632,'KVEGA','kvega@exact.com.pe','kvega','$2a$04$VXlH1WjopIud3AagK45V1.lyv9Jpd8.WiCLEEzRR7hppuIpkOvDR2')


insert into perfiles_permisos values (1,1)
insert into perfiles_permisos values (1,9)
insert into perfiles_permisos values (1,10)
insert into perfiles_permisos values (2,2)
insert into perfiles_permisos values (2,3)
insert into perfiles_permisos values (2,4)
insert into perfiles_permisos values (2,5)
insert into perfiles_permisos values (2,6)
insert into perfiles_permisos values (2,7)
insert into perfiles_permisos values (2,8)
insert into perfiles_permisos values (3,5)
insert into perfiles_permisos values (3,6)
insert into perfiles_permisos values (3,7)
insert into perfiles_permisos values (4,6)
insert into perfiles_permisos values (3,11)

insert into usuario_perfil values (1,GETDATE(),'',1,1)
insert into usuario_perfil values (1,GETDATE(),'',2,2)
insert into usuario_perfil values (1,GETDATE(),'',3,3)
insert into usuario_perfil values (1,GETDATE(),'',4,4)
insert into usuario_perfil values (1,GETDATE(),'',4,5)
insert into usuario_perfil values (1,GETDATE(),'',1,6)
insert into usuario_perfil values (1,GETDATE(),'',2,7)
insert into usuario_perfil values (1,GETDATE(),'',3,8)

insert into tipo_autenticacion (nombre, descripcion) values ('AUTENTICACION BASICA','INGRESO CON USUARIO Y CONTRASEÑA')


