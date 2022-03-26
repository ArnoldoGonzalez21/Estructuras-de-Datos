package Interfaz;

import Clases.Usuario;
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
public class VentanaEstructura extends JFrame implements ActionListener {

    Tools tools;
    Administrador administracion;
    Usuario usuarioActual;
    Registro registro;
    JButton btnMostrarImagen, btnRegresar, btnGenerarImagen;
    JLabel lblTitulo, lblSubTitulo, lblGrafica;
    JComboBox comboEstructura;

    public VentanaEstructura(Administrador administracion, Usuario usuarioActual, Registro registro, Tools tools) {
        this.administracion = administracion;
        this.usuarioActual = usuarioActual;
        this.registro = registro;
        this.tools = tools;
        this.AjustarVentana();
        this.componentes();
    }

    public void AjustarVentana() {
        setTitle("Ventana Estructura");
        setLayout(null);
        setSize(650, 475);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Gráfica de las Estructuras", 25, 20, 300, 40, 20);
        btnGenerarImagen = this.tools.addButton("Generar Imagen", 265, 375, 150, 25);
        btnMostrarImagen = this.tools.addButton("Mostrar Imagen", 430, 375, 150, 25);
        btnRegresar = tools.addButton("← Regresar", 485, 15, 117, 20);
        lblSubTitulo = this.tools.addLabel("Seleccione la estructura:", 25, 70, 250, 40, 13);
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
        String[] opciones = {"Árbol Binario de Búsqueda", "Árbol AVL", "Lista Simple"};
        comboEstructura = this.tools.addComboBox(opciones, 25, 100, 200, 25);
        add(comboEstructura);
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnGenerarImagen) {
            if (comboEstructura.getSelectedIndex() == 0) {
                this.administracion.generarAbb(this.usuarioActual, "Grafica_" + this.usuarioActual.getDpi());
            } else if (comboEstructura.getSelectedIndex() == 1) {
                this.administracion.generarAVL(this.usuarioActual, "Grafica_" + this.usuarioActual.getDpi());
            } else if (comboEstructura.getSelectedIndex() == 2) {
                this.administracion.crearGrafico(this.administracion.generarListaAlbum(this.usuarioActual), "Grafica_" + this.usuarioActual.getDpi());
            }
        }
        if (AE.getSource() == this.btnMostrarImagen) {
            lblGrafica = this.tools.addLabelImagen(275, 50, 300, 300);
            Image img = new ImageIcon("Grafica_" + this.usuarioActual.getDpi() + ".png").getImage();
            ImageIcon img2 = new ImageIcon(img.getScaledInstance(lblGrafica.getWidth(), lblGrafica.getHeight(), Image.SCALE_SMOOTH));
            lblGrafica.setIcon(img2);
            add(lblGrafica);
            repaint();
        }

        if (AE.getSource() == this.btnRegresar) {
            this.setVisible(false);
            new VentanaCliente(this.administracion, this.usuarioActual, this.registro);
            this.dispose();
        }
    }
}
