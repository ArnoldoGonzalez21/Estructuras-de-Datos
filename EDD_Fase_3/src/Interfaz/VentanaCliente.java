package Interfaz;

import Clases.Cliente;
import edd_fase_3.Registro;
import edd_fase_3.Administrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Arnoldo González
 */
public class VentanaCliente extends JFrame implements ActionListener {

    Tools tools = new Tools();
    Administrador administracion;
    Cliente usuarioActual;
    Registro registro;
    JButton btnLogOut;
    JLabel lblTitulo, lblNombre, lblUsuario, lblDpi;

    public VentanaCliente(Administrador administracion, Cliente usuarioActual, Registro registro) {
        this.administracion = administracion;
        this.usuarioActual = usuarioActual;
        this.registro = registro;
        this.AjustarVentana();
        this.componentes();
    }

    public void AjustarVentana() {
        setTitle("Ventana Cliente");
        setLayout(null);
        setSize(300, 275);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Datos Cliente:", 25, 50, 300, 30, 15);
        lblUsuario = this.tools.addLabel("Nombre Usuario: " + this.usuarioActual.getUsuario(), 25, 85, 200, 30, 15);
        lblNombre = this.tools.addLabel("Nombre: " + this.usuarioActual.getNombre(), 25, 120, 200, 30, 15);
        lblDpi = this.tools.addLabel("DPI: " + this.usuarioActual.getDpi(), 25, 155, 200, 30, 15);
        btnLogOut = tools.addButton("← Log Out", 135, 15, 117, 20);
        add(lblTitulo);
        add(lblUsuario);
        add(lblNombre);
        add(lblDpi);
        btnLogOut.addActionListener(this);
        add(btnLogOut);

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnLogOut) {
            setVisible(false);
            new Autenticacion(this.registro, this.administracion);
            this.dispose();
        }
    }
}
