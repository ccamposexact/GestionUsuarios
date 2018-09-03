insert into permisos (nombre, descripcion) values ('CREADOR DE PERFILES','CREAR PERFILES DE USUARIO')
insert into permisos (nombre, descripcion) values ('MODIFICADOR DE PERFILES','MODIFICAR PERFILES DE USUARIO')
insert into permisos (nombre, descripcion) values ('MODIFICADOR DE ESTADO DE PERFILES','MODIFICA EL ESTADO DE LOS PERFILES')
insert into permisos (nombre, descripcion) values ('ASIGNADOR DE PERFILES','ASIGNAR PERFILES A UN USUARIO')
insert into permisos (nombre, descripcion) values ('CREADOR DE PERMISOS','CREAR PERMISOS DE UN USUARIO')
insert into permisos (nombre, descripcion) values ('MODIFICADOR DE PERMISOS','MODIFICAR PERMISOS DE USUARIO')
insert into permisos (nombre, descripcion) values ('ASIGNADOR DE PERMISOS','ASIGNAR PERMISOS A UN PERFIL')
insert into permisos (nombre, descripcion) values ('QUITAR PERMISOS','QUITAR PERMISOS A UN PERFIL')
insert into permisos (nombre, descripcion) values ('CREAR USUARIOS','CREA USUARIOS CON UN PERFIL')
insert into permisos (nombre, descripcion) values ('MODIFICADOR DE ESTADO DE USUARIOS','MODIFICA EL ESTADO')
insert into permisos (nombre, descripcion) values ('MODIFICADOR USUARIOS','MODIFICA LOS DATOS DEL USUARIO')


--debe estar por defecto activo a '1'
insert into perfiles values (1,'ESTA A CARGO DE UN AREA ASIGNADA',GETDATE(),'JEFE')
insert into perfiles values (1,'ES EL RESPONSABLE DEL CONTROL DE ACTIVIDADES DIARIAS Y MANEJO DE LAS OPERACIONES',GETDATE(),'SUPERVISOR')
insert into perfiles values (1,'ES EL ENCARGADO DE REALIZAR LAS ACTIVIDADES DIARIAS DE LA EMPRESA',GETDATE(),'OPERATIVO')
insert into perfiles values (1,'ES EL ENCARGADO DE GUARDAR TARJETAS DE CREDITO',GETDATE(),'ASISTENTE')

--debe estar por defecto activo a '1'
insert into usuarios (activo, nombre, apellido, dni, matricula, correo) values (1,'KATHERINE','VEGA',79461382,'S25113','kvega@exact.com.pe')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo) values (1,'RONALD','SANTOS',46059112,'S13369','rsantos@exact.com.pe')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo) values (1,'CHRISTIAN','CAMPOS',75435710,'S57188','ccampos@exact.com.pe')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo) values (1,'ORLANDO','HEREDIA',12345698,'S46697','oheredia@exact.com.pe')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo) values (1,'CESAR','BALTAZAR',58963258,'S45687','cbaltazar@exact.com.pe')

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





