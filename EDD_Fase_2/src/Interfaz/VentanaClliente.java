package Interfaz;

import edd_fase_2.Administrador;
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
public class VentanaClliente extends JFrame implements ActionListener {

    Tools tools = new Tools();
    Administrador administracion;
    JButton btnMostrarImagen, btnCargaAlbum, btnCargaCapa, btnCargaImagen, btnLogOut, btnGenerarImagen;
    JLabel lblTitulo, lblGrafica;
    String nombre = "";

    public VentanaClliente(Administrador administracion) {
        this.administracion = administracion;
        AjustarVentana();
        componentes();
    }

    public void AjustarVentana() {
        setTitle("Ventana Cliente");
        setLayout(null);
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Cliente", 25, 10, 300, 40, 15);
        btnCargaCapa = this.tools.addButton("Cargar Capa", 25, 60, 200, 30);
        btnCargaAlbum = this.tools.addButton("Cargar Album", 25, 100, 200, 30);
        btnCargaImagen = this.tools.addButton("Cargar Imagen", 25, 140, 200, 30);
        btnMostrarImagen = this.tools.addButton("Mostrar Imagen", 25, 180, 200, 30);
        btnGenerarImagen = this.tools.addButton("Generar Imagen", 25, 220, 200, 30);
        btnLogOut = tools.addButton("← Log Out", 225, 25, 117, 20);
        add(lblTitulo);
        btnMostrarImagen.addActionListener(this);
        add(btnMostrarImagen);
        btnCargaAlbum.addActionListener(this);
        add(btnCargaAlbum);
        btnCargaCapa.addActionListener(this);
        add(btnCargaCapa);
        btnCargaImagen.addActionListener(this);
        add(btnCargaImagen);
        btnGenerarImagen.addActionListener(this);
        add(btnGenerarImagen);
        btnLogOut.addActionListener(this);
        add(btnLogOut);
        repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnGenerarImagen) {
            // if (nombre != "") {
            this.administracion.crearGrafico(this.administracion.matriz(), "imagen");

            //}
        }
        if (AE.getSource() == this.btnMostrarImagen) {
            // if (nombre != "") {
            lblGrafica = this.tools.addLabelImagen(300, 25, 300, 300);
            Image img = new ImageIcon("imagen.png").getImage();
            ImageIcon img2 = new ImageIcon(img.getScaledInstance(lblGrafica.getWidth(), lblGrafica.getHeight(), Image.SCALE_SMOOTH));
            lblGrafica.setIcon(img2);
            add(lblGrafica);
            repaint();
            //}
        }

        if (AE.getSource() == this.btnCargaAlbum) {
            String contenido = edd_fase_2.Archivo.leerArchivoJson(this);
            this.administracion.cargaMasivaAlbum(contenido);
        }

        if (AE.getSource() == this.btnCargaCapa) {
            String contenido = edd_fase_2.Archivo.leerArchivoJson(this);
            this.administracion.cargaMasivaCapas(contenido);
        }

        if (AE.getSource() == this.btnCargaImagen) {
            String contenido = edd_fase_2.Archivo.leerArchivoJson(this);
            this.administracion.cargaMasivaImagen(contenido);
        }
    }

}
