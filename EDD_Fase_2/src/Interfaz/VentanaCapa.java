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
public class VentanaCapa extends JFrame implements ActionListener {

    Tools tools;
    Administrador administracion;
    JButton btnMostrarImagen, btnRegresar, btnGenerarImagen;
    JLabel lblTitulo, lblSubTitulo, lblGrafica;
    JComboBox comboCapa;

    public VentanaCapa(Administrador administracion, Tools tools) {
        this.administracion = administracion;
        this.tools = tools;
        AjustarVentana();
        this.componentes();

    }

    public void AjustarVentana() {
        setTitle("Ventana Capa");
        setLayout(null);
        setSize(650, 475);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Gráfica de Capas", 25, 20, 300, 40, 20);
        btnMostrarImagen = this.tools.addButton("Mostrar Imagen", 265, 375, 150, 25);
        btnGenerarImagen = this.tools.addButton("Generar Imagen", 430, 375, 150, 25);
        btnRegresar = tools.addButton("← Regresar", 485, 15, 117, 20);
        lblSubTitulo = this.tools.addLabel("Seleccione la capa:", 25, 70, 250, 40, 13);
        add(lblTitulo);
        add(lblSubTitulo);
        btnMostrarImagen.addActionListener(this);
        add(btnMostrarImagen);
        btnGenerarImagen.addActionListener(this);
        add(btnGenerarImagen);
        btnRegresar.addActionListener(this);
        add(btnRegresar);
        agregarCombo();
        repaint();
    }

    public void agregarCombo() {
        String[] opciones = this.administracion.capasCombo();
        comboCapa = this.tools.addComboBox(opciones, 25, 100, 200, 25);
        add(comboCapa);
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnGenerarImagen) {
            // if (nombre != "") {
//            String contenido = this.administracion.matriz();
//            this.administracion.crearGrafico(contenido, "imagen");
            this.administracion.generarAbb();
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
        
        if (AE.getSource() == this.btnRegresar) {
            this.setVisible(false);
            new VentanaClliente(this.administracion);
            this.dispose();
        }

    }

}
