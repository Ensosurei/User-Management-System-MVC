/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Modelo;
/**
 *
 * @author dntva
 */
public class TestProductoDB{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductoDB db = new ProductoDB();
        Producto producto = new Producto();
        
        /*db.crearTabla();
        System.out.println("Se creo correctamente la tabla");*/
        
        producto.setFolio("PRD-001");
        producto.setNombre("Laptop ASUS ZenBook");
        producto.setPrecio(18999.99f);
        producto.setDescripcion("Ultrabook 14'' Intel i7");
        producto.setFecha("2025-08-13");
        producto.setCategoria("Electrónica");
        producto.setStatus(0);
        
        db.registrar(producto);
    }
    
}
