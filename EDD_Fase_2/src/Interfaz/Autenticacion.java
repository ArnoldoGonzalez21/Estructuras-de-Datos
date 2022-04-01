package Interfaz;

import Clases.NodoB;
import edd_fase_2.Administrador;
import edd_fase_2.Registro;
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

    Tools tools = new Tools();
    Registro registro;
    NodoB usuarioActual;
    Administrador administracion;
    JTextField txtDpi;
    JPasswordField txtContrasena;
    JButton btnIniciarSesion;
    JLabel lblTitulo, lblDpi, lblContra;

    public Autenticacion(Registro registro, Administrador administracion) {
        this.registro = registro;
        this.administracion = administracion;
        this.AjustarVentana();
        this.componentes();
    }

    private void AjustarVentana() {
        setTitle("Login");
        setLayout(null);
        setSize(535, 270);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Login", 255, 20, 400, 40, 20);
        lblDpi = this.tools.addLabel("DPI: ", 40, 70, 200, 30, 15);
        txtDpi = this.tools.addTextField("", 140, 70, 312, 25);
        lblContra = this.tools.addLabel("Contraseña: ", 40, 100, 200, 30, 15);
        txtContrasena = this.tools.addPasswordField("", 140, 100, 312, 25);
        btnIniciarSesion = this.tools.addButton("Iniciar Sesión", 140, 140, 310, 30);
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
        if (AE.getSource() == this.btnIniciarSesion) {
            Clases.NodoB user = this.registro.validarUsuario(this.txtDpi.getText(), this.txtContrasena.getText());
            if (user == null) {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña inválida");
            }
            if (user != null) {
                if (user.equals(registro.getAdministrador())) {
                    this.setVisible(true);
                    this.dispose();
                    new VentanaAdministrador(this.administracion, user, this.registro, this.tools);
                    this.dispose();
                } else {
                    Clases.NodoB actual = this.registro.getCabezaUsuario();
                    while (actual != null) {
                        if (user.equals(actual)) {
                            this.setVisible(true);
                            this.dispose();
                            new VentanaCliente(this.administracion, user, this.registro);
                            this.dispose();
                            break;
                        }
                        actual = actual.getSiguiente();
                    }
                }
            }
        }
    }
}
