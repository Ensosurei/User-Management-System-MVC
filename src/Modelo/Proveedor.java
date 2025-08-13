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

public class Proveedor implements imprimible{
    private int idProveedor;
    private String usuario;
    private String RFC;
    private String nombre;
    private String apellido;
    private String correo;
    private String fecha;
    private String localizacion;
    private int status;

    public Proveedor() {
        this.usuario = "";
        this.RFC = "";
        this.nombre = "";
        this.apellido = "";
        this.correo = "";
        this.fecha = "";
        this.localizacion = "";
        this.status = 0;
    }


    public Proveedor(String usuario, String RFC, String nombre, String apellido, String correo, String fecha, String localizacion, int status) {
        this.usuario = usuario;
        this.RFC = RFC;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fecha = fecha;
        this.localizacion = localizacion;
        this.status = status;
    }


    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

@Override
    public void imprimir() {
        System.out.println("ID Proveedor: " + idProveedor);
        System.out.println("Usuario: " + usuario);
        System.out.println("RFC: " + RFC);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Correo: " + correo);
        System.out.println("Fecha: " + fecha);
        System.out.println("Localización: " + localizacion);
        System.out.println("Status: " + status);
    }
}
