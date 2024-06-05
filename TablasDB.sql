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
    ('Usuario', 'Usuario', 87654321, 2),
    ('Usuario2', 'Usuario2', 87654322, 2),
    ('Usuario3', 'Usuario3', 87654323, 2);

-- Insertar registros en Autor
INSERT INTO Autor (nombre) VALUES
    ('Gabriel García Márquez'),
    ('Mario Vargas Llosa'),
    ('Julio Ramón Ribeyro'),
    ('César Vallejo'),
    ('José María Arguedas');

-- Insertar registros en Libro
INSERT INTO Libro (isbn, titulo, autor_id, link_imagen, descripcion, stock) VALUES
    ('978-84-376-0494-7', 'Cien años de soledad', 1, 'https://www.postimg.jpg', 'Cien años de soledad es una novela del escritor colombiano Gabriel García Márquez, ganador del Premio Nobel de Literatura en 1982. Es considerada una obra maestra de la literatura hispanoamericana y universal, así como una de las obras más traducidas y leídas en español.', 10),
    ('978-84-376-0634-2', 'La casa verde', 2, 'https://www.postimg.jpg', 'La casa verde es la novela más conocida del escritor peruano Mario Vargas Llosa, publicada en 1966. La obra es considerada una de las más importantes de la literatura hispanoamericana del siglo XX.', 5);



-- Stored Procedures

-- Procedimiento para realizar un préstamo comprobando que el "estado" del usuario sea TRUE
DELIMITER //
CREATE PROCEDURE sp_realizar_prestamo(IN p_usuario_id INT, IN p_libro_id INT, IN p_dias INT)
BEGIN
    DECLARE v_estado BOOLEAN;
    DECLARE v_stock INT;

    SELECT estado INTO v_estado
    FROM Usuario
    WHERE usuario_id = p_usuario_id;

    SELECT stock INTO v_stock
    FROM Libro
    WHERE libro_id = p_libro_id;

    IF v_estado = TRUE AND v_stock > 0 THEN
        INSERT INTO Prestamo (usuario_id, libro_id, fecha_prestamo, fecha_limite)
        VALUES (p_usuario_id, p_libro_id, CURRENT_DATE, CURRENT_DATE + INTERVAL p_dias DAY);
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
