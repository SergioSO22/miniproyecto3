
package Clases;
import java.io.*;
import java.util.*;

/**
 *
 * @author Sergio Sánchez
 */
public class Medicos extends Persona implements Serializable {
    private ArrayList<Servicio> serviciosMedicos;
    
    public Medicos(ArrayList<Servicio> serviciosMedicos, String nombre,
            String sexo, String direccion, String correo, long identificacion,
           int edad, long celular ){
        super(nombre, sexo, direccion, correo, identificacion, edad, celular);
        this.serviciosMedicos = serviciosMedicos;
    }
    public ArrayList<Servicio> getServicios(){
        return serviciosMedicos;
    }
    public void setServicios(ArrayList<Servicio> serviciosMedicos){
        this.serviciosMedicos = serviciosMedicos;
    }
}
