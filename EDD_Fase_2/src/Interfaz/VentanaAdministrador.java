package Interfaz;

import edd_fase_2.Administrador;
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

    Tools tools = new Tools();
    Administrador administracion;
    JButton btnEliminar, btnInsertar, btnCargaUsuario, btnModificar, btnLogOut;
    JLabel lblTitulo;

    public VentanaAdministrador(Administrador administracion) {
        this.administracion = administracion;
        AjustarVentana();
        componentes();
    }

    public void AjustarVentana() {
        setTitle("Ventana Administrador");
        setLayout(null);
        setSize(490, 275);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Administrador", 25, 10, 300, 40, 15);
        btnCargaUsuario = this.tools.addButton("Cargar Usuarios", 25, 60, 200, 30);
        btnInsertar = this.tools.addButton("Insertar", 25, 100, 200, 30);
        btnModificar = this.tools.addButton("Modificar", 25, 140, 200, 30);
        btnEliminar = this.tools.addButton("Eliminar", 25, 180, 200, 30);
        btnLogOut = tools.addButton("← Log Out", 225, 25, 117, 20);
        add(lblTitulo);
        btnEliminar.addActionListener(this);
        add(btnEliminar);
        btnInsertar.addActionListener(this);
        add(btnInsertar);
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
        if (AE.getSource() == this.btnEliminar) {
            this.setVisible(false);
//            new VentanaResidencia(this.administracion, this.tools);
            this.dispose();
        }

        if (AE.getSource() == this.btnInsertar) {
            this.setVisible(false);
//            new VentanaMotivo(this.administracion, this.tools);
            this.dispose();
        }

        if (AE.getSource() == this.btnCargaUsuario) {
//            this.administracion.borrarContenido();
//            String contenido = Archivo.leerArchivo(this);
//            this.administracion.cargaMasivaTaller(contenido);
        }

        if (AE.getSource() == this.btnModificar) {
//            this.administracion.borrarContenido();
//            String contenido = Archivo.leerArchivo(this);
//            this.administracion.cargaMasivaAtencion(contenido);
        }
    }
}
