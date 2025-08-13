/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Modelo;

/**
 *
 * @author dntva
 */
public class TestPedidoDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    PedidoDB db = new PedidoDB();
    Pedido pedido = new  Pedido();
    
   /* db.crearTabla();
     System.out.println("Se creo correctamente la tabla");*/
     
     pedido.setCodigo("PED002");
     pedido.setEstadoEnvio("pendiente");           
     pedido.setMetodoPago("transferencia");         
     pedido.setFechaPedido("2025-08-15");          
     pedido.setTotal(2499.50f);                     
     pedido.setCantidadProducto(5);                 
     pedido.setStatus(0); 
     
     db.registrar(pedido);
    }
 
}
