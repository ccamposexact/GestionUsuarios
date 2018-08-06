<<<<<<< HEAD
insert into usuarios values (1,'SANTOS','rsantos@exact.com.pe','RONALD')
insert into usuarios values (1,'CAMPOS','ccampos@exact.com.pe','CHRISTIAN')
insert into usuarios values (1,'HEREDIA','oheredia@exact.com.pe','ORLANDO')


insert into perfiles values (1,'Es el responsable del control de actividades diarias y manejo de las operaciones. Además tiene personal a su cargo',GETDATE(),'Jefe de Operaciones')
insert into perfiles values (1,'Es el encargado de realizar las actividades diarias de la empresa',GETDATE(),'Operativo')
insert into perfiles values (1,'Es el encargado de guardar tarjetas de credito',GETDATE(),'Asistente')


insert into permisos values ('CREAR PERFILES', 'TIENE LA CAPACIDAD DE CREAR PERFILES DE USUARIOS')
insert into permisos values ('MODIFICAR PERFILES', 'TIENE LA CAPACIDAD MODIFICAR PERFILES EXISTENTES')
insert into permisos values ('CREAR PERMISOS', 'TIENE LA CAPACIDAD DE CREAR PERMISOS DE PERFILES')
insert into permisos values ('MODIFICAR PERMISOS', 'TIENE LA CAPACIDAD DE MODIFICAR PERMISOS EXISTENTES')
insert into permisos values ('ASIGNAR PERMISOS', 'TIENE LA CAPACIDAD DE ASIGNAR PERMISOS A LOS PERFILES')


=======
insert into usuarios values ('SANTOS','RONALD','rsantos@exact.com.pe')
insert into usuarios values ('CAMPOS','CHRISTIAN','ccampos@exact.com.pe')
insert into usuarios values ('HEREDIA','ORLANDO','oheredia@exact.com.pe')


insert into perfiles value ('Jefe de Operaciones', '06/08/2018', 'Es el responsable del control de actividades diarias y manejo de las operaciones. Además tiene personal a su cargo', 1)
insert into perfiles value ('Operativo', '03/05/2018', 'Es el encargado de realizar las actividades diarias de la empresa', 1)
insert into perfiles value ('Asistente', '04/03/2018', 'Es el encargado de guardar tarjetas de credito', 0)


insert into permisos values ('CREAR PERFILES, TIENE LA CAPACIDAD DE CREAR PERFILES DE USUARIOS')
insert into permisos values ('MODIFICAR PERFILES, TIENE LA CAPACIDAD MODIFICAR PERFILES EXISTENTES')
insert into permisos values ('CREAR PERMISOS, TIENE LA CAPACIDAD DE CREAR PERMISOS DE PERFILES')
insert into permisos values ('MODIFICAR PERMISOS, TIENE LA CAPACIDAD DE MODIFICAR PERMISOS EXISTENTES')
insert into permisos values ('ASIGNAR PERMISOS, TIENE LA CAPACIDAD DE ASIGNAR PERMISOS A LOS PERFILES')


>>>>>>> branch 'master' of https://github.com/ccamposexact/GestionUsuarios.git
