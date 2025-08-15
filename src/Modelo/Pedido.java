/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author dntva
 */
import java.sql.Date;

public class Pedido implements imprimible {
    private int idPedido;
    private String codigo;
    private String estadoEnvio;
    private String metodoPago;
    private String fechaPedido;
    private float total;
    private int cantidadProducto;
    private String usuario;
    private String folio;
    private String direccion;
    private int status;

    // Constructor vacío
    public Pedido() {
        this.codigo = "";
        this.folio = "";
        this.usuario = "";
        this.estadoEnvio = "pendiente";
        this.metodoPago = "";
        this.fechaPedido = "";
        this.total = 0.0f;
        this.cantidadProducto = 0;
        this.direccion = "";
        this.status = 0;
    }

    // Constructor completo
    public Pedido(String codigo, String estadoEnvio, String metodoPago, String fechaPedido, float total, int cantidadProducto, String direccion, int status, String folio, String usuario) {
        this.codigo = codigo;
        this.estadoEnvio = estadoEnvio;
        this.metodoPago = metodoPago;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.folio = folio;
        this.usuario = usuario;
        this.cantidadProducto = cantidadProducto;
        this.direccion = direccion;
        this.status = status;
    }

    // Getters y Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Método imprimir
    @Override
    public void imprimir() {
        System.out.println("ID Pedido: " + idPedido);
        System.out.println("Código: " + codigo);
        System.out.println("Estado de envío: " + estadoEnvio);
        System.out.println("Método de pago: " + metodoPago);
        System.out.println("Fecha del pedido: " + fechaPedido);
        System.out.println("Total: " + total);
        System.out.println("Cantidad de productos: " + cantidadProducto);
        System.out.println("Direccion:" + direccion);
        System.out.println("Status: " + status);
    }
}

