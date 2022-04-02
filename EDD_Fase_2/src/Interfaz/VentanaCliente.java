package Interfaz;

import Clases.NodoB;
import edd_fase_2.Administrador;
import edd_fase_2.Registro;
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
public class VentanaCliente extends JFrame implements ActionListener {

    Tools tools = new Tools();
    Administrador administracion;
    NodoB usuarioActual;
    Registro registro;
    JButton btnMostrarImagen, btnCargaAlbum, btnCargaCapa, btnCargaImagen, btnLogOut,
            btnGenerarImagen, btnVentanaEstructura, btnVentanaCapa, btnReporte;
    JLabel lblTitulo, lblSubTitulo, lblGrafica, lblGraficaImg;
    JComboBox comboImagen;

    public VentanaCliente(Administrador administracion, NodoB usuarioActual, Registro registro) {
        this.administracion = administracion;
        this.usuarioActual = usuarioActual;
        this.registro = registro;
        this.AjustarVentana();
        this.componentes();
    }

    public void AjustarVentana() {
        setTitle("Ventana Cliente");
        setLayout(null);
        setSize(1000, 475);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Ventana Cliente", 25, 50, 300, 40, 20);
        btnCargaCapa = this.tools.addButton("Cargar Capa", 25, 100, 200, 30);
        btnCargaAlbum = this.tools.addButton("Cargar Album", 25, 140, 200, 30);
        btnCargaImagen = this.tools.addButton("Cargar Imagen", 25, 180, 200, 30);
        btnGenerarImagen = this.tools.addButton("Generar Imagen", 415, 375, 150, 25);
        btnMostrarImagen = this.tools.addButton("Mostrar Imagen", 580, 375, 150, 25);
        btnLogOut = tools.addButton("← Log Out", 835, 15, 117, 20);
        btnVentanaEstructura = tools.addButton("Graficar Estructuras", 15, 15, 175, 20);
        btnVentanaCapa = tools.addButton("Graficar Capa", 200, 15, 117, 20);
        btnReporte = tools.addButton("Reportes", 328, 15, 117, 20);
        lblSubTitulo = this.tools.addLabel("Seleccione el Id de la imagen:", 25, 245, 300, 40, 13);
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
        btnReporte.addActionListener(this);
        add(btnReporte);
        if (this.usuarioActual.cargoImagen) {
            agregarCombo();
        }
        repaint();
    }

    public void agregarCombo() {
        String[] opciones = this.administracion.imagenCombo(this.usuarioActual);
        comboImagen = this.tools.addComboBox(opciones, 25, 275, 200, 25);
        add(comboImagen);
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnGenerarImagen) {
            if (this.usuarioActual.cargoCapa && this.usuarioActual.cargoImagen) {
                System.out.println(comboImagen.getSelectedItem().toString());
                String contenido = this.administracion.crearImagen(this.usuarioActual, comboImagen.getSelectedItem().toString());
                this.administracion.crearGrafico(contenido, "Imagen_" + this.usuarioActual.getDpi());
                this.administracion.crearImagenHTML(this.usuarioActual, "ImagenHTML_" + this.usuarioActual.getDpi());
                this.usuarioActual.genero = true;
                this.setVisible(false);
                new VentanaCliente(this.administracion, this.usuarioActual, this.registro);
                this.dispose();
            }
        }

        if (AE.getSource() == this.btnMostrarImagen) {
            if (this.usuarioActual.cargoCapa && this.usuarioActual.cargoImagen && this.usuarioActual.genero) {
                lblGrafica = this.tools.addLabelImagen(275, 50, 300, 300);
                Image img = new ImageIcon("Imagen_" + this.usuarioActual.getDpi() + ".png").getImage();
                ImageIcon img2 = new ImageIcon(img.getScaledInstance(lblGrafica.getWidth(), lblGrafica.getHeight(), Image.SCALE_SMOOTH));
                lblGrafica.setIcon(img2);
                add(lblGrafica);
                /*Imagen HTML*/
                lblGraficaImg = this.tools.addLabelImagen(625, 50, 500, 500);
                Image imgHTML = new ImageIcon("ImagenHTML_" + this.usuarioActual.getDpi() + "_1.png").getImage();
                ImageIcon imgHTML2 = new ImageIcon(imgHTML.getScaledInstance(lblGraficaImg.getWidth(), lblGraficaImg.getHeight(), Image.SCALE_SMOOTH));
                lblGraficaImg.setIcon(imgHTML2);
                add(lblGraficaImg);
                repaint();
            }
        }

        if (AE.getSource() == this.btnCargaAlbum) {
            String contenido = edd_fase_2.Archivo.leerArchivoJson(this);
            this.administracion.cargaMasivaAlbum(contenido, this.usuarioActual);
            this.usuarioActual.cargoAlbum = true;
        }

        if (AE.getSource() == this.btnCargaCapa) {
            String contenido = edd_fase_2.Archivo.leerArchivoJson(this);
            this.administracion.cargaMasivaCapas(contenido, this.usuarioActual);
            this.usuarioActual.cargoCapa = true;
        }

        if (AE.getSource() == this.btnCargaImagen) {
            String contenido = edd_fase_2.Archivo.leerArchivoJson(this);
            this.administracion.cargaMasivaImagen(contenido, this.usuarioActual);
            this.usuarioActual.cargoImagen = true;
            this.setVisible(false);
            new VentanaCliente(this.administracion, this.usuarioActual, this.registro);
            this.dispose();
        }

        if (AE.getSource() == this.btnVentanaEstructura) {
            this.setVisible(false);
            new VentanaEstructura(this.administracion, this.usuarioActual, this.registro, this.tools);
            this.dispose();
        }

        if (AE.getSource() == this.btnVentanaCapa) {
            this.setVisible(false);
            new VentanaCapa(this.administracion, this.usuarioActual, this.registro, this.tools);
            this.dispose();
        }

        if (AE.getSource() == this.btnReporte) {
            this.setVisible(false);
            new VentanaReporte(this.administracion, this.usuarioActual, this.registro, this.tools);
            this.dispose();
        }

        if (AE.getSource() == this.btnLogOut) {
            setVisible(false);
            new Autenticacion(this.registro, this.administracion);
            this.dispose();
        }
    }
}
