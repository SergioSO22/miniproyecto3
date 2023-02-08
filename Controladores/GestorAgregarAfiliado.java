
package Controladores;
/**
 *
 * @author Sergio Sánchez
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Clases.Afiliados;
import Clases.RespaldoDatos;
import Vista.GestionServicios;
import Vista.ListaAfiliados;
import Vista.AgregarAfiliado;

public class GestorAgregarAfiliado {
    

    private final AgregarAfiliado vistaPlantillaAfiliado;
    private final RespaldoDatos almacenamiento;
    private final String opcion;
    private final long cedula;

    
    public GestorAgregarAfiliado(AgregarAfiliado vistaPlantillaAfiliado, String opcion, RespaldoDatos almacenamiento, long cedula) {
        this.vistaPlantillaAfiliado = vistaPlantillaAfiliado;
        this.opcion = opcion;
        this.almacenamiento = almacenamiento;
        this.cedula = cedula;
        modificarPlantilla();
        verificarNumero(vistaPlantillaAfiliado.getTxtIdentificacion());
        verificarNumero(vistaPlantillaAfiliado.getTxtEdad());
        verificarNumero(vistaPlantillaAfiliado.getTxtCelular());
        this.vistaPlantillaAfiliado.addBtnAgregarListener(new ManejadoraDeMouse());
        this.vistaPlantillaAfiliado.addBtnRegresarListener(new ManejadoraDeMouse());

    }
    public void modificarPlantilla(){
        switch(opcion){
            case "Actualizar" -> {
                
                plantillaActualizarAfiliado();
            }
            case "Eliminar" -> {
                plantillaEliminarAfiliado();
                
            }
            case "Consultar" -> {
                plantillaConsultarAfiliado();
                
            }
        }
    }

    public void plantillaActualizarAfiliado(){
        //Modificando título y botones
        vistaPlantillaAfiliado.getLblTitulo().setText("Actualizar afiliado");
        vistaPlantillaAfiliado.getBtnAgregar().setText("Actualizar afiliado");
        
        //Ingresando los datos del afiliado a eliminar
        Afiliados miAfiliado = almacenamiento.getAfiliados().get(cedula);
        vistaPlantillaAfiliado.getTxtIdentificacion().setText(Long.toString(miAfiliado.getIdentificacion()));
        vistaPlantillaAfiliado.getTxtEdad().setText(Integer.toString(miAfiliado.getEdad()));
        vistaPlantillaAfiliado.getTxtCelular().setText(Long.toString(miAfiliado.getCelular()));
        vistaPlantillaAfiliado.getTxtNombre().setText(miAfiliado.getNombre());
        vistaPlantillaAfiliado.getTxtDireccion().setText(miAfiliado.getDireccion());
        vistaPlantillaAfiliado.getTxtCorreo().setText(miAfiliado.getCorreo());
        vistaPlantillaAfiliado.getComboSexo().setSelectedItem(miAfiliado.getSexo());
    }
    
    public void plantillaEliminarAfiliado(){
        
        //Modificando título y botones
        vistaPlantillaAfiliado.getLblTitulo().setText("Eliminar afiliado");
        vistaPlantillaAfiliado.getBtnAgregar().setText("Eliminar afiliado");
        
        //Ingresando los datos del afiliado a eliminar
        Afiliados miAfiliado = almacenamiento.getAfiliados().get(cedula);
        vistaPlantillaAfiliado.getTxtIdentificacion().setText(Long.toString(miAfiliado.getIdentificacion()));
        vistaPlantillaAfiliado.getTxtEdad().setText(Integer.toString(miAfiliado.getEdad()));
        vistaPlantillaAfiliado.getTxtCelular().setText(Long.toString(miAfiliado.getCelular()));
        vistaPlantillaAfiliado.getTxtNombre().setText(miAfiliado.getNombre());
        vistaPlantillaAfiliado.getTxtDireccion().setText(miAfiliado.getDireccion());
        vistaPlantillaAfiliado.getTxtCorreo().setText(miAfiliado.getCorreo());
        vistaPlantillaAfiliado.getComboSexo().setSelectedItem(miAfiliado.getSexo());
        
        //Desabilitando campos de texto
        vistaPlantillaAfiliado.getTxtIdentificacion().setEditable(false);
        vistaPlantillaAfiliado.getTxtEdad().setEditable(false);
        vistaPlantillaAfiliado.getTxtNombre().setEditable(false);
        vistaPlantillaAfiliado.getTxtDireccion().setEditable(false);
        vistaPlantillaAfiliado.getTxtCorreo().setEditable(false);
        vistaPlantillaAfiliado.getTxtCelular().setEditable(false);
        vistaPlantillaAfiliado.getComboSexo().setEnabled(false);
        
    }
    
    public void plantillaConsultarAfiliado(){
        
        //Modificando título y botones
        vistaPlantillaAfiliado.getLblTitulo().setText("Consultar afiliado");
        vistaPlantillaAfiliado.getBtnAgregar().setVisible(false);
        vistaPlantillaAfiliado.getBtnRegresar().setVisible(true);
        
        //Ingresando los datos del afiliado a eliminar
        Afiliados miAfiliado = almacenamiento.getAfiliados().get(cedula);
        vistaPlantillaAfiliado.getTxtIdentificacion().setText(Long.toString(miAfiliado.getIdentificacion()));
        vistaPlantillaAfiliado.getTxtEdad().setText(Integer.toString(miAfiliado.getEdad()));
        vistaPlantillaAfiliado.getTxtCelular().setText(Long.toString(miAfiliado.getCelular()));
        vistaPlantillaAfiliado.getTxtNombre().setText(miAfiliado.getNombre());
        vistaPlantillaAfiliado.getTxtDireccion().setText(miAfiliado.getDireccion());
        vistaPlantillaAfiliado.getTxtCorreo().setText(miAfiliado.getCorreo());
        vistaPlantillaAfiliado.getComboSexo().setSelectedItem(miAfiliado.getSexo());
        
        //Desabilitando campos de texto
        vistaPlantillaAfiliado.getTxtIdentificacion().setEditable(false);
        vistaPlantillaAfiliado.getTxtEdad().setEditable(false);
        vistaPlantillaAfiliado.getTxtNombre().setEditable(false);
        vistaPlantillaAfiliado.getTxtDireccion().setEditable(false);
        vistaPlantillaAfiliado.getTxtCorreo().setEditable(false);
        vistaPlantillaAfiliado.getTxtCelular().setEditable(false);
        vistaPlantillaAfiliado.getComboSexo().setEnabled(false);
        
        //Quitando boton regresar
        
    }
    
    class ManejadoraDeMouse extends MouseAdapter{
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            if (e.getSource() == vistaPlantillaAfiliado.getBtnAgregar() && "Agregar".equals(opcion)){
                if (e.getButton() == 1){
                    agregarAfiliado();
                }
            }

            if (e.getSource() == vistaPlantillaAfiliado.getBtnAgregar() && "Actualizar".equals(opcion)){
                if (e.getButton() == 1){
                    actualizarAfiliado();
                }
            }
            
            if (e.getSource() == vistaPlantillaAfiliado.getBtnAgregar() && "Eliminar".equals(opcion)){
                if (e.getButton() == 1){
                    eliminarAfiliado();
                }
            }
            
            if (e.getSource() == vistaPlantillaAfiliado.getBtnRegresar() && !"Consultar".equals(opcion)){
                if (e.getButton() == 1){
                    irGestionServicioGUI();  
                }
            } 
            if (e.getSource() == vistaPlantillaAfiliado.getBtnRegresar() && "Consultar".equals(opcion)) {
                if (e.getButton() == 1){
                    irListarAfiliado();
                }
            }
        }
    }
    
    public void agregarAfiliado() {
        if(!validarCamposVacios()){
            
            //Obteniendo los datos de la ventana
            long cedulaNueva = Long.parseLong(vistaPlantillaAfiliado.getTxtIdentificacion().getText());
            String nombre = vistaPlantillaAfiliado.getTxtNombre().getText();
            int edad = Integer.parseInt(vistaPlantillaAfiliado.getTxtEdad().getText());
            String direccion = vistaPlantillaAfiliado.getTxtDireccion().getText();
            String correo = vistaPlantillaAfiliado.getTxtCorreo().getText();
            long telefono = Long.parseLong(vistaPlantillaAfiliado.getTxtCelular().getText());
            String sexo = (String)vistaPlantillaAfiliado.getComboSexo().getSelectedItem();
            
            //Estableciendo los datos obtenidos al modelo
            Afiliados afiliado = new Afiliados(nombre, sexo, direccion, correo, cedulaNueva, edad, telefono);
            try {
                //Agregando el afiliado
                if (almacenamiento.anadirAfiliado(afiliado)){
                    JOptionPane.showMessageDialog(null, "Afiliado agregado con éxito", "Resultado de agregar", JOptionPane.INFORMATION_MESSAGE);
                    irGestionServicioGUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Ya existe una persona con ese número de cédula", "Resultado de agregar", JOptionPane.ERROR_MESSAGE);
                }
            } catch(IOException e){
                JOptionPane.showMessageDialog(null, "Error al agregar: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Llene todos los campos requeridos antes de continuar.", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarAfiliado() {
        if(!validarCamposVacios()){
            
            //Obteniendo los datos
            long cedulaNueva = Long.parseLong(vistaPlantillaAfiliado.getTxtIdentificacion().getText());
            String nombre = vistaPlantillaAfiliado.getTxtNombre().getText();
            int edad = Integer.parseInt(vistaPlantillaAfiliado.getTxtEdad().getText());
            String direccion = vistaPlantillaAfiliado.getTxtDireccion().getText();
            String correo = vistaPlantillaAfiliado.getTxtCorreo().getText();
            long telefono = Long.parseLong(vistaPlantillaAfiliado.getTxtCelular().getText());
            String sexo = (String)vistaPlantillaAfiliado.getComboSexo().getSelectedItem();
            
            //Verifica si al actualizar el afiliado se ingresa una cédula que ya existía
            if(!almacenamiento.getAfiliados().containsKey(cedulaNueva) || almacenamiento.getAfiliados().get(cedula).getIdentificacion() == (cedulaNueva)){
                //Creando el afiliado
                Afiliados afiliado = new Afiliados(nombre, sexo, direccion, correo, cedulaNueva, edad, telefono);
                try {
                    //Actualizando los datos de afiliado
                    almacenamiento.modificarAfiliado(cedula, afiliado);
                    JOptionPane.showMessageDialog(null, "Afiliado actualizado con éxito", "Resultado de actualizar", JOptionPane.INFORMATION_MESSAGE);
                    irGestionServicioGUI();
                } catch(IOException e){
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(null, "Ya existe un afiliado con esa cédula", "Error", JOptionPane.ERROR_MESSAGE);
            }
        
        } else{
            JOptionPane.showMessageDialog(null, "Llene todos los campos requeridos antes de continuar.", "Datos incompletos", JOptionPane.ERROR_MESSAGE);            
        }
    }

    public void eliminarAfiliado(){
        //Eliminando el afiliado
        try{
            almacenamiento.eliminarAfiliado(cedula);
            JOptionPane.showMessageDialog(null, "Afiliado eliminado con éxito", "Resultado de eliminar", JOptionPane.INFORMATION_MESSAGE);
            irGestionServicioGUI();
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void irGestionServicioGUI() {
        
        //Creación de vistas
        GestionServicios vistaGestionServicio = new GestionServicios("Gestión de servicios", almacenamiento);
        vistaPlantillaAfiliado.dispose();
    }
    
    public void irListarAfiliado() {
        
        //Creación de vistas
        ListaAfiliados ventanaListaAfiliado = new ListaAfiliados("Lista de afiliados",almacenamiento);
        vistaPlantillaAfiliado.dispose();
    }
    
    public void verificarNumero(JTextField a){
        a.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if (!Character.isDigit(c) | Character.isSpaceChar(c)){
                    e.consume();
                }
            }
        });
    }
    public boolean validarCamposVacios(){
        boolean error = false;
        if(vistaPlantillaAfiliado.getTxtIdentificacion().getText().isBlank())
            error = true;
        if(vistaPlantillaAfiliado.getTxtNombre().getText().isBlank())
            error = true;
        if(vistaPlantillaAfiliado.getTxtEdad().getText().isBlank())
            error = true;
        if(vistaPlantillaAfiliado.getTxtDireccion().getText().isBlank())
            error = true;
        if(vistaPlantillaAfiliado.getTxtCelular().getText().isBlank())
            error = true;
        if(vistaPlantillaAfiliado.getTxtCorreo().getText().isBlank())
            error = true;
        String sexo=(String)vistaPlantillaAfiliado.getComboSexo().getSelectedItem();
        if(!"Masculino".equals(sexo) && !"Femenino".equals(sexo))
            error = true;
        return error;
    }
}

