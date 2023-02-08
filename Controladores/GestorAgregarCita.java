

package Controladores;
/**
 *
 * @author Sergio Sánchez
 */

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import Clases.RespaldoDatos;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import Clases.Afiliados;
import Clases.Citas;
import Clases.Consultorios;
import Clases.Medicos;
import Clases.Servicio;
import Vista.CitasAfiliado;
import Vista.GestionCitas;
import Vista.AgregarCita;

public class GestorAgregarCita {
    
    private final AgregarCita vistaPlantillaCita;
    private final RespaldoDatos almacenamiento;
    private final String opcion;
    private final String motivoCita;
    private final long cedula;
    private final int numRef;
    private final HashMap<Long, Medicos> medicos;
    private final HashMap<String, Consultorios> consultorios;
    private ArrayList<Object[]> opcionesComboBox;
    private int referenciaCita;
    
    public GestorAgregarCita(AgregarCita vistaPlantillaCita, String opcion, String motivoCita, long cedula, int numRef, RespaldoDatos almacenamiento) {
        this.almacenamiento = almacenamiento;
        this.opcion = opcion;
        this.motivoCita = motivoCita;
        this.cedula = cedula;
        this.numRef = numRef;
        medicos = almacenamiento.getMedicos();
        consultorios = almacenamiento.getConsultorios();
        this.vistaPlantillaCita = vistaPlantillaCita;
        modificarPlantilla();
        if("Agendar".equals(opcion)){
            asignarReferenciaCita();
        }
        //Añadiendo Listeners
        this.vistaPlantillaCita.addBtnRegresarListener(new ManejadoraDeMouse());
        this.vistaPlantillaCita.addBtnAgendarListener(new ManejadoraDeMouse());
        this.vistaPlantillaCita.addBtnVerificarListener(new ManejadoraDeMouse());
        this.vistaPlantillaCita.addBtnAsignarListener(new ManejadoraDeMouse());
        this.vistaPlantillaCita.addMedicosComboListener(new ManejadoraDeLista());
    }
    
    public void modificarPlantilla(){
        switch (opcion) {
        
            case "Agendar" -> {
                plantillaAgendarCita();
            }
            case "Modificar" -> {
                plantillaModificarCita();
            }
            case "Eliminar" -> {
                plantillaEliminarCita();
            }
            case "Consultar" -> {
                plantillaConsultarCita();
            }
        }
    }
    
    public void plantillaAgendarCita(){
        
        //Ingresando nombre, cédula y motivo de cita en interfaz
        vistaPlantillaCita.getTxtNombre().setText(almacenamiento.getAfiliados().get(cedula).getNombre());
        vistaPlantillaCita.getTxtCedula().setText(String.valueOf(cedula));
        vistaPlantillaCita.getTxtServicio().setText(motivoCita);
    }
    
    public void plantillaModificarCita(){
        //Modificando título y botones
        vistaPlantillaCita.getLblTitulo().setText("Modificar Cita");
        vistaPlantillaCita.getBtnAgendar().setText("Modificar Cita");
        
        //Ingresando datos de cita en la interfaz
        vistaPlantillaCita.getTxtNombre().setText(almacenamiento.getAfiliados().get(cedula).getNombre());
        vistaPlantillaCita.getTxtCedula().setText(String.valueOf(cedula));
        vistaPlantillaCita.getTxtServicio().setText(motivoCita);
        vistaPlantillaCita.getTxtReferencia().setText(String.valueOf(numRef));
        
        //Estableciendo la fecha en el JDateChooser
        java.sql.Date miFecha = almacenamiento.getCitas().get(numRef).getFecha();
        java.util.Date  utilDate = new java.util.Date(miFecha.getTime());
        vistaPlantillaCita.getDateChooser().setDate(utilDate);   
        vistaPlantillaCita.getTxtReferencia().setEditable(true);
    }
    
