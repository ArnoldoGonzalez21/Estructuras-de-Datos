package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Arnoldo González
 */
public class Autenticacion extends JFrame implements ActionListener {

    JTextField txtDpi;
    JPasswordField txtContrasena;
    JButton btnIniciarSesion;
    JLabel lblTitulo, lblDpi, lblContra;
    edd_fase_2.Registro registro;
    Clases.Usuario usuarioActual;
    edd_fase_2.Administrador administracion;

    public Autenticacion(edd_fase_2.Registro registro, Clases.Usuario usuarioActual, edd_fase_2.Administrador administracion, Tools tools) {
        this.registro = registro;
        this.administracion = administracion;
        this.usuarioActual = usuarioActual;
        AjustarVentana();
        this.componentes(tools);
    }

    private void AjustarVentana() {
        setTitle("Login");
        setLayout(null);
        setSize(535, 270);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void componentes(Tools tools) {
        lblTitulo = tools.addLabelTitulo("Login", 255, 20, 400, 40, 20);
        lblDpi = tools.addLabel("Código: ", 40, 70, 200, 30, 15);
        txtDpi = tools.addTextField("", 140, 70, 312, 25);
        lblContra = tools.addLabel("Contraseña: ", 40, 100, 200, 30, 15);
        txtContrasena = tools.addPasswordField("", 140, 100, 312, 25);
        btnIniciarSesion = tools.addButton("Iniciar Sesión", 140, 140, 310, 30);
        add(lblTitulo);
        add(lblDpi);
        add(txtDpi);
        add(lblContra);
        add(txtContrasena);
        btnIniciarSesion.addActionListener(this);
        add(btnIniciarSesion);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
//        if (AE.getSource() == this.btnIniciarSesion) {
//            int posicionUser = 0, posicionUserAlumno = 0;
//            int cont = -1, cont2 = -1;
//            boolean acabo = false, acaboAlumno = false;
//            Clases.Usuario user = this.registro.validarUsuario(this.txtDpi.getText(), this.txtContrasena.getText());
//            Clases.Usuario userAlumno = this.registro.validarUsuarioAlumno(this.txtDpi.getText(), this.txtContrasena.getText());
//            if (user == null && userAlumno == null) {
//                JOptionPane.showMessageDialog(this, "Usuario o contraseña inválidos"); 
//            }
//            if (user != null) {
//                if (user.equals(registro.getAdministrador()) && userAlumno != null) {
//                    this.setVisible(true);
//                    this.dispose();
//                    new VentanaPrincipal(this.usuarioActual, this.registro, this.administracion, this.escuela);
//                    this.dispose();
//                }
//            }
//            if (user != null) {
//                for (int i = 0; i < registro.getUsuario().length; i++) {
//                    if (registro.getUsuario()[i] == null) {
//                        continue;
//                    } else if (user.equals(registro.getUsuario()[i]) && registro.getUsuario()[i] != null) {
//                        acabo = true;
//                        cont = registro.getUsuario()[i].getCodigo();
//                        this.setVisible(true);
//                        this.dispose();
//                        new VentanaCursosProfesor(this.usuarioActual, this.registro, posicionUser, this.administracion, this.escuela);
//                    }
//                    if (!acabo) {
//                        posicionUser++;
//                    }
//                }
//            }
//            if (userAlumno != null) {
//                for (int i = 0; i < registro.getUsuarioAlumno().length; i++) {
//                    if (registro.getUsuarioAlumno()[i] == null) {
//                        continue;
//                    } else if (userAlumno.equals(registro.getUsuarioAlumno()[i]) && registro.getUsuarioAlumno()[i] != null) {
//                        acaboAlumno = true;
//                        cont2 = registro.getUsuarioAlumno()[i].getCodigo();
//                        System.out.println("aca no creo");
//                        this.setVisible(true);
//                        this.dispose();
//                        new VentanaCursoAlumno(this.usuarioActual, this.registro, posicionUserAlumno, this.administracion, this.escuela);
//                    }
//                    if (!acaboAlumno) {
//                        posicionUserAlumno++;
//                    }
//                }
//            }
//        }
    }
}
