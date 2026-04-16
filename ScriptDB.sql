CREATE DATABASE IF NOT EXISTS examen_2_nustes
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE examen_2_nustes;

CREATE TABLE IF NOT EXISTS asegurado (
    id      VARCHAR(50)  NOT NULL,
    nombre  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS seguro (
    numero           VARCHAR(20)  NOT NULL,
    fecha_expedicion VARCHAR(10)  NOT NULL,
    estado           TINYINT(1)   NOT NULL DEFAULT 1,
    asegurado_id     VARCHAR(50)  NOT NULL,
    PRIMARY KEY (numero),
    CONSTRAINT fk_seguro_asegurado
        FOREIGN KEY (asegurado_id)
        REFERENCES asegurado (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS seguro_vida (
    numero       VARCHAR(20)  NOT NULL,
    beneficiario VARCHAR(100) NOT NULL,
    PRIMARY KEY (numero),
    CONSTRAINT fk_vida_seguro
        FOREIGN KEY (numero)
        REFERENCES seguro (numero)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS seguro_vehiculo (
    numero VARCHAR(20)  NOT NULL,
    marca  VARCHAR(100) NOT NULL,
    PRIMARY KEY (numero),
    CONSTRAINT fk_vehiculo_seguro
        FOREIGN KEY (numero)
        REFERENCES seguro (numero)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE = InnoDB;

-- Insert con IGNORE para evitar errores por duplicados
INSERT IGNORE INTO asegurado (id, nombre) VALUES
    ('A001', 'Juan Nustes'),
    ('A002', 'María López');

INSERT IGNORE INTO seguro (numero, fecha_expedicion, estado, asegurado_id) VALUES
    ('1001', '25/12/2025', 1, 'A001'),
    ('1002', '01/06/2026', 1, 'A001'),
    ('1003', '15/09/2025', 0, 'A002'),
    ('1004', '10/03/2027', 1, 'A002');

INSERT IGNORE INTO seguro_vida (numero, beneficiario) VALUES
    ('1001', 'Pedro Nustes'),
    ('1003', 'Ana López');

INSERT IGNORE INTO seguro_vehiculo (numero, marca) VALUES
    ('1002', 'Toyota'),
    ('1004', 'Mazda');