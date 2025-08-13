/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
/**
 *
 * @author dntva
 */

public class Cliente implements imprimible{
    private int idCliente;
    private String usuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String fecha;
    private String ciudad;
    private int status;

    public Cliente() {
        this.usuario = "";
        this.nombre = "";
        this.apellido = "";
        this.correo = "";
        this.telefono = "";
        this.fecha="";
        this.ciudad = "";
        this.status = 0;
    }

    public Cliente(String usuario, String nombre, String apellido, String correo, String telefono, String  fecha, String ciudad, int status) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha = fecha;
        this.ciudad = ciudad;
        this.status = status;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

@Override
    public void imprimir() {
        System.out.println("ID Cliente: " + idCliente);
        System.out.println("Usuario: " + usuario);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Correo: " + correo);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Fecha: " + fecha);
        System.out.println("Ciudad: " + ciudad);
        System.out.println("Status: " + status);
    }
}




