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


INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (1, 'Robert E.O. Speedwagon', 'JojoBroNumber1', '5p33dWag02', 'fundacion.speedwagon@gmail.com', 'ADMIN');
INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (2, 'Will A. Zeppeli', 'HamonMaster', 'Z3pp3l1H4m0n', 'will.zeppeli@gmail.com', 'USER');
INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (3, 'Joseph Joestar', 'HermitPurple', 'Y3sY3sY3sN0', 'joseph.joestar@gmail.com', 'USER');

INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (4, 'Giorno Giovanna', 'GoldExperienceRequiem', 'MuD4MuD4', 'giorno.giovanna@gmail.com', 'USER');
INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (5, 'Diego Brando', 'TheWorldAU', 'Th3W0rldAU', 'diego.brando@gmail.com', 'USER');
INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (6, 'Anasui Narciso', 'DiverDown', 'L0v3J0lYn3', 'anasui.narciso@gmail.com', 'USER');

INSERT INTO Alumno (id) VALUES (4);
INSERT INTO Alumno (id) VALUES (5);
INSERT INTO Alumno (id) VALUES (6);

INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (7, 'Elizabeth Joestar', 'LisaLisa', 'M4skH4m0n', 'lisa.lisa@gmail.com', 'ADMIN');
INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (8, 'Yoshikage Kira', 'KillerQueen', 'Qu13tL1f3', 'kira.yoshikage@gmail.com', 'USER');
INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (9, 'Gyro Zeppeli', 'BallBreaker', 'St33lB4llz', 'gyro.zeppeli@gmail.com', 'ADMIN');

INSERT INTO Personal (id, tipo) VALUES (7, 'PROFESOR');
INSERT INTO Personal (id, tipo) VALUES (8, 'PAS');
INSERT INTO Personal (id, tipo) VALUES (9, 'PROFESOR');

INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (10, 'Emporio Alnino', 'GhostRoom', 'B4s3b4llK1d', 'emporio.alnino@gmail.com', 'ADMIN');
INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (11, 'Hot Pants', 'CreamStarter', 'C0wG1rlH0ly', 'hot.pants@gmail.com', 'USER');
INSERT INTO Usuario (id, nombre, username, password, email, role) VALUES (12, 'Funny Valentine', 'DirtyDeedsDoneDirtCheap', 'D4CS4CR1F1C3', 'valentine.funny@gmail.com', 'USER');

INSERT INTO Tecnico (id) VALUES (10);
INSERT INTO Tecnico (id) VALUES (11);
INSERT INTO Tecnico (id) VALUES (12);

ALTER SEQUENCE usuario_seq RESTART WITH 62;


INSERT INTO historico_cursos (alumno_id, curso, curso_escolar) VALUES (4, '1ºDAM', '2023-2024');
INSERT INTO historico_cursos (alumno_id, curso, curso_escolar) VALUES (4, '1ºElectricidad', '2024-2025');
INSERT INTO historico_cursos (alumno_id, curso, curso_escolar) VALUES (5, '2ºBachillerato', '2022-2023');
INSERT INTO historico_cursos (alumno_id, curso, curso_escolar) VALUES (5, '1ºAyF', '2023-2024');
INSERT INTO historico_cursos (alumno_id, curso, curso_escolar) VALUES (5, '2ºAyF', '2024-2025');
INSERT INTO historico_cursos (alumno_id, curso, curso_escolar) VALUES (5, '1ºTeleco', '2020-2021');
INSERT INTO historico_cursos (alumno_id, curso, curso_escolar) VALUES (6, '2ºTeleco', '2022-2023');
INSERT INTO historico_cursos (alumno_id, curso, curso_escolar) VALUES (6, '1ºElectricidad', '2023-2024');
INSERT INTO historico_cursos (alumno_id, curso, curso_escolar) VALUES (6, '2ºElectricidad', '2024-2025');


INSERT INTO incidencia (id, fecha, titulo, descripcion, estado, urgencia, categoria_id, usuario_id, equipo_id, ubicacion_id) VALUES (1, '2025-02-05T10:00:00', 'Fallo en la red Wi-Fi', 'No hay conexión en la sala 102 debido a un fallo en el router.', 'PENDIENTE', true, 1, 2, 3, NULL);
INSERT INTO incidencia (id, fecha, titulo, descripcion, estado, urgencia, categoria_id, usuario_id, equipo_id, ubicacion_id) VALUES (2, '2025-02-05T11:00:00', 'Aire acondicionado no funciona', 'El aire acondicionado del gimnasio no responde.', 'ABIERTA', false, 7, 4, 10, 4);
INSERT INTO incidencia (id, fecha, titulo, descripcion, estado, urgencia, categoria_id, usuario_id, equipo_id, ubicacion_id) VALUES (3, '2025-02-05T12:00:00', 'Fallo en la pantalla del ordenador', 'El monitor Dell no enciende en el aula 204.', 'TRABAJANDO', true, 3, 1, 5, 3);
INSERT INTO incidencia (id, fecha, titulo, descripcion, estado, urgencia, categoria_id, usuario_id, equipo_id, ubicacion_id) VALUES (4, '2025-02-05T13:00:00', 'Error al encender el móvil', 'El móvil Samsung Galaxy S21 no se enciende en el aula 102.', 'CERRADA', false, 5, 2, 8, 2);
INSERT INTO incidencia (id, fecha, titulo, descripcion, estado, urgencia, categoria_id, usuario_id, equipo_id, ubicacion_id) VALUES (5, '2025-02-05T14:30:00', 'Fallo en el aire acondicionado', 'El aire acondicionado no enfría correctamente en el aula 204.', 'PENDIENTE', true, 7, 6, 10, 3);
INSERT INTO incidencia (id, fecha, titulo, descripcion, estado, urgencia, categoria_id, usuario_id, equipo_id, ubicacion_id) VALUES (6, '2025-02-05T15:30:00', 'Problemas con la impresora', 'La impresora no imprime en el aula 102.', 'TRABAJANDO', false, 1, 3, NULL, 2);
INSERT INTO incidencia (id, fecha, titulo, descripcion, estado, urgencia, categoria_id, usuario_id, equipo_id, ubicacion_id) VALUES (7, '2025-02-05T16:00:00', 'Fallo en el sistema de sonido', 'El sistema de sonido del gimnasio no emite sonido.', 'ABIERTA', false, 6, 3, 9, 4);
INSERT INTO incidencia (id, fecha, titulo, descripcion, estado, urgencia, categoria_id, usuario_id, equipo_id, ubicacion_id) VALUES (8, '2025-02-05T17:00:00', 'No carga el dispositivo', 'El dispositivo de tablet Samsung Galaxy S7 no carga en el aula 204.', 'PENDIENTE', true, 4, 4, 7, 3);

ALTER SEQUENCE incidencia_seq RESTART WITH 58;
