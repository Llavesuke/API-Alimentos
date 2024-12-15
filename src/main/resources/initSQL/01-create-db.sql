-- initSQL/01-create-db.sql

-- Drop existing tables if they exist
DROP TABLE IF EXISTS existencias;
DROP TABLE IF EXISTS alimentos;
DROP TABLE IF EXISTS ubicaciones;

-- Create the `ubicaciones` table first
CREATE TABLE ubicaciones (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             descripcion VARCHAR(255) NOT NULL,
                             tipo_ubicacion VARCHAR(255) NOT NULL,
                             capacidad INT NOT NULL
);

-- Create the `alimentos` table
CREATE TABLE alimentos (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(255) NOT NULL,
                           tipo VARCHAR(255) NOT NULL,
                           estado VARCHAR(255) NOT NULL,
                           fecha_caducidad DATE NULL
);

-- Create the `existencias` table with foreign keys referencing `alimentos` and `ubicaciones`
CREATE TABLE existencias (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             alimento_id BIGINT NOT NULL,
                             ubicacion_id BIGINT NOT NULL,
                             cantidad INT NOT NULL,
                             fecha_entrada DATE NOT NULL DEFAULT CURRENT_DATE,
                             FOREIGN KEY (alimento_id) REFERENCES alimentos(id) ON DELETE CASCADE,
                             FOREIGN KEY (ubicacion_id) REFERENCES ubicaciones(id) ON DELETE CASCADE
);

-- Insert initial data into `ubicaciones` table
INSERT INTO ubicaciones (descripcion, tipo_ubicacion, capacidad) VALUES
                                                                     ('Balda superior en la alacena', 'Alacena', 6),
                                                                     ('Nevera con 4 baldas, 3 estantes de puerta y 2 cajones', 'Nevera', 9),
                                                                     ('Congelador con 4 baldas, 3 estantes de puerta y 2 cajones', 'Congelador', 9);

-- Insert initial data into `alimentos` table
INSERT INTO alimentos (nombre, tipo, estado, fecha_caducidad) VALUES
                                                                  ('Arroz', 'No Perecedero', 'Cerrado', NULL),
                                                                  ('Pasta', 'No Perecedero', 'Cerrado', NULL),
                                                                  ('Leche', 'Perecedero', 'Abierto', '2024-12-20'),
                                                                  ('Pollo', 'Perecedero', 'Cerrado', '2024-12-16'),
                                                                  ('Manzanas', 'Perecedero', 'Cerrado', '2024-12-22'),
                                                                  ('Verduras Congeladas', 'Perecedero', 'Cerrado', '2025-01-01');

-- Insert initial data into `existencias` table
INSERT INTO existencias (alimento_id, ubicacion_id, cantidad, fecha_entrada) VALUES
                                                                                 (1, 1, 100, '2023-12-01'),
                                                                                 (2, 1, 200, '2023-12-02'),
                                                                                 (3, 2, 50, '2023-12-03'),
                                                                                 (4, 2, 75, '2023-12-04'),
                                                                                 (5, 3, 30, '2023-12-05'),
                                                                                 (6, 3, 60, '2023-12-06');