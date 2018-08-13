insert into permisos values ('CREADOR DE PERFILES','CREAR PERFILES DE USUARIO')
insert into permisos values ('MODIFICADOR DE PERFILES','MODIFICAR PERFILES DE USUARIO')
insert into permisos values ('DESACTIVADOR DE PERFILES','DESACTIVAR PERFILES DE USUARIO')
insert into permisos values ('ASIGNADOR DE PERFILES','ASIGNAR PERFILES A UN USUARIO')
insert into permisos values ('CREADOR DE PERMISOS','CREAR PERMISOS DE UN USUARIO')
insert into permisos values ('MODIFICADOR DE PERMISOS','MODIFICAR PERMISOS DE USUARIO')
insert into permisos values ('ASIGNADOR DE PERMISOS','ASIGNAR PERMISOS A UN PERFIL')
--debe estar por defecto activo a '1'
insert into perfiles values (1,'ES EL RESPONSABLE DEL CONTROL DE ACTIVIDADES DIARIAS Y MANEJO DE LAS OPERACIONES',GETDATE(),'SUPERVISOR')
insert into perfiles values (1,'ES EL ENCARGADO DE REALIZAR LAS ACTIVIDADES DIARIAS DE LA EMPRESA',GETDATE(),'OPERATIVO')
insert into perfiles values (1,'ES EL ENCARGADO DE GUARDAR TARJETAS DE CREDITO',GETDATE(),'ASISTENTE')

--debe estar por defecto activo a '1'
insert into usuarios values (1,'SANTOS','rsantos@exact.com.pe','RONALD')
insert into usuarios values (1,'CAMPOS','ccampos@exact.com.pe','CHRISTIAN')
insert into usuarios values (1,'HEREDIA','oheredia@exact.com.pe','ORLANDO')

insert into perfiles_permisos values (1,1)
insert into perfiles_permisos values (1,2)
insert into perfiles_permisos values (1,3)
insert into perfiles_permisos values (1,4)
insert into perfiles_permisos values (1,5)
insert into perfiles_permisos values (1,6)
insert into perfiles_permisos values (1,7)
insert into perfiles_permisos values (2,5)
insert into perfiles_permisos values (2,6)
insert into perfiles_permisos values (2,7)

insert into usuario_perfil values (1,GETDATE(),'',1,1)
insert into usuario_perfil values (1,GETDATE(),'',2,2)
insert into usuario_perfil values (1,GETDATE(),'',3,3)





