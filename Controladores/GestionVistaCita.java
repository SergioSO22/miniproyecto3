
package Controladores;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import Clases.Afiliados;
import Clases.Citas;
import Clases.RespaldoDatos;
import Clases.Consultorios;
import Clases.Medicos;
import Clases.Servicio;
import Vista.CitasAfiliado;
import Vista.GestionCitas;
import Vista.VentanaPrincipal;
import Vista.AgregarCita;

/**
 *
 * @author Sergio Sánchez
 */
public class GestionVistaCita {
    
    private final GestionCitas vistaGestionCitas;
    private final RespaldoDatos respaldoDatos;
    
    public GestionVistaCita(GestionCitas vistaGestionCitas, RespaldoDatos respaldoDatos){
        this.respaldoDatos = respaldoDatos;
        
        //Vista
        this.vistaGestionCitas = vistaGestionCitas;
        
        //Listeners
        this.vistaGestionCitas.addBtnRegresarListener(new ManejadorMouse());
        this.vistaGestionCitas.addBtnConsultarListener(new ManejadorMouse());
        this.vistaGestionCitas.addBtnAgendarListener(new ManejadorMouse());
        this.vistaGestionCitas.addBtnEliminarListener(new ManejadorMouse());
        this.vistaGestionCitas.addBtnModificarListener(new ManejadorMouse());
    }
    
    class ManejadorMouse extends MouseAdapter{
        
        public void mouseClicked(MouseEvent e){
            if(e.getSource() == vistaGestionCitas.getBtnRegresar()){
                if(e.getButton() == 1){
                    irPrincipal();
                }
            }
            if (e.getSource() == vistaGestionCitas.getBtnConsultar()){
              if (e.getButton() == 1){
                  irCitasAfiliado();
              }
        }
            if (e.getSource() == vistaGestionCitas.getBtnAgendar()){
              if (e.getButton() == 1){
                  irAgendarCita();
              }
          }
           if (e.getSource() == vistaGestionCitas.getBtnEliminar()){
              if (e.getButton() == 1){  
                  elegirEliminarCita();
              }
          }
            if (e.getSource() == vistaGestionCitas.getBtnModificar()){
              if (e.getButton() == 1){                  
                  elegirModificarCita();
              }
          }
    }
    
}
    
    public void irPrincipal(){
        VentanaPrincipal ventanaPr = new VentanaPrincipal("Administrador del servicio de salud - Universidad del Valle", respaldoDatos);
        vistaGestionCitas.dispose();
    }
    public void irCitasAfiliado(){
        HashMap<Integer, Citas> lasCitas = respaldoDatos.getCitas();
        if(!lasCitas.isEmpty()){
            String BuscarCedula;
            
            try{
                BuscarCedula = (String) JOptionPane.showInputDialog(vistaGestionCitas,"<html><p style = \" font:12px; \">Ingrese la cédula del afiliado</p></html>"
                , "Agendar cita", JOptionPane.DEFAULT_OPTION );
                
                if(BuscarCedula.isBlank()){
                    JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">Por favor ingrese una cédula</p></html>"
                    , "Error", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.errorIcon") );
                }else{
                 long cedula = Long.parseLong(BuscarCedula);
                 
                 if(!respaldoDatos.getAfiliados().containsKey(cedula)){
                     JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">No se encontró ningún afiliado registrado con esa cédula</p></html>",
                           "Afiliado no encontrado", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.errorIcon")  );
                 }else{
                     CitasAfiliado ventanaConsultaCitasAfiliado = new CitasAfiliado("Citas del afiliado", cedula, respaldoDatos);
                     vistaGestionCitas.dispose();
                 }
                }
            }catch(NullPointerException np){
                
            }
                    
                    
                    
        }else{
            JOptionPane.showMessageDialog(vistaGestionCitas,"<html><p style = \" font:12px; \">Agregue una cita primero</p></html>","Aviso", JOptionPane.OK_OPTION,UIManager.getIcon("OptionPane.informationIcon") );
        }
    }
    
