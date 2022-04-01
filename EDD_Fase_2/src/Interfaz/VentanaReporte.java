package Interfaz;

import Clases.NodoB;
import edd_fase_2.Administrador;
import edd_fase_2.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Arnoldo González
 */
public class VentanaReporte extends JFrame implements ActionListener {

    Tools tools;
    Administrador administracion;
    NodoB usuarioActual;
    Registro registro;
    JButton btnMostrarImagen, btnRegresar, btnMostrar;
    JLabel lblTitulo, lblNodoHoja, lblTopCapa, lblGrafica;
    JTextField txtNodoHoja;

    public VentanaReporte(Administrador administracion, NodoB usuarioActual, Registro registro, Tools tools) {
        this.administracion = administracion;
        this.usuarioActual = usuarioActual;
        this.registro = registro;
        this.tools = tools;
        this.AjustarVentana();
        this.componentes();
    }

    public void AjustarVentana() {
        setTitle("Ventana Reporte");
        setLayout(null);
        setSize(565, 475);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Reportes", 25, 20, 300, 40, 20);
        btnMostrar = this.tools.addButton("Mostrar", 200, 375, 150, 25);
        btnRegresar = tools.addButton("← Regresar", 400, 15, 117, 20);
        lblTopCapa = this.tools.addLabel("Top 5 Imágenes con más capas:", 25, 50, 250, 40, 13);
        lblNodoHoja = this.tools.addLabel("Capas que son hojas:", 25, 230, 250, 40, 13);
        txtNodoHoja = this.tools.addTextField("", 25, 265, 200, 25);
        add(lblTitulo);
        add(lblNodoHoja);
        add(lblTopCapa);
        add(txtNodoHoja);
        btnMostrar.addActionListener(this);
        add(btnMostrar);
        btnRegresar.addActionListener(this);
        add(btnRegresar);
        if (this.usuarioActual.cargoCapa && this.usuarioActual.cargoImagen) {
            addTable();
        }
        repaint();
    }

    private void addTable() {
        String[] columnas = {"Posición", "Nodo Imagen", "Cantidad"};
        Object[][] datos = this.administracion.tablaTopCinco(this.usuarioActual);
        JTable tbl = new JTable(datos, columnas);
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(135, 80, 300, 125);
        add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        
        if (AE.getSource() == this.btnMostrar) {
            txtNodoHoja.setText(this.administracion.textCapaHoja(this.usuarioActual));
        }

        if (AE.getSource() == this.btnRegresar) {
            this.setVisible(false);
            new VentanaCliente(this.administracion, this.usuarioActual, this.registro);
            this.dispose();
        }
    }
}
