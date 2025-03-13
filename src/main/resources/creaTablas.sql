-- 1. Crear la base de datos
CREATE DATABASE IF NOT EXISTS gym_db;
USE gym_db;

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


