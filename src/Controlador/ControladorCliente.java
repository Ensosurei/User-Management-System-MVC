package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDB;
import Vista.jifVistaUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorCliente implements ActionListener {

    private Cliente cliente;
    private jifVistaUsuario vista;
    private ClienteDB db = new ClienteDB();

    public ControladorCliente(Cliente cliente, jifVistaUsuario vista) {
        this.cliente = cliente;
        this.vista = vista;

        vista.btnGuardar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnCancelar.addActionListener(this);
        vista.btnCerrar.addActionListener(this);
        vista.btnNuevo.addActionListener(this);
        vista.btnBorrar.addActionListener(this);

        vista.tblCliente.setModel(db.mostrarTabla());
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public jifVistaUsuario getVista() {
        return vista;
    }

    public void setVista(jifVistaUsuario vista) {
        this.vista = vista;
    }

    private String fechaMysql() {
        Date fechaSeleccionada = vista.jdcFecha.getDate();
        if (fechaSeleccionada != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.format(fechaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una fecha válida.");
            return null;
        }
    }

    private void guardarDatos(){
        cliente.setUsuario(vista.txtUsuario.getText());
        cliente.setNombre(vista.txtNombres.getText());
        cliente.setApellido(vista.txtApellido.getText());
        cliente.setCorreo(vista.txtCorreo.getText());
        cliente.setTelefono(vista.txtTelefono.getText());
        String ciudad = "";

        switch (vista.cmbCiudad.getSelectedIndex()) {
            case 0:
                ciudad = "Ahome";
                break;
            case 1:
                ciudad = "Angostura";
                break;
            case 2:
                ciudad = "Badiraguato";
                break;
            case 3:
                ciudad = "Choix";
                break;
            case 4:
                ciudad = "Concordia";
                break;
            case 5:
                ciudad = "Cosalá";
                break;
            case 6:
                ciudad = "Culiacán";
                break;
            case 7:
                ciudad = "Eldorado";
                break;
            case 8:
                ciudad = "El Fuerte";
                break;
            case 9:
                ciudad = "Elota";
                break;
            case 10:
                ciudad = "Escuinapa";
                break;
            case 11:
                ciudad = "Guasave";
                break;
            case 12:
                ciudad = "Juan José Ríos";
                break;
            case 13:
                ciudad = "Mazatlán";
                break;
            case 14:
                ciudad = "Mocorito";
                break;
            case 15:
                ciudad = "Navolato";
                break;
            case 16:
                ciudad = "Rosario";
                break;
            case 17:
                ciudad = "Salvador Alvarado";
                break;
            case 18:
                ciudad = "San Ignacio";
                break;
            case 19:
                ciudad = "Sinaloa";
                break;
        }

        cliente.setCiudad(ciudad);
        
        //fecha    
        cliente.setFecha(this.fechaMysql());
    }
    
    private void mostrarDatos(){
        vista.txtUsuario.setText(cliente.getUsuario());
        vista.txtNombres.setText(cliente.getNombre());
        vista.txtApellido.setText(cliente.getApellido());
        vista.txtCorreo.setText(cliente.getCorreo());
        vista.txtTelefono.setText(cliente.getTelefono());
        
        switch (cliente.getCiudad()) {
            case "Ahome":
                vista.cmbCiudad.setSelectedIndex(0);
                break;
            case "Angostura":
                vista.cmbCiudad.setSelectedIndex(1);
                break;
            case "Badiraguato":
                vista.cmbCiudad.setSelectedIndex(2);
                break;
            case "Choix":
                vista.cmbCiudad.setSelectedIndex(3);
                break;
            case "Concordia":
                vista.cmbCiudad.setSelectedIndex(4);
                break;
            case "Cosalá":
                vista.cmbCiudad.setSelectedIndex(5);
                break;
            case "Culiacán":
                vista.cmbCiudad.setSelectedIndex(6);
                break;
            case "Eldorado":
                vista.cmbCiudad.setSelectedIndex(7);
                break;
            case "El Fuerte":
                vista.cmbCiudad.setSelectedIndex(8);
                break;
            case "Elota":
                vista.cmbCiudad.setSelectedIndex(9);
                break;
            case "Escuinapa":
                vista.cmbCiudad.setSelectedIndex(10);
                break;
            case "Guasave":
                vista.cmbCiudad.setSelectedIndex(11);
                break;
            case "Juan José Ríos":
                vista.cmbCiudad.setSelectedIndex(12);
                break;
            case "Mazatlán":
                vista.cmbCiudad.setSelectedIndex(13);
                break;
            case "Mocorito":
                vista.cmbCiudad.setSelectedIndex(14);
                break;
            case "Navolato":
                vista.cmbCiudad.setSelectedIndex(15);
                break;
            case "Rosario":
                vista.cmbCiudad.setSelectedIndex(16);
                break;
            case "Salvador Alvarado":
                vista.cmbCiudad.setSelectedIndex(17);
                break;
            case "San Ignacio":
                vista.cmbCiudad.setSelectedIndex(18);
                break;
            case "Sinaloa":
                vista.cmbCiudad.setSelectedIndex(19);
                break;
        }
        
        //fecha
        try{
            SimpleDateFormat formato =new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(cliente.getFecha());
            vista.jdcFecha.setDate(fecha);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(vista,"Surgio un error al convertir la fecha","Cliente",JOptionPane.ERROR_MESSAGE);
        }
        
        //mostrar tabla
        vista.tblCliente.setModel(db.mostrarTabla());
    }

    private boolean cerrarVista(){
        boolean exito=true;
        
        int opcion= JOptionPane.showConfirmDialog(vista,"¿ Deseas cerra la vista?","Cliente",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (opcion==JOptionPane.YES_OPTION){
            return exito;
        }
        return !exito;
    }
    
    private boolean validar(){
        boolean exito=true;
        if(vista.txtUsuario.getText().equals("") 
                || vista.txtNombres.getText().equals("") 
                || vista.txtApellido.getText().equals("") 
                || vista.txtCorreo.getText().equals("")
                || vista.txtTelefono.getText().equals("")){
            return !exito;
        }
        return exito;
    }
    
    private void deshabilitar(){
        vista.txtNombres.setEnabled(!true);
        vista.txtApellido.setEnabled(!true);
        vista.txtCorreo.setEnabled(!true);
        vista.cmbCiudad.setEnabled(!true);
        vista.btnGuardar.setEnabled(!true);
        vista.btnBorrar.setEnabled(!true);
        vista.txtTelefono.setEnabled(!true);
    }
    
    private void habilitar(){
        vista.txtNombres.setEnabled(true);
        vista.txtApellido.setEnabled(true);
        vista.txtCorreo.setEnabled(true);
        vista.cmbCiudad.setEnabled(true);
        vista.btnGuardar.setEnabled(true);
        vista.btnBorrar.setEnabled(true);
        vista.txtTelefono.setEnabled(true);
    }
    
    private void limpiar(){
     vista.txtUsuario.setText("");
     vista.txtNombres.setText("");
     vista.txtApellido.setText("");
     vista.txtCorreo.setText("");
     vista.txtTelefono.setText("");
     vista.cmbCiudad.setSelectedIndex(0);
     
     vista.jdcFecha.setDate(new java.util.Date());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnBorrar){
            if(vista.txtUsuario.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "Faltó capturar el usuario","Cliente",JOptionPane.ERROR_MESSAGE);
                vista.txtUsuario.requestFocus();
            }
            else if(JOptionPane.showConfirmDialog(vista, "Desea deshabilitar el usuario "+cliente.getUsuario(),"Cliente",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
                db.desactivar(cliente);
                vista.tblCliente.setModel(db.mostrarTabla());
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
            vista.txtUsuario.requestFocus(); //para que te ponga el cursor en la caja de texto
        }
        
        if(e.getSource()==vista.btnGuardar){
            
            cliente=new Cliente();
            Cliente cli=null;
            
            if(this.validar()==true){
                this.guardarDatos();
                if(cliente.getFecha()==null) return;
                cli=(Cliente) db.consultar(cliente);
                if(cli==null){
                    if(db.registrar(cliente)>0){
                        JOptionPane.showMessageDialog(vista,"Se registro el cliente con usuario "+cliente.getUsuario()+" Con exito","Cliente",JOptionPane.INFORMATION_MESSAGE);
                        vista.tblCliente.setModel(db.mostrarTabla());
                    }else JOptionPane.showMessageDialog(vista,"No fue posible guardarse el Cliente","Cliente",JOptionPane.ERROR_MESSAGE);
                }else{
                    if(JOptionPane.showConfirmDialog(vista, "El usuario ya existe "+vista.txtUsuario.getText()+" Deseas actualizar?","Cliente",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
                        this.guardarDatos();
                        if(db.actualizar(cliente)>0){
                            JOptionPane.showMessageDialog(vista, "Se actualizo el cliente con usuario "+cliente.getUsuario()+" con exito","Cliente",JOptionPane.INFORMATION_MESSAGE);
                            vista.tblCliente.setModel(db.mostrarTabla());
                        }else JOptionPane.showMessageDialog(vista, "No fue posible actualizar el cliente","Cliente",JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                this.deshabilitar();
                this.limpiar();
                
            }else JOptionPane.showMessageDialog(vista, "Falto capturar informacion");
        }
        
        if(e.getSource()==vista.btnBuscar){
            //validar informacion
            if(vista.txtUsuario.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "Faltó capturar el usuario","Cliente",JOptionPane.ERROR_MESSAGE);
                vista.txtUsuario.requestFocus();
            }
            else{
                
                cliente=new Cliente();
                cliente.setUsuario(vista.txtUsuario.getText());
                cliente=(Cliente) db.consultar(cliente);
                
                if(cliente!=null){
                    this.mostrarDatos();
                    vista.btnGuardar.setEnabled(true);
                    vista.btnBorrar.setEnabled(true);
                }
                else{
                    JOptionPane.showMessageDialog(vista, "No se encontro el Usuario: " + vista.txtUsuario.getText(),"Cliente",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
