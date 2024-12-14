-- initSQL/01-create-db.sql

-- Drop existing tables if they exist
DROP TABLE IF EXISTS existencias;
DROP TABLE IF EXISTS alimentos;
DROP TABLE IF EXISTS ubicacion;

-- Create the `ubicacion` table first
CREATE TABLE ubicacion (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(255) NOT NULL
);

-- Create the `alimentos` table
CREATE TABLE alimentos (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(255) NOT NULL,
                           tipo ENUM('Perecedero', 'No Perecedero') NOT NULL,
                           estado ENUM('Abierto', 'Cerrado') NOT NULL,
                           fecha_caducidad DATE NULL
);

-- Create the `existencias` table with foreign keys referencing `alimentos` and `ubicacion`
CREATE TABLE existencias (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             alimento_id BIGINT NOT NULL,
                             ubicacion_id BIGINT NOT NULL,
                             cantidad INT NOT NULL,
                             fecha_entrada DATE NOT NULL DEFAULT CURRENT_DATE,
                             FOREIGN KEY (alimento_id) REFERENCES alimentos(id) ON DELETE CASCADE,
                             FOREIGN KEY (ubicacion_id) REFERENCES ubicacion(id) ON DELETE CASCADE
);

-- Insert initial data into `alimentos` table
INSERT INTO alimentos (nombre, tipo, estado, fecha_caducidad) VALUES
                                                                  ('Arroz', 'No Perecedero', 'Cerrado', NULL),
                                                                  ('Pasta', 'No Perecedero', 'Cerrado', NULL),
                                                                  ('Leche', 'Perecedero', 'Abierto', '2024-12-20'),
                                                                  ('Pollo', 'Perecedero', 'Cerrado', '2024-12-16'),
                                                                  ('Manzanas', 'Perecedero', 'Cerrado', '2024-12-22'),
                                                                  ('Verduras Congeladas', 'Perecedero', 'Cerrado', '2025-01-01');