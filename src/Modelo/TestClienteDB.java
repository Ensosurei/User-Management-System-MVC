/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Modelo;
import java.sql.*;
/**
 *
 * @author dntva
 */
public class TestClienteDB {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) {
        
ClienteDB db = new ClienteDB();
Cliente cliente = new Cliente();


/* db.crearTabla();
 System.out.println("Se creo la tabla");
 
 
       cliente.setUsuario("Dvalladares");
        cliente.setNombre("Dante");
        cliente.setApellido("Uribe");
        cliente.setCorreo("1234@ejemplo.com");
        cliente.setTelefono("6693288905");
        cliente.setFecha("2025-08-13");
        cliente.setCiudad("Mazatlán");
        cliente.setStatus(0);
        db.registrar(cliente);*/
        
        cliente.setUsuario("Dvalladares");
        cliente.setNombre("Dante");
        cliente.setApellido("Valladares");
        cliente.setCorreo("5678@ejemplo.com");
        cliente.setTelefono("6693288905");
        cliente.setFecha("2025-08-13");
        cliente.setCiudad("Choix");
        cliente.setStatus(0);
        if ( db.actualizar(cliente)>0 ) System.out.println("Se Actualizo con exito");
    }
    
    
}
