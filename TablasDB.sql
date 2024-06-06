DROP DATABASE IF EXISTS `biblioteca-prestamos`;
CREATE DATABASE `biblioteca-prestamos`;

USE `biblioteca-prestamos`;

-- Crear tabla Rol
CREATE TABLE Rol (
    rol_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) UNIQUE NOT NULL
);

-- Crear tabla Usuario
CREATE TABLE Usuario (
    usuario_id INT PRIMARY KEY AUTO_INCREMENT,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni INT UNIQUE NOT NULL,
    correo VARCHAR(100) UNIQUE,
    contraseña VARCHAR(255),
    rol_id INT,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (rol_id) REFERENCES Rol(rol_id)
);

-- Crear tabla Autor
CREATE TABLE Autor (
    autor_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) UNIQUE NOT NULL
);

-- Crear tabla Libro
CREATE TABLE Libro (
    libro_id INT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    autor_id INT NOT NULL,
    link_imagen VARCHAR(255),
    descripcion TEXT,
    stock INT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES Autor(autor_id)
);

-- Crear tabla Prestamo
CREATE TABLE Prestamo (
    prestamo_id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT,
    libro_id INT,
    fecha_prestamo DATE NOT NULL,
    fecha_limite DATE NOT NULL,
    fecha_devolucion DATE,
    devuelto BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id),
    FOREIGN KEY (libro_id) REFERENCES Libro(libro_id)
);

-- Crear índices
CREATE INDEX idx_usuario_dni ON Usuario(dni);
CREATE INDEX idx_libro_titulo ON Libro(titulo);
CREATE INDEX idx_prestamo_usuario_id ON Prestamo(usuario_id);
CREATE INDEX idx_prestamo_libro_id ON Prestamo(libro_id);

-- Insertar registros en Rol
INSERT INTO Rol (nombre) VALUES
    ('Bibliotecario'),
    ('Usuario');

-- Insertar registros en Usuario
INSERT INTO Usuario (nombres, apellidos, dni, correo, contraseña, rol_id) VALUES
    ('Bibliotecario', 'Bibliotecario', 12345678, 'biblioteca@utp.edu.pe', 'biblioteca123', 1);

INSERT INTO Usuario (nombres, apellidos, dni, rol_id) VALUES
    ('Usuario2', 'Usuario', 87654321, 2),
    ('Usuario3', 'Usuario2', 87654322, 2),
    ('Usuario4', 'Usuario3', 87654323, 2);

-- Insertar registros en Autor
INSERT INTO Autor (nombre) VALUES
    ('Gabriel García Márquez'),
    ('Isabel Allende'),
    ('Jorge Luis Borges'),
    ('Juan Rulfo'),
    ('Julio Cortázar'),
    ('Laura Esquivel'),
    ('Mario Vargas Llosa'),
    ('Octavio Paz'),
    ('Pablo Neruda');


DELIMITER //
CREATE TRIGGER tr_set_default_image
    BEFORE INSERT ON Libro
    FOR EACH ROW
BEGIN
    IF NEW.link_imagen IS NULL THEN
        SET NEW.link_imagen = CONCAT('https://placehold.co/100x150?text=', REPLACE(NEW.titulo, ' ', '+'));
    END IF;
END;
//
DELIMITER ;


