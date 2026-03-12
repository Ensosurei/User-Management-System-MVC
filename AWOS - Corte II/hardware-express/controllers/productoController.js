const productoModel = require('../models/productoModel');

function obtenerProductos(req, res) {
    const productos = productoModel.obtenerProductos();
    res.json(productos);
}

function crearProducto(req, res) {

    const { nombre, precio, categoria, stock } = req.body;

    if (!nombre || !precio || !categoria || stock === undefined) {
        return res.status(400).json({ error: "Faltan datos obligatorios" });
    }

    if (precio <= 0) {
        return res.status(400).json({ error: "El precio debe ser mayor a 0" });
    }

    if (stock < 0) {
        return res.status(400).json({ error: "Stock invalido" });
    }

    const productos = productoModel.obtenerProductos();

    const duplicado = productos.find(p => p.nombre.toLowerCase() === nombre.toLowerCase());

    if (duplicado) {
        return res.status(400).json({ error: "El producto ya existe" });
    }

    const nuevoProducto = productoModel.crearProducto(nombre, precio, categoria, stock);

    res.status(201).json(nuevoProducto);
}

function obtenerProducto(req, res) {

    const id = parseInt(req.params.id);

    const producto = productoModel.obtenerProductoPorId(id);

    if (!producto) {
        return res.status(404).json({ error: "Producto no encontrado" });
    }

    res.json(producto);
}

function eliminarProducto(req, res) {

    const id = parseInt(req.params.id);

    const eliminado = productoModel.eliminarProducto(id);

    if (!eliminado) {
        return res.status(404).json({ error: "Producto no encontrado" });
    }

    res.status(204).send();
}

function actualizarProducto(req, res) {

    const id = parseInt(req.params.id);
    const { nombre, precio, categoria, stock } = req.body;

    if (!nombre || !precio || !categoria || stock === undefined) {
        return res.status(400).json({ error: "Faltan datos obligatorios" });
    }

    if (precio <= 0) {
        return res.status(400).json({ error: "Precio invalido" });
    }

    if (stock < 0) {
        return res.status(400).json({ error: "Stock invalido" });
    }

    const productos = productoModel.obtenerProductos();

    const duplicado = productos.find(
        p => p.nombre.toLowerCase() === nombre.toLowerCase() && p.id !== id
    );

    if (duplicado) {
        return res.status(400).json({ error: "El nombre del producto ya existe" });
    }

    const producto = productoModel.actualizarProducto(id, nombre, precio, categoria, stock);

    if (!producto) {
        return res.status(404).json({ error: "Producto no encontrado" });
    }

    res.json(producto);
}

module.exports = {
    obtenerProductos,
    crearProducto,
    obtenerProducto,
    eliminarProducto,
    actualizarProducto
};