const express = require('express');
const app = express();

const productoRoutes = require('./routes/productoRoutes');

app.use(express.json());

app.use('/api', productoRoutes);

const PORT = 3000;

app.listen(PORT, () => {
    console.log(`Servidor corriendo en http://localhost:${PORT}`);
});