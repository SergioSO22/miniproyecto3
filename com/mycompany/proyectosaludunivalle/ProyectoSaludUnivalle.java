

package com.mycompany.proyectosaludunivalle;
import java.util.Arrays;
import javax.swing.*;
import Vista.VentanaPrincipal;
import Clases.*;
/**
 *
 * @author Sergio Sánchez
 */
public class ProyectoSaludUnivalle {

    public static void main(String[] args) {
       try{
           RespaldoDatos almacenamiento = new RespaldoDatos();
           VentanaPrincipal inicio = new VentanaPrincipal("Administrador Servicio de salud - Universidad del Valle", almacenamiento);
       }catch (Exception e) {
           System.out.println(Arrays.toString(e.getStackTrace()));
           JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
       }
    }
}
