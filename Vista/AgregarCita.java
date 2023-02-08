
package Vista;
import com.toedter.calendar.JDateChooser;
import Controladores.GestorAgregarCita;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Clases.RespaldoDatos;

/**
 *
 * @author Sergio Sánchez
 */
public class AgregarCita extends javax.swing.JFrame {
    
   private DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();

    /**
     * Creates new form AgregarCita
     */
    public AgregarCita(String titulo, String opcion, String motivoCita, long cedula, int numRef, RespaldoDatos almacenamiento) {
        initComponents();
        setVisible(true);
       GestorAgregarCita gestorCita = new GestorAgregarCita(this, opcion, motivoCita, cedula, numRef, almacenamiento);
        setTitle(titulo);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblIdentificacion = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtIdentificacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblServicio = new javax.swing.JLabel();
        txtServicio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblEscoja = new javax.swing.JLabel();
        btnComprobar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblMedico = new javax.swing.JLabel();
        btnAsignar = new javax.swing.JButton();
        lblConsultorio = new javax.swing.JLabel();
        txtConsultorio = new javax.swing.JTextField();
        lblReferencia = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        dateChooser = new com.toedter.calendar.JDateChooser();
        comboMedico = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setText("Agregar cita");

        jLabel2.setText("Datos del afiliado:");

        lblNombre.setText("Nombre");

        lblIdentificacion.setText("Identificacion");

        jLabel5.setText("Servicio requerido:");

        lblServicio.setText("Servicio");

        jLabel7.setText("Fecha de la cita:");

        lblEscoja.setText("Escoja una fecha:");

        btnComprobar.setText("Comprobar");

        jLabel9.setText("Detalles de la cita:");

        lblMedico.setText("Médico y hora de la cita:");

        btnAsignar.setText("Asignar");

        lblConsultorio.setText("Consultorio");

        lblReferencia.setText("Número de referencia:");

        btnRegresar.setText("Regresar");

        btnAgregar.setText("Agregar cita");

        comboMedico.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboMedico.setModel(modeloCombo);
        comboMedico.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre)
                                    .addComponent(lblIdentificacion)
                                    .addComponent(jLabel5)
                                    .addComponent(lblServicio)
                                    .addComponent(jLabel7)
                                    .addComponent(lblEscoja)
                                    .addComponent(jLabel9)
                                    .addComponent(lblMedico)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(lblConsultorio))
                                    .addComponent(lblReferencia))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                            .addComponent(txtIdentificacion)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtReferencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                                        .addComponent(txtConsultorio, javax.swing.GroupLayout.Alignment.LEADING))
                                                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(comboMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(39, 39, 39)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnComprobar, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                                    .addComponent(btnAsignar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdentificacion)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jLabel5)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblServicio)
                    .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jLabel7)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnComprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(lblEscoja))))
                .addGap(28, 28, 28)
                .addComponent(jLabel9)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedico)
                    .addComponent(btnAsignar)
                    .addComponent(comboMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConsultorio))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReferencia)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     public void anadirMedicosCombo(String fila){
        modeloCombo.addElement(fila);
    }
    public void limpiarMedicosCombo(){
        modeloCombo.removeAllElements();
    }
    public JLabel getLblTitulo() {
        return lblTitulo;
    }
    /**
     * @param lblTitulo
     */
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }    
    public JLabel getLblNombre() {
        return lblNombre;
    }
    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }   
    public JLabel getLblCedula() {
        return lblIdentificacion;
    }
    public void setLblCedula(JLabel lblCedula) {
        this.lblIdentificacion = lblCedula;
    }    
    public JLabel getlblServicio() {
        return lblServicio;
    }
    public void setLblServicio(JLabel lblServicio) {
        this.lblServicio = lblServicio;
    }   
    public JLabel getlblDia() {
        return lblServicio;
    }
    public JLabel getlblMes() {
        return lblServicio;
    }
    public JButton getBtnRegresar() {
        return btnRegresar;
    }
    public void setBtnRegresar(JButton btnRegresar) {
        this.btnRegresar = btnRegresar;
    }
    public JButton getBtnAgendar() {
        return btnAgregar;
    }
    public void setBtnAgendar(JButton btnAgendar) {
        this.btnAgregar = btnAgendar;
    }
    public JButton getBtnVerificar() {
        return btnComprobar;
    }
    public void setBtnVerificar(JButton btnVerificar) {
        this.btnComprobar = btnVerificar;
    }
    public JComboBox<String> getComboMedico() {
        return comboMedico;
    }
    public void setComboMedico(JComboBox<String> comboMedico) {
        this.comboMedico = comboMedico;
    }
    public JTextField getTxtCedula() {
        return txtIdentificacion;
    }
    public void setTxtCedula(JTextField txtCedula) {
        this.txtIdentificacion = txtCedula;
    }
    public JTextField getTxtConsultorio() {
        return txtConsultorio;
    }
    public void setTxtConsultorio(JTextField txtConsultorio) {
        this.txtConsultorio = txtConsultorio;
    }
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtReferencia() {
        return txtReferencia;
    }

    public void setTxtReferencia(JTextField txtReferencia) {
        this.txtReferencia = txtReferencia;
    }

    public JTextField getTxtServicio() {
        return txtServicio;
    }

    public void setTxtServicio(JTextField txtServicio) {
        this.txtServicio = txtServicio;
    }

    public JDateChooser getDateChooser() {
        return dateChooser;
    }

    public void setDateChooser(JDateChooser dateChooser) {
        this.dateChooser = dateChooser;
    }

    public JButton getBtnAsignar() {
        return btnAsignar;
    }

    public void setBtnAsignar(JButton btnAsignar) {
        this.btnAsignar = btnAsignar;
    }
    
    public void addBtnRegresarListener(MouseListener listenerBotones){
        btnRegresar.addMouseListener(listenerBotones);
    }
    
    public void addBtnVerificarListener(MouseListener listenerBotones){
        btnComprobar.addMouseListener(listenerBotones);
    }
    public void addBtnAgendarListener(MouseListener listenerBotones){
        btnAgregar.addMouseListener(listenerBotones);
    }
    
    public void addBtnAsignarListener(MouseListener listenerBotones){
        btnAsignar.addMouseListener(listenerBotones);
    }
    public void addDateChooserListener (MouseListener listenerBotones) {
        dateChooser.addMouseListener(listenerBotones);
    }
    public void addMedicosComboListener(ItemListener listenerBotones){
        comboMedico.addItemListener(listenerBotones);
    }

    
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnComprobar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> comboMedico;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblConsultorio;
    private javax.swing.JLabel lblEscoja;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblMedico;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblReferencia;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtConsultorio;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtServicio;
    // End of variables declaration//GEN-END:variables
}