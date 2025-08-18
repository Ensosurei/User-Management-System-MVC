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

public class PedidoDB extends ManejadorDB {

    public PedidoDB() {}

    @Override
    public int crearTabla() {
        this.sqlConsulta = "CREATE TABLE if not exists pedido (" +
            "idPedido INT AUTO_INCREMENT PRIMARY KEY," +
            "codigo VARCHAR(100)," +
            "estado_envio VARCHAR(50) DEFAULT 'pendiente'," +
            "metodo_pago VARCHAR(50) NOT NULL," +
            "fecha_pedido DATE," +
            "total FLOAT," +
            "cantidadProducto INT," +
            "direccion varchar(255)," +
            "status INT DEFAULT 0, " +
            "usuario varchar(100)," +
            "folio_producto varchar(100)" +    
        ");";

        int fila = 0;
        try {
            if (abrir()) {
                this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
                fila = this.pstm.executeUpdate();
                cerrar();
            }
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla pedido: " + e.getMessage());
            fila = -1;
        }
        return fila;
    }

    @Override
    public int registrar(Object obj) {
        if (!(obj instanceof Pedido)) return 0;

        int fila = 0;
        Pedido p = (Pedido) obj;
        this.sqlConsulta = "INSERT INTO pedido VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getCodigo());
            this.pstm.setString(2, p.getEstadoEnvio());
            this.pstm.setString(3, p.getMetodoPago());
            this.pstm.setString(4, p.getFechaPedido());
            this.pstm.setFloat(5, p.getTotal());
            this.pstm.setInt(6, p.getCantidadProducto());
            this.pstm.setString(7, p.getDireccion());
            this.pstm.setInt(8, p.getStatus());
            this.pstm.setString(9, p.getUsuario());
            this.pstm.setString(10, p.getFolio());
            

            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al registrar pedido: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int actualizar(Object obj) {
        if (!(obj instanceof Pedido)) return 0;

        int fila = 0;
        Pedido p = (Pedido) obj;
        this.sqlConsulta = "UPDATE pedido SET estado_envio=?, metodo_pago=?, fecha_pedido=?, total=?, cantidadProducto=?, direccion=?, folio_producto=?, usuario=? WHERE codigo=? AND status=0";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getEstadoEnvio());
            this.pstm.setString(2, p.getMetodoPago());
            this.pstm.setString(3, p.getFechaPedido());
            this.pstm.setFloat(4, p.getTotal());
            this.pstm.setInt(5, p.getCantidadProducto());
            this.pstm.setString(6, p.getDireccion());
            this.pstm.setString(7, p.getFolio());
            this.pstm.setString(8, p.getUsuario());
            this.pstm.setString(9, p.getCodigo());
           

            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al actualizar pedido: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int activar(Object obj) {
        if (!(obj instanceof Pedido)) return 0;

        int fila = 0;
        Pedido p = (Pedido) obj;
        this.sqlConsulta = "UPDATE pedido SET status=0 WHERE codigo=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getCodigo());
            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al activar pedido: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public int desactivar(Object obj) {
        if (!(obj instanceof Pedido)) return 0;

        int fila = 0;
        Pedido p = (Pedido) obj;
        this.sqlConsulta = "UPDATE pedido SET status=1 WHERE codigo=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getCodigo());
            fila = this.pstm.executeUpdate();
            cerrar();
        } catch (SQLException e) {
            fila = -1;
            System.err.println("Error al desactivar pedido: " + e.getMessage());
        }
        return fila;
    }

    @Override
    public Object consultar(Object obj) {
        if (!(obj instanceof Pedido)) return 0;

        Pedido p = (Pedido) obj;
        this.sqlConsulta = "SELECT * FROM pedido WHERE status=0 AND codigo=?";

        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setString(1, p.getCodigo());
            registros = this.pstm.executeQuery();

            if (registros.next()) {
                p.setEstadoEnvio(registros.getString("estado_envio"));
                p.setMetodoPago(registros.getString("metodo_pago"));
                p.setFechaPedido(registros.getString("fecha_pedido"));
                p.setTotal(registros.getFloat("total"));
                p.setCantidadProducto(registros.getInt("cantidadProducto"));
                p.setDireccion(registros.getString("direccion"));
                p.setStatus(registros.getInt("status"));
                p.setFolio(registros.getString("folio_producto"));
                p.setUsuario(registros.getString("usuario"));
            } else {
                p = null;
            }
            cerrar();
        } catch (SQLException e) {
            System.err.println("Error al consultar pedido: " + e.getMessage());
        }
        return p;
    }

    @Override
    public ResultSet todos(int status) {
        this.sqlConsulta = "SELECT * FROM pedido WHERE status=?";
        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setInt(1, status);
            registros = this.pstm.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los pedidos: " + e.getMessage());
        }
        return registros;
    }

    @Override
    public int contarRegistros(int status) {
        int cantidad = 0;
        this.sqlConsulta = "SELECT COUNT(*) AS cantidad FROM pedido WHERE status=?";
        abrir();
        try {
            this.pstm = this.conexion.prepareStatement(this.sqlConsulta);
            this.pstm.setInt(1, status);
            registros = this.pstm.executeQuery();
            if (registros.next()) cantidad = registros.getInt("cantidad");
        } catch (SQLException e) {
            System.err.println("Error al contar pedidos: " + e.getMessage());
        }
        return cantidad;
    }

    public DefaultTableModel mostrarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        PedidoDB db = new PedidoDB() {};
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
            System.err.println("Error al mostrar tabla de pedidos: " + e.getMessage());
        }
        return modelo;
    }
}
