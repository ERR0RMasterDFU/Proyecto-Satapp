INSERT INTO Ubicacion (id, nombre) VALUES (1, 'Sala de profesores');
INSERT INTO Ubicacion (id, nombre) VALUES (2, 'Aula 102');
INSERT INTO Ubicacion (id, nombre) VALUES (3, 'Aula 204');
INSERT INTO Ubicacion (id, nombre) VALUES (4, 'Gimnasio');

ALTER SEQUENCE ubicacion_seq RESTART WITH 54;


INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (1, 'Ordenador Dell XPS', 'Procesador Intel i7, 16GB RAM, SSD 512GB', 1);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (2, 'Móvil Samsung Galaxy S21', 'Pantalla OLED 6.5", batería 5000mAh', 2);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (3, 'Tablet iPad Pro', 'Pantalla 10", 128GB almacenamiento', 3);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (4, 'Radio Sony ZR-100', 'Sintonización AM/FM, altavoces estéreo', 4);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (5, 'Ordenador HP Pavilion', 'Ryzen 5, 8GB RAM, HDD 1TB', 1);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (6, 'Móvil iPhone 13 Pro', 'Cámara 108MP, carga rápida 65W', 2);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (7, 'Tablet Samsung Galaxy Tab S7', 'Compatible con stylus, batería de larga duración', 3);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (8, 'Radio Philips Retro 2000', 'Reproductor de CD y USB', 4);
INSERT INTO Equipo (id, nombre, caracteristicas, ubicacion_id) VALUES (9, 'Ordenador ASUS ROG Strix', 'Tarjeta gráfica RTX 3060, SSD NVMe 1TB', 1);

ALTER SEQUENCE equipo_seq RESTART WITH 59;
