INSERT INTO Ubicacion (id, nombre) VALUES (1, 'Sala de profesores');
INSERT INTO Ubicacion (id, nombre) VALUES (2, 'Aula 102');
INSERT INTO Ubicacion (id, nombre) VALUES (3, 'Aula 204');
INSERT INTO Ubicacion (id, nombre) VALUES (4, 'Gimnasio');

ALTER SEQUENCE ubicacion_seq RESTART WITH 54;


INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (1, 'Ordenador Dell XPS', 'Procesador Intel i7, 16GB RAM, SSD 512GB', 1);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (2, 'Tablet iPad Pro', 'Pantalla 10", 128GB almacenamiento', 2);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (3, 'Móvil Samsung Galaxy S21', 'Pantalla OLED 6.5", batería 5000mAh', 3);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (4, 'Radio Sony ZR-100', 'Sintonización AM/FM, altavoces estéreo', 4);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (5, 'Aire Acondicionado LG DualCool', 'Capacidad 12000 BTU, ahorro energético', 1);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (6, 'Ordenador HP Pavilion', 'Ryzen 5, 8GB RAM, HDD 1TB', 2);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (7, 'Tablet Samsung Galaxy Tab S7', 'Compatible con stylus, batería de larga duración', 3);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (8, 'Móvil iPhone 13 Pro', 'Cámara 108MP, carga rápida 65W', 4);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (9, 'Radio Philips Retro 2000', 'Reproductor de CD y USB', 1);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (10, 'Aire Acondicionado Daikin Inverter', 'Tecnología inverter, refrigerante ecológico', 2);

ALTER SEQUENCE equipo_seq RESTART WITH 60;


INSERT INTO Categoria (id, nombre, categoria_padre_categoria_id) VALUES (1, 'Computación y comunicación.', null);
INSERT INTO Categoria (id, nombre, categoria_padre_categoria_id) VALUES (2, 'Electrodoméstico', null);
INSERT INTO Categoria (id, nombre, categoria_padre_categoria_id) VALUES (3, 'Ordenador', 1);
INSERT INTO Categoria (id, nombre, categoria_padre_categoria_id) VALUES (4, 'Tablet', 1);
INSERT INTO Categoria (id, nombre, categoria_padre_categoria_id) VALUES (5, 'Móvil', 1);
INSERT INTO Categoria (id, nombre, categoria_padre_categoria_id) VALUES (6, 'Radio', 2);
INSERT INTO Categoria (id, nombre, categoria_padre_categoria_id) VALUES (7, 'Aire acondicionado', 2);

ALTER SEQUENCE categoria_seq RESTART WITH 57;