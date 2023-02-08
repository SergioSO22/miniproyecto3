   
package Clases;
import java.io.*;

/**
 *
 * @author Sergio Sánchez
 */
public class Afiliados extends Persona implements Serializable {
    public Afiliados(String nombre, String sexo, String direccion, String correo,
    long identificacion, int edad, long celular){
    
    super(nombre, sexo, correo,  direccion, identificacion, edad, celular);
    
}
    
    
}
