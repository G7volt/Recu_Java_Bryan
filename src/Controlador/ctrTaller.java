
package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import Modelo.mdlTaller;
import Vista.jfrTaller; 

public class ctrTaller implements MouseListener, KeyListener{
    
    private mdlTaller Modelo;
    private jfrTaller Vista;
    
    public ctrTaller(mdlTaller Modelo, jfrTaller Vista) {
        this.Modelo = Modelo;
        this.Vista = Vista;

        //Siempre poner todos los botones que vamos a detectar
        Vista.btnAgregar.addMouseListener(this);
        Vista.btnEliminar.addMouseListener(this);
        Vista.btnActualizar.addMouseListener(this);
        Vista.dtvVehiculos.addMouseListener(this);

        Modelo.Mostrar(Vista.dtvVehiculos);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnAgregar) {
            if (Vista.txtNombre_Dueño.getText().isEmpty() || Vista.txtTelefono_Dueño.getText().isEmpty() || Vista.txtModelo_Vehiculo.getText().isEmpty()|| Vista.txtDescripcion_Problema.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    Modelo.setNombre(Vista.txtNombre_Dueño.getText());
                    Modelo.setTelefono(Integer.parseInt(Vista.txtTelefono_Dueño.getText()));
                    Modelo.setModelo(Vista.txtModelo_Vehiculo.getText());
                    Modelo.setDescripcion(Vista.txtDescripcion_Problema.getText());
                    //Ejecutar el metodo 
                    Modelo.Agregar();
                    Modelo.Mostrar(Vista.dtvVehiculos);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == Vista.btnEliminar) {
            if (Vista.txtNombre_Dueño.getText().isEmpty() || Vista.txtTelefono_Dueño.getText().isEmpty() || Vista.txtModelo_Vehiculo.getText().isEmpty()|| Vista.txtDescripcion_Problema.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Modelo.Eliminar(Vista.dtvVehiculos);
                Modelo.Mostrar(Vista.dtvVehiculos);
            }
        }

        if (e.getSource() == Vista.btnActualizar) {
            if (Vista.txtNombre_Dueño.getText().isEmpty() || Vista.txtTelefono_Dueño.getText().isEmpty() || Vista.txtModelo_Vehiculo.getText().isEmpty()|| Vista.txtDescripcion_Problema.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    Modelo.setNombre(Vista.txtNombre_Dueño.getText());
                    Modelo.setTelefono(Integer.parseInt(Vista.txtTelefono_Dueño.getText()));
                    Modelo.setModelo(Vista.txtModelo_Vehiculo.getText());
                    Modelo.setModelo(Vista.txtDescripcion_Problema.getText());

                    //Ejecutar el método    
                    Modelo.Actualizar(Vista.dtvVehiculos);
                    Modelo.Mostrar(Vista.dtvVehiculos);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == Vista.dtvVehiculos) {
            Modelo.cargarDatosTabla(Vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
