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

 public class ProveedorDB extends ManejadorDB {

    public ProveedorDB() {}

    @Override
    public int crearTabla() {
        this.sqlConsulta = "CREATE TABLE  if not exists proveedor (" +
            "idProveedor INT AUTO_INCREMENT PRIMARY KEY," +
            "Usuario VARCHAR(100)," +
            "RFC VARCHAR(100) NOT NULL," +
            "nombre VARCHAR(100) NOT NULL," +
            "apellido VARCHAR(100) NOT NULL," +
            "correo VARCHAR(255) NOT NULL," +
            "telefono VARCHAR(50) NOT NULL," +
            "fecha DATE," +
            "localizacion VARCHAR(50)," +
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
            System.err.println("Error al crear la tabla proveedor: " + e.getMessage());
            fila = -1;
        }
        return fila;
    }

    @Override
    public int registrar(Object obj) {
        if (!(obj instanceof Proveedor)) return 0;

        int fila = 0;
        Proveedor p = (Proveedor) obj;
        this.sqlConsulta = "INSERT INTO proveedor VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getUsuario());
            this.pstm.setString(2, p.getRFC());
            this.pstm.setString(3, p.getNombre());
            this.pstm.setString(4, p.getApellido());
            this.pstm.setString(5, p.getCorreo());
            this.pstm.setString(6, p.getTelefono());
            this.pstm.setString(7, p.getFecha());
            this.pstm.setString(8, p.getLocalizacion());
            this.pstm.setInt(9, p.getStatus());

            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al registrar proveedor: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int actualizar(Object obj) {
        if (!(obj instanceof Proveedor)) return 0;

        int fila = 0;
        Proveedor p = (Proveedor) obj;
        this.sqlConsulta = "UPDATE proveedor SET RFC=?, nombre=?, apellido=?, correo=?, telefono=?, fecha=?, localizacion=? WHERE Usuario=? AND status=0";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getRFC());
            this.pstm.setString(2, p.getNombre());
            this.pstm.setString(3, p.getApellido());
            this.pstm.setString(4, p.getCorreo());
            this.pstm.setString(5, p.getTelefono());
            this.pstm.setString(6, p.getFecha());
            this.pstm.setString(7, p.getLocalizacion());
            this.pstm.setString(8, p.getUsuario());

            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al actualizar proveedor: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int activar(Object obj) {
        if (!(obj instanceof Proveedor)) return 0;

        int fila = 0;
        Proveedor p = (Proveedor) obj;
        this.sqlConsulta = "UPDATE proveedor SET status=0 WHERE Usuario=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getUsuario());
            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al activar proveedor: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int desactivar(Object obj) {
        if (!(obj instanceof Proveedor)) return 0;

        int fila = 0;
        Proveedor p = (Proveedor) obj;
        this.sqlConsulta = "UPDATE proveedor SET status=1 WHERE Usuario=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getUsuario());
            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al desactivar proveedor: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public Object consultar(Object obj) {
        if (!(obj instanceof Proveedor)) return 0;

        Proveedor p = (Proveedor) obj;
        this.sqlConsulta = "SELECT * FROM proveedor WHERE status=0 AND Usuario=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getUsuario());
            registros = this.pstm.executeQuery();

            if (registros.next()) {
                p.setRFC(registros.getString("RFC"));
                p.setNombre(registros.getString("nombre"));
                p.setApellido(registros.getString("apellido"));
                p.setCorreo(registros.getString("correo"));
                p.setTelefono(registros.getString("telefono"));
                p.setFecha(registros.getString("fecha"));
                p.setLocalizacion(registros.getString("localizacion"));
                p.setStatus(registros.getInt("status"));
            } else {
                p = null;
            }
            cerrar();
        } catch (SQLException e) {
            System.err.println("Error al consultar proveedor: " + e.getMessage());
        }
        return p;
    }

    @Override
    public ResultSet todos(int status) {
        this.sqlConsulta = "SELECT * FROM proveedor WHERE status=?";
        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setInt(1, status);
            registros = this.pstm.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los proveedores: " + e.getMessage());
        }
        return registros;
    }

    @Override
    public int contarRegistros(int status) {
        int cantidad = 0;
        this.sqlConsulta = "SELECT COUNT(*) AS cantidad FROM proveedor WHERE status=?";
        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setInt(1, status);
            registros = this.pstm.executeQuery();
            if (registros.next()) cantidad = registros.getInt("cantidad");
        } catch (SQLException e) {
            System.err.println("Error al contar proveedores: " + e.getMessage());
        }
        return cantidad;
    }

    public DefaultTableModel mostrarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        ProveedorDB db = new ProveedorDB() {};
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
            System.err.println("Error al mostrar tabla de proveedores: " + e.getMessage());
        }
        return modelo;
    }
}