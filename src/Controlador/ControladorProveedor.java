package Controlador;

import Modelo.Proveedor;
import Modelo.ProveedorDB;
import Vista.jifVistaProveedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import javax.swing.JOptionPane;

public class ControladorProveedor implements ActionListener {

    private Proveedor proveedor;
    private jifVistaProveedor vista;
    private ProveedorDB db = new ProveedorDB();

    public ControladorProveedor(Proveedor proveedor, jifVistaProveedor vista) {
        this.proveedor = proveedor;
        this.vista = vista;

        vista.btnBorrar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnCancelar.addActionListener(this);
        vista.btnCerrar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnNuevo.addActionListener(this);

        vista.tblProveedor.setModel(db.mostrarTabla());
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public jifVistaProveedor getVista() {
        return vista;
    }

    public void setVista(jifVistaProveedor vista) {
        this.vista = vista;
    }
    
    private void guardarDatos(){
        proveedor.setUsuario(vista.txtUsuario.getText());
        proveedor.setNombre(vista.txtNombres.getText());
        proveedor.setApellido(vista.txtApellido.getText());
        proveedor.setTelefono(vista.txtTelefono.getText());
        proveedor.setRFC(vista.txtRfc.getText());
        proveedor.setCorreo(vista.txtCorreo.getText());
        
        proveedor.setFecha(fechaMysql());

        String comunidad = "";
        switch (vista.cmbComunidad.getSelectedIndex()) {
            case 0: comunidad = "Cofradía"; break;
            case 1: comunidad = "El Habal"; break;
            case 2: comunidad = "El Pozole"; break;
            case 3: comunidad = "El Quelite"; break;
            case 4: comunidad = "El Recodo"; break;
            case 5: comunidad = "El Roble"; break;  
            case 6: comunidad = "Mármol de Salcido"; break;  
            case 7: comunidad = "Palmillas"; break;  
            case 8: comunidad = "Potrerillos"; break;  
        }
        proveedor.setLocalizacion(comunidad);
    }
    
    private void mostrarDatos(){
        vista.txtUsuario.setText(proveedor.getUsuario());
        vista.txtNombres.setText(proveedor.getNombre());
        vista.txtApellido.setText(proveedor.getApellido());
        vista.txtTelefono.setText(proveedor.getTelefono());
        vista.txtCorreo.setText(proveedor.getCorreo());
        vista.txtRfc.setText(proveedor.getRFC());

        int comunidad = -1;
        switch (proveedor.getLocalizacion()) {
            case "Cofradía": comunidad = 0; break;
            case "El Habal": comunidad = 1; break;
            case "El Pozole": comunidad = 2; break;
            case "El Quelite": comunidad = 3; break;
            case "El Recodo": comunidad = 4; break;   
            case "El Roble": comunidad = 5; break; 
            case "Mármol de Salcido": comunidad = 6; break; 
            case "Palmillas": comunidad = 7; break; 
            case "Potrerillos": comunidad = 8; break; 
        }
        vista.cmbComunidad.setSelectedIndex(comunidad);

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(proveedor.getFecha());
            vista.jdcFecha.setDate(fecha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                "Surgió un error al convertir la fecha",
                "Proveedor", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean cerrarVista(){
        int opcion = JOptionPane.showConfirmDialog(vista, 
            "¿Deseas cerrar la vista?", 
            "Proveedor", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        return opcion == JOptionPane.YES_OPTION;
    }
    
    private boolean validar(){
        return !(vista.txtUsuario.getText().equals("") ||
                 vista.txtNombres.getText().equals("") ||
                 vista.txtApellido.getText().equals("") ||
                 vista.txtCorreo.getText().equals("")||
                 vista.txtRfc.getText().equals("")||
                 vista.txtTelefono.getText().equals(""));
    }

    private void habilitar() {
        vista.txtRfc.setEnabled(true);
        vista.txtCorreo.setEnabled(true);
        vista.txtTelefono.setEnabled(true);
        vista.txtApellido.setEnabled(true);
        vista.txtNombres.setEnabled(true);
        vista.btnGuardar.setEnabled(true);
        vista.btnCancelar.setEnabled(true);
        vista.cmbComunidad.setEnabled(true);
        vista.btnBorrar.setEnabled(true);
    }

    private void deshabilitar() {
        vista.txtRfc.setEnabled(false);
        vista.txtApellido.setEnabled(false);
        vista.txtCorreo.setEnabled(false);
        vista.txtTelefono.setEnabled(false);
        vista.txtNombres.setEnabled(false);
        vista.btnGuardar.setEnabled(false);
        vista.cmbComunidad.setEnabled(false);
        vista.btnBorrar.setEnabled(false);
    }
    
    private void limpiar(){
        vista.txtUsuario.setText("");
        vista.txtNombres.setText("");
        vista.txtApellido.setText("");
        vista.txtCorreo.setText("");
        vista.txtTelefono.setText("");
        vista.txtRfc.setText("");
        vista.cmbComunidad.setSelectedIndex(0);
 
        vista.jdcFecha.setDate(new java.util.Date());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnNuevo) {
            this.habilitar();
            vista.txtUsuario.requestFocus();
        }

        if (e.getSource() == vista.btnGuardar) {
            Proveedor pro = null;

            if (validar()) {
                guardarDatos();
                pro = (Proveedor) db.consultar(proveedor);

                if (pro == null) {
                    if (db.registrar(proveedor) > 0) {
                        JOptionPane.showMessageDialog(vista, "Se registró el Proveedor con Usuario " + proveedor.getUsuario() + " con éxito", 
                            "Proveedor", JOptionPane.INFORMATION_MESSAGE);
                        vista.tblProveedor.setModel(db.mostrarTabla());
                    } else {
                        JOptionPane.showMessageDialog(vista, "No fue posible guardar el Proveedor",
                            "Proveedor", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    int respuesta = JOptionPane.showConfirmDialog(vista, "El Proveedor " + vista.txtUsuario.getText() + " ya existe.\n¿Deseas actualizar?",
                        "Proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        guardarDatos();
                        if (db.actualizar(proveedor) > 0) {
                            JOptionPane.showMessageDialog(vista, "Se actualizó el Proveedor con Usuario " + proveedor.getUsuario(),
                                "Proveedor", JOptionPane.INFORMATION_MESSAGE);
                            vista.tblProveedor.setModel(db.mostrarTabla());
                        } else {
                            JOptionPane.showMessageDialog(vista, "No fue posible actualizar el Proveedor",
                                "Proveedor", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                limpiar();
                deshabilitar();
            } else {
                JOptionPane.showMessageDialog(vista, "Faltó capturar información",
                    "Cliente", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vista.btnBuscar) {
            if (vista.txtUsuario.getText().equals("")) {
                JOptionPane.showMessageDialog(vista, 
                    "Faltó capturar el usuario",
                    "Proveedor", JOptionPane.ERROR_MESSAGE);
            } else {
                proveedor = new Proveedor();
                proveedor.setUsuario(vista.txtUsuario.getText());
                proveedor = (Proveedor) db.consultar(proveedor);

                if (proveedor != null) {
                    mostrarDatos();
                    vista.btnGuardar.setEnabled(true);
                    vista.btnBorrar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(vista,
                        "No se encontró el proveedor con el usuario: " + vista.txtUsuario.getText(),
                        "Proveedor", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnBorrar) {
            if (JOptionPane.showConfirmDialog(vista, "¿Desea deshabilitar el Usuario " + proveedor.getUsuario() + "?",
                "Proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                db.desactivar(proveedor);
                vista.tblProveedor.setModel(db.mostrarTabla());
                limpiar();
            }
        }

        if (e.getSource() == vista.btnCancelar) {
            limpiar();
            deshabilitar();
        }

        if (e.getSource() == vista.btnCerrar) {
            if (cerrarVista()) vista.dispose();
        }
    }
    
    private String fechaMysql() {
        String fec = null;
        Date fechaSeleccionada = vista.jdcFecha.getDate();
        if (fechaSeleccionada != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fec = formato.format(fechaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(vista, "Es necesario seleccionar una fecha",
                "Cotización", JOptionPane.INFORMATION_MESSAGE);
        }
        return fec;
    }
    
}
