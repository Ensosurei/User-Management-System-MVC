/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author dntva
 */
import java.sql.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ProductoDB extends ManejadorDB {

    public ProductoDB() {}

    @Override
    public int crearTabla() {
        this.sqlConsulta = "CREATE TABLE if not exists producto (" +
            "idProducto INT AUTO_INCREMENT PRIMARY KEY," +
            "folio VARCHAR(30) NOT NULL," +
            "nombre VARCHAR(30) NOT NULL," +
            "precio FLOAT NOT NULL," +
            "descripcion VARCHAR(50) NOT NULL," +
            "fecha DATE," +
            "categoria VARCHAR(30)," +
            "status INT DEFAULT 0" +
        ");";

        int fila = 0;
        try {
            if (abrir()) {
                this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
                fila = this.pstm.executeUpdate();
                cerrar();
            }
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla producto: " + e.getMessage());
            fila = -1;
        }
        return fila;
    }

    @Override
    public int registrar(Object obj) {
        if (!(obj instanceof Producto)) return 0;

        int fila = 0;
        Producto p = (Producto) obj;
        this.sqlConsulta = "INSERT INTO producto VALUES (null, ?, ?, ?, ?, ?, ?, ?);";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getFolio());
            this.pstm.setString(2, p.getNombre());
            this.pstm.setFloat(3, p.getPrecio());
            this.pstm.setString(4, p.getDescripcion());
            this.pstm.setString(5, p.getFecha());
            this.pstm.setString(6, p.getCategoria());
            this.pstm.setInt(7, p.getStatus());

            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al registrar producto: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int actualizar(Object obj) {
        if (!(obj instanceof Producto)) return 0;

        int fila = 0;
        Producto p = (Producto) obj;
        this.sqlConsulta = "UPDATE producto SET nombre=?, precio=?, descripcion=?, fecha=?, categoria=? WHERE folio=? AND status=0";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getNombre());
            this.pstm.setFloat(2, p.getPrecio());
            this.pstm.setString(3, p.getDescripcion());
            this.pstm.setString(4, p.getFecha());
            this.pstm.setString(5, p.getCategoria());
            this.pstm.setString(6, p.getFolio());

            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int activar(Object obj) {
        if (!(obj instanceof Producto)) return 0;

        int fila = 0;
        Producto p = (Producto) obj;
        this.sqlConsulta = "UPDATE producto SET status=0 WHERE folio=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getFolio());
            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al activar producto: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int desactivar(Object obj) {
        if (!(obj instanceof Producto)) return 0;

        int fila = 0;
        Producto p = (Producto) obj;
        this.sqlConsulta = "UPDATE producto SET status=1 WHERE folio=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getFolio());
            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al desactivar producto: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public Object consultar(Object obj) {
        if (!(obj instanceof Producto)) return 0;

        Producto p = (Producto) obj;
        this.sqlConsulta = "SELECT * FROM producto WHERE status=0 AND folio=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getFolio());
            registros = this.pstm.executeQuery();

            if (registros.next()) {
                p.setNombre(registros.getString("nombre"));
                p.setPrecio(registros.getFloat("precio"));
                p.setDescripcion(registros.getString("descripcion"));
                p.setFecha(registros.getString("fecha"));
                p.setCategoria(registros.getString("categoria"));
                p.setStatus(registros.getInt("status"));
            } else {
                p = null;
            }
            cerrar();
        } catch (SQLException e) {
            System.err.println("Error al consultar producto: " + e.getMessage());
        }
        return p;
    }

    @Override
    public ResultSet todos(int status) {
        this.sqlConsulta = "SELECT * FROM producto WHERE status=?";
        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setInt(1, status);
            registros = this.pstm.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los productos: " + e.getMessage());
        }
        return registros;
    }

    @Override
    public int contarRegistros(int status) {
        int cantidad = 0;
        this.sqlConsulta = "SELECT COUNT(*) AS cantidad FROM producto WHERE status=?";
        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setInt(1, status);
            registros = this.pstm.executeQuery();
            if (registros.next()) cantidad = registros.getInt("cantidad");
        } catch (SQLException e) {
            System.err.println("Error al contar productos: " + e.getMessage());
        }
        return cantidad;
    }

    public DefaultTableModel mostrarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        ProductoDB db = new ProductoDB() {};
        db.crearTabla();
        ResultSet reg = db.todos(0);

        try {
            ResultSetMetaData metaData = reg.getMetaData();
            int columnas = metaData.getColumnCount();

            for (int i = 1; i <= columnas; i++) {
                modelo.addColumn(metaData.getColumnLabel(i));
            }

            while (reg.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 1; i <= columnas; i++) {
                    fila[i - 1] = reg.getObject(i);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al mostrar tabla de productos: " + e.getMessage());
        }
        return modelo;
    }
}
