--debe estar por defecto activo a '1'
insert into usuarios values (1,'SANTOS','rsantos@exact.com.pe','RONALD')
insert into usuarios values (1,'CAMPOS','ccampos@exact.com.pe','CHRISTIAN')
insert into usuarios values (1,'HEREDIA','oheredia@exact.com.pe','ORLANDO')

--debe estar por defecto activo a '1'
insert into perfiles values (1,'Es el responsable del control de actividades diarias y manejo de las operaciones. Además tiene personal a su cargo',GETDATE(),'SUPERVISOR')
insert into perfiles values (1,'Es el encargado de realizar las actividades diarias de la empresa',GETDATE(),'OPERATIVO')
insert into perfiles values (1,'Es el encargado de guardar tarjetas de credito',GETDATE(),'ASISTENTE')


insert into permisos values ('TIENE LA CAPACIDAD DE CREAR PERFILES DE USUARIOS','CREAR PERFILES')
insert into permisos values ('TIENE LA CAPACIDAD MODIFICAR PERFILES EXISTENTES','MODIFICAR PERFILES')
insert into permisos values ('TIENE LA CAPACIDAD DE CREAR PERMISOS DE PERFILES','CREAR PERMISOS')
insert into permisos values ('TIENE LA CAPACIDAD DE MODIFICAR PERMISOS EXISTENTES','MODIFICAR PERMISOS')
insert into permisos values ('TIENE LA CAPACIDAD DE ASIGNAR PERMISOS A LOS PERFILES','ASIGNAR PERMISOS')



insert into usuario_perfil values (1,GETDATE(),'',1,1)
insert into usuario_perfil values (1,GETDATE(),'',2,2)
insert into usuario_perfil values (1,GETDATE(),'',3,3)

insert into perfiles_permisos values (1,1)
insert into perfiles_permisos values (1,2)
insert into perfiles_permisos values (2,2)



