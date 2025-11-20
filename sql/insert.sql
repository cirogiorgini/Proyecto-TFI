-- insert.sql
USE empleados_db;

-- 1
INSERT INTO empleado (nombre, apellido, dni, email, fecha_ingreso, area)
VALUES ('María', 'González', '30111222', 'maria.gonzalez@example.com', '2020-03-15', 'Recursos Humanos');
SET @id1 = LAST_INSERT_ID();
INSERT INTO legajo (nro_legajo, categoria, estado, fecha_alta, observaciones, empleado_id)
VALUES ('LEG-0001','Senior','ACTIVO','2020-03-15','Legajo inicial', @id1);

-- 2
INSERT INTO empleado (nombre, apellido, dni, email, fecha_ingreso, area)
VALUES ('Juan', 'Pérez', '27123456', 'juan.perez@example.com', '2019-06-01', 'Contabilidad');
SET @id2 = LAST_INSERT_ID();
INSERT INTO legajo (nro_legajo, categoria, estado, fecha_alta, observaciones, empleado_id)
VALUES ('LEG-0002','Junior','ACTIVO','2019-06-01','Ingreso por concurso', @id2);

-- 3
INSERT INTO empleado (nombre, apellido, dni, email, fecha_ingreso, area)
VALUES ('Laura', 'Martínez', '33999888', 'laura.martinez@example.com', '2021-02-10', 'IT');
SET @id3 = LAST_INSERT_ID();
INSERT INTO legajo (nro_legajo, categoria, estado, fecha_alta, observaciones, empleado_id)
VALUES ('LEG-0003','SemiSenior','ACTIVO','2021-02-10','Pase interno', @id3);

-- 4
INSERT INTO empleado (nombre, apellido, dni, email, fecha_ingreso, area)
VALUES ('Fernando', 'Chacón', '28777666', 'fernando.chacon@example.com', '2018-11-20', 'Desarrollo');
SET @id4 = LAST_INSERT_ID();
INSERT INTO legajo (nro_legajo, categoria, estado, fecha_alta, observaciones, empleado_id)
VALUES ('LEG-0004','Senior','INACTIVO','2018-11-20','Licencia prolongada', @id4);

-- 5
INSERT INTO empleado (nombre, apellido, dni, email, fecha_ingreso, area)
VALUES ('Sofía', 'Ruiz', '31555444', 'sofia.ruiz@example.com', '2022-08-05', 'Comercial');
SET @id5 = LAST_INSERT_ID();
INSERT INTO legajo (nro_legajo, categoria, estado, fecha_alta, observaciones, empleado_id)
VALUES ('LEG-0005','Junior','ACTIVO','2022-08-05','Reciente incorporación', @id5);
