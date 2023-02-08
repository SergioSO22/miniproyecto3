

package Controladores;
/**
 *
 * @author Sergio Sánchez
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;
import Clases.RespaldoDatos;
import Clases.Medicos;
import Clases.Servicio;
import Vista.GestionServicios;
import Vista.ListaMedicos;
import Vista.AgregarMedico;

public class GestorListaMedico {
    
    private ListaMedicos ventanaListarMedico;
    private RespaldoDatos almacenamiento;
    private HashMap<Long, Medicos> medicos;

    public GestorListaMedico(ListaMedicos ventanaListarMedico, RespaldoDatos almacenamiento) {
        this.ventanaListarMedico = ventanaListarMedico;
        this.almacenamiento = almacenamiento;
        medicos = almacenamiento.getMedicos();
        insertarMedicos();
        this.ventanaListarMedico.addBtnRegresarListener(new ManejadoraDeMouse());
        this.ventanaListarMedico.addBtnConsultarListener(new ManejadoraDeMouse());
    }
    class ManejadoraDeMouse extends MouseAdapter{
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            if (e.getSource() == ventanaListarMedico.getBtnRegresar()){
                if (e.getButton() == 1){
                    irGestionServicioGUI();  
                }
            }
            if (e.getSource() == ventanaListarMedico.getBtnConsultar()){
                if (e.getButton() == 1){
                    irConsultar();  
                }
            }
        }
    }
    private void irGestionServicioGUI() {
        
        //Creación de vistas
        GestionServicios vistaGestionServicio = new GestionServicios("Gestión de servicios", almacenamiento);
        ventanaListarMedico.dispose();
    }
    public void insertarMedicos() {
        Iterator i = medicos.entrySet().iterator();

        while(i.hasNext()) {
            HashMap.Entry <Long, Medicos> mapa = (HashMap.Entry) i.next();
            Medicos medico = mapa.getValue();
            Object[] fila = new Object[5];
            fila[0] = medico.getNombre();
            fila[1] = medico.getIdentificacion();
            ArrayList <Servicio> servicios = medico.getServicios();
            String serviciosString = "";
            for (int o = 0; o < servicios.size(); o++) {
                if (o != (servicios.size()-1)) {
                    serviciosString += servicios.get(o) + ", ";
                } else {
                    serviciosString += servicios.get(o) + ".";
                }
            }
            fila[2] = serviciosString;
            fila[3] = medico.getCelular();
            fila[4] = medico.getCorreo();
            ventanaListarMedico.anadirFilaTabla(fila);
        }
    }
    
    public void irConsultar() {
        int fila = ventanaListarMedico.filaSeleccionada();
        if (fila != -1) {
            long cedula = ventanaListarMedico.afiliadoSeleccionado(fila);
            AgregarMedico ventanaConsultarMedico = new AgregarMedico("Consultar afiliado", "Consultar", almacenamiento, cedula);
            ventanaListarMedico.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Ninguna entrada seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
