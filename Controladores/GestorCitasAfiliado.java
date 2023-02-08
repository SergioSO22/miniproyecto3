

package Controladores;
/**
 *
 * @author Sergio Sánchez
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;
import Clases.RespaldoDatos;
import Clases.Citas;
import Vista.CitasAfiliado;
import Vista.GestionCitas;
import Vista.AgregarCita;

public class GestorCitasAfiliado {
    
    private CitasAfiliado ventanaCitasAfiliado;
    private final RespaldoDatos almacenamiento;
    private final long cedula;
    private HashMap<Integer, Citas> citas;
    
    public GestorCitasAfiliado(CitasAfiliado ventanaCitasAfiliado, long cedula, RespaldoDatos almacenamiento) {
        this.ventanaCitasAfiliado = ventanaCitasAfiliado;
        this.almacenamiento = almacenamiento;
        this.citas = almacenamiento.getCitas();
        this.cedula = cedula;
        consultarCitasDeAfiliado();
        this.ventanaCitasAfiliado.addBtnRegresarListener(new ManejadoraDeMouse());
        this.ventanaCitasAfiliado.addBtnConsultarListener(new ManejadoraDeMouse());
    }
    class ManejadoraDeMouse extends MouseAdapter{
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            if (e.getSource() == ventanaCitasAfiliado.getBtnRegresar()){
                if (e.getButton() == 1){
                    irGestionDeCitas();  
                }
            }
            if (e.getSource() == ventanaCitasAfiliado.getBtnConsultar()){
                if (e.getButton() == 1){
                    irConsultar();  
                }
            }
        }
    }
    private void irGestionDeCitas() {
        
        GestionCitas vistaGestionCitas = new GestionCitas("Gestión de citas", almacenamiento);
        ventanaCitasAfiliado.dispose();
    }
    private void consultarCitasDeAfiliado() {
        
        Iterator i = citas.entrySet().iterator();
        while(i.hasNext()) {
            HashMap.Entry <Integer, Citas> mapa = (HashMap.Entry) i.next();
            Citas cita = mapa.getValue();
            if (cita.getAfiliado().getIdentificacion()==cedula) {
                Object[] fila = new Object[4];
                fila[0] = cita.getNumeroReferencia();
                fila[1] = cita.getAfiliado().getNombre();
                fila[2] = cita.getMedico().getNombre();
                fila[3] = cita.getFecha();
                ventanaCitasAfiliado.anadirFilaTabla(fila);
            }
        }
    }
    public void irConsultar() {
        int fila = ventanaCitasAfiliado.filaSeleccionada();
        if (fila != -1) {
            int numRef = ventanaCitasAfiliado.citaSeleccionada(fila);
            AgregarCita ventanaAgregarCita = new AgregarCita("Consultar cita", "Consultar", "", cedula, numRef, almacenamiento);
            ventanaCitasAfiliado.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Ninguna entrada seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
