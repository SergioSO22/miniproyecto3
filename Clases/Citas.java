
package Clases;
import java.io.*;
import java.sql.*;
import java.time.LocalTime;

/**
 *
 * @author Sergio Sánchez
 */
public class Citas implements Serializable {
    
    private int numeroReferencia;
    private Date fecha;
    private Servicio servicioElegido;
    private Consultorios consultorio;
    private Medicos medico;
    private LocalTime hora;
    private Afiliados afiliado;
    
    public Citas(int numeroReferencia, Date fecha, LocalTime hora, Servicio servicioElegido, Afiliados afiliado,
           Consultorios consultorio,Medicos medico ){
        
        this.numeroReferencia = numeroReferencia;
        this.fecha = fecha;
        this.hora = hora;
        this.servicioElegido = servicioElegido;
        this.afiliado = afiliado;
        this.consultorio = consultorio;
        this.medico = medico;
    }

    public int getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(int numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Servicio getServicioElegido() {
        return servicioElegido;
    }

    public void setServicioElegido(Servicio servicioElegido) {
        this.servicioElegido = servicioElegido;
    }

    public Consultorios getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorios consultorio) {
        this.consultorio = consultorio;
    }

    public Medicos getMedico() {
        return medico;
    }

    public void setMedico(Medicos medico) {
        this.medico = medico;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Afiliados getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliados afiliado) {
        this.afiliado = afiliado;
    }
    
   
   
    
    
    
}