-- Insertar registros en Libro
INSERT INTO Libro (isbn, titulo, autor_id, descripcion, stock) VALUES
    ('3888580894', 'Cien años de soledad', 1, 'Novela icónica que sigue la historia de la familia Buendía en el pueblo ficticio de Macondo.', 2),
    ('5215707454', 'El amor en los tiempos del cólera', 1, 'Historia de amor entre Fermina Daza y Florentino Ariza que abarca varias décadas.', 3),
    ('2997016528', 'Crónica de una muerte anunciada', 1, 'Historia sobre la muerte de Santiago Nasar, narrada como una crónica periodística.', 3),
    ('1948210141', 'El coronel no tiene quien le escriba', 1, 'Relato sobre un coronel retirado que espera una pensión que nunca llega.', 3),
    ('5396283330', 'Relato de un náufrago', 1, 'Historia basada en hechos reales sobre un marinero que sobrevive diez días a la deriva en el Caribe.', 3),
    ('4282237437', 'El otoño del patriarca', 1, 'Novela que describe la decadencia de un dictador caribeño.', 3),
    ('5219989508', 'Memoria de mis putas tristes', 1, 'Historia de un hombre de 90 años que decide regalarse una noche de amor con una joven virgen.', 1),
    ('4123306029', 'Noticia de un secuestro', 1, 'Crónica periodística sobre una serie de secuestros en Colombia llevados a cabo por el cartel de Medellín.', 1),
    ('8434688259', 'Doce cuentos peregrinos', 1, 'Conjunto de relatos que tienen en común a personajes latinoamericanos viviendo en Europa.', 3),
    ('7001079571', 'La hojarasca', 1, 'Primera novela del autor, que introduce el pueblo de Macondo y sus habitantes.', 2),
    ('8843607213', 'El general en su laberinto', 1, 'Novela histórica que narra los últimos días de Simón Bolívar.', 2),
    ('8798519701', 'Vivir para contarla', 1, 'Autobiografía del autor, que abarca su infancia y juventud.', 1),
    ('7134435711', 'La casa de los espíritus', 2, 'Saga familiar que mezcla realismo mágico con la historia política de Chile.', 2),
    ('8455392077', 'Eva Luna', 2, 'Novela que sigue la vida de Eva Luna, una joven huérfana con un talento innato para contar historias.', 1),
    ('7185986487', 'De amor y de sombra', 2, 'Historia de amor ambientada en un país latinoamericano bajo una dictadura militar.', 1),
    ('8767162147', 'Paula', 2, 'Libro de memorias escrito tras la enfermedad y muerte de la hija de la autora.', 3),
    ('2647566867', 'Hija de la fortuna', 2, 'Historia de una joven chilena que viaja a California durante la fiebre del oro en busca de su amor perdido.', 3),
    ('6325164774', 'El amante japonés', 2, 'Historia de amor entre una joven polaca y un jardinero japonés en el San Francisco de la Segunda Guerra Mundial.', 2),
    ('6084252216', 'La suma de los días', 2, 'Continuación de las memorias de Allende, centrada en su vida familiar en California.', 2),
    ('4988450015', 'Inés del alma mía', 2, 'Novela histórica sobre Inés Suárez, una conquistadora española en el siglo XVI.', 2),
    ('2860112969', 'Ficciones', 3, 'Colección de cuentos que exploran temas como la metafísica, la literatura y la identidad.', 3),
    ('9472143119', 'El Aleph', 3, 'Conjunto de relatos que incluyen elementos de la filosofía, la teología y la literatura.', 3),
    ('5305404287', 'El libro de arena', 3, 'Colección de cuentos que abordan temas como el infinito y la identidad.', 2),
    ('4605999020', 'Historia universal de la infamia', 3, 'Serie de biografías ficticias de criminales y aventureros.', 3),
    ('5430430360', 'El informe de Brodie', 3, 'Colección de cuentos que exploran lo fantástico y lo metafísico.', 1),
    ('4692161890', 'La memoria de Shakespeare', 3, 'Colección de cuentos publicados póstumamente que abordan temas recurrentes del autor.', 3),
    ('3527112981', 'Seis problemas para don Isidro Parodi', 3, 'Colección de relatos policiales escritos en colaboración con Adolfo Bioy Casares bajo el seudónimo de H. Bustos Domecq.', 3),
    ('8862721725', 'Manual de zoología fantástica', 3, 'Compendio de seres mitológicos y fantásticos de diversas culturas.', 2),
    ('3754841856', 'Pedro Páramo', 4, 'Novela sobre Juan Preciado, quien viaja al pueblo de Comala en busca de su padre, Pedro Páramo.', 2),
    ('5405199911', 'Rayuela', 5, 'Novela que se puede leer de varias maneras, explorando la vida de un grupo de intelectuales en París y Buenos Aires.', 1),
    ('7423166542', 'Bestiario', 5, 'Primera colección de cuentos publicada por Cortázar, donde lo fantástico irrumpe en lo cotidiano.', 2),
    ('1087162506', 'Final del juego', 5, 'Conjunto de relatos que incluyen elementos de lo fantástico y lo absurdo.', 2),
    ('7895373356', 'Los premios', 5, 'Novela que sigue a un grupo de personas que ganan un viaje en barco y sus experiencias a bordo.', 3),
    ('1552680208', 'Las armas secretas', 5, 'Colección de cuentos que incluye algunos de los relatos más conocidos del autor.', 1),
    ('5581758914', 'La vuelta al día en ochenta mundos', 5, 'Colección de ensayos, cuentos y reflexiones sobre diversos temas, con ilustraciones y fotografías.', 2),
    ('5640784008', '62 Modelo para armar', 5, 'Novela experimental que sigue a un grupo de personajes en diversas ciudades de Europa.', 2),
    ('7604486737', 'Un tal Lucas', 5, 'Conjunto de relatos, poemas y reflexiones protagonizados', 3),
    ('2259208617', 'Como agua para chocolate', 6, 'Novela que combina recetas de cocina con una historia de amor en tiempos de la Revolución Mexicana.', 1),
    ('6143579964', 'La ley del amor', 6, 'Novela que mezcla elementos de ciencia ficción y romance en un futuro distópico.', 2),
    ('1974320067', 'Tan veloz como el deseo', 6, 'Novela sobre un hombre con el don de interpretar los deseos de las personas a través del oído.', 3),
    ('2113103506', 'Malinche', 6, 'Novela histórica sobre la vida de Malinche, la intérprete y amante de Hernán Cortés.', 3),
    ('8725928128', 'La ciudad y los perros', 7, 'Novela sobre la vida en una academia militar en Lima, Perú, y las tensiones sociales y personales de los cadetes.', 1),
    ('6447523443', 'La fiesta del chivo', 7, 'Historia basada en la dictadura de Rafael Leónidas Trujillo en República Dominicana.', 1),
    ('1203345490', 'Conversación en La Catedral', 7, 'Novela que explora la corrupción y la decadencia moral en Perú durante la dictadura de Manuel A. Odría.', 2),
    ('7323111686', 'La casa verde', 7, 'Novela que entrelaza varias historias en la selva peruana y en un burdel.', 3),
    ('6197490784', 'Pantaleón y las visitadoras', 7, 'Novela satírica sobre un oficial del ejército peruano encargado de organizar un servicio de prostitutas para las tropas en la Amazonía.', 1),
    ('1148782808', 'Los cachorros', 7, 'Novela corta que sigue la vida de un grupo de amigos en Lima desde su infancia hasta la adultez.', 2),
    ('2102104421', 'Travesuras de la niña mala', 7, 'Historia de amor entre Ricardo Somocurcio y una mujer que aparece y desaparece a lo largo de su vida.', 2),
    ('2810822794', 'El sueño del celta', 7, 'Novela basada en la vida de Roger Casement, un diplomático británico que denunció los abusos coloniales en el Congo y Perú.', 1),
    ('3337921053', 'El laberinto de la soledad', 8, 'Ensayo sobre la identidad mexicana y el carácter del pueblo mexicano.', 2),
    ('4639190320', 'Sor Juana Inés de la Cruz o Las trampas de la fe', 8, 'Biografía de la poeta y monja mexicana Sor Juana Inés de la Cruz.', 1),
    ('1380981131', 'El arco y la lira', 8, 'Ensayo sobre la poesía y su relación con la vida y la cultura.', 3),
    ('1935045932', 'Piedra de sol', 8, 'Extenso poema circular que refleja las preocupaciones filosóficas y estéticas del autor.', 2),
    ('8484165258', 'Veinte poemas de amor y una canción desesperada', 9, 'Colección de poemas que exploran el amor y la pérdida.', 3),
    ('2803601269', 'Canto general', 9, 'Extensa obra poética que traza la historia de América Latina desde la prehistoria hasta el siglo XX.', 2),
    ('5036265268', 'Residencia en la tierra', 9, 'Serie de poemas que exploran la alienación y el absurdo de la existencia humana.', 1),
    ('6626438257', 'Memorial de Isla Negra', 9, 'Poemario autobiográfico que recorre la vida del poeta y su relación con Chile.', 1);

