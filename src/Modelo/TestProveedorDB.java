/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Modelo;

/**
 *
 * @author dntva
 */

    public class TestProveedorDB {
    public static void main(String[] args) {
        
        ProveedorDB db = new ProveedorDB();
        Proveedor proveedor = new Proveedor();

        db.crearTabla();
        System.out.println("Se creo la tabla");

        
        
        proveedor.setUsuario("proveedorMX");
        proveedor.setRFC("ABC123456XYZ");
        proveedor.setNombre("Carlos");
        proveedor.setApellido("Ramírez");
        proveedor.setCorreo("proveedor@ejemplo.com");
        proveedor.setFecha("2025-08-13");
        proveedor.setLocalizacion("Guadalajara");
        proveedor.setStatus(0);
        
        db.registrar(proveedor);

        /*proveedor.setUsuario("proveedorMX");
        proveedor.setRFC("XYZ987654ABC");
        proveedor.setNombre("Carlos");
        proveedor.setApellido("Ramírez López");
        proveedor.setCorreo("nuevo@ejemplo.com");
        proveedor.setFecha("2025-08-14");
        proveedor.setLocalizacion("Zapopan");
        proveedor.setStatus(0);

        if (db.actualizar(proveedor) > 0) {
            System.out.println("Proveedor actualizado con éxito.");
        } else {
            System.out.println("Error al actualizar proveedor.");
        }*/
    }
}

