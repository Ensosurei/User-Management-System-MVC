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

public class Producto implements imprimible {
    private int idProducto;
    private String folio;
    private String nombre;
    private float precio;
    private String descripcion;
    private String fecha;
    private String categoria;
    private int status;

    // Constructor vacío
    public Producto() {
        this.folio = "";
        this.nombre = "";
        this.precio = 0.0f;
        this.descripcion = "";
        this.fecha = "";
        this.categoria = "";
        this.status = 0;
    }

    // Constructor completo
    public Producto(String folio, String nombre, float precio, String descripcion, String fecha, String categoria, int status) {
        this.folio = folio;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.categoria = categoria;
        this.status = status;
    }

    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
        System.out.println("ID Producto: " + idProducto);
        System.out.println("Folio: " + folio);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Fecha: " + fecha);
        System.out.println("Categoría: " + categoria);
        System.out.println("Status: " + status);
    }
}
