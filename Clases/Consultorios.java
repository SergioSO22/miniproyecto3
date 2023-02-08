
package Clases;
import java.io.*;

/**
 *
 * @author Sergio Sánchez
 */
public class Consultorios implements Serializable {
    
    private String identificador;
    private Servicio servicioAfiliado;
    
    public Consultorios(String identificador, Servicio servicioAfiliado){
        this.identificador = identificador;
        this.servicioAfiliado = servicioAfiliado;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Servicio getServicioAfiliado() {
        return servicioAfiliado;
    }

    public void setServicioAfiliado(Servicio servicioAfiliado) {
        this.servicioAfiliado = servicioAfiliado;
    }
    
 
    
}
