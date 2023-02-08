
package Clases;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class RespaldoDatos {
    
    private HashMap <Long, Afiliados> afiliados;
    private HashMap <Long, Medicos> medicos;
    private ArrayList <Servicio> servicios;
    private HashMap <String, Consultorios> consultorios;
    private HashMap <Integer, Citas> citas;
    
    public RespaldoDatos () throws IOException, ClassNotFoundException {
        try
        {
            restaurarDatos();
        } catch (IOException | ClassNotFoundException e) {
            throw e;
        }
    }
    
    public boolean hacerBackUp() throws IOException{
        
        try
        {
            OutputStream os = new FileOutputStream("Datos.bin");
            ObjectOutputStream oos = new ObjectOutputStream(os);
            
            oos.writeObject(afiliados);
            oos.writeObject(medicos);
            oos.writeObject(servicios);
            oos.writeObject(consultorios);
            oos.writeObject(citas);
            
            oos.close();
            return true;
        } catch (IOException e) {
            throw e;
        }
    }
    
    public boolean restaurarDatos() throws IOException, ClassNotFoundException {
        
        try
        {
            InputStream is = new FileInputStream("Datos.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            
            afiliados = (HashMap) ois.readObject();
            medicos = (HashMap) ois.readObject();
            servicios = (ArrayList) ois.readObject();
            consultorios = (HashMap) ois.readObject();
            citas = (HashMap) ois.readObject();
            
            ois.close();
            return true;
        } catch (FileNotFoundException e) {
            afiliados = new HashMap();
            medicos = new HashMap();
            servicios = new ArrayList();
            consultorios = new HashMap();
            citas = new HashMap();
            try
            {
                hacerBackUp();
            } catch (IOException ex) {
                throw ex;
            }
            return true;
        } catch(IOException | ClassNotFoundException e) {
            throw e;
        }
    }
    
    public boolean exportarDatosAfiliados(File ruta) throws IOException {
        
        try
        {
            FileWriter fw = new FileWriter(ruta);
            PrintWriter pw = new PrintWriter(fw);

            Iterator i = afiliados.entrySet().iterator();

            while(i.hasNext()) {
                HashMap.Entry <Integer, Afiliados> mapa = (HashMap.Entry) i.next();
                Afiliados afiliado = mapa.getValue();
                pw.print(afiliado.getNombre() + ";");
                pw.print(afiliado.getSexo() + ";");
                pw.print(afiliado.getDireccion() + ";");
                pw.print(afiliado.getCorreo() + ";");
                pw.print(String.valueOf(afiliado.getIdentificacion()) + ";");
                pw.print(String.valueOf(afiliado.getEdad()) + ";");
                pw.println(String.valueOf(afiliado.getCelular()));
            }

            pw.close();
            return true;
        } catch (IOException e) {
            throw e;
        }
    }
    
    public boolean hacerBackUp(File ruta) throws IOException{
        
        try
        {
            OutputStream os = new FileOutputStream(ruta);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(afiliados);
            oos.writeObject(medicos);
            oos.writeObject(servicios);
            oos.writeObject(consultorios);
            oos.writeObject(citas);

            oos.close();
            return true;
        } catch (IOException e) {
            throw e;
        }
    }
    
    public boolean restaurarDatos(File ruta) throws IOException, ClassNotFoundException {
        
        try
        {
            InputStream is = new FileInputStream(ruta);
            ObjectInputStream ois = new ObjectInputStream(is);

            afiliados = (HashMap) ois.readObject();
            medicos = (HashMap) ois.readObject();
            servicios = (ArrayList) ois.readObject();
            consultorios = (HashMap) ois.readObject();
            citas = (HashMap) ois.readObject();

            ois.close();
            return true;
        } catch(IOException | ClassNotFoundException e) {
            throw e;
        }
    }

    public boolean anadirAfiliado(Afiliados afiliado) throws IOException {
        if (!afiliados.containsKey(afiliado.getIdentificacion()) && !medicos.containsKey(afiliado.getIdentificacion())) {
            afiliados.put(afiliado.getIdentificacion(), afiliado);
            try
            {
                hacerBackUp();
                return true;
            } catch (IOException e) {
                throw e;
            }
        }
        return false;
    }
    
    public void modificarAfiliado(long cedulaAnterior, Afiliados afiliado) throws IOException {
        afiliados.remove(cedulaAnterior);
        afiliados.put(afiliado.getIdentificacion(), afiliado);
        try
        {
            hacerBackUp();
        } catch (IOException e) {
            throw e;
        }
    }
    
    public void eliminarAfiliado(long cedula) throws IOException {
        Afiliados afiliado = afiliados.get(cedula);
        afiliados.remove(cedula);
        
        for (int i=0; i<citas.size(); i++) {
            Citas cita = citas.get(i);
            if (cita.getAfiliado() == afiliado) {
                citas.remove(i);
            }
        }
        try
        {
            hacerBackUp();
        } catch (IOException e) {
            throw e;
        }
    }
    
    public boolean anadirMedico(Medicos medico) throws IOException {
        if (!medicos.containsKey(medico.getIdentificacion()) && !afiliados.containsKey(medico.getIdentificacion())) {
            medicos.put(medico.getIdentificacion(), medico);
            try
            {
                hacerBackUp();
                return true;
            } catch (IOException e) {
                throw e;
            }
        }
        return false;
    }
    
    public void modificarMedico(long cedulaAnterior, Medicos medico) throws IOException {
        medicos.remove(cedulaAnterior);
        medicos.put(medico.getIdentificacion(), medico);
        try
        {
            hacerBackUp();
        } catch (IOException e) {
            throw e;
        }
    }
    
    public void eliminarMedico(long cedula) throws IOException {
        Medicos medico = medicos.get(cedula);
        medicos.remove(cedula);
        
        for (int i=0; i<citas.size(); i++) {
            Citas cita = citas.get(i);
            if (cita.getMedico() == medico) {
                citas.remove(i);
            }
        }
        try
        {
            hacerBackUp();
        } catch (IOException e) {
            throw e;
        }
    }
    
    public boolean anadirConsultorio(Consultorios consultorio) throws IOException {
        if (!consultorios.containsKey(consultorio.getIdentificador())) {
            consultorios.put(consultorio.getIdentificador(), consultorio);
            try
            {
                hacerBackUp();
                return true;
            } catch (IOException e) {
                throw e;
            }
        }
        return false;
    }
    
    public void modificarConsultorio(String indentificadorAnterior, Consultorios consultorio) throws IOException {
        consultorios.remove(indentificadorAnterior);
        consultorios.put(consultorio.getIdentificador(), consultorio);
        try
        {
            hacerBackUp();
        } catch (IOException e) {
            throw e;
        }
    }
    
    public void eliminarConsultorio(String indentificador) throws IOException {
        Consultorios consultorio = consultorios.get(indentificador);
        consultorios.remove(indentificador);
        
        for (int i=0; i<citas.size(); i++) {
            Citas cita = citas.get(i);
            if (cita.getConsultorio() == consultorio) {
                citas.remove(i);
            }
        }
        try
        {
            hacerBackUp();
        } catch (IOException e) {
            throw e;
        }
    }
    
    public boolean anadirServicio(Servicio servicio) throws IOException {
        
        boolean puedeAgregar = false;
        if(!servicios.isEmpty()){
            for(int i = 0; i<servicios.size(); i++){
                if(!servicios.get(i).getNombre().equalsIgnoreCase(servicio.getNombre())){
                    puedeAgregar = true;
                } else{
                    puedeAgregar = false;
                    return puedeAgregar;
                }
            }
            if(puedeAgregar == true){
                servicios.add(servicio);
                try
                {
                    hacerBackUp();
                    puedeAgregar = true;
                    return puedeAgregar;
                } catch (IOException e) {
                    throw e;
                }
            }
            return puedeAgregar;
            
        } else{
            servicios.add(servicio);
            try
            {
                hacerBackUp();
                puedeAgregar = true;
                return puedeAgregar;
            } catch (IOException e) {
                throw e;
            }
        }
    }
    
    public void modificarServicio(int indentificador, Servicio servicio) throws IOException {
        
        servicios.set(indentificador,servicio);
        try
        {
            hacerBackUp();
        } catch (IOException e) {
            throw e;
        }
    }
    
    public void eliminarServicio(int indentificador) throws IOException {
        Servicio servicio = servicios.get(indentificador);
        servicios.remove(indentificador);
        
        for (int i=0; i<citas.size(); i++) {
            Citas cita = citas.get(i);
            if (cita.getServicioElegido() == servicio) {
                citas.remove(i);
            }
        }
        for (int i=0; i<consultorios.size(); i++) {
            Consultorios consultorio = consultorios.get(i);
            if (consultorio.getServicioAfiliado()== servicio) {
                consultorios.remove(i);
            }
        }
        
        Iterator i = medicos.entrySet().iterator();
        while(i.hasNext()) {
            HashMap.Entry <Integer, Medicos> mapa = (HashMap.Entry) i.next();
            Medicos medico = mapa.getValue();
            while (medico.getServicios().remove(servicio)){

            }
        }
        
        try
        {
            hacerBackUp();
        } catch (IOException e) {
            throw e;
        }
    }
    
    public boolean anadirCita(Citas cita) throws IOException {
        if (!citas.containsKey(cita.getNumeroReferencia())) {
            citas.put(cita.getNumeroReferencia(), cita);
            try
            {
                hacerBackUp();
                return true;
            } catch (IOException e) {
                throw e;
            }
        }
        return false;
    }
    
    public void modificarCita(int referenciaAnterior, Citas cita) throws IOException {
        citas.remove(referenciaAnterior);
        citas.put(cita.getNumeroReferencia(), cita);
        try
        {
            hacerBackUp();
        } catch (IOException e) {
            throw e;
        }
    }
    
    public void eliminarCita(int numeroReferencia) throws IOException {
        Citas cita = citas.get(numeroReferencia);
        citas.remove(numeroReferencia);
        
        try
        {
            hacerBackUp();
        } catch (IOException e) {
            throw e;
        }
    }
    
    public HashMap<Long, Afiliados> getAfiliados() {
        return afiliados;
    }

    public void setAfiliados(HashMap<Long, Afiliados> afiliados) {
        this.afiliados = afiliados;
    }

    public HashMap<Long, Medicos> getMedicos() {
        return medicos;
    }

    public void setMedicos(HashMap<Long, Medicos> medicos) {
        this.medicos = medicos;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public HashMap<String, Consultorios> getConsultorios() {
        return consultorios;
    }

    public void setConsultorios(HashMap<String, Consultorios> consultorios) {
        this.consultorios = consultorios;
    }

    public HashMap<Integer, Citas> getCitas() {
        return citas;
    }

    public void setCitas(HashMap<Integer, Citas> citas) {
        this.citas = citas;
    }
    
}
