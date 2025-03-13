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

