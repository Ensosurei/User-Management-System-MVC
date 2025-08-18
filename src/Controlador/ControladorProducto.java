/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javax.swing.JFrame;  
import javax.swing.JOptionPane;
import Modelo.ProductoDB;
import Modelo.Producto;
import java.text.SimpleDateFormat;
import java.util.Date;

import Vista.jifVistaProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author ensos
 */
public class ControladorProducto implements ActionListener{
    private Producto producto;
    private jifVistaProducto vista;
    private ProductoDB db =new ProductoDB();
    
    public ControladorProducto(Producto producto, jifVistaProducto vista) {
        this.producto = producto;
        this.vista = vista;
        
        //hacer que controlador escuche los componentes de la vista que estan publicos(botones)
        vista.btnBorrar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnCancelar.addActionListener(this);
        vista.btnCerrar.addActionListener(this);
        vista.btnNuevo.addActionListener(this);
        
        vista.tblProducto.setModel(db.mostrarTabla());
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public jifVistaProducto getVista() {
        return vista;
    }

    public void setVista(jifVistaProducto vista) {
        this.vista = vista;
    }
    
    //metodos de utilerias
    
    private void guardarDatos(){
        producto.setFolio(vista.txtFolio.getText());
        producto.setDescripcion(vista.txtDescripcion.getText());
        producto.setNombre(vista.txtTitulo.getText());
        producto.setPrecio(Float.parseFloat(vista.txtPrecio.getText()));
        String categoria="";
        
        switch(vista.cmbCategoria.getSelectedIndex()){
            case 0: categoria="Artesanias de materiales naturales"; break;
            case 1: categoria="Artesanias textiles y de fibra"; break;
            case 2: categoria="Artesanias artisticas, plasticas y decorativas"; break;
            case 3: categoria="Uso diario"; break;
            case 4: categoria="Delicias naturales"; break;
        }
        
        producto.setCategoria(categoria);
        
        //fecha    
        producto.setFecha(this.fechaMysql());
    }
    
    private void mostrarDatos(){
        vista.txtFolio.setText(producto.getFolio());
        vista.txtDescripcion.setText(producto.getDescripcion());
        vista.txtTitulo.setText(producto.getNombre());
        vista.txtPrecio.setText(String.valueOf(producto.getPrecio()));
        
        switch(producto.getCategoria()){
            case "Artesanias de materiales naturales": vista.cmbCategoria.setSelectedIndex(0); break;
            case "Artesanias textiles y de fibra": vista.cmbCategoria.setSelectedIndex(1); break;
            case "Artesanias artisticas, plasticas y decorativas": vista.cmbCategoria.setSelectedIndex(2); break;
            case "Uso diario": vista.cmbCategoria.setSelectedIndex(3); break;
            case "Delicias naturales": vista.cmbCategoria.setSelectedIndex(4); break;
        }
        
        //fecha
        try{
            SimpleDateFormat formato =new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(producto.getFecha());
            vista.jdcFecha.setDate(fecha);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(vista,"Surgió un error al convertir la fecha","Producto",JOptionPane.ERROR_MESSAGE);
        }
        
        //mostrar tabla
        vista.tblProducto.setModel(db.mostrarTabla());
    }
    
    private boolean cerrarVista(){
        boolean exito=true;
        
        int opcion= JOptionPane.showConfirmDialog(vista,"¿Desea cerrar la vista?","Producto",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (opcion==JOptionPane.YES_OPTION){
            return exito;
        }
        return !exito;
    }
    
    private boolean validar(){
        boolean exito=true;
        if(vista.txtFolio.getText().equals("") || vista.txtDescripcion.getText().equals("") || vista.txtTitulo.getText().equals("") || vista.txtPrecio.getText().equals("")){
            return !exito;
        }
        return exito;
    }
    
    private void deshabilitar(){
        vista.txtDescripcion.setEnabled(!true);
        vista.txtTitulo.setEnabled(!true);
        vista.txtPrecio.setEnabled(!true);
        vista.cmbCategoria.setEnabled(!true);
        vista.btnGuardar.setEnabled(!true);
        vista.btnBorrar.setEnabled(!true);
    }
    
    private void habilitar(){
        vista.txtFolio.setEnabled(true);
        vista.txtDescripcion.setEnabled(true);
        vista.txtTitulo.setEnabled(true);
        vista.txtPrecio.setEnabled(true);
        vista.cmbCategoria.setEnabled(true);
        vista.btnGuardar.setEnabled(true);
        vista.btnBorrar.setEnabled(true);
    }
    
    private void limpiar(){
     vista.txtFolio.setText("");
     vista.txtDescripcion.setText("");
     vista.txtTitulo.setText("");
     vista.txtPrecio.setText("");
     vista.cmbCategoria.setSelectedIndex(0);
     
     vista.jdcFecha.setDate(new java.util.Date());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnBorrar){
            if(vista.txtFolio.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "Faltó capturar el folio","Producto",JOptionPane.ERROR_MESSAGE);
                vista.txtFolio.requestFocus();
            }
            else if(JOptionPane.showConfirmDialog(vista, "¿Desea deshabilitar el folio "+producto.getFolio()+"?","Producto",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
                db.desactivar(producto);
                vista.tblProducto.setModel(db.mostrarTabla());
                this.limpiar();
            }
        }
        
        if(e.getSource()==vista.btnCancelar){
            this.limpiar();
            this.deshabilitar();
        }
        
        if(e.getSource()==vista.btnCerrar){
            if(this.cerrarVista())vista.dispose();
        }
        
        if(e.getSource()==vista.btnNuevo){
            this.habilitar();
            vista.txtFolio.requestFocus(); //para que te ponga el cursor en la caja de texto
        }
        
        if(e.getSource()==vista.btnGuardar){
            
            producto=new Producto();
            Producto pro=null;
            
            if(this.validar()==true){
                this.guardarDatos();
                if(producto.getFecha()==null) return;
                pro=(Producto) db.consultar(producto);
                if(pro==null){
                    if(db.registrar(producto)>0){
                        JOptionPane.showMessageDialog(vista,"Se registró el producto con folio "+producto.getFolio()+" con éxito","Producto",JOptionPane.INFORMATION_MESSAGE);
                        vista.tblProducto.setModel(db.mostrarTabla());
                    }else JOptionPane.showMessageDialog(vista,"No fue posible guardarse el producto","Producto",JOptionPane.ERROR_MESSAGE);
                }else{
                    if(JOptionPane.showConfirmDialog(vista, "El folio "+vista.txtFolio.getText()+"ya existe ¿Deseas actualizar?","Producto",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
                        this.guardarDatos();
                        if(db.actualizar(producto)>0){
                            JOptionPane.showMessageDialog(vista, "Se actualizó el producto con folio "+producto.getFolio()+" con exito","Producto",JOptionPane.INFORMATION_MESSAGE);
                            vista.tblProducto.setModel(db.mostrarTabla());
                        }else JOptionPane.showMessageDialog(vista, "No fue posible actualizar el producto","Producto",JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                this.deshabilitar();
                this.limpiar();
                
            }else JOptionPane.showMessageDialog(vista, "Faltó capturar información");
        }
        
        if(e.getSource()==vista.btnBuscar){
            //validar informacion
            if(vista.txtFolio.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "Faltó capturar el folio","Producto",JOptionPane.ERROR_MESSAGE);
                vista.txtFolio.requestFocus();
            }
            else{
                
                producto=new Producto();
                producto.setFolio(vista.txtFolio.getText());
                producto=(Producto) db.consultar(producto);
                
                if(producto!=null){
                    this.mostrarDatos();
                    vista.btnGuardar.setEnabled(true);
                    vista.btnBorrar.setEnabled(true);
                }
                else{
                    JOptionPane.showMessageDialog(vista, "No se encontró el folio: " + vista.txtFolio.getText(),"Producto",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    
    private String fechaMysql(){
        String fec=null;
        Date fechaSeleccionada = vista.jdcFecha.getDate();
        
        if(fechaSeleccionada!=null){
            SimpleDateFormat formato= new SimpleDateFormat("yyyy/MM/dd");
            fec=formato.format(fechaSeleccionada);
            
        }
        else JOptionPane.showMessageDialog(vista, "Es necesario seleccionar una fecha", "Producto",JOptionPane.INFORMATION_MESSAGE);
        return fec;
    }
}
