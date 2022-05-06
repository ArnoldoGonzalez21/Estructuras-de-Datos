package Interfaz;

import Clases.Cliente;
import edd_fase_3.Administrador;
import edd_fase_3.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Arnoldo González
 */
public class VentanaAdministrador extends JFrame implements ActionListener {

    Tools tools;
    Administrador administracion;
    Cliente usuarioActual;
    Registro registro;
    JButton btnCargaUsuario, btnModificar, btnLogOut;
    JLabel lblTitulo;

    public VentanaAdministrador(Administrador administracion, Cliente usuarioActual, Registro registro, Tools tools) {
        this.administracion = administracion;
        this.usuarioActual = usuarioActual;
        this.registro = registro;
        this.tools = tools;
        this.AjustarVentana();
        this.componentes();
    }

    public void AjustarVentana() {
        setTitle("Ventana Administrador");
        setLayout(null);
        setSize(500, 225);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Ventana Administrador", 25, 20, 300, 40, 20);
        btnCargaUsuario = this.tools.addButton("Cargar Clientes", 25, 70, 200, 30);
        btnModificar = this.tools.addButton("Crear/Modificar/Eliminar Cliente", 25, 110, 200, 30);
        btnLogOut = tools.addButton("← Log Out", 335, 15, 117, 20);
        add(lblTitulo);
        btnCargaUsuario.addActionListener(this);
        add(btnCargaUsuario);
        btnModificar.addActionListener(this);
        add(btnModificar);
        btnLogOut.addActionListener(this);
        add(btnLogOut);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnCargaUsuario) {
            String contenido = edd_fase_3.Archivo.leerArchivoJson(this);
            this.administracion.cargaMasivaCliente(contenido, this.registro);
            this.usuarioActual.cargoCliente = true;
        }

        if (AE.getSource() == this.btnModificar) {
            this.setVisible(false);
            new VentanaModificarCliente(this.administracion, this.usuarioActual, this.registro, this.tools);
            this.dispose();
        }

        if (AE.getSource() == this.btnLogOut) {
            setVisible(false);
            new Autenticacion(this.registro, this.administracion);
            this.dispose();
        }
    }
}
