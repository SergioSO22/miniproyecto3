

package Controladores;

/**
 *
 * @author Sergio Sánchez
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Clases.RespaldoDatos;
import Vista.GestionCitas;
import Vista.GestionServicios;
import Vista.OpcionesRespaldo;
import Vista.VentanaPrincipal;

public class GestorVentanaPrincipal {
    
    private final VentanaPrincipal vistaPpal;
    private final RespaldoDatos almacenamiento;
  
    public GestorVentanaPrincipal(VentanaPrincipal vistaPpal, RespaldoDatos almacenamiento) {
        this.vistaPpal = vistaPpal;
        this.vistaPpal.addBtnGestionServicioListener(new ManejadoraDeMouse());
        this.vistaPpal.addBtnGestionCitaListener(new ManejadoraDeMouse());
        this.vistaPpal.addBtnRespaldoListener(new ManejadoraDeMouse());
        this.almacenamiento = almacenamiento;
    }
    
    class ManejadoraDeMouse extends MouseAdapter{
        
        @Override
        public void mouseClicked(MouseEvent e){
          if (e.getSource() == vistaPpal.getBtnGestionServicio()){
                if (e.getButton() == 1){
                    irGestionServicioGUI();
                }
          }
          
          if (e.getSource() == vistaPpal.getBtnGestionCita()){
                if (e.getButton() == 1){
                    irGestionDeCitas();
                }
          }
          
          if (e.getSource() == vistaPpal.getBtnRespaldo()){
                if (e.getButton() == 1){
                    irRespaldo();
                }
          }
        }
    }
    
    public void irGestionServicioGUI(){
        GestionServicios ventanaGestionServicio = new GestionServicios("Gestión de servicios", almacenamiento);
        ventanaGestionServicio.setVisible(true);
        vistaPpal.dispose();
    }
    
    public void irGestionDeCitas(){
        GestionCitas ventanaGestionCitas = new GestionCitas("Gestión de citas", almacenamiento);
        ventanaGestionCitas.setVisible(true);
        vistaPpal.dispose();
    }
    
    public void irRespaldo(){
        OpcionesRespaldo ventanaRespaldo = new OpcionesRespaldo("Opciones de respaldo", almacenamiento);
        ventanaRespaldo.setVisible(true);
        vistaPpal.dispose();
    }
    
}
