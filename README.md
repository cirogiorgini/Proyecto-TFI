# Proyecto TFI - Empleado / Legajo  
Trabajo Final Integrador – Programacion 2  
UTN – Tecnicatura Universitaria en Programacion  

---

## 📘 Descripcion del proyecto

Aplicacion en Java que implementa el dominio **Empleado – Legajo** con relacion 1 a 1, siguiendo una arquitectura en capas (Entities, DAO, Service y App).  
El proyecto incluye validaciones, operaciones CRUD completas, transacciones con commit/rollback, y acceso a base de datos MySQL mediante JDBC.

---

## 👥 Integrantes y roles

- **Fernando Chacon ** – UML + Base de Datos + Modelado  
- **Ciro Giorgini** – DAO + Service + Transacciones + Logica de negocio  
- **Francisco Frascona** – Menu en consola + Informe + Video

---

## 🧩 Dominio: Empleado → Legajo (1 a 1)

- Cada empleado posee un unico legajo.  
- La relacion se implementa con **empleado_id UNIQUE** en la tabla `legajo`.  
- Se evita que un empleado tenga mas de un legajo.  
- Validaciones integradas:  
  - DNI unico  
  - Numero de legajo unico  
  - Baja logica sincronizada

---

## 🏗 Arquitectura del proyecto

El proyecto usa una estructura en capas clara:

```
src/tfi/empleadolegajo/
├─ app/        → AppMenu y TestServicios
├─ config/     → Conexion a MySQL (DatabaseConnection + db.properties)
├─ entities/   → Empleado.java y Legajo.java
├─ dao/        → Interfaces DAO + Implementaciones JDBC
├─ service/    → Logica de negocio + transacciones
└─ sql/        → Scripts SQL (create.sql / insert.sql)
```

---

## 🛢 Base de datos

Scripts incluidos en `/sql`:

- `create.sql` → Crea BD, tablas, claves primarias, foraneas y restricciones  
- `insert.sql` → Inserta datos iniciales  

Tecnologia usada: **MySQL 8 + JDBC**

---

## 🔧 Configuracion mediante archivo properties

Las credenciales se configuran en:

```
src/proyecto/tfi/resources/db.properties
```

Ejemplo:

```
db.url=jdbc:mysql://localhost:3306/empleados_db?useSSL=false&serverTimezone=UTC
db.user=root
db.password=xxxx
db.driver=com.mysql.cj.jdbc.Driver
```

El sistema lee estas propiedades dinamicamente en tiempo de ejecucion.

---

## ⚙ Logica de negocio (Service)

La logica se implementa principalmente en `EmpleadoServiceImpl`.

### ✔ crearEmpleadoConLegajo()
- Valida DNI y numero de legajo  
- Abre transaccion  
- Crea empleado  
- Asigna id al legajo  
- Crea legajo  
- Commit  
- Si algo falla → Rollback automatico  

### ✔ eliminarEmpleadoYLegajo()
- Marca ambos registros como eliminados  
- Ejecuta la operacion dentro de una transaccion

---

## 💾 Operaciones CRUD implementadas

- Alta de empleado + legajo  
- Listado completo de empleados  
- Busqueda por DNI  
- Busqueda de legajo por numero  
- Actualizacion de datos  
- Baja logica  

---

## 🧪 Pruebas del sistema

El archivo `TestServicios.java` realiza:

- Alta correcta  
- Error por DNI repetido  
- Error por numero de legajo repetido  
- Baja logica  
- Verificacion de rollback

En el informe se muestran capturas del menu y de consultas SQL.

---

## 🖥 Ejecucion del proyecto

### 1) Crear la BD
Ejecutar:

```
sql/create.sql
sql/insert.sql
```

### 2) Configurar db.properties
Actualizar usuario, clave y puerto.

### 3) Probar logica
Ejecutar:

```
TestServicios.java
```

### 4) Ejecutar la aplicacion
Correr:

```
AppMenu.java
```

---

## 📂 Estructura del repositorio

```
/docs/      → Informe, UML, capturas
/sql/       → Scripts SQL
/src/       → Codigo Java
.gitignore  → Ignora build/, dist/ y archivos temporales
README.md   → Este archivo
```

---

## 🎥 Video (link)

https://drive.google.com/file/d/11m8y9uhg0zDAecu7yQAXHc77M1Fno1B2/view?usp=sharing

---

## 🛠 Herramientas utilizadas

- Java 17  
- NetBeans  
- MySQL 8  
- MySQL Workbench 
- IA utilizada como herramienta de asistencia tecnica y documental

---
