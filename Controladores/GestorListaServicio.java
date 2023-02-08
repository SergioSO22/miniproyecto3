

package Controladores;
/**
 *
 * @author Sergio Sánchez
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import Clases.RespaldoDatos;
import Clases.Servicio;
import Vista.GestionServicios;
import Vista.ListaServicios;

public class GestorListaServicio {
    
    private ListaServicios ventanaListarServicio;
    private RespaldoDatos almacenamiento;
    private ArrayList <Servicio> servicios;
    
    public GestorListaServicio(ListaServicios ventanaListarServicio, RespaldoDatos almacenamiento) {
        this.ventanaListarServicio = ventanaListarServicio;
        this.almacenamiento = almacenamiento;
        servicios = almacenamiento.getServicios();
        insertarServicios();
        this.ventanaListarServicio.addBtnRegresarListener(new ManejadoraDeMouse());
    }
    
    class ManejadoraDeMouse extends MouseAdapter{
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            if (e.getSource() == ventanaListarServicio.getBtnRegresar()){
                if (e.getButton() == 1){
                    irGestionServicioGUI();  
                }
            }
        }
    }
    private void irGestionServicioGUI() {
        
        //Creación de vistas
        GestionServicios vistaGestionServicio = new GestionServicios("Gestión de servicios", almacenamiento);
        ventanaListarServicio.dispose();
    }
    public void insertarServicios() {
        Iterator i = servicios.iterator();

        while(i.hasNext()) {
            Servicio servicio = (Servicio) i.next();
            Object[] fila = new Object[1];
            fila[0] = servicio.getNombre();
            ventanaListarServicio.anadirFilaTabla(fila);
        }
    }
}