INSERT INTO prestamo (usuario_id, libro_id, fecha_prestamo, fecha_limite, fecha_devolucion, devuelto) VALUES
  (2, 7, '2021-09-01', '2021-09-15', '2021-09-15', TRUE),
  (3, 48, '2021-09-01', '2021-09-15', '2021-09-15', TRUE),
  (4, 39, '2021-09-01', '2021-09-15', '2021-09-15', TRUE);

-- Stored Procedures

-- Procedimiento para realizar un préstamo comprobando que el "estado" del usuario sea TRUE
DELIMITER //
CREATE PROCEDURE sp_realizar_prestamo(IN p_usuario_dni INT, IN p_libro_id INT, IN p_dias INT)
BEGIN
    DECLARE v_estado BOOLEAN;
    DECLARE v_stock INT;

    SELECT estado INTO v_estado
    FROM Usuario
    WHERE dni = p_usuario_dni;

    SELECT stock INTO v_stock
    FROM Libro
    WHERE libro_id = p_libro_id;

    IF v_estado = TRUE AND v_stock > 0 THEN
        INSERT INTO Prestamo (usuario_id, libro_id, fecha_prestamo, fecha_limite)
        VALUES ((SELECT usuario_id FROM Usuario WHERE dni = p_usuario_dni), p_libro_id, CURRENT_DATE, CURRENT_DATE + INTERVAL p_dias DAY);
    ELSEIF v_stock < 1 THEN
        SELECT 'No hay stock disponible para este libro' AS mensaje;
    ELSE
        SELECT 'El usuario no puede realizar préstamos' AS mensaje;
    END IF;
