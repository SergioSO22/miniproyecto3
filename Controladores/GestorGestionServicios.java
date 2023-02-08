

package Controladores;
/**
 *
 * @author Sergio Sánchez
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import Clases.RespaldoDatos;
import Clases.Servicio;
import Vista.GestionServicios;
import Vista.ListaAfiliados;
import Vista.ListaConsultorios;
import Vista.ListaMedicos;
import Vista.ListaServicios;
import Vista.AgregarAfiliado;
import Vista.AgregarConsultorio;
import Vista.AgregarMedico;
import Vista.AgregarServicio;
import Vista.VentanaPrincipal;

public class GestorGestionServicios {
    
    private final GestionServicios vistaGestionServicio;
    private final RespaldoDatos respaldoDatos;
    private final String[] opciones = {
                                    "Agregar",
                                    "Actualizar",
                                    "Listar",
                                    "Eliminar"
                                   };
    
    public GestorGestionServicios(GestionServicios vistaGestionServicio, RespaldoDatos respaldoDatos) {
        
        //Vista
        this.vistaGestionServicio = vistaGestionServicio;
        
        //Añadiendo Listeners
        this.vistaGestionServicio.addBtnAfiliadosListener(new ManejadoraDeMouse());
        this.vistaGestionServicio.addBtnMedicosListener(new ManejadoraDeMouse());
        this.vistaGestionServicio.addBtnConsultoriosListener(new ManejadoraDeMouse());
        this.vistaGestionServicio.addBtnServiciosListener(new ManejadoraDeMouse());
        this.vistaGestionServicio.addBtnRegresarListener(new ManejadoraDeMouse());
        this.respaldoDatos = respaldoDatos;
    }
    
    class ManejadoraDeMouse extends MouseAdapter{
        
        @Override
        public void mouseClicked(MouseEvent e){
            
          if (e.getSource() == vistaGestionServicio.getBtnAfiliados()){
                if (e.getButton() == 1){
                    opcionesAfiliado();
                }
          }
          
          if (e.getSource() == vistaGestionServicio.getBtnMedicos()){
                if (e.getButton() == 1){
                    opcionesMedico();
                }
          }
          
          if (e.getSource() == vistaGestionServicio.getBtnConsultorios()){
                if (e.getButton() == 1){
                    opcionesConsultorio();
                }
          }
          
          if (e.getSource() == vistaGestionServicio.getBtnServicios()){
                if (e.getButton() == 1){
                    opcionesServicio();
                }
          }
          
          if (e.getSource() == vistaGestionServicio.getBtnRegresar()){
                if (e.getButton() == 1){
                    irPpal();
                }
          }
          
        }

    }
    
    public void irPpal() {
        VentanaPrincipal ventanaPpal = new VentanaPrincipal("Servicio de salud - Universidad del Valle", respaldoDatos);
        vistaGestionServicio.dispose();
    }
    
    //Opciones a realizar con un afiliado
    public void opcionesAfiliado(){
        try {
            String resp = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                    "<html><p style = \" font:12px; \">¿Qué desea hacer?</p></html>", 
                    "Opciones afiliados", JOptionPane.DEFAULT_OPTION, 
                    UIManager.getIcon("OptionPane.questionIcon"), opciones, opciones[0]);

            switch(resp){
                case "Agregar" -> {
                    irAgregarAfiliado();
                }
                case "Actualizar" -> {
                    try{
                        String cedulaABuscar = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                                "<html><p style = \" font:12px; \">Ingrese la cédula del afiliado "
                                + "a actualizar</p></html>", "Actualizar afiliado", 
                                JOptionPane.DEFAULT_OPTION);
                        long cedula;
                        if (cedulaABuscar.isBlank()){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">Por favor ingrese una "
                                    + "cédula</p></html>", "Error", JOptionPane.OK_OPTION, 
                                    UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        }
                        try{
                            cedula = Long.parseLong(cedulaABuscar);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Por favor ingrese una cédula "
                                    + "válida", "Error", JOptionPane.ERROR_MESSAGE);
                            return;  
                        }
                        if(!respaldoDatos.getAfiliados().containsKey(cedula)){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">No se encontró ningún "
                                    + "afiliado registrado con esa cédula</p></html>", 
                                    "Afiliado no encontrado", JOptionPane.OK_OPTION, 
                                    UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        }
                        irActualizarAfiliado(cedula);
                    } catch (NullPointerException np) {
                        
                    }
                }
                case "Listar" -> {
                    irListarAfiliado();
                }
                case "Eliminar" -> {
                    try{
                        String cedulaABuscar = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                                "<html><p style = \" font:12px; \">Ingrese la cédula del afiliado "
                                        + "a actualizar</p></html>", "Actualizar afiliado", 
                                        JOptionPane.DEFAULT_OPTION);
                        long cedula;
                        if (cedulaABuscar.isBlank()){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">Por favor ingrese una "
                                            + "cédula</p></html>", "Error", JOptionPane.OK_OPTION, 
                                            UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        }
                        try{
                            cedula = Long.parseLong(cedulaABuscar);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Por favor ingrese una cédula "
                                    + "válida", "Error", JOptionPane.ERROR_MESSAGE);
                            return;  
                        }
                        if(!respaldoDatos.getAfiliados().containsKey(cedula)){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">No se encontró ningún afiliado"
                                    + " registrado con esa cédula</p></html>", 
                                    "Afiliado no encontrado", JOptionPane.OK_OPTION, 
                                    UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        }
                        irEliminarAfiliado(cedula);
                    } catch (NullPointerException np) {
                        
                    }
                }
            }
        } catch(NullPointerException np){

        }
    }
    
    public void irAgregarAfiliado(){
        AgregarAfiliado ventanaAgregarAfiliado = new AgregarAfiliado("Agregar afiliado", 
                "Agregar", respaldoDatos, 0);
        vistaGestionServicio.dispose();
    }
    
    public void irActualizarAfiliado(long cedula) {
        AgregarAfiliado ventanaAgregarAfiliado = new AgregarAfiliado("Actualizar afiliado", 
                "Actualizar", respaldoDatos, cedula);
        vistaGestionServicio.dispose();
    }
    
    public void irListarAfiliado(){
        ListaAfiliados ventanaListaAfiliado = new ListaAfiliados("Lista de afiliados", 
                respaldoDatos);
        vistaGestionServicio.dispose();
    }
    
    public void irEliminarAfiliado(long cedula) {
        AgregarAfiliado ventanaEliminarAfiliado = new AgregarAfiliado("Eliminar afiliado", 
                "Eliminar", respaldoDatos, cedula);
        vistaGestionServicio.dispose();
    }
    
    //Opciones a realizar con un médico
    public void opcionesMedico(){
        try {
            String resp = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                    "<html><p style = \" font:12px; \">¿Qué desea hacer?</p></html>", 
                    "Opciones médicos", JOptionPane.DEFAULT_OPTION, 
                    UIManager.getIcon("OptionPane.questionIcon"), opciones, opciones[0]);

            switch(resp){
                case "Agregar" -> {
                    if(respaldoDatos.getServicios().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Antes de agregar un médico, "
                                + "agregue un servicio.", "Datos incompletos", 
                                JOptionPane.ERROR_MESSAGE);                    
                        return;
                    } 
                    irAgregarMedico();
                }
                case "Actualizar" -> {
                    try{
                        String cedulaABuscar = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                                "<html><p style = \" font:12px; \">Ingrese la cédula del médico a "
                                        + "actualizar</p></html>", "Actualizar médico", 
                                        JOptionPane.DEFAULT_OPTION);
                        long cedula;
                        if (cedulaABuscar.isBlank()){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">Por favor ingrese una "
                                            + "cédula</p></html>", "Error", JOptionPane.OK_OPTION,
                                            UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        }
                        try{
                            cedula = Long.parseLong(cedulaABuscar);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Por favor ingrese una cédula "
                                    + "válida", "Error", JOptionPane.ERROR_MESSAGE);
                            return;  
                        }
                        if(!respaldoDatos.getMedicos().containsKey(cedula)){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">No se encontró ningún médico"
                                            + " registrado con esa cédula</p></html>", 
                                    "Médico no encontrado", JOptionPane.OK_OPTION, 
                                    UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        }
                        irActualizarMedico(cedula);
                    } catch (NullPointerException np) {
                        
                    }
                }
                case "Listar" -> {
                    irListarMedico();
                }
                case "Eliminar" -> {
                    try{
                        String cedulaABuscar = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                                "<html><p style = \" font:12px; \">Ingrese la cédula del médico a "
                                        + "eliminar</p></html>", "Eliminar médico", 
                                        JOptionPane.DEFAULT_OPTION);
                        long cedula;
                        if (cedulaABuscar.isBlank()){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">Por favor ingrese una "
                                    + "cédula</p></html>", "Error", 
                                    JOptionPane.OK_OPTION, 
                                    UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        }
                        try{
                            cedula = Long.parseLong(cedulaABuscar);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Por favor ingrese una cédula "
                                    + "válida", "Error", JOptionPane.ERROR_MESSAGE);
                            return;  
                        }
                        if(!respaldoDatos.getMedicos().containsKey(cedula)){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">No se encontró ningún "
                                    + "médico registrado con esa cédula</p></html>", 
                                    "Médico no encontrado", JOptionPane.OK_OPTION, 
                                    UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        }
                        irEliminarMedico(cedula);
                    } catch (NullPointerException np) {
                        
                    }
                }
            }
        } catch(NullPointerException np){
            
        }
    }
    public void irAgregarMedico(){
        AgregarMedico ventanaAgregarMedico = new AgregarMedico("Agregar médico", 
                "Agregar", respaldoDatos, 0);
        vistaGestionServicio.dispose();
    }
    public void irActualizarMedico(long cedula) {
        AgregarMedico ventanaAgregarMedico = new AgregarMedico("Actualizar médico", 
                "Actualizar", respaldoDatos, cedula);
        vistaGestionServicio.dispose();
    }
    public void irListarMedico(){
        ListaMedicos ventanaListaMedico = new ListaMedicos("Lista de médicos", respaldoDatos);
        vistaGestionServicio.dispose();
    }
    public void irEliminarMedico(long cedula) {
        AgregarMedico ventanaAgregarMedico = new AgregarMedico("Eliminar médico", 
                "Eliminar", respaldoDatos, cedula);
        vistaGestionServicio.dispose();
    }
    
    //Opciones a realizar con un consultorio
    public void opcionesConsultorio(){
        try {
            String resp = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                    "<html><p style = \" font:12px; \">¿Qué desea hacer?</p></html>", 
                    "Opciones consultorios", JOptionPane.DEFAULT_OPTION, 
                    UIManager.getIcon("OptionPane.questionIcon"), opciones, opciones[0]);
            
            switch(resp){
                case "Agregar" -> {
                    if(respaldoDatos.getServicios().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Antes de agregar un consultorio, "
                            + "agregue un servicio.", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                irAgregarConsultorio();
                }
                
                case "Listar" -> {
                    irListarConsultorio();
                }
                
                case "Actualizar" -> {
                    try {
                        String idABuscar = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                                "<html><p style = \" font:12px; \">Ingrese el identificador del "
                                        + "consultorio a actualizar</p></html>", "Actualizar "
                                        + "consultorio", JOptionPane.DEFAULT_OPTION);
                        if (idABuscar.isBlank()){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">Por favor ingrese un "
                                            + "identificador</p></html>", "Error", 
                                            JOptionPane.OK_OPTION, 
                                            UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        } 
                        if(!respaldoDatos.getConsultorios().containsKey(idABuscar)){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">No se encontró ningún "
                                            + "consultorio registrado con ese identificador</p></html>", 
                                    "Consultorio no encontrado", JOptionPane.OK_OPTION, 
                                    UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        }
                        irActualizarConsultorio(idABuscar);

                    } catch(NullPointerException np){

                    }
                }

                case "Eliminar" -> {
                    try {
                        String idABuscar = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                                "<html><p style = \" font:12px; \">Ingrese el identificador del "
                                + "consultorio a eliminar</p></html>", 
                                "Eliminar consultorio", JOptionPane.DEFAULT_OPTION);
                        if (idABuscar.isBlank()){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">Por favor ingrese un "
                                    + "identificador</p></html>", "Error", 
                                    JOptionPane.OK_OPTION, 
                                    UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        } 
                        if(!respaldoDatos.getConsultorios().containsKey(idABuscar)){
                            JOptionPane.showMessageDialog(vistaGestionServicio, 
                                    "<html><p style = \" font:12px; \">No se encontró ningún "
                                            + "consultorio registrado con ese identificador</p></html>", 
                                    "Consultorio no encontrado", JOptionPane.OK_OPTION, 
                                    UIManager.getIcon("OptionPane.errorIcon"));
                            return;
                        }
                        irEliminarConsultorio(idABuscar);

                    } catch(NullPointerException np){

                    }
                }
            }
        } catch(NullPointerException np){
            
        }
    }
    
    public void irAgregarConsultorio(){
        AgregarConsultorio ventanaAgregarConsultorio = new AgregarConsultorio("Agregar "
                + "consultorio", "Agregar", respaldoDatos, "");
        vistaGestionServicio.dispose();
    }
    public void irActualizarConsultorio(String id) {
        AgregarConsultorio ventanaActualizarConsultorio = new AgregarConsultorio("Actualizar "
                + "consultorio", "Actualizar", respaldoDatos, id);
        vistaGestionServicio.dispose();
    }
    public void irListarConsultorio(){
        ListaConsultorios ventanaListaConsultorio = new ListaConsultorios("Listas de consultorios",
                respaldoDatos);
        vistaGestionServicio.dispose();
    }
    public void irEliminarConsultorio(String id) {
        AgregarConsultorio ventanaActualizarConsultorio = new AgregarConsultorio("Eliminar "
                + "consultorio", "Eliminar", respaldoDatos, id);
        vistaGestionServicio.dispose();
    }
    
    //Opciones a realizar con un servicio
    public void opcionesServicio(){
        try {
            String resp = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                    "<html><p style = \" font:12px; \">¿Qué desea hacer?</p></html>", 
                    "Opciones servicios médicos", JOptionPane.DEFAULT_OPTION, 
                    UIManager.getIcon("OptionPane.questionIcon"), opciones, opciones[0]);
            
            //Convirtiendo los servicios actuales a String[]
            ArrayList<Servicio> servicios = respaldoDatos.getServicios();
            String[] misServicios = new String[servicios.size()];
            
            for (int i= 0; i<servicios.size(); i++){
                String servicio = "";
                servicio += servicios.get(i).getNombre();
                misServicios[i] = servicio;
            }
            switch (resp) {
                case "Agregar" -> {
                    irAgregarServicio();
                }
                case "Listar" -> {
                    irListarServicio();
                }
                case "Actualizar" -> {
                    if(misServicios.length == 0){
                        JOptionPane.showMessageDialog(vistaGestionServicio, "<html><p style = \" "
                                + "font:12px; \">Agregue un servicio primero</p></html>", "Aviso", 
                                JOptionPane.OK_OPTION, 
                                UIManager.getIcon("OptionPane.informationIcon"));                    
                        return;
                    }
                    String resp2 = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                            "<html><p style = \" font:12px; \">Escoja el servicio</p></html>", 
                            "Actualizar servicio", JOptionPane.DEFAULT_OPTION, 
                            UIManager.getIcon("OptionPane.questionIcon"), misServicios, misServicios[0]);
                    if (resp2 != null){
                        irActualizarServicio(resp2);
                    }
                }
                case "Eliminar" -> {
                    if(misServicios.length == 0){
                        JOptionPane.showMessageDialog(vistaGestionServicio, "<html><p style = \" "
                                + "font:12px; \">Agregue un servicio primero</p></html>", "Aviso", 
                                JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));                    
                        return;
                    }
                    String resp2 = (String) JOptionPane.showInputDialog(vistaGestionServicio, 
                            "<html><p style = \" font:12px; \">Escoja el servicio</p></html>", 
                            "Eliminar servicio", JOptionPane.DEFAULT_OPTION, 
                            UIManager.getIcon("OptionPane.questionIcon"), misServicios, misServicios[0]);
                    if (resp2 != null){
                        irEliminarServicio(resp2);
                    }
                }
            }
        } catch(NullPointerException np){
            
        }
    }
    public void irAgregarServicio(){
        AgregarServicio ventanaAgregarServicio = new AgregarServicio("Agregar servicio", 
                "Agregar", respaldoDatos, null);
        vistaGestionServicio.dispose();
    }
    public void irActualizarServicio(String servicio) {
        AgregarServicio ventanaActualizarServicio = new AgregarServicio("Actualizar servicio", 
                "Actualizar", respaldoDatos, servicio);
        vistaGestionServicio.dispose();
    }
    public void irListarServicio(){
        ListaServicios ventanaListaServicio = new ListaServicios("Lista de servicios", respaldoDatos);
        vistaGestionServicio.dispose();
    }
    public void irEliminarServicio(String servicio) {
        AgregarServicio ventanaEliminarServicio = new AgregarServicio("Eliminar servicio", 
                "Eliminar", respaldoDatos, servicio);
        vistaGestionServicio.dispose();
    }
}
