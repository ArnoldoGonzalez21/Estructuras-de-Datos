package Interfaz;

import Clases.NodoB;
import edd_fase_2.Administrador;
import edd_fase_2.Registro;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
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
    NodoB usuarioActual;
    Registro registro;
    JButton btnEliminar, btnInsertar, btnCargaUsuario, btnModificar, btnLogOut, btnMostrarImagen, btnGenerarImagen;
    JLabel lblTitulo, lblGrafica;

    public VentanaAdministrador(Administrador administracion, NodoB usuarioActual, Registro registro) {
        this.administracion = administracion;
        this.usuarioActual = usuarioActual;
        this.registro = registro;
        AjustarVentana();
        componentes();
    }

    public void AjustarVentana() {
        setTitle("Ventana Administrador");
        setLayout(null);
        setSize(1200, 475);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Ventana Administrador", 25, 20, 300, 40, 20);
        btnCargaUsuario = this.tools.addButton("Cargar Clientes", 25, 70, 200, 30);
        btnInsertar = this.tools.addButton("Insertar Clientes", 25, 110, 200, 30);
        btnModificar = this.tools.addButton("Modificar Cliente", 25, 150, 200, 30);
        btnEliminar = this.tools.addButton("Eliminar Cliente", 25, 190, 200, 30);
        btnMostrarImagen = this.tools.addButton("Mostrar Imagen", 680, 375, 150, 25);
        btnGenerarImagen = this.tools.addButton("Generar Imagen", 515, 375, 150, 25);
        btnLogOut = tools.addButton("← Log Out", 1035, 15, 117, 20);
        add(lblTitulo);
        btnCargaUsuario.addActionListener(this);
        add(btnCargaUsuario);
        btnInsertar.addActionListener(this);
        add(btnInsertar);
        btnModificar.addActionListener(this);
        add(btnModificar);
        btnEliminar.addActionListener(this);
        add(btnEliminar);
        btnMostrarImagen.addActionListener(this);
        add(btnMostrarImagen);
        btnGenerarImagen.addActionListener(this);
        add(btnGenerarImagen);
        btnLogOut.addActionListener(this);
        add(btnLogOut);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnCargaUsuario) {
            String contenido = edd_fase_2.Archivo.leerArchivoJson(this);
            this.administracion.cargaMasivaCliente(contenido, this.registro);
        }
        if (AE.getSource() == this.btnInsertar) {
            this.setVisible(false);
//            new VentanaMotivo(this.administracion, this.tools);
            this.dispose();
        }

        if (AE.getSource() == this.btnModificar) {
//            this.administracion.borrarContenido();
//            String contenido = Archivo.leerArchivo(this);
//            this.administracion.cargaMasivaAtencion(contenido);
        }

        if (AE.getSource() == this.btnEliminar) {
            this.setVisible(false);
//            new VentanaResidencia(this.administracion, this.tools);
            this.dispose();
        }

        if (AE.getSource() == this.btnGenerarImagen) {
            this.administracion.generarB(this.usuarioActual, "ArbolB");
        }

        if (AE.getSource() == this.btnMostrarImagen) {
            lblGrafica = this.tools.addLabelImagen(275, 50, 850, 300);
            Image img = new ImageIcon("ArbolB.png").getImage();
            ImageIcon img2 = new ImageIcon(img.getScaledInstance(lblGrafica.getWidth(), lblGrafica.getHeight(), Image.SCALE_SMOOTH));
            lblGrafica.setIcon(img2);
            add(lblGrafica);
            repaint();
        }

        if (AE.getSource() == this.btnLogOut) {
            setVisible(false);
            new Autenticacion(this.registro, this.administracion);
            this.dispose();
        }
    }
}