END //
DELIMITER ;

-- Procedimiento para devolver un libro
DELIMITER //
CREATE PROCEDURE sp_devolver_libro(IN p_prestamo_id INT)
BEGIN
    UPDATE Prestamo
    SET fecha_devolucion = CURRENT_DATE,
        devuelto = TRUE
    WHERE prestamo_id = p_prestamo_id;
END //
DELIMITER ;


-- Triggers

-- Trigger para actualizar el stock de un libro cuando se realiza un préstamo
DELIMITER //
CREATE TRIGGER tr_actualizar_stock_prestamo
    AFTER INSERT ON Prestamo
    FOR EACH ROW
BEGIN
    UPDATE Libro
    SET stock = stock - 1
    WHERE libro_id = NEW.libro_id;
END //
DELIMITER ;

-- Trigger para actualizar el stock de un libro cuando se devuelve un libro
DELIMITER //
CREATE TRIGGER tr_actualizar_stock_devolucion
    AFTER UPDATE ON Prestamo
    FOR EACH ROW
BEGIN
    IF NEW.devuelto = TRUE THEN
        UPDATE Libro
        SET stock = stock + 1
        WHERE libro_id = NEW.libro_id;
    END IF;
END //
DELIMITER ;


-- Eventos

-- Evento para verificar si hay libros que se han pasado de la fecha límite de devolución
-- y marcar a los usuarios "estado" como FALSE
DELIMITER //
CREATE EVENT e_verificar_prestamos_vencidos
    ON SCHEDULE EVERY 1 DAY
        STARTS (CURRENT_TIMESTAMP + INTERVAL 11 HOUR)
    DO
    BEGIN
        UPDATE Usuario
        SET estado = FALSE
        WHERE usuario_id IN (
            SELECT usuario_id
            FROM Prestamo
            WHERE fecha_limite < CURRENT_DATE
              AND devuelto = FALSE
        );
    END //
DELIMITER ;