    public void plantillaEliminarCita(){
        
        //Modificando título y botones
        vistaPlantillaCita.getLblTitulo().setText("Eliminar Cita");
        vistaPlantillaCita.getBtnAgendar().setText("Eliminar Cita");
        
        //Ingresando datos de cita en la interfaz
        vistaPlantillaCita.getTxtNombre().setText(almacenamiento.getAfiliados().get(cedula).getNombre());
        vistaPlantillaCita.getTxtCedula().setText(String.valueOf(cedula));
        vistaPlantillaCita.getTxtServicio().setText(motivoCita);
        vistaPlantillaCita.getTxtReferencia().setText(String.valueOf(numRef));
        vistaPlantillaCita.getTxtConsultorio().setText(almacenamiento.getCitas().get(numRef).getConsultorio().getIdentificador());
        //Estableciendo la fecha en el JDateChooser
        java.sql.Date miFecha = almacenamiento.getCitas().get(numRef).getFecha();
        java.util.Date  utilDate = new java.util.Date(miFecha.getTime());
        vistaPlantillaCita.getDateChooser().setDate(utilDate);
        
        String medicoYHora = almacenamiento.getCitas().get(numRef).getMedico().getNombre() + ", " + almacenamiento.getCitas().get(numRef).getHora().toString();
        vistaPlantillaCita.getComboMedico().getModel().setSelectedItem(medicoYHora);
        //Desabilitando componentes
        vistaPlantillaCita.getBtnVerificar().setEnabled(false);
        vistaPlantillaCita.getBtnAgendar().setEnabled(true);
        vistaPlantillaCita.getDateChooser().setEnabled(false);
        
        
    }
    
    public void plantillaConsultarCita(){
        
        //Modificando título y botones
        vistaPlantillaCita.getLblTitulo().setText("Consultar Cita");
        vistaPlantillaCita.getBtnAgendar().setVisible(false);
        
        //Ingresando datos de cita en la interfaz
        vistaPlantillaCita.getTxtNombre().setText(almacenamiento.getAfiliados().get(cedula).getNombre());
        vistaPlantillaCita.getTxtCedula().setText(String.valueOf(cedula));
        vistaPlantillaCita.getTxtServicio().setText(almacenamiento.getCitas().get(numRef).getServicioElegido().getNombre());
        vistaPlantillaCita.getTxtReferencia().setText(String.valueOf(numRef));
        vistaPlantillaCita.getTxtConsultorio().setText(almacenamiento.getCitas().get(numRef).getConsultorio().getIdentificador());
        //Estableciendo la fecha en el JDateChooser
        java.sql.Date miFecha = almacenamiento.getCitas().get(numRef).getFecha();
        java.util.Date  utilDate = new java.util.Date(miFecha.getTime());
        vistaPlantillaCita.getDateChooser().setDate(utilDate);
        
        String medicoYHora = almacenamiento.getCitas().get(numRef).getMedico().getNombre() + ", " + almacenamiento.getCitas().get(numRef).getHora().toString();
        vistaPlantillaCita.getComboMedico().getModel().setSelectedItem(medicoYHora);
        //Desabilitando componentes
        vistaPlantillaCita.getBtnVerificar().setEnabled(false);
        vistaPlantillaCita.getDateChooser().setEnabled(false);
        
        
    }
    
    class ManejadoraDeMouse extends MouseAdapter{
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            if (e.getSource() == vistaPlantillaCita.getBtnAgendar()&& "Agendar".equals(opcion) && vistaPlantillaCita.getBtnAgendar().isEnabled()){
                if (e.getButton() == 1){
                    agendarCita();
                }
            }
            
            if (e.getSource() == vistaPlantillaCita.getBtnAgendar()&& "Modificar".equals(opcion)){
                if (e.getButton() == 1){
                    modificarCita();
                }
            }
            if (e.getSource() == vistaPlantillaCita.getBtnAgendar()&& "Eliminar".equals(opcion)){
                if (e.getButton() == 1){
                    eliminarCita();
                }
            }

            if (e.getSource() == vistaPlantillaCita.getBtnRegresar() && !"Consultar".equals(opcion)){
                if (e.getButton() == 1){
                    irGestionCitasGUI();  
                }
            }
            
            if (e.getSource() == vistaPlantillaCita.getBtnRegresar() && "Consultar".equals(opcion)){
                if (e.getButton() == 1){
                    irCitasAfiliadoGUI();  
                }
            }
            
