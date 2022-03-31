package Interfaz;

import Clases.NodoB;
import edd_fase_2.Administrador;
import edd_fase_2.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Arnoldo González
 */
public class VentanaModificarCliente extends JFrame implements ActionListener {

    Registro registro;
    Administrador administracion;
    NodoB usuarioActual;
    Tools tools;
    JLabel lblTitulo, lblSubTitulo, lblNombre, lblDpi, lblPassword;
    JTextField txtNombre, txtDpi, txtPassword;
    JButton btnRegresar, btnEliminar, btnModificar, btnBuscar;
    JComboBox comboCliente;
    String dpiViejo = "";

    public VentanaModificarCliente(Administrador administracion, NodoB usuarioActual, Registro registro, Tools tools) {
        this.registro = registro;
        this.usuarioActual = usuarioActual;
        this.administracion = administracion;
        this.tools = tools;
        this.AjustarVentana();
        this.componentes();
    }

    private void AjustarVentana() {
        setTitle("Ventana Modificar/Eliminar");
        setLayout(null);
        setSize(450, 375);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Modificar / Elimnar Alumno", 20, 10, 300, 40, 15);
        lblSubTitulo = this.tools.addLabel("Seleccione el DPI del Cliente:", 25, 50, 300, 40, 13);
        lblDpi = this.tools.addLabel("DPI: ", 50, 140, 200, 30, 15);
        txtDpi = this.tools.addTextField("", 150, 140, 200, 25);
        lblNombre = this.tools.addLabel("Nombre: ", 50, 180, 200, 30, 15);
        txtNombre = this.tools.addTextField("", 150, 180, 200, 25);
        lblPassword = this.tools.addLabel("Contraseña: ", 50, 220, 200, 30, 15);
        txtPassword = this.tools.addTextField("", 150, 220, 200, 25);
        btnModificar = this.tools.addButton("Modificar", 100, 275, 100, 30);
        btnEliminar = this.tools.addButton("Eliminar", 225, 275, 100, 30);
        btnRegresar = tools.addButton("← Regresar", 300, 15, 117, 20);
        btnBuscar = this.tools.addButton("Buscar", 250, 90, 100, 26);
        add(lblTitulo);
        add(lblSubTitulo);
        add(lblNombre);
        add(lblDpi);
        add(lblPassword);
        add(txtNombre);
        add(txtDpi);
        add(txtPassword);
        btnModificar.addActionListener(this);
        add(btnModificar);
        btnEliminar.addActionListener(this);
        add(btnEliminar);
        btnRegresar.addActionListener(this);
        add(btnRegresar);
        btnBuscar.addActionListener(this);
        add(btnBuscar);
        if (this.usuarioActual.cargoCliente) {
            agregarCombo();
        }
        repaint();
    }

    public void agregarCombo() {
        String[] opciones = this.administracion.opcionesUser(this.registro);
        comboCliente = this.tools.addComboBox(opciones, 25, 90, 200, 25);
        add(comboCliente);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnModificar) {
            if (this.usuarioActual.cargoCliente) {
                this.administracion.modificarUser(this.registro, this.dpiViejo, this.txtDpi.getText(), this.txtNombre.getText(), this.txtPassword.getText());
                this.setVisible(false);
                new VentanaModificarCliente(this.administracion, this.usuarioActual, this.registro, this.tools);
                this.dispose();
            }
        }

        if (AE.getSource() == this.btnEliminar) {
            if (!this.txtDpi.getText().equals("") && !this.txtNombre.getText().equals("") && !this.txtPassword.getText().equals("")) {
                boolean elimino = this.administracion.eliminarUsuario(this.usuarioActual, this.registro, comboCliente.getSelectedItem().toString());
                if (elimino) {
                    JOptionPane.showMessageDialog(this, "Usuario Eliminado");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el Usuario");
                }
                this.setVisible(false);
                new VentanaModificarCliente(this.administracion, this.usuarioActual, this.registro, this.tools);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Buscar el Cliente a eliminar");
            }
        }

        if (AE.getSource() == this.btnBuscar) {
            if (this.usuarioActual.cargoCliente) {
                NodoB user = this.administracion.buscarUser(this.registro, comboCliente.getSelectedItem().toString());
                if (user != null) {
                    this.dpiViejo = user.getDpi();
                    txtDpi.setText(user.getDpi());
                    txtNombre.setText(user.getNombre());
                    txtPassword.setText(user.getContrasena());
                }
            }
        }

        if (AE.getSource() == this.btnRegresar) {
            setVisible(false);
            new VentanaAdministrador(this.administracion, this.usuarioActual, this.registro);
            this.dispose();
        }
    }
}
