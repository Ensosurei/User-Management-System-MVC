const fs = require('fs');
const path = require('path');

const archivo = path.join(__dirname, '../inventario.json');

function leerDatos() {
    const data = fs.readFileSync(archivo, 'utf-8');
    return JSON.parse(data);
}

function guardarDatos(datos) {
    fs.writeFileSync(archivo, JSON.stringify(datos, null, 2));
}

function obtenerProductos() {
    const datos = leerDatos();
    return datos.productos;
}

function obtenerProductoPorId(id) {
    const datos = leerDatos();
    return datos.productos.find(p => p.id === id);
}

function crearProducto(nombre, precio, categoria, stock) {

    const datos = leerDatos();

    datos.ultimoId++;

    const nuevoProducto = {
        id: datos.ultimoId,
        nombre,
        precio,
        categoria,
        stock
    };

    datos.productos.push(nuevoProducto);

    guardarDatos(datos);

    return nuevoProducto;
}

function eliminarProducto(id) {

    const datos = leerDatos();

    const index = datos.productos.findIndex(p => p.id === id);

    if (index === -1) return false;

    datos.productos.splice(index, 1);

    guardarDatos(datos);

    return true;
}

function actualizarProducto(id, nombre, precio, categoria, stock) {

    const datos = leerDatos();

    const producto = datos.productos.find(p => p.id === id);

    if (!producto) return null;

    producto.nombre = nombre;
    producto.precio = precio;
    producto.categoria = categoria;
    producto.stock = stock;

    guardarDatos(datos);

    return producto;
}

module.exports = {
    obtenerProductos,
    obtenerProductoPorId,
    crearProducto,
    eliminarProducto,
    actualizarProducto
};