            if (e.getSource() == vistaPlantillaCita.getBtnVerificar() && vistaPlantillaCita.getBtnVerificar().isEnabled()){
                if (vistaPlantillaCita.getDateChooser().getDate() == null){
                    JOptionPane.showMessageDialog(null, "<html><p style = \" font:12px; \">Por favor seleccione una fecha</p></html>", "Aviso", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));            
                    return;
                }
                if(!verificarFechasAntiguas()){
                    JOptionPane.showMessageDialog(null, "<html><p style = \" font:12px; \">Seleccione sólo fechas a partir de la actual</p></html>", "Aviso", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
                    return;
                }
                
                if (e.getButton() == 1){
                    agregarOyente();
                    medicosDisponibles();
                }
            }
            
            if (e.getSource() == vistaPlantillaCita.getBtnAsignar() && vistaPlantillaCita.getBtnAsignar().isEnabled()){
                if (e.getButton() == 1){
                    asignarConsultorio(); 
                }
            }
        }
    }
    
    class ManejadoraDeLista implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource() == vistaPlantillaCita.getComboMedico()){
                vistaPlantillaCita.getTxtConsultorio().setText("");
                vistaPlantillaCita.getBtnAgendar().setEnabled(false);
            }
        }
    }
    
    public void agendarCita() {
       
        //Obteniendo los datos
        int numeroReferencia = Integer.parseInt(vistaPlantillaCita.getTxtReferencia().getText());
        Date date = vistaPlantillaCita.getDateChooser().getDate();
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        LocalTime hora = obtenerHoraEscogida();
        ArrayList <Servicio> servicios = almacenamiento.getServicios();
        Iterator i = servicios.iterator();
        Servicio servicio = null;
        while (i.hasNext()) {
           servicio = (Servicio) i.next();
           if (servicio.getNombre().equals(motivoCita)) {
               break;
           }
        }
        Afiliados afiliado = almacenamiento.getAfiliados().get(cedula);
        Consultorios consultorio = almacenamiento.getConsultorios().get(vistaPlantillaCita.getTxtConsultorio().getText());
        Medicos medico = obtenerMedicoEscogido();
        Citas cita = new Citas(numeroReferencia, fecha, hora, servicio, afiliado, consultorio, medico);
        try {
            almacenamiento.anadirCita(cita);
            JOptionPane.showMessageDialog(null, "<html><p style = \" font:12px; \">Cita agendada con éxito</p></html>", "Aviso", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
            irGestionCitasGUI();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "<html><p style = \" font:12px; \">Error al agendar cita: " + e.getMessage() + "</p></html>", "Aviso", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.errorIcon"));
        }
    }
    
    public void modificarCita() {
       
       //Obteniendo los datos
        int numeroReferencia = Integer.parseInt(vistaPlantillaCita.getTxtReferencia().getText());
        Date date = vistaPlantillaCita.getDateChooser().getDate();
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        LocalTime hora = obtenerHoraEscogida();
        ArrayList <Servicio> servicios = almacenamiento.getServicios();
        Iterator i = servicios.iterator();
        Servicio servicio = null;
        while (i.hasNext()) {
           servicio = (Servicio) i.next();
           if (servicio.getNombre().equals(motivoCita)) {
               break;
           }
        }
        Afiliados afiliado = almacenamiento.getAfiliados().get(cedula);
        Consultorios consultorio = almacenamiento.getConsultorios().get(vistaPlantillaCita.getTxtConsultorio().getText());
        Medicos medico = obtenerMedicoEscogido();
        Citas cita = new Citas(numeroReferencia, fecha, hora, servicio, afiliado, consultorio, medico);
        
        try {
            almacenamiento.modificarCita(numRef, cita);
            JOptionPane.showMessageDialog(null, "Cita actualizada con éxito", "Resultado de actualizar", JOptionPane.INFORMATION_MESSAGE);
            irGestionCitasGUI();
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + io, "Error", JOptionPane.ERROR_MESSAGE);

        } 
       
    }
    
    public void eliminarCita() {
       
       try {
           
           almacenamiento.eliminarCita(numRef);
           JOptionPane.showMessageDialog(null, "Cita eliminada con éxito", "Resultado de eliminar", JOptionPane.INFORMATION_MESSAGE);
           irGestionCitasGUI();
       } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + io, "Error", JOptionPane.ERROR_MESSAGE);
     
       }
    }
    
    // Actualiza el comboBox con los médicos que prestan el servicio requerido y sus horas disponibles
    public void medicosDisponibles(){
        vistaPlantillaCita.limpiarMedicosCombo();
        opcionesComboBox = new ArrayList();
        Iterator i = medicos.entrySet().iterator();
        ArrayList<Medicos> misMedicos = new ArrayList();
        
        while(i.hasNext()) {
            HashMap.Entry <Long, Medicos> mapa = (HashMap.Entry) i.next();
            Medicos medico = mapa.getValue();
            ArrayList<Servicio> servicioDelMedico = medico.getServicios();
            
            //Convirtiendo los servicios del médico a String[]
            for (int o = 0; o < servicioDelMedico.size(); o++){
                String servicio = "";
                servicio += servicioDelMedico.get(o);
                if (servicio.equals(motivoCita)) {
                    misMedicos.add(medico);
                }
            }
        }
        
        Iterator o = misMedicos.iterator();
        Date date = vistaPlantillaCita.getDateChooser().getDate();
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        while (o.hasNext()) {
            Medicos medico = (Medicos) o.next();
            ArrayList<LocalTime> horas = verificarHorarios(medico, fecha);
            for (LocalTime hora : horas) {
                vistaPlantillaCita.anadirMedicosCombo(medico.getNombre() + ", " + hora);
                Object[] medicoYHora = new Object[2];
                medicoYHora[0] = medico;
                medicoYHora[1] = hora;
                opcionesComboBox.add(medicoYHora);
            }
        }
        if (vistaPlantillaCita.getComboMedico().getModel().getSize() == 0) {
            JOptionPane.showMessageDialog(null, "<html><p style = \" font:12px; \">No hay disponibilidad para esa fecha.</p></html>", "Aviso", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
            vistaPlantillaCita.getComboMedico().setEnabled(false);
            vistaPlantillaCita.getBtnAsignar().setEnabled(false);
        } else {
            vistaPlantillaCita.getComboMedico().setEnabled(true);
            vistaPlantillaCita.getBtnAsignar().setEnabled(true);
        }
    }
    
    public Medicos obtenerMedicoEscogido(){
        int opcionElegida = vistaPlantillaCita.getComboMedico().getSelectedIndex();
        return (Medicos)opcionesComboBox.get(opcionElegida)[0];
    }
    
    //Asigna el consultorio de acuerdo a la hora escogida
    public void asignarConsultorio(){
        if(validarCamposVacios()){
            JOptionPane.showMessageDialog(null, "Llene todos los campos requeridos antes de continuar.", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //Asignando consultorios
        Iterator i = consultorios.entrySet().iterator();
        ArrayList<Consultorios> misConsultorios = new ArrayList();
        
        while(i.hasNext()) {
            HashMap.Entry <Long, Consultorios> mapa = (HashMap.Entry) i.next();
            Consultorios consultorio = mapa.getValue();
            Servicio servicioDelConsultorio = consultorio.getServicioAfiliado();
            
            //Convirtiendo los servicios del consultorio a String[]
            //String servicio = "";
            String servicio = servicioDelConsultorio.getNombre();
            if (servicio.equals(motivoCita)) {
                misConsultorios.add(consultorio);
            }
        }
        
        Iterator o = misConsultorios.iterator();
        Date date = vistaPlantillaCita.getDateChooser().getDate();
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        Consultorios consultorioDisponible = null;
        LocalTime hora = obtenerHoraEscogida();
        while (o.hasNext()) {
            Consultorios consultorio = (Consultorios) o.next();
            if (!verificarHorarios(consultorio, fecha, hora)) {
                vistaPlantillaCita.getTxtConsultorio().setText(consultorio.getIdentificador());
                vistaPlantillaCita.getBtnAgendar().setEnabled(true);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "<html><p style = \" font:12px; \">No hay disponibilidad para ese momento.</p></html>", "Aviso", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
    }
    
    public LocalTime obtenerHoraEscogida(){
        String opcionElegida = vistaPlantillaCita.getComboMedico().getModel().getSelectedItem().toString();
        int posicionDeComa = opcionElegida.indexOf(',');
        String horaElegida = opcionElegida.substring(posicionDeComa+2, opcionElegida.length());
        LocalTime lt = LocalTime.parse(horaElegida);
        return lt;
    }
    
    public ArrayList<LocalTime> verificarHorarios(Medicos medico, Date date) {
        HashMap <Integer, Citas> citas = almacenamiento.getCitas();
        Iterator i = citas.entrySet().iterator();
        ArrayList<LocalTime> horariosOcupados = new ArrayList();
        ArrayList<LocalTime> horariosPosibles = new ArrayList();
        ArrayList<LocalTime> horariosdisponibles = new ArrayList();
        for (int h = 7; h < 17; h++) {
            if (h != 12 && h != 13) {
                horariosPosibles.add(LocalTime.of(h, 0));
                horariosPosibles.add(LocalTime.of(h, 30));
            }
        }
        while (i.hasNext()) {
            HashMap.Entry <Integer, Citas> mapa = (HashMap.Entry) i.next();
            Citas cita = mapa.getValue();
            if (cita.getMedico().equals(medico) && cita.getFecha().toString().equals(date.toString())) {
                horariosOcupados.add(cita.getHora());
            }
        }
        for (LocalTime hora : horariosPosibles) {
            if (!horariosOcupados.contains(hora)) {
                horariosdisponibles.add(hora);
            }
        }
        return horariosdisponibles;
    }

    public boolean verificarHorarios(Consultorios consultorio, Date date, LocalTime hora) {
        HashMap <Integer, Citas> citas = almacenamiento.getCitas();
        Iterator i = citas.entrySet().iterator();
        
        while (i.hasNext()) {
            HashMap.Entry <Integer, Citas> mapa = (HashMap.Entry) i.next();
            Citas cita = mapa.getValue();
            if (cita.getConsultorio().equals(consultorio) && cita.getFecha().toString().equals(date.toString()) && cita.getHora().toString().equals(hora.toString())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean verificarFechasAntiguas() {
        boolean fechaValida = false;
        
        //Convirtiendo la fecha escogida (Date) a LocalDate
        Date date = vistaPlantillaCita.getDateChooser().getDate();
        long d = date.getTime();
        String miFecha = new java.sql.Date(d).toString();
        LocalDate fechaEscogida = LocalDate.parse(miFecha);

        //Obteniendo la fecha actual
        LocalDate fecha = LocalDate.now();
        if(fechaEscogida.isBefore(fecha)){
            vistaPlantillaCita.limpiarMedicosCombo();
            opcionesComboBox = new ArrayList();
            vistaPlantillaCita.getTxtConsultorio().setText("");
            vistaPlantillaCita.getComboMedico().setEnabled(false);
            vistaPlantillaCita.getBtnAsignar().setEnabled(false);
            vistaPlantillaCita.getBtnAgendar().setEnabled(false);
            fechaValida = false;
            return fechaValida;
        }
        fechaValida = true;
        return fechaValida;
    }
    
    public void agregarOyente() {
        vistaPlantillaCita.getDateChooser().addPropertyChangeListener(
            new java.beans.PropertyChangeListener() {

                @Override
                public void propertyChange(java.beans.PropertyChangeEvent evt) {
                    if (!evt.getOldValue().equals(evt.getNewValue())) {
                        vistaPlantillaCita.limpiarMedicosCombo();
                        opcionesComboBox = new ArrayList();
                        vistaPlantillaCita.getTxtConsultorio().setText("");
                        vistaPlantillaCita.getComboMedico().setEnabled(false);
                        vistaPlantillaCita.getBtnAsignar().setEnabled(false);
                        vistaPlantillaCita.getBtnAgendar().setEnabled(false);
                    }
                }
            });
    }
    
    public void asignarReferenciaCita() {
        int numeroDeReferencia = 1;
        while (almacenamiento.getCitas().containsKey(numeroDeReferencia)) {
            numeroDeReferencia += 1;
        }
        referenciaCita = numeroDeReferencia;
        vistaPlantillaCita.getTxtReferencia().setText(String.valueOf(referenciaCita));
    }
    
    public boolean validarCamposVacios(){
        boolean error = false;
        
        if(vistaPlantillaCita.getComboMedico().getModel().getSelectedItem() == null)
            error = true;
        return error;
    }
    
    public void irGestionCitasGUI() {
        
        GestionCitas vistaGestionCitas= new GestionCitas("Gestión de Citas", almacenamiento);
        vistaPlantillaCita.dispose();
    }
    
    public void irCitasAfiliadoGUI() {
        
        CitasAfiliado vistaCitasAfiliado= new CitasAfiliado("Gestión de Citas", cedula, almacenamiento);
        vistaPlantillaCita.dispose();
    }
}

