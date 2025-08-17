package Controlador;

import Modelo.Pedido;
import Modelo.PedidoDB;

import Modelo.Cliente;
import Modelo.ClienteDB;

import Modelo.Producto;
import Modelo.ProductoDB;
        
import Vista.jifVistaPedido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Alfredo
 */
public class ControladorPedido implements ActionListener {

    private Pedido pedido;
    private Producto producto;
    private Cliente cliente;
    private jifVistaPedido vista;
    private PedidoDB db = new PedidoDB();
    private ProductoDB dbP = new ProductoDB();
    private ClienteDB dbC = new ClienteDB();

    public ControladorPedido(Pedido pedido, jifVistaPedido vista) {
        this.pedido = pedido;
        this.vista = vista;

        vista.btnGuardar.addActionListener(this);
        vista.btnCancelar.addActionListener(this);
        vista.btnBorrar.addActionListener(this);
        vista.btnCerrar.addActionListener(this);
        vista.btnBuscarPedido.addActionListener(this);
        vista.btnBuscarProducto.addActionListener(this);
        vista.btnNuevo.addActionListener(this);
        vista.btnBuscarUsuario.addActionListener(this);

        vista.tblPedido.setModel(db.mostrarTabla());
    }
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public jifVistaPedido getVista() {
        return vista;
    }

    public void setVista(jifVistaPedido vista) {
        this.vista = vista;
    }


    private String fechaMysql() {
        Date fechaSeleccionada = vista.jdcFecha.getDate();
        if (fechaSeleccionada != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.format(fechaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(vista, "Es necesario seleccionar una fecha", "Pedido", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    private void guardarDatos() {
        pedido.setCantidadProducto(Integer.parseInt(vista.txtCantidad.getText()));
        pedido.setTotal(Float.parseFloat(vista.txtTotal.getText()));
        pedido.setDireccion(vista.txtDireccion.getText());
        pedido.setUsuario(vista.txtUsuario.getText());
        pedido.setFolio(vista.txtProducto.getText());

        String metodo = switch (vista.cmbPago.getSelectedIndex()) {
            case 0 -> "Tarjeta de credito";
            case 1 -> "Transferencia";
            case 2 -> "Pago contra entrega";
            default -> "Sin método";
        };
        pedido.setMetodoPago(metodo);

        pedido.setCodigo(vista.txtCodigo.getText());
        pedido.setFechaPedido(this.fechaMysql());
    }

    private boolean calcularTotal() {
        boolean exito=true;
        try {
            float precio = Float.parseFloat(vista.txtPrecio1.getText());
            int cantidad = Integer.parseInt(vista.txtCantidad.getText());
            float total = precio * cantidad;
            vista.txtTotal.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Precio o cantidad inválidos.");
            return !exito;
        }
        return exito;
    }
    
    private void mostrarDatosPedido(){
        vista.txtCodigo.setText(pedido.getCodigo());
        vista.txtCantidad.setText(String.valueOf(pedido.getCantidadProducto()));
        vista.txtDireccion.setText(pedido.getDireccion());
        vista.txtTotal.setText(String.valueOf(pedido.getTotal()));
        
        switch(pedido.getMetodoPago()){
            case "Tarjeta de credito": vista.cmbPago.setSelectedIndex(0); break;
            case "Transferencia": vista.cmbPago.setSelectedIndex(1); break;
            case "Pago contra entrega": vista.cmbPago.setSelectedIndex(2); break;
        }
        
        //fecha
        try{
            SimpleDateFormat formato =new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(pedido.getFechaPedido());
            vista.jdcFecha.setDate(fecha);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(vista,"Surgió un error al convertir la fecha","Pedido",JOptionPane.ERROR_MESSAGE);
        }
        
        //mostrar tabla
        vista.tblPedido.setModel(db.mostrarTabla());
    }
    
    private void mostrarDatosProducto(){
        vista.txtProducto.setText(producto.getFolio());
        vista.txtNombreProducto.setText(producto.getNombre());
        vista.txtPrecio1.setText(String.valueOf(producto.getPrecio()));
    }
    
    private void mostrarDatosCliente(){
        vista.txtUsuario.setText(cliente.getUsuario());
        vista.txtNombres.setText(cliente.getNombre());
        vista.txtCorreo.setText(cliente.getCorreo());
        vista.txtTelefono.setText(cliente.getTelefono());
    }

    private void limpiar() {
        vista.txtCantidad.setText("");
        vista.txtTotal.setText("");
        vista.txtDireccion.setText("");
        vista.txtNombreProducto.setText("");
        vista.txtPrecio1.setText("");
        vista.txtTelefono.setText("");
        vista.txtUsuario.setText("");
        vista.txtProducto.setText("");
        vista.txtCorreo.setText("");
        vista.txtCodigo.setText("");
        vista.txtNombres.setText("");
        
        vista.cmbPago.setSelectedIndex(0);
        
        vista.jdcFecha.setDate(new java.util.Date());
    }
    
    private void habilitar(){
        vista.txtDireccion.setEnabled(true);
        vista.txtCodigo.setEnabled(true);
        vista.txtCantidad.setEnabled(true);
        vista.btnBorrar.setEnabled(true);
        vista.btnGuardar.setEnabled(true);
        
        vista.cmbPago.setEnabled(true);
        
        vista.jdcFecha.setEnabled(true);
    }
    
    private void deshabilitar(){
        vista.txtCantidad.setEnabled(!true);
        vista.txtTotal.setEnabled(!true);
        vista.txtDireccion.setEnabled(!true);
        vista.txtNombreProducto.setEnabled(!true);
        vista.txtPrecio1.setEnabled(!true);
        vista.txtTelefono.setEnabled(!true);
        vista.txtCorreo.setEnabled(!true);
        vista.txtNombres.setEnabled(!true);
        vista.btnBorrar.setEnabled(!true);
        vista.btnGuardar.setEnabled(!true);
        
        vista.cmbPago.setEnabled(!true);
    }
    
    private boolean cerrarVista(){
        boolean exito=true;
        
        int opcion= JOptionPane.showConfirmDialog(vista,"¿Deseas cerra la vista?","Pedido",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (opcion==JOptionPane.YES_OPTION){
            return exito;
        }
        return !exito;
    }
    
    private boolean validar(){
        boolean exito=true;
        if(vista.txtCodigo.getText().equals("") 
                || vista.txtDireccion.getText().equals("") 
                || vista.txtCantidad.getText().equals("")
                || vista.txtProducto.getText().equals("")
                || vista.txtUsuario.getText().equals(""))
        {
            return !exito;
        }
        try{
            Integer.parseInt(vista.txtCantidad.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(vista, "No se capturará la cantidad (solo números enteros)");
            return !exito;
        }
        return exito;
    }
    
    public boolean validarProducto(){
        boolean exito=true;
        
        producto=new Producto();
        producto.setFolio(vista.txtProducto.getText());
        producto=(Producto) dbP.consultar(producto);
            
        if(producto!=null){
            if(vista.txtProducto.getText().equals(producto.getFolio()) 
                    &&  vista.txtPrecio1.getText().equals(String.valueOf(producto.getPrecio()))
                    && vista.txtNombreProducto.getText().equals(producto.getNombre())){
                return exito;
            }
            else{
                JOptionPane.showMessageDialog(vista, "Los datos de Producto no coinciden","Producto",JOptionPane.ERROR_MESSAGE);
                return !exito;
            }
        }
        return !exito;
    }
    
    public boolean validarUsuario(){
        boolean exito=true;
        
        cliente=new Cliente();
        cliente.setUsuario(vista.txtUsuario.getText());
        cliente=(Cliente) dbC.consultar(cliente);
            
        if(cliente!=null){
            if(vista.txtUsuario.getText().equals(cliente.getUsuario()) 
                    &&  vista.txtNombres.getText().equals(cliente.getNombre())
                    && vista.txtCorreo.getText().equals(cliente.getCorreo())
                    && vista.txtTelefono.getText().equals(cliente.getTelefono())){
                return exito;
            }
            else{
                JOptionPane.showMessageDialog(vista, "Los datos del Cliente no coinciden","Cliente",JOptionPane.ERROR_MESSAGE);
                return !exito;
            }
        }
        return !exito;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnBorrar){
            if(vista.txtCodigo.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "Faltó capturar el código","Pedido",JOptionPane.ERROR_MESSAGE);
                vista.txtCodigo.requestFocus();
            }
            else if(JOptionPane.showConfirmDialog(vista, "¿Desea deshabilitar el código? "+pedido.getCodigo(),"Pedido",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
                db.desactivar(pedido);
                vista.tblPedido.setModel(db.mostrarTabla());
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
            vista.txtCodigo.requestFocus(); //para que te ponga el cursor en la caja de texto
        }
        
        if(e.getSource()==vista.btnGuardar){
            
            Pedido pe=null;
            
            if(this.validar()==true){
                if(vista.txtCantidad.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "Faltó capturar la cantidad","Pedido",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if(this.calcularTotal()==true)vista.txtCantidad.setEnabled(!true);
                }
                this.guardarDatos();
                if(this.validarProducto()==!true){
                   return;
                }
                if(this.validarUsuario()==!true){
                   return;
                }
                if(pedido.getFechaPedido()==null) return;
                pe=(Pedido) db.consultar(pedido);
                if(pe==null){
                    if(db.registrar(pedido)>0){
                        JOptionPane.showMessageDialog(vista,"Se registró el pedido con código "+pedido.getCodigo()+" Con exito","Pedido",JOptionPane.INFORMATION_MESSAGE);
                        vista.tblPedido.setModel(db.mostrarTabla());
                    }else JOptionPane.showMessageDialog(vista,"No fue posible guardarse el pedido","Pedido",JOptionPane.ERROR_MESSAGE);
                }else{
                    if(JOptionPane.showConfirmDialog(vista, "El código ya existe "+vista.txtCodigo.getText()+" ¿Deseas actualizar?","Pedido",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
                        this.guardarDatos();
                        if(db.actualizar(pedido)>0){
                            JOptionPane.showMessageDialog(vista, "Se actualizó el pedido con código "+pedido.getCodigo()+" con éxito","Pedido",JOptionPane.INFORMATION_MESSAGE);
                            vista.tblPedido.setModel(db.mostrarTabla());
                        }else JOptionPane.showMessageDialog(vista, "No fue posible actualizar el pedido","Pedido",JOptionPane.ERROR_MESSAGE);
                    }
                }
                this.deshabilitar();
                this.limpiar();
            }else JOptionPane.showMessageDialog(vista, "Faltó capturar información");
        }
        
        if(e.getSource()==vista.btnBuscarPedido){
            //validar informacion
            if(vista.txtCodigo.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "Faltó capturar el código","Pedido",JOptionPane.ERROR_MESSAGE);
                vista.txtCodigo.requestFocus();
            }
            else{
                
                pedido =new Pedido();
                pedido.setCodigo(vista.txtCodigo.getText());
                pedido=(Pedido) db.consultar(pedido);
                
                
                
                if(pedido!=null){
                    producto=new Producto();
                    producto.setFolio(pedido.getFolio());
                    producto=(Producto) dbP.consultar(producto);

                    cliente=new Cliente();
                    cliente.setUsuario(pedido.getUsuario());
                    cliente=(Cliente) dbC.consultar(cliente);
                    this.mostrarDatosPedido();
                    this.mostrarDatosCliente();
                    this.mostrarDatosProducto();
                    vista.btnGuardar.setEnabled(true);
                    vista.btnBorrar.setEnabled(true);
                }
                else{
                    JOptionPane.showMessageDialog(vista, "No se encontró el código: " + vista.txtCodigo.getText(),"Pedido",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        if(e.getSource()==vista.btnBuscarProducto){
            //validar informacion
            if(vista.txtProducto.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "Faltó capturar el folio","Producto",JOptionPane.ERROR_MESSAGE);
                vista.txtProducto.requestFocus();
            }
            else{
                
                producto=new Producto();
                producto.setFolio(vista.txtProducto.getText());
                producto=(Producto) dbP.consultar(producto);
                
                if(producto!=null){
                    this.mostrarDatosProducto();
                }
                else{
                    JOptionPane.showMessageDialog(vista, "No se encontró el folio: " + vista.txtProducto.getText(),"Producto",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        if(e.getSource()==vista.btnBuscarUsuario){
            //validar informacion
            if(vista.txtUsuario.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "Faltó capturar el usuario","Cliente",JOptionPane.ERROR_MESSAGE);
                vista.txtUsuario.requestFocus();
            }
            else{
                
                cliente=new Cliente();
                cliente.setUsuario(vista.txtUsuario.getText());
                cliente=(Cliente) dbC.consultar(cliente);
                
                if(cliente!=null){
                    this.mostrarDatosCliente();
                }
                else{
                    JOptionPane.showMessageDialog(vista, "No se encontró el Usuario: " + vista.txtUsuario.getText(),"Cliente",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
