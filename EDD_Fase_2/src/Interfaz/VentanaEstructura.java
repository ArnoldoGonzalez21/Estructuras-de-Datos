package Interfaz;

import Clases.Usuario;
import edd_fase_2.Administrador;
import edd_fase_2.Registro;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Arnoldo González
 */
public class VentanaEstructura extends JFrame implements ActionListener {

    Tools tools;
    Administrador administracion;
    Usuario usuarioActual;
    Registro registro;
    JButton btnMostrarImagen, btnRegresar, btnGenerarImagen, btnRecorrido, btnAltura;
    JLabel lblTitulo, lblEstructuraGrafica, lblRecorrido, lblAltura, lblGrafica;
    JComboBox comboEstructura;
    JTextField txtRecorrido, txtAlturaAVL;
    JRadioButton rbtnInOrder, rbtnPostOrder, rbtnPreOrder;

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
        btnRecorrido = this.tools.addButton("Mostrar Recorrido", 45, 310, 150, 25);
        btnAltura = this.tools.addButton("Mostrar", 130, 375, 98, 25);
        btnRegresar = tools.addButton("← Regresar", 485, 15, 117, 20);
        lblEstructuraGrafica = this.tools.addLabel("Seleccione la estructura:", 25, 55, 250, 40, 13);
        lblRecorrido = this.tools.addLabel("Seleccione el tipo de recorrido", 25, 175, 250, 40, 13);
        lblAltura = this.tools.addLabel("Altura Árbol AVL", 25, 338, 250, 40, 13);
        String[] opciones = {"Árbol Binario de Búsqueda", "Árbol AVL", "Lista Simple"};
        comboEstructura = this.tools.addComboBox(opciones, 25, 100, 200, 25);
        ButtonGroup orden = new ButtonGroup();
        rbtnPreOrder = this.tools.addRadioButton("PreOrder", 25, 215, 100, 25);
        rbtnPostOrder = this.tools.addRadioButton("PostOrder", 125, 215, 100, 25);
        rbtnInOrder = this.tools.addRadioButton("InOrder", 75, 240, 100, 25);
        orden.add(rbtnPreOrder);
        orden.add(rbtnInOrder);
        orden.add(rbtnPostOrder);
        txtRecorrido = this.tools.addTextField("", 25, 270, 200, 25);
        txtAlturaAVL = this.tools.addTextField("", 25, 375, 98, 25);
        txtAlturaAVL.setEnabled(false);
        add(lblTitulo);
        add(lblEstructuraGrafica);
        add(lblRecorrido);
        add(lblAltura);
        add(comboEstructura);
        add(txtRecorrido);
        add(txtAlturaAVL);
        btnMostrarImagen.addActionListener(this);
        add(btnMostrarImagen);
        btnGenerarImagen.addActionListener(this);
        add(btnGenerarImagen);
        btnRecorrido.addActionListener(this);
        add(btnRecorrido);
        btnRegresar.addActionListener(this);
        add(btnAltura);
        btnAltura.addActionListener(this);
        add(btnRegresar);
        add(rbtnPreOrder);
        add(rbtnPostOrder);
        add(rbtnInOrder);
        repaint();
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

        if (AE.getSource() == this.btnRecorrido) {
            if (rbtnPreOrder.isSelected()) {
                txtRecorrido.setText(this.administracion.textRecorrido(this.usuarioActual, 0, comboEstructura.getSelectedIndex()));
            } else if (rbtnPostOrder.isSelected()) {
                txtRecorrido.setText(this.administracion.textRecorrido(this.usuarioActual, 1, comboEstructura.getSelectedIndex()));
            } else if (rbtnInOrder.isSelected()) {
                txtRecorrido.setText(this.administracion.textRecorrido(this.usuarioActual, 2, comboEstructura.getSelectedIndex()));
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un tipo de recorrido");
            }
        }

        if (AE.getSource() == this.btnAltura) {
            txtRecorrido.setEnabled(true);
            txtAlturaAVL.setText(this.administracion.textAltura(this.usuarioActual));
            txtRecorrido.setEnabled(false);
        }

        if (AE.getSource() == this.btnRegresar) {
            this.setVisible(false);
            new VentanaCliente(this.administracion, this.usuarioActual, this.registro);
            this.dispose();
        }
    }
}
