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
VALUES ('Carlos Ramírez', 'Musculación y fuerza', 10, 'https://fivestarsfitness.com/wp-content/uploads/Preguntas-que-debe-hacer-un-entrenador-personal.jpg');

INSERT INTO entrenadores (nombre, especialidad, experiencia, imagen) 
VALUES ('María González', 'Entrenamiento funcional', 8, 'https://c.superprof.com/i/a/11785229/6158453/600/20230517022037/entrenadora-personal-ayudo-cambiar-cuerpo-mente-haciendo-del-ejercicio-una-forma-vida.jpg');

INSERT INTO entrenadores (nombre, especialidad, experiencia, imagen) 
VALUES ('Juan Pérez', 'Crossfit y HIIT', 6, 'https://www.clikisalud.net/wp-content/uploads/2019/08/consejos-top-entrenadores-personales-hacer-ejercicio.jpg');



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
    correoUsuario VARCHAR(100) NOT NULL,
    idClase BIGINT NOT NULL,
    fechaReserva DATE NOT NULL,
    FOREIGN KEY (idClase) REFERENCES clases(idClase)
);



-- Insertar clases
INSERT INTO clases (nombreClase, descripcionClase, horarioClase, nombreEntrenador, imagenClase) 
VALUES 
('Aqua Box', 'Ejercicios de boxeo en piscina', 'Lunes 6:00 PM', 'Carlos Ramírez', '/images/aqua_box.jpg'),
('Cross Training', 'Entrenamiento de alta intensidad', 'Martes 7:00 AM', 'María González', '/images/cross_training.jpg'),
('Yoga Fit', 'Yoga con ejercicios funcionales', 'Miércoles 8:00 AM', 'Juan Pérez', '/images/yoga_fit.jpg'),
('Baile Activo', 'Clases de baile fitness', 'Jueves 6:30 PM', 'Carlos Ramírez', '/images/baile_activo.jpg');




CREATE TABLE IF NOT EXISTS membresias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    precio DOUBLE NOT NULL,
    duracionMeses INT NOT NULL
);

INSERT INTO membresias (tipo, precio, duracionMeses) VALUES 
('Mensual', 40.0, 1),
('Trimestral', 100.0, 3),
('Anual', 350.0, 12);



SELECT * FROM gym_db.users;

ALTER TABLE users ADD COLUMN membresia_id BIGINT;
ALTER TABLE users ADD CONSTRAINT fk_membresia 
FOREIGN KEY (membresia_id) REFERENCES membresias(id);




ALTER TABLE clases DROP COLUMN nombre_clase;
ALTER TABLE clases DROP COLUMN descripcion_clase;
ALTER TABLE clases DROP COLUMN horario_clase;
ALTER TABLE clases DROP COLUMN nombre_entrenador;
ALTER TABLE clases DROP COLUMN imagen_clase;
ALTER TABLE clases DROP COLUMN estado_reserva;




INSERT INTO clases 
(nombreClase, descripcionClase, horarioClase, nombreEntrenador, imagenClase, estadoReserva) 
VALUES
(
  'Cross Training', 
  'Entrenamiento funcional para mejorar resistencia, fuerza y coordinación.', 
  'Lunes y Miércoles - 8am', 
  'Mario Herrera', 
  'https://images.contentstack.io/v3/assets/blt45c082eaf9747747/blt38b3a2af80dd53fe/5de0b92485f2fe640c6fa50b/Benefits_of_Cross_Training_1_copy.JPG?width=1015&auto=webp&format=pjpg&quality=76', 
  'DISPONIBLE'
),
(
  'Aqua Box', 
  'Combina boxeo y entrenamiento en el agua para mayor resistencia.', 
  'Martes y Jueves - 5pm', 
  'Sofía Pérez', 
  'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZJbl5zlyOwR23_3uFdjGHLA_ayXPbO8eb5A&s', 
  'DISPONIBLE'
),
(
  'Baile', 
  'Diviértete y mantente en forma con nuestras clases de baile.', 
  'Viernes - 6pm', 
  'Ana Torres', 
  'https://escueladfitness.com/wp-content/uploads/2022/12/gente-divirtiendose-clase-zumba_23-2149074860.jpg.webp', 
  'DISPONIBLE'
);

ALTER TABLE reservas ADD COLUMN idUsuario BIGINT;
ALTER TABLE reservas DROP COLUMN correoUsuario;
ALTER TABLE clases DROP COLUMN estadoReserva;
