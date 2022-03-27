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
import javax.swing.JTextField;

/**
 *
 * @author Arnoldo González
 */
public class VentanaCapa extends JFrame implements ActionListener {

    Tools tools;
    Administrador administracion;
    Usuario usuarioActual;
    Registro registro;
    JButton btnMostrarImagen, btnRegresar, btnGenerarImagen, btnNodoHoja;
    JLabel lblTitulo, lblSubTitulo, lblNodoHoja, lblTopCapa, lblGrafica;
    JComboBox comboCapa;
    JTextField txtNodoHoja, txtTopCapa;

    public VentanaCapa(Administrador administracion, Usuario usuarioActual, Registro registro, Tools tools) {
        this.administracion = administracion;
        this.usuarioActual = usuarioActual;
        this.registro = registro;
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
        btnGenerarImagen = this.tools.addButton("Generar Imagen", 265, 375, 150, 25);
        btnMostrarImagen = this.tools.addButton("Mostrar Imagen", 430, 375, 150, 25);
        btnNodoHoja = this.tools.addButton("Mostrar", 45, 375, 150, 25);
        btnRegresar = tools.addButton("← Regresar", 485, 15, 117, 20);
        lblSubTitulo = this.tools.addLabel("Seleccione el Id de la capa:", 25, 70, 250, 40, 13);
        lblNodoHoja = this.tools.addLabel("Nodo capa que son hojas:", 25, 230, 250, 40, 13);
        lblTopCapa = this.tools.addLabel("<html>Top 5 imagenes con más capas<br>de menor a mayor:<html>", 25, 295, 250, 40, 13);
        txtNodoHoja = this.tools.addTextField("", 25, 265, 200, 25);
        txtTopCapa = this.tools.addTextField("", 25, 338, 200, 25);
        add(lblTitulo);
        add(lblSubTitulo);
        add(lblNodoHoja);
        add(lblTopCapa);
        add(txtNodoHoja);
        add(txtTopCapa);
        btnMostrarImagen.addActionListener(this);
        add(btnMostrarImagen);
        btnGenerarImagen.addActionListener(this);
        add(btnGenerarImagen);
        btnNodoHoja.addActionListener(this);
        add(btnNodoHoja);
        btnRegresar.addActionListener(this);
        add(btnRegresar);
        if (this.usuarioActual.cargoCapa) {
            agregarCombo();
        }
        repaint();
    }

    public void agregarCombo() {
        String[] opciones = this.administracion.capaCombo(this.usuarioActual);
        comboCapa = this.tools.addComboBox(opciones, 25, 100, 200, 25);
        add(comboCapa);
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnGenerarImagen) {
            System.out.println(comboCapa.getSelectedItem().toString());
            String contenido = this.administracion.matrizNodo(this.usuarioActual, comboCapa.getSelectedItem().toString());
            this.administracion.crearGrafico(contenido, "Capa_" + this.usuarioActual.getDpi());
        }
        if (AE.getSource() == this.btnMostrarImagen) {
            lblGrafica = this.tools.addLabelImagen(275, 50, 300, 300);
            Image img = new ImageIcon("Capa_" + this.usuarioActual.getDpi() + ".png").getImage();
            ImageIcon img2 = new ImageIcon(img.getScaledInstance(lblGrafica.getWidth(), lblGrafica.getHeight(), Image.SCALE_SMOOTH));
            lblGrafica.setIcon(img2);
            add(lblGrafica);
            repaint();
        }

        if (AE.getSource() == this.btnNodoHoja) {
            txtNodoHoja.setText(this.administracion.textCapaHoja(this.usuarioActual));
            txtTopCapa.setText(this.administracion.textTopCinco(this.usuarioActual));
        }

        if (AE.getSource() == this.btnRegresar) {
            this.setVisible(false);
            new VentanaCliente(this.administracion, this.usuarioActual, this.registro);
            this.dispose();
        }
    }
}
