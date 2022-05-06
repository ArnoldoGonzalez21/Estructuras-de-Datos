package Interfaz;

import Clases.Cliente;
import edd_fase_3.Administrador;
import edd_fase_3.Registro;
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
    Cliente usuarioActual;
    Tools tools;
    JLabel lblTitulo, lblSubTitulo, lblNombre, lblDpi, lblPassword, lblUsuario, lblCorreo, lblTelefono, lblDireccion, lblIdMun;
    JTextField txtNombre, txtDpi, txtPassword, txtUsuario, txtCorreo, txtTelefono, txtDireccion, txtIdMun;
    JButton btnRegresar, btnEliminar, btnModificar, btnBuscar, btnCrear;
    JComboBox comboCliente;

    public VentanaModificarCliente(Administrador administracion, Cliente usuarioActual, Registro registro, Tools tools) {
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
        setSize(450, 575);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Crear / Modificar / Eliminar", 20, 10, 375, 40, 15);
        lblSubTitulo = this.tools.addLabel("Seleccione el nombre de Usuario del Cliente:", 25, 50, 350, 40, 13);
        lblUsuario = this.tools.addLabel("Usuario: ", 50, 140, 200, 30, 15);
        txtUsuario = this.tools.addTextField("", 150, 140, 200, 25);
        lblNombre = this.tools.addLabel("Nombre: ", 50, 180, 200, 30, 15);
        txtNombre = this.tools.addTextField("", 150, 180, 200, 25);
        lblPassword = this.tools.addLabel("Contraseña: ", 50, 220, 200, 30, 15);
        txtPassword = this.tools.addTextField("", 150, 220, 200, 25);
        lblDpi = this.tools.addLabel("DPI: ", 50, 260, 200, 30, 15);
        txtDpi = this.tools.addTextField("", 150, 260, 200, 25);
        lblCorreo = this.tools.addLabel("Correo: ", 50, 300, 200, 30, 15);
        txtCorreo = this.tools.addTextField("", 150, 300, 200, 25);
        lblTelefono = this.tools.addLabel("Telefono: ", 50, 340, 200, 30, 15);
        txtTelefono = this.tools.addTextField("", 150, 340, 200, 25);
        lblDireccion = this.tools.addLabel("Dirección: ", 50, 380, 200, 30, 15);
        txtDireccion = this.tools.addTextField("", 150, 380, 200, 25);
        lblIdMun = this.tools.addLabel("Id Municipio: ", 50, 420, 200, 30, 15);
        txtIdMun = this.tools.addTextField("", 150, 420, 200, 25);

        btnCrear = this.tools.addButton("Crear", 50, 475, 100, 30);
        btnModificar = this.tools.addButton("Modificar", 175, 475, 100, 30);
        btnEliminar = this.tools.addButton("Eliminar", 300, 475, 100, 30);
        btnRegresar = tools.addButton("← Regresar", 300, 15, 117, 20);
        btnBuscar = this.tools.addButton("Buscar", 250, 90, 100, 26);
        add(lblTitulo);
        add(lblSubTitulo);
        add(lblNombre);
        add(lblDpi);
        add(lblPassword);
        add(lblUsuario);
        add(lblCorreo);
        add(lblTelefono);
        add(lblDireccion);
        add(lblIdMun);
        add(txtNombre);
        add(txtDpi);
        add(txtPassword);
        add(txtUsuario);
        add(txtCorreo);
        add(txtTelefono);
        add(txtDireccion);
        add(txtIdMun);
        btnCrear.addActionListener(this);
        add(btnCrear);
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
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnModificar) {
            if (this.usuarioActual.cargoCliente) {
                this.administracion.modificarUser(this.registro, this.txtDpi.getText(), this.txtUsuario.getText(), this.txtNombre.getText(), this.txtPassword.getText(), this.txtTelefono.getText(), this.txtDireccion.getText(), Integer.parseInt(this.txtIdMun.getText()), this.txtCorreo.getText());
                this.setVisible(false);
                new VentanaModificarCliente(this.administracion, this.usuarioActual, this.registro, this.tools);
                this.dispose();
            }
        }

        if (AE.getSource() == this.btnEliminar) {
            if (!this.txtDpi.getText().equals("") && !this.txtNombre.getText().equals("") && !this.txtPassword.getText().equals("") && !this.txtUsuario.getText().equals("")) {
                boolean elimino = this.administracion.eliminarUsuario(this.registro, comboCliente.getSelectedItem().toString());
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
                Cliente user = this.administracion.buscarUser(this.registro, comboCliente.getSelectedItem().toString());
                txtUsuario.setEditable(true);
                if (user != null) {
                    txtDpi.setText(user.getDpi());
                    txtNombre.setText(user.getNombre());
                    txtPassword.setText(user.getContrasena());
                    txtUsuario.setText(user.getUsuario());
                    txtTelefono.setText(user.getTelefono());
                    txtDireccion.setText(user.getDireccion());
                    txtCorreo.setText(user.getCorreo());
                    txtIdMun.setText(String.valueOf(user.getIdMunicipio()));
                    txtUsuario.setEditable(false);
                }
            }
        }

        if (AE.getSource() == this.btnCrear) {
            if (!this.txtDpi.getText().equals("") && !this.txtNombre.getText().equals("") && !this.txtPassword.getText().equals("")
                    && !this.txtUsuario.getText().equals("") && !this.txtTelefono.getText().equals("") && !this.txtDireccion.getText().equals("")
                    && !this.txtCorreo.getText().equals("") && !this.txtIdMun.getText().equals("")) {

                boolean creo = this.administracion.crearUser(this.registro, this.txtDpi.getText(), this.txtUsuario.getText(), this.txtNombre.getText(),
                        this.txtPassword.getText(), this.txtTelefono.getText(), this.txtDireccion.getText(),
                        Integer.parseInt(this.txtIdMun.getText()), this.txtCorreo.getText());
                if (creo) {
                    this.usuarioActual.cargoCliente = true;
                    this.setVisible(false);
                    new VentanaModificarCliente(this.administracion, this.usuarioActual, this.registro, this.tools);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error en los datos ingresados");
                }
            }
        }

        if (AE.getSource() == this.btnRegresar) {
            setVisible(false);
            new VentanaAdministrador(this.administracion, this.usuarioActual, this.registro, this.tools);
            this.dispose();
        }
    }
}
