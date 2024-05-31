CREATE DATABASE `biblioteca-prestamos`;

USE `biblioteca-prestamos`;

CREATE TABLE Rol (
    rol_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Usuario (
    usuario_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    rol_id INT,
    FOREIGN KEY (rol_id) REFERENCES Rol(rol_id)
);

CREATE TABLE Libro (
    libro_id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    link_imagen VARCHAR(255),
    descripcion TEXT,
    stock INT NOT NULL,
    disponible BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE Prestamo (
    prestamo_id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT,
    libro_id INT,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE,
    devuelto BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id),
    FOREIGN KEY (libro_id) REFERENCES Libro(libro_id)
);

-- Índices

CREATE INDEX idx_usuario_correo ON Usuario(correo);

CREATE INDEX idx_libro_titulo ON Libro(titulo);

CREATE INDEX idx_prestamo_usuario_id ON Prestamo(usuario_id);
CREATE INDEX idx_prestamo_libro_id ON Prestamo(libro_id);
