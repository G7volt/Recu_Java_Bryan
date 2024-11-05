
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import Vista.jfrTaller;

public class mdlTaller {
    private String Nombre;
    private int Telefono;
    private String Modelo;
    private String Descripcion;
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    public void Agregar(){
        
        Connection conexion = ClaseConexion.getConexion();
        
        try{
            
          String sql = "Insert into Vehiculo(UUID_Vehiculo, Nombre_Dueño, Telefono_Dueño, Modelo_Vehiculo, Problema_Vehiculo) VALUES (?, ?, ?, ?, ?)"; 
          
          PreparedStatement pstmt = conexion.prepareStatement(sql);
          
          pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getNombre());
            pstmt.setInt(3, getTelefono());
            pstmt.setString(4, getModelo());
            pstmt.setString(5, getDescripcion());
            
            pstmt.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("este es el error en el metodo agregar: metodo agregar" + ex);
        }
    }
    
    public void Mostrar(JTable tabla){
        
        Connection conexion = ClaseConexion.getConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Vehiculo", "Nombre_Dueño", "Telefono_Dueño", "Modelo_Vehiculo", "Problema_Vehiculo"});
         try{
         String query = "Select * From Vehiculo";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                modelo.addRow(new Object[]{rs.getString("UUID"),
                    rs.getString("Nombre_Dueño"),
                    rs.getInt("Telefono_Dueño"),
                    rs.getString("Modelo_Vehiculo"),
                    rs.getString("Problema_vehiculo")});
            }
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        }catch(Exception e){
            System.out.println("Este es el error del modelo, metodo mostrar" + e);
        
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from Vehiculo where UUID_Vehiculo = ?";
            PreparedStatement deleteVehiculo = conexion.prepareStatement(sql);
            deleteVehiculo.setString(1, miId);
            deleteVehiculo.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update Vehiculo set Nombre_Dueño= ?, Telefono_Dueño = ?, Modelo_Vehiculo = ?, Problema_Vehiculo = ? where UUID_Vehiculo = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getTelefono());
                updateUser.setString(3, getModelo());
                updateUser.setString(4, getDescripcion());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void cargarDatosTabla(jfrTaller Vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = Vista.dtvVehiculos.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = Vista.dtvVehiculos.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = Vista.dtvVehiculos.getValueAt(filaSeleccionada, 1).toString();
            String TelefonoDeTb = Vista.dtvVehiculos.getValueAt(filaSeleccionada, 2).toString();
            String ModeloDeTB = Vista.dtvVehiculos.getValueAt(filaSeleccionada, 3).toString();
            String ProblemaDeTB = Vista.dtvVehiculos.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            Vista.txtNombre_Dueño.setText(NombreDeTB);
            Vista.txtTelefono_Dueño.setText(TelefonoDeTb);
            Vista.txtModelo_Vehiculo.setText(ModeloDeTB);
            Vista.txtDescripcion_Problema.setText(ModeloDeTB);
        }
    }
    
}


