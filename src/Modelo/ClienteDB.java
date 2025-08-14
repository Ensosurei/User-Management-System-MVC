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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class ClienteDB extends ManejadorDB {

    public ClienteDB() {}

    @Override
    public int crearTabla() {
        this.sqlConsulta = "create table if not exists cliente (" +
            "idCliente INT AUTO_INCREMENT PRIMARY KEY," +
            "Usuario VARCHAR(30) NOT NULL," +
            "nombre VARCHAR(30) NOT NULL," +
            "apellido VARCHAR(30) NOT NULL," +
            "correo VARCHAR(30) NOT NULL," +
            "telefono VARCHAR(10) NOT NULL," +
            "fecha DATE," +
            "ciudad VARCHAR(30)," +
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
            System.err.println("Error al crear la tabla cliente: " + e.getMessage());
            fila = -1;
        }
        return fila;
    }

    @Override
    public int registrar(Object obj) {
        if (!(obj instanceof Cliente)) return 0;

        int fila = 0;
        Cliente c = (Cliente) obj;
        this.sqlConsulta = "INSERT INTO cliente VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?);";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, c.getUsuario());
            this.pstm.setString(2, c.getNombre());
            this.pstm.setString(3, c.getApellido());
            this.pstm.setString(4, c.getCorreo());
            this.pstm.setString(5, c.getTelefono());
            this.pstm.setString(6, c.getFecha());
            this.pstm.setString(7, c.getCiudad());
            this.pstm.setInt(8, c.getStatus());

            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al registrar cliente: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int actualizar(Object obj) {
        if (!(obj instanceof Cliente)) return 0;

        int fila = 0;
        Cliente c = (Cliente) obj;
        this.sqlConsulta = "UPDATE cliente SET nombre=?, apellido=?, correo=?, telefono=?, fecha=?, ciudad=? WHERE Usuario=? AND status=0";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, c.getNombre());
            this.pstm.setString(2, c.getApellido());
            this.pstm.setString(3, c.getCorreo());
            this.pstm.setString(4, c.getTelefono());
            this.pstm.setString(5, c.getFecha());
            this.pstm.setString(6, c.getCiudad());
            this.pstm.setString(7, c.getUsuario());

            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int activar(Object obj) {
        if (!(obj instanceof Cliente)) return 0;

        int fila = 0;
        Cliente c = (Cliente) obj;
        this.sqlConsulta = "UPDATE cliente SET status=0 WHERE Usuario=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, c.getUsuario());
            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al activar cliente: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int desactivar(Object obj) {
        if (!(obj instanceof Cliente)) return 0;

        int fila = 0;
        Cliente c = (Cliente) obj;
        this.sqlConsulta = "UPDATE cliente SET status=1 WHERE Usuario=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, c.getUsuario());
            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al desactivar cliente: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public Object consultar(Object obj) {
        if (!(obj instanceof Cliente)) return 0;

        Cliente c = (Cliente) obj;
        this.sqlConsulta = "SELECT * FROM cliente WHERE status=0 AND Usuario=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, c.getUsuario());
            registros = this.pstm.executeQuery();

            if (registros.next()) {
                c.setNombre(registros.getString("nombre"));
                c.setApellido(registros.getString("apellido"));
                c.setCorreo(registros.getString("correo"));
                c.setTelefono(registros.getString("telefono"));
                c.setFecha(registros.getString("fecha"));
                c.setCiudad(registros.getString("ciudad"));
                c.setStatus(registros.getInt("status"));
            } else {
                c = null;
            }
            cerrar();
        } catch (SQLException e) {
            System.err.println("Error al consultar cliente: " + e.getMessage());
        }
        return c;
    }

    @Override
    public ResultSet todos(int status) {
        this.sqlConsulta = "SELECT * FROM cliente WHERE status=?";
        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setInt(1, status);
            registros = this.pstm.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los clientes: " + e.getMessage());
        }
        return registros;
    }

    @Override
    public int contarRegistros(int status) {
        int cantidad = 0;
        this.sqlConsulta = "SELECT COUNT(*) AS cantidad FROM cliente WHERE status=?";
        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setInt(1, status);
            registros = this.pstm.executeQuery();
            if (registros.next()) cantidad = registros.getInt("cantidad");
        } catch (SQLException e) {
            System.err.println("Error al contar clientes: " + e.getMessage());
        }
        return cantidad;
    }

    public DefaultTableModel mostrarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        ClienteDB db = new ClienteDB();
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
            System.err.println("Error al mostrar tabla de clientes: " + e.getMessage());
        }
        return modelo;
    }
}

