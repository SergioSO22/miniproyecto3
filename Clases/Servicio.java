
package Clases;
import java.io.*;

/**
 *
 * @author Sergio Sánchez
 */
public class Servicio implements Serializable {
    
    private String nombre;
    public Servicio(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
