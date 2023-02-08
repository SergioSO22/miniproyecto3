
package Clases;

import java.io.Serializable;
/**
 *
 * @author Sergio Sánchez
 */
public class Persona implements Serializable {
    protected String nombre;
    protected String sexo;
    protected String correo;
    protected String direccion;
    protected long identificacion;
    protected int edad;
    protected long celular;
    
    public Persona(String nombre, String sexo, String direccion, String correo, long identificacion,
    int edad, long celular){
        
        this.nombre = nombre;
        this.edad  = edad;
        this.direccion = direccion;
        this.correo = correo;
        this.identificacion = identificacion;
        this.celular = celular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(long identificacion) {
        this.identificacion = identificacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }
    
    
}
