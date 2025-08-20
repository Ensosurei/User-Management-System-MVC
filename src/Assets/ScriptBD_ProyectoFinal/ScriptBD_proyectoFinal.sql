create database proyectofinal;
use proyectofinal;


create table if not exists cliente (
	idCliente INT AUTO_INCREMENT PRIMARY KEY,
	Usuario VARCHAR(100) NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(100) NOT NULL,
	correo VARCHAR(100) NOT NULL,
	telefono VARCHAR(20) NOT NULL,
	fecha DATE,
	ciudad VARCHAR(100),
	status INT DEFAULT 0
        );

CREATE TABLE if not exists pedido (
            idPedido INT AUTO_INCREMENT PRIMARY KEY, 
            codigo VARCHAR(100),
            estado_envio VARCHAR(50) DEFAULT 'pendiente',
            metodo_pago VARCHAR(50) NOT NULL,
            fecha_pedido DATE,
            total FLOAT,
            cantidadProducto INT,
            direccion varchar(255),
            status INT DEFAULT 0, 
            usuario varchar(100),
            folio_producto varchar(100)  
);

CREATE TABLE if not exists producto (
	idProducto INT AUTO_INCREMENT PRIMARY KEY,
	folio VARCHAR(100) NOT NULL,
	nombre VARCHAR(255) NOT NULL,
	precio FLOAT NOT NULL,
	descripcion VARCHAR(255) NOT NULL,
	fecha DATE,
	categoria VARCHAR(100),
	status INT DEFAULT 0
);

CREATE TABLE  if not exists proveedor (
	idProveedor INT AUTO_INCREMENT PRIMARY KEY,
	Usuario VARCHAR(100),
	RFC VARCHAR(100) NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(100) NOT NULL,
	correo VARCHAR(255) NOT NULL,
	telefono VARCHAR(50) NOT NULL,
	fecha DATE,
	localizacion VARCHAR(50),
	status INT DEFAULT 0
);
