package Interfaz;

import edd_fase_2.Administrador;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Arnoldo González
 */
public class VentanaClliente extends JFrame implements ActionListener {

    Tools tools = new Tools();
    Administrador administracion;
    JButton btnMostrarImagen, btnCargaAlbum, btnCargaCapa, btnCargaImagen, btnLogOut, btnGenerarImagen, btnVentanaEstructura, btnVentanaCapa;
    JLabel lblTitulo, lblSubTitulo, lblGrafica;
    JComboBox comboCapa;
    String nombre = "";

    public VentanaClliente(Administrador administracion) {
        this.administracion = administracion;
        AjustarVentana();
        componentes();
    }

    public void AjustarVentana() {
        setTitle("Ventana Cliente");
        setLayout(null);
        setSize(650, 475);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Ventana Cliente", 25, 50, 300, 40, 20);
        btnCargaCapa = this.tools.addButton("Cargar Capa", 25, 100, 200, 30);
        btnCargaAlbum = this.tools.addButton("Cargar Album", 25, 140, 200, 30);
        btnCargaImagen = this.tools.addButton("Cargar Imagen", 25, 180, 200, 30);
        btnMostrarImagen = this.tools.addButton("Mostrar Imagen", 265, 375, 150, 25);
        btnGenerarImagen = this.tools.addButton("Generar Imagen", 430, 375, 150, 25);
        btnLogOut = tools.addButton("← Log Out", 485, 15, 117, 20);
        btnVentanaEstructura = tools.addButton("Graficar Estructuras", 15, 15, 175, 20);
        btnVentanaCapa = tools.addButton("Graficar Capa", 200, 15, 117, 20);        
        lblSubTitulo = this.tools.addLabel("Seleccione la imagen:", 25, 245, 300, 40, 13);
        add(lblTitulo);
        add(lblSubTitulo);
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
        btnVentanaEstructura.addActionListener(this);
        add(btnVentanaEstructura);
        btnVentanaCapa.addActionListener(this);
        add(btnVentanaCapa);
        agregarCombo();
        repaint();
    }

    public void agregarCombo() {
        String[] opciones = this.administracion.capasCombo();
        comboCapa = this.tools.addComboBox(opciones, 25, 275, 200, 25);
        add(comboCapa);
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnGenerarImagen) {
            // if (nombre != "") {
            String contenido = this.administracion.matriz();
            this.administracion.crearGrafico(contenido, "imagen");
//            this.administracion.generarAbb();
            //}
        }
        if (AE.getSource() == this.btnMostrarImagen) {
            // if (nombre != "") {
            lblGrafica = this.tools.addLabelImagen(275, 50, 300, 300);
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
        if (AE.getSource() == this.btnVentanaEstructura) {
            this.setVisible(false);
            new VentanaEstructura(this.administracion, this.tools);
            this.dispose();
        }
        if (AE.getSource() == this.btnVentanaCapa) {
            this.setVisible(false);
            new VentanaCapa(this.administracion, this.tools);
            this.dispose();
        }
    }

}
