
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
import Clases.Afiliados;
import Clases.RespaldoDatos;
import Vista.GestionServicios;
import Vista.ListaAfiliados;
import Vista.AgregarAfiliado;

public class GestorListaAfiliado {
    
    private ListaAfiliados ventanaListarAfiliado;
    private RespaldoDatos respaldoDatos;
    private HashMap<Long, Afiliados> afiliado;

    public GestorListaAfiliado(ListaAfiliados ventanaListarAfiliado, RespaldoDatos respaldoDatos) {
        this.ventanaListarAfiliado = ventanaListarAfiliado;
        this.respaldoDatos = respaldoDatos;
        afiliado = respaldoDatos.getAfiliados();
        insertarAfiliados();
        this.ventanaListarAfiliado.addBtnRegresarListener(new ManejadoraDeMouse());
        this.ventanaListarAfiliado.addBtnConsultarListener(new ManejadoraDeMouse());
    }
    class ManejadoraDeMouse extends MouseAdapter{
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            if (e.getSource() == ventanaListarAfiliado.getBtnRegresar()){
                if (e.getButton() == 1){
                    irGestionServicioGUI();  
                }
            }
            if (e.getSource() == ventanaListarAfiliado.getBtnConsultar()){
                if (e.getButton() == 1){
                    irConsultar();  
                }
            }
        }
    }
    private void irGestionServicioGUI() {
        
        //Creación de vistas
        GestionServicios vistaGestionServicio = new GestionServicios("Gestión de servicios", respaldoDatos);
        ventanaListarAfiliado.dispose();
    }
    public void insertarAfiliados() {
        Iterator i = afiliado.entrySet().iterator();

        while(i.hasNext()) {
            HashMap.Entry <Long, Afiliados> mapa = (HashMap.Entry) i.next();
            Afiliados afiliado = mapa.getValue();
            Object[] fila = new Object[4];
            fila[0] = afiliado.getNombre();
            fila[1] = afiliado.getIdentificacion();
            fila[2] = afiliado.getCelular();
            fila[3] = afiliado.getCorreo();
            ventanaListarAfiliado.anadirFilaTabla(fila);
        }
    }
    
    public void irConsultar() {
        int fila = ventanaListarAfiliado.filaSeleccionada();
        if (fila != -1) {
            long cedula = ventanaListarAfiliado.afiliadoSeleccionado(fila);
            AgregarAfiliado ventanaAgregarAfiliado = new AgregarAfiliado("Consultar afiliado", "Consultar", respaldoDatos, cedula);
            ventanaListarAfiliado.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Ninguna entrada seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
}
