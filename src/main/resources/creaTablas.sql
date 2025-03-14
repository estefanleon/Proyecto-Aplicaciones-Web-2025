-- 1. Crear la base de datos
CREATE DATABASE IF NOT EXISTS gym_db;
USE gym_db;

create user 'usuario_prueba'@'%' identified by 'Usuar1o_Clave.';
create user 'usuario_reportes'@'%' identified by 'Usuar1o_Reportes.';

-- 2. Crear la tabla de usuarios
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN', 'TRAINER') NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Insertar datos de prueba
INSERT INTO users (name, email, password, role) VALUES 
('Admin', 'admin@gym.com', 'admin123', 'ADMIN'),
('Carlos', 'Carlos@gmail.com','123', 'USER'),
('Juan', 'Juan@gmail.com', '000', 'TRAINER');

CREATE TABLE entrenadores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(150) NOT NULL,
    experiencia INT NOT NULL,
    imagen VARCHAR(255) NOT NULL
);



INSERT INTO entrenadores (nombre, especialidad, experiencia, imagen) 
VALUES ('Carlos Ramírez', 'Musculación y fuerza', 10, '/images/entrenador1.jpg');

INSERT INTO entrenadores (nombre, especialidad, experiencia, imagen) 
VALUES ('María González', 'Entrenamiento funcional', 8, '/images/entrenador2.jpg');

INSERT INTO entrenadores (nombre, especialidad, experiencia, imagen) 
VALUES ('Juan Pérez', 'Crossfit y HIIT', 6, '/images/entrenador3.jpg');



CREATE TABLE clases (
    idClase BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombreClase VARCHAR(100) NOT NULL,
    descripcionClase TEXT NOT NULL,
    horarioClase VARCHAR(50) NOT NULL,
    nombreEntrenador VARCHAR(100) NOT NULL, -- Guardamos el nombre del entrenador en vez del ID
    imagenClase VARCHAR(255)
);

ALTER TABLE clases ADD estadoReserva ENUM('DISPONIBLE', 'RESERVADA') NOT NULL DEFAULT 'DISPONIBLE';


CREATE TABLE reservas (
    idReserva BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombreUsuario VARCHAR(100) NOT NULL, -- Guardamos el nombre del usuario en vez del ID
    nombreClase VARCHAR(100) NOT NULL, -- Guardamos el nombre de la clase en vez del ID
    fechaReserva TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



-- Insertar clases
INSERT INTO clases (nombreClase, descripcionClase, horarioClase, nombreEntrenador, imagenClase) 
VALUES 
('Aqua Box', 'Ejercicios de boxeo en piscina', 'Lunes 6:00 PM', 'Carlos Ramírez', '/images/aqua_box.jpg'),
('Cross Training', 'Entrenamiento de alta intensidad', 'Martes 7:00 AM', 'María González', '/images/cross_training.jpg'),
('Yoga Fit', 'Yoga con ejercicios funcionales', 'Miércoles 8:00 AM', 'Juan Pérez', '/images/yoga_fit.jpg'),
('Baile Activo', 'Clases de baile fitness', 'Jueves 6:30 PM', 'Carlos Ramírez', '/images/baile_activo.jpg');

-- Insertar reservas
INSERT INTO reservas (nombreUsuario, nombreClase) 
VALUES 
('Pedro López', 'Cross Training'), 
('Ana Martínez', 'Aqua Box'),
('Luis Gómez', 'Yoga Fit');