    public void irAgendarCita(){
        HashMap<Long, Afiliados> misAfiliados = respaldoDatos.getAfiliados();
        HashMap<Long, Medicos> misMedicos = respaldoDatos.getMedicos();
        HashMap<String, Consultorios> misConsultorios = respaldoDatos.getConsultorios();
        ArrayList<Servicio> serviciosMedicos = respaldoDatos.getServicios();
        
        if(misAfiliados.isEmpty() | misMedicos.isEmpty() | misConsultorios.isEmpty() | serviciosMedicos.isEmpty()){
            JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">Debe agregar por lo menos: 1 afiliado, 1 médico, 1 consultorio y 1 servicio</p></html>", "Aviso", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
            return;
        }

        //Convirtiendo los servicios actuales a String[]
        String[] misServicios = new String[serviciosMedicos.size()];

        for (int i= 0; i<serviciosMedicos.size(); i++){
            misServicios[i] = serviciosMedicos.get(i).getNombre();
        }

        try {
            String BuscarCedula = (String) JOptionPane.showInputDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">Ingrese la cédula del afiliado</p></html>", "Agendar cita", JOptionPane.DEFAULT_OPTION);
            long cedula;
            if (BuscarCedula.isBlank()){
                JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">Por favor ingrese una cédula</p></html>", "Error", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.errorIcon"));
                return;
            }
            try {
                cedula = Long.parseLong(BuscarCedula);
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "<html><p style = \" font:12px; \">Por favor ingrese una cédula válida</p></html>", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(!respaldoDatos.getAfiliados().containsKey(cedula)){
                JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">No se encontró ningún afiliado registrado con esa cédula</p></html>", "Afiliado no encontrado", 
                        JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.errorIcon"));
                return;
            }
            String motivoCita = (String) JOptionPane.showInputDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">Escoja el motivo de la cita</p></html>", 
                    "Lista de servicios", JOptionPane.DEFAULT_OPTION, UIManager.getIcon("OptionPane.questionIcon"), misServicios, misServicios[0]);

            Iterator i = misMedicos.entrySet().iterator();

            while(i.hasNext()) {
                HashMap.Entry <Long, Medicos> mapa = (HashMap.Entry) i.next();
                ArrayList<Servicio> servicioDelMedico = mapa.getValue().getServicios();

                for (int o = 0; o < servicioDelMedico.size(); o++){
                    String servicio = "";
                    servicio += servicioDelMedico.get(o);
                    if (servicio.equals(motivoCita)) {
                        AgregarCita ventanaAgendarCita = new AgregarCita("Agendar Citas", "Agendar", motivoCita, cedula, 0, respaldoDatos);
                        vistaGestionCitas.dispose();
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">No se encontró ningún médico que preste ese servicio</p></html>", "Médico sin servicio requerido", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.errorIcon"));
        } catch(NullPointerException np){
            
        }
    }
    public void elegirModificarCita(){
        HashMap<Integer, Citas> misCitas = respaldoDatos.getCitas();
        
        if(misCitas.isEmpty()){
            JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">Agregue una cita primero</p></html>", "Aviso", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
            return;
        }
        try {
            String referenciaABuscar = (String) JOptionPane.showInputDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">Ingrese el numero de referencia de la cita a modificar</p></html>", "Modificar cita", JOptionPane.DEFAULT_OPTION);
            if(referenciaABuscar.isBlank()){
                JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">Por favor ingrese un número de referencia</p></html>", "Error", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.errorIcon"));
                return;
            }
            int numReferenciaABuscar;
            try{
                numReferenciaABuscar = Integer.parseInt(referenciaABuscar);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número de referencia "
                        + "válido", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }
            if(!misCitas.containsKey(numReferenciaABuscar)){
                JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">No se encontró ninguna cita</p></html>", "Aviso", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
                return;
            }
            irModificarCita(numReferenciaABuscar);
        } catch(NullPointerException np) {
            
        }
    }
    
    public void irModificarCita(int numReferencia){
        String motivoCita = respaldoDatos.getCitas().get(numReferencia).getServicioElegido().getNombre();
        long cedula = respaldoDatos.getCitas().get(numReferencia).getAfiliado().getIdentificacion();
        AgregarCita ventanaModificarCita = new AgregarCita("Modificar cita", "Modificar", motivoCita, cedula, numReferencia, respaldoDatos);
        vistaGestionCitas.dispose();
    }
    
    public void elegirEliminarCita(){
        HashMap<Integer, Citas> lasCitas = respaldoDatos.getCitas();
        
        if(lasCitas.isEmpty()){
            JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">Agregue una cita primero</p></html>", "Aviso", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
            return;
        }
        try {
            String referenciaABuscar = (String) JOptionPane.showInputDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">Ingrese el numero de referencia de la cita a eliminar</p></html>", "Eliminar cita", JOptionPane.DEFAULT_OPTION);
            if(referenciaABuscar.isBlank()){
                JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">Por favor ingrese una cédula</p></html>", "Error", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.errorIcon"));
                return;
            }
            int numReferenciaABuscar = Integer.parseInt(referenciaABuscar);
            if(!lasCitas.containsKey(numReferenciaABuscar)){
                JOptionPane.showMessageDialog(vistaGestionCitas, "<html><p style = \" font:12px; \">No se encontró ninguna cita</p></html>", "Aviso", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
                return;
            }
            irEliminarCita(numReferenciaABuscar);
            vistaGestionCitas.dispose();
        } catch(NullPointerException np) {
            
        }
    }
    
    public void irEliminarCita(int numRef){
       String motivoCita = respaldoDatos.getCitas().get(numRef).getServicioElegido().getNombre();
       long cedula = respaldoDatos.getCitas().get(numRef).getAfiliado().getIdentificacion();
       AgregarCita ventanaEliminarCita = new AgregarCita("Eliminar cita", "Eliminar", motivoCita, cedula, numRef, respaldoDatos);
       vistaGestionCitas.dispose();
    }
    
    
    
}
