insert into usuario values (1, "jmllantenm@uqvirtual.edu", "Pepito Perez", "$2a$10$xOCjYcYbT/FPXxnyVdyudOK/SO53TnXloP0vb386RBj9L5dx1j6CC", "Cra 53", "3141111111");
insert into usuario values (2, "mejia@gmail.com", "Florinda Florez", "$2a$10$xOCjYcYbT/FPXxnyVdyudOK/SO53TnXloP0vb386RBj9L5dx1j6CC", "Cll 25", "3152222222");
insert into usuario values (3, "jmllantenm@uqvirtual.ed", "Juan Lopez", "$2a$10$xOCjYcYbT/FPXxnyVdyudOK/SO53TnXloP0vb386RBj9L5dx1j6CC", "Cra 29", "3143333333");
insert into usuario values (4, "anamejia@email.com", "Ana Mejia", "$2a$10$xOCjYcYbT/FPXxnyVdyudOK/SO53TnXloP0vb386RBj9L5dx1j6CC", "Cll 13", "3144444444");
insert into usuario values (5, "pedromolina@email.com", "Pedro Molina", "$2a$10$xOCjYcYbT/FPXxnyVdyudOK/SO53TnXloP0vb386RBj9L5dx1j6CC", "Cra 19", "3146666666");

insert into moderador values (1,"moderador1@email.com", "Pedro Perez", "$2a$10$xOCjYcYbT/FPXxnyVdyudOK/SO53TnXloP0vb386RBj9L5dx1j6CC");
insert into moderador values (2,"moderador2@email.com", "Sara Mora", "$2a$10$xOCjYcYbT/FPXxnyVdyudOK/SO53TnXloP0vb386RBj9L5dx1j6CC");
insert into moderador values (3,"moderador3@email.com", "Martha Lopez", "$2a$10$xOCjYcYbT/FPXxnyVdyudOK/SO53TnXloP0vb386RBj9L5dx1j6CC");
insert into moderador values (4,"moderador4@email.com", "Diego Arias", "$2a$10$xOCjYcYbT/FPXxnyVdyudOK/SO53TnXloP0vb386RBj9L5dx1j6CC");
insert into moderador values (5,"moderador5@email.com", "Pedro Perez", "$2a$10$xOCjYcYbT/FPXxnyVdyudOK/SO53TnXloP0vb386RBj9L5dx1j6CC");

insert into producto values (1, "AUTORIZADO", "color rojo", "2023-03-01 13:10:05", "2023-08-01 13:10:05", "Blusa", 53000, 30, 2);
insert into producto values (2, "AUTORIZADO", "color cafe", "2023-05-01 10:10:05", "2023-08-01 10:10:05", "Patines", 100000, 10, 1);
insert into producto values (3, "DENEGADO", "color rosado", "2023-04-15 22:10:05", "2023-06-15 22:10:05", "Balon", 10000, 20, 2);
insert into producto values (4, "AUTORIZADO", "material: Carton", "2023-06-20 13:10:05", "2023-08-20 13:10:05", "rascador", 25000, 15, 3);
insert into producto values (5, "DENEGADO", "color azul", "2023-07-01 13:10:05", "2023-09-01 13:10:05", "Pantalon", 53000, 25, 5);

insert into producto_categoria values (1, "MODA");
insert into producto_categoria values (2, "DEPORTES");
insert into producto_categoria values (3, "JUEGOS");
insert into producto_categoria values (4, "MASCOTAS");
insert into producto_categoria values (5, "MODA");

insert into producto_imagen values (1, "ruta imagen", "1");
insert into producto_imagen values (1, "ruta imagen", "2");
insert into producto_imagen values (2, "ruta imagen", "3");
insert into producto_imagen values (3, "ruta imagen", "4");
insert into producto_imagen values (4, "ruta imagen", "5");
insert into producto_imagen values (5, "ruta imagen", "6");

insert into favoritos values (1, 2);
insert into favoritos values (1, 4);
insert into favoritos values (3, 4);
insert into favoritos values (4, 2);
insert into favoritos values (4, 1);

insert into producto_moderador values (1, "AUTORIZADO", "2023-03-05 13:10:05", "producto ok", 2, 1);
insert into producto_moderador values (2, "AUTORIZADO", "2023-05-06 10:10:05", "producto ok", 5, 2);
insert into producto_moderador values (3, "DENEGADO", "2023-04-20 22:10:05", "imagen borrosa", 3, 3);
insert into producto_moderador values (4, "AUTORIZADO", "2023-06-08 13:10:05", "producto ok", 2, 4);
insert into producto_moderador values (5, "DENEGADO", "2023-07-03 13:10:05", "descripcion no valida", 4, 5);

insert into compra values (1, "2023-08-15 13:10:05", "VISA", 200000, 3);
insert into compra values (2, "2023-07-15 13:10:05", "MASTERCARD", 400000, 4);
insert into compra values (3, "2023-04-15 13:10:05", "VISA", 500000, 3);
insert into compra values (4, "2023-05-15 13:10:05", "MASTERCARD", 80000, 1);
insert into compra values (5, "2023-03-15 13:10:05", "VISA", 90000, 2);

insert into detalle_compra values (1, 300000, 3, 1, 2);
insert into detalle_compra values (2, 50000, 2, 5, 4);
insert into detalle_compra values (3, 53000, 1, 4, 1);
insert into detalle_compra values (4, 400000, 4, 2, 2);
insert into detalle_compra values (5, 53000, 1, 3, 1);

insert into comentario values (1, "2023-07-15 13:10:05", "me encanta", 4, 2);
insert into comentario values (2, "2023-08-15 13:10:05", "me gustaria en otros colores", 1, 3);
insert into comentario values (3, "2023-06-15 13:10:05", "me encanta", 2, 2);
insert into comentario values (4, "2023-05-15 13:10:05", "me gusta", 1, 4);
insert into comentario values (5, "2023-08-15 15:10:05", "me encanta", 2, 5);

insert into queja values (1, "Llego la cantidad incorrecta", "2023-08-20 13:10:05", 3);
insert into queja values (2, "No puedo cambiar la contraseña", "2023-07-25 13:10:05", 1);
insert into queja values (3, "El cobro es incorrecto", "2023-07-01 13:10:05", 4);
insert into queja values (4, "Llego en color diferente", "2023-06-20 13:10:05", 2);
insert into queja values (5, "No me deja agregar favoritos", "2023-07-25 13:10:05", 1);


