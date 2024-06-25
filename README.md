-- Crear la tabla de usuarios
CREATE TABLE usuarios (
    id NUMBER PRIMARY KEY,
    username VARCHAR2(50) NOT NULL,
    password VARCHAR2(100) NOT NULL
);

-- Crear la secuencia para la tabla de usuarios
CREATE SEQUENCE usuarios_seq START WITH 1 INCREMENT BY 1;

-- Crear el trigger para asignar automáticamente el ID
CREATE OR REPLACE TRIGGER usuarios_bi
BEFORE INSERT ON usuarios
FOR EACH ROW
BEGIN
    SELECT usuarios_seq.NEXTVAL INTO :new.id FROM dual;
END;
/

-- Crear la tabla de reportes
CREATE TABLE reportes (
    id NUMBER PRIMARY KEY,
    usuario_id NUMBER NOT NULL,
    titulo VARCHAR2(100) NOT NULL,
    contenido VARCHAR2(1000),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Insertar un registro en la tabla de usuarios
INSERT INTO usuarios (username, password) VALUES ('gamboa', '123');

-- Verificar la inserción
SELECT * FROM usuarios;

SELECT * FROM usuarios WHERE username = 'newuser' AND password = 'newpassword'


