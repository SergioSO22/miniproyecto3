

package Controladores;
/**
 *
 * @author Sergio Sánchez
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Clases.RespaldoDatos;
import Clases.Servicio;
import Vista.GestionServicios;
import Vista.AgregarServicio;

public class GestorAgregarServicio {
    
    AgregarServicio vistaPlantillaServicio;
    RespaldoDatos almacenamiento;
    String opcion;
    String servicio;
    public GestorAgregarServicio(AgregarServicio vistaPlantillaServicio, String opcion, RespaldoDatos almacenamiento, String servicio) {
        this.vistaPlantillaServicio = vistaPlantillaServicio;
        this.opcion = opcion;
        this.servicio = servicio;
        this.almacenamiento = almacenamiento;
        modificarPlantilla();
        this.vistaPlantillaServicio.addBtnRegresarListener(new ManejadoraDeMouse());
        this.vistaPlantillaServicio.addBtnAgregarListener(new ManejadoraDeMouse());
    }
    
    public void modificarPlantilla(){
        
        switch(opcion){
            case "Actualizar" -> {
                plantillaActualizarServicio();
            }
            case "Eliminar" -> {
                plantillaEliminarServicio();
            }
        }
    }
    public void plantillaActualizarServicio(){
        //Modificando título y botones
        vistaPlantillaServicio.getLblTitulo().setText("Actualizar servicio");
        vistaPlantillaServicio.getBtnAgregar().setText("Actualizar servicio");
        
        //Agregando los datos del servicio
        ArrayList<Servicio> servicios = almacenamiento.getServicios();
        for(int i = 0; i<servicios.size(); i++){
            if(servicios.get(i).getNombre().equalsIgnoreCase(servicio)){
                vistaPlantillaServicio.getTxtServicio().setText(servicios.get(i).getNombre());
            } 
        }
    }
    
    public void plantillaEliminarServicio(){
        //Modificando título y botones
        vistaPlantillaServicio.getLblTitulo().setText("Eliminar servicio");
        vistaPlantillaServicio.getBtnAgregar().setText("Eliminar servicio");
        
        //Agregando los datos del servicio
        ArrayList<Servicio> servicios = almacenamiento.getServicios();
        for(int i = 0; i<servicios.size(); i++){
            if(servicios.get(i).getNombre().equals(servicio)){
                vistaPlantillaServicio.getTxtServicio().setText(servicios.get(i).getNombre());
            }
        }
        
        //Desabilitando campos de texto
        vistaPlantillaServicio.getTxtServicio().setEditable(false);
    }
    
    class ManejadoraDeMouse extends MouseAdapter{
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            if (e.getSource() == vistaPlantillaServicio.getBtnAgregar() && "Agregar".equals(opcion)){
                if (e.getButton() == 1){
                    agregarServicio();
                }
            }
            
            if (e.getSource() == vistaPlantillaServicio.getBtnAgregar()&& "Actualizar".equals(opcion)){
                if (e.getButton() == 1){
                    actualizarServicio();
                }
            }
            if (e.getSource() == vistaPlantillaServicio.getBtnAgregar() && "Eliminar".equals(opcion)){
                if (e.getButton() == 1){
                    eliminarServicio();
                }
            }

            if (e.getSource() == vistaPlantillaServicio.getBtnRegresar()){
                if (e.getButton() == 1){
                    irGestionServicioGUI();  
                }
            }
        }
    }
    
    public void agregarServicio() {
        if(validarCamposVacios()){
            JOptionPane.showMessageDialog(null, "Llene todos los campos requeridos antes de continuar.", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Obteniendo los datos de la ventana
        String nombre = vistaPlantillaServicio.getTxtServicio().getText();

        //Creando el servicio
        Servicio servicio = new Servicio(nombre);
        try {
            //Agregando el servicio
            if (almacenamiento.anadirServicio(servicio)){
                JOptionPane.showMessageDialog(null, "Servicio agregado con éxito", "Resultado de agregar", JOptionPane.INFORMATION_MESSAGE);
                irGestionServicioGUI();
            } else {
                JOptionPane.showMessageDialog(null, "Este servicio ya existe", "Resultado de agregar", JOptionPane.ERROR_MESSAGE);
            }
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al agregar: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void actualizarServicio() {
        
        if(validarCamposVacios()) {
            JOptionPane.showMessageDialog(null, "Llene todos los campos requeridos antes de continuar.", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Obteniendo datos de la ventana
        String nombreNuevo = vistaPlantillaServicio.getTxtServicio().getText();
        
        //Creando el servicio con el nuevo nombre
        Servicio miServicio = new Servicio(nombreNuevo);
        
        //Convirtiendo los servicios actuales a String[]
        ArrayList<Servicio> servicios = almacenamiento.getServicios();

        for (int i= 0; i<servicios.size(); i++){
            String nombreServicio = "";
            nombreServicio += servicios.get(i).getNombre();
            if(nombreNuevo.equalsIgnoreCase(nombreServicio)) {
                JOptionPane.showMessageDialog(null, "Ya existe un servicio con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        for (int i= 0; i<servicios.size(); i++) {
            if (servicios.get(i).getNombre().equalsIgnoreCase(servicio)) {
                try {
                    //Actualizando el servicio
                    almacenamiento.modificarServicio(i, miServicio);
                    JOptionPane.showMessageDialog(null, "Servicio actualizado con éxito", "Resultado de actualizar", JOptionPane.INFORMATION_MESSAGE);
                    irGestionServicioGUI();
                } catch(IOException e){
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    public void eliminarServicio() {
        ArrayList<Servicio> servicios = almacenamiento.getServicios();
        //Se recorre el ArrayList de servicios en búsqueda del servicio con el nombre anterior para así actualizarlo
        for(int i = 0; i<servicios.size(); i++){
            if(servicios.get(i).getNombre().equals(servicio)){
                try {
                    //Eliminando el servicio
                    almacenamiento.eliminarServicio(i);
                    JOptionPane.showMessageDialog(null, "Servicio eliminado con éxito", "Resultado de agregar", JOptionPane.INFORMATION_MESSAGE);
                    irGestionServicioGUI();

                } catch(IOException e){
                    JOptionPane.showMessageDialog(null, "Error al eliminar: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
    }
    public void irGestionServicioGUI() {
        
        GestionServicios vistaGestionServicio = new GestionServicios("Gestión de servicios", almacenamiento);
        vistaPlantillaServicio.dispose();
    }
    public boolean validarCamposVacios(){
        boolean error = false;
        if(vistaPlantillaServicio.getTxtServicio().getText().isBlank())
            error = true;
        return error;
    }
}
