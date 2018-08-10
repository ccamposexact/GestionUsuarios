--debe estar por defecto activo a '1'
insert into usuarios values (1,'SANTOS','rsantos@exact.com.pe','RONALD')
insert into usuarios values (1,'CAMPOS','ccampos@exact.com.pe','CHRISTIAN')
insert into usuarios values (1,'HEREDIA','oheredia@exact.com.pe','ORLANDO')
insert into usuarios values (1,'Doc','elDoc@exact.com.pe','YoElDoc')

--debe estar por defecto activo a '1'
insert into perfiles values (1,'Es el responsable del control de actividades diarias y manejo de las operaciones. Además tiene personal a su cargo',GETDATE(),'SUPERVISOR')
insert into perfiles values (1,'Es el encargado de realizar las actividades diarias de la empresa',GETDATE(),'OPERATIVO')
insert into perfiles values (1,'Es el encargado de guardar tarjetas de credito',GETDATE(),'ASISTENTE')


insert into permisos values ('CREADOR DE PERFILES','TIENE EL PERMISO DE CREAR PERFILES DE USUARIO')
insert into permisos values ('MODIFICADOR DE PERFILES','TIENE EL PERMISO DE MODIFICAR PERFILES DE USUARIO')
insert into permisos values ('DESACTIVADOR DE PERFILES','TIENE EL PERMISO DE DESACTIVAR PERFILES DE USUARIO')
insert into permisos values ('ASIGNADOR DE PERFILES','TIENE EL PERMISO DE ASIGNAR PERFILES A UN USUARIO')
insert into permisos values ('CREADOR DE PERMISOS','TIENE EL PERMISO DE CREAR PERMISOS DE USUARIO')
insert into permisos values ('MODIFICADOR DE PERMISOS','TIENE EL PERMISO DE MODIFICAR PERMISOS DE USUARIO')
insert into permisos values ('ASIGNADOR DE PERMISOS','TIENE EL PERMISO DE ASIGNAR PERMISOS A UN PERFIL')



insert into usuario_perfil values (1,GETDATE(),'',1,1)
insert into usuario_perfil values (1,GETDATE(),'',2,2)
insert into usuario_perfil values (1,GETDATE(),'',3,3)

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



