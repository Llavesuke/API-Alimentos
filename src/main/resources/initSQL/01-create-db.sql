-- initSQL/01-create-db.sql

-- Drop existing tables if they exist
DROP TABLE IF EXISTS existencias;
DROP TABLE IF EXISTS alimentos;
DROP TABLE IF EXISTS ubicaciones;

-- Create the `ubicaciones` table
CREATE TABLE ubicaciones (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             descripcion VARCHAR(255) NOT NULL,
                             tipo_ubicacion VARCHAR(255) NOT NULL,
                             capacidad INT NOT NULL
);

-- Insert initial data into `ubicaciones` table
INSERT INTO ubicaciones (descripcion, tipo_ubicacion, capacidad) VALUES
                                                                     ('Balda superior', 'Alacena', 50),
                                                                     ('Balda inferior', 'Alacena', 50),
                                                                     ('Estante medio', 'Nevera', 30),
                                                                     ('Cajón de verduras', 'Nevera', 20),
                                                                     ('Compartimento superior', 'Congelador', 25),
                                                                     ('Compartimento inferior', 'Congelador', 25);

-- Create the `alimentos` table
CREATE TABLE alimentos (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(255) NOT NULL,
                           tipo ENUM('Perecedero', 'No Perecedero') NOT NULL,
                           estado ENUM('Abierto', 'Cerrado') NOT NULL,
                           fecha_caducidad DATE NULL
);

-- Insert initial data into `alimentos` table
INSERT INTO alimentos (nombre, tipo, estado, fecha_caducidad) VALUES
                                                                  ('Arroz', 'No Perecedero', 'Cerrado', NULL),
                                                                  ('Pasta', 'No Perecedero', 'Cerrado', NULL),
                                                                  ('Leche', 'Perecedero', 'Abierto', '2024-12-20'),
                                                                  ('Pollo', 'Perecedero', 'Cerrado', '2024-12-16'),
                                                                  ('Manzanas', 'Perecedero', 'Cerrado', '2024-12-22'),
                                                                  ('Verduras Congeladas', 'Perecedero', 'Cerrado', '2025-01-01');

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

-- Insert initial data into `existencias` table
INSERT INTO existencias (alimento_id, ubicacion_id, cantidad, fecha_entrada) VALUES
                                                                                 (1, 1, 10, '2023-12-01'),
                                                                                 (2, 2, 20, '2023-12-02'),
                                                                                 (3, 3, 15, '2023-12-03'),
                                                                                 (4, 4, 5, '2023-12-04'),
                                                                                 (5, 5, 8, '2023-12-05'),
                                                                                 (6, 6, 12, '2023-12-06');