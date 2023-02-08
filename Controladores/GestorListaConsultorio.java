

package Controladores;
/**
 *
 * @author Sergio Sánchez
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import Clases.RespaldoDatos;
import Clases.Consultorios;
import Vista.GestionServicios;
import Vista.ListaConsultorios;

public class GestorListaConsultorio {
    
    private ListaConsultorios ventanaListarConsultorio;
    private RespaldoDatos almacenamiento;
    private HashMap <String, Consultorios> consultorio;

    public GestorListaConsultorio(ListaConsultorios ventanaListarConsultorio, RespaldoDatos almacenamiento) {
        this.ventanaListarConsultorio = ventanaListarConsultorio;
        this.almacenamiento = almacenamiento;
        consultorio = almacenamiento.getConsultorios();
        insertarConsultorios();
        this.ventanaListarConsultorio.addBtnRegresarListener(new ManejadoraDeMouse());
    }
    class ManejadoraDeMouse extends MouseAdapter{
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            if (e.getSource() == ventanaListarConsultorio.getBtnRegresar()){
                if (e.getButton() == 1){
                    irGestionServicioGUI();  
                }
            }
        }
    }
    private void irGestionServicioGUI() {
        
        //Creación de vistas
        GestionServicios vistaGestionServicio = new GestionServicios("Gestión de servicios", almacenamiento);
        ventanaListarConsultorio.dispose();
    }
    
    public void insertarConsultorios() {
        Iterator i = consultorio.entrySet().iterator();

        while(i.hasNext()) {
            HashMap.Entry <String, Consultorios> mapa = (HashMap.Entry) i.next();
            Consultorios consultorio = mapa.getValue();
            Object[] fila = new Object[2];
            fila[0] = consultorio.getIdentificador();
            fila[1] = consultorio.getServicioAfiliado().getNombre();
            ventanaListarConsultorio.anadirFilaTabla(fila);
        }
    }
    
}
