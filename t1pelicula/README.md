# Proyecto Sistema de Gestión de Alquileres

Aplicación web desarrollada para gestionar de manera eficiente el proceso de alquiler de películas. El sistema está construido con tecnologías Java EE, utilizando JSP, Servlets, JPA (Hibernate) y MySQL como base de datos. La arquitectura del proyecto sigue el patrón Modelo-Vista-Controlador (MVC), lo que permite una separación clara de responsabilidades y una estructura mantenible.

## Tecnologías utilizadas:
- Java 17
- Maven
- Jakarta EE (Servlets, JSP)
- Hibernate (JPA)
- MySQL
- Apache Tomcat 10
- JSTL
- Bootstrap 5 (para la interfaz)

## Estructura principal del proyecto

t1pelicula/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/examen/proyectot1/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── servlet/
│   │   │   └── util/
│   │   ├── resources/META-INF/
│   │   │   └── persistence.xml
│   │   └── webapp/
│   │       ├── alquiler.jsp
│   │       └── WEB-INF/web.xml
└── target/

## Base de datos (MySQL)

```sql
create database if not exists BD2_Vilchez;
use BD2_Vilchez;

-- Tabla clientes
create table if not exists clientes(
id_cliente int auto_increment primary key,
nombre varchar(100) not null,
email varchar(100) not null unique
);

insert into clientes (nombre, email) values
('Susan Vilchez', 'susan.vilchez@gmail.com'),
('Odila Ninanya', 'odila.ninanya@gmail.com'),
('Daniel Blas', 'daniel.blas@gmail.com'),
('Luciana Rojas', 'luciana.rojas@gmail.com'),
('Andry Flores', 'andry.flores@gmail.com');

create table if not exists pelicula(
id_pelicula int auto_increment primary key,
titulo varchar(150) not null,
genero varchar(60) not null,
stock int not null default 0
);

insert into pelicula(titulo, genero, stock) values
('La Caza', 'Drama', 5),
('El aro', 'Suspenso', 3),
('Historias de terror', 'Suspenso', 2),
('The book', 'Romance', 8),
('Erase una vez', 'Romance', 1),
('Volver a casa', 'Drama', 3),
('La Casa del Lago', 'Romance', 10),
('La Vida de los Otros', 'Suspenso', 5);

create table if not exists alquileres(
id_alquiler int auto_increment primary key,
fecha timestamp not null default current_timestamp,
id_cliente int not null,
total decimal(8,2) not null,
estado enum('ACTIVO', 'DEVUELTO', 'RETRASADO') not null default 'ACTIVO',
foreign key (id_cliente) references clientes(id_cliente) on delete cascade
);

create table if not exists detalle_alquiler(
id_pelicula int not null,
id_alquiler int not null,
cantidad int not null,
primary key (id_pelicula, id_alquiler),
foreign key (id_pelicula) references pelicula(id_pelicula) on delete cascade,
foreign key (id_alquiler) references alquileres(id_alquiler) on delete cascade
);

select * from clientes;
select * from pelicula;
select * from alquileres;
```

## Ejecución

1. Configura tu archivo persistence.xml con tus credenciales de MySQL.
2. Genera el archivo .war con Maven:
```
mvn clean package
```
3. Coloca el .war en la carpeta webapps de Apache Tomcat.
4. Inicia el servidor Tomcat y accede desde el navegador:
```
http://localhost:8080/t1pelicula/alquiler.jsp
```

## Funcionalidad
- Selección de cliente desde combo.
- Registro de alquiler de películas con cantidades.
- Cálculo automático del total del alquiler.
- Actualización automática del stock de películas.
- Registro de detalles del alquiler.

## Uso

1. Accede a la página de alquiler.
2. Selecciona un cliente.
3. Ingresa la cantidad de cada película a alquilar (no debe superar el stock).
4. Selecciona el estado del alquiler.
5. Haz clic en "Procesar Alquiler" para registrar el proceso.
6. El sistema mostrará mensajes de confirmación o error según la validación.

## Visualización de proyecto ejecutado

1. Combo clientes

![Imagen 01](src/main/resources/static/imagen_01.png)

2. Combo peliculas

![Imagen 02](src/main/resources/static/imagen_02.png)

3. Selección de peliculas y cantidad

![Imagen 03](./src/main/resources/static/imagen_03.png)