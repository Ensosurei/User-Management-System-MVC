const express = require('express');
const router = express.Router();

const controller = require('../controllers/productoController');

router.get('/productos', controller.obtenerProductos);

router.get('/productos/:id', controller.obtenerProducto);

router.post('/productos', controller.crearProducto);

router.put('/productos/:id', controller.actualizarProducto);

router.delete('/productos/:id', controller.eliminarProducto);

module.exports = router;