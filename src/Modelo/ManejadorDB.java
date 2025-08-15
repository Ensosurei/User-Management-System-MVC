/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ensos
 */
import java.sql.*;

public abstract class ManejadorDB {
    protected Connection conexion;
    protected PreparedStatement pstm;
    protected ResultSet registros;
    protected String sqlConsulta;
    
    //CONSTANTES
    final static String DATABASE="proyectofinal";
    final static String IP_SERVER="localhost";
    final static String USER="root";
    final static String PASSWORD="1234";
    final static String URL ="jdbc:mysql://"+ IP_SERVER + ":3306/" + DATABASE;
    
    //METODOS DE PERSISTENCIA
    public abstract int crearTabla();
    public abstract int registrar(Object obj);
    public abstract int actualizar(Object obj);
    public abstract int activar(Object obj);
    public abstract int desactivar(Object obj);
    
    //METODOS DE PROYECCION
    public abstract Object consultar(Object obj);
    public abstract ResultSet todos(int status);
    public abstract int contarRegistros(int status);
  
    public boolean abrir(){
        boolean exito= false;
        
        try{
            conexion=DriverManager.getConnection(URL, USER, PASSWORD);
            if(conexion!=null)exito=true;
        }catch(SQLException e){
            System.err.println("Surgio un error al abrir la base de datos "+e.getMessage());
        }
        return exito;
    }
    
    public void cerrar(){
        try{
            if(!conexion.isClosed()) conexion.close();
        }catch(SQLException e){
            System.err.println("No se pudo cerrar la base de datos  "+e.getMessage());
        }
        
        
    }
}
