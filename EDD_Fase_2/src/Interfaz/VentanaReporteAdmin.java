package Interfaz;

import Clases.NodoB;
import edd_fase_2.Administrador;
import edd_fase_2.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Arnoldo González
 */
public class VentanaReporteAdmin extends JFrame implements ActionListener {

    Tools tools;
    Administrador administracion;
    NodoB usuarioActual;
    Registro registro;
    JButton btnBuscarDpi, btnRegresar, btnGenerarLista;
    JLabel lblTitulo, lblBuscarDpi, lblTabla, lblNombre, lblDpi, lblPassword, lblCantAlbum, lblImgAlbum, lblCantImgTotal, lblCantCapa;
    JTextField txtNombre, txtDpi, txtPassword, txtCantAlbun, txtImgAlbum, txtCantImgTotal, txtCantCapa;
    JComboBox comboCliente;

    public VentanaReporteAdmin(Administrador administracion, NodoB usuarioActual, Registro registro, Tools tools) {
        this.administracion = administracion;
        this.usuarioActual = usuarioActual;
        this.registro = registro;
        this.tools = tools;
        this.AjustarVentana();
        this.componentes();
    }

    public void AjustarVentana() {
        setTitle("Ventana Reporte Administrador");
        setLayout(null);
        setSize(700, 525);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void componentes() {
        lblTitulo = this.tools.addLabelTitulo("Reportes Administrador", 25, 10, 300, 40, 20);
        lblBuscarDpi = this.tools.addLabel("Seleccione el DPI del Cliente a buscar:", 25, 40, 275, 40, 13);

        lblNombre = this.tools.addLabel("Nombre:", 25, 145, 250, 40, 13);
        txtNombre = this.tools.addTextField("", 100, 155, 225, 25);

        lblDpi = this.tools.addLabel("DPI:", 25, 185, 250, 40, 13);
        txtDpi = this.tools.addTextField("", 70, 195, 255, 25);

        lblPassword = this.tools.addLabel("Contraseña:", 25, 225, 250, 40, 13);
        txtPassword = this.tools.addTextField("", 122, 235, 203, 25);

        lblCantAlbum = this.tools.addLabel("Cantidad de Álbumes:", 25, 265, 250, 40, 13);
        txtCantAlbun = this.tools.addTextField("", 188, 275, 137, 25);

        lblImgAlbum = this.tools.addLabel("Imágenes en Album:", 25, 305, 250, 40, 13);
        txtImgAlbum = this.tools.addTextField("", 180, 315, 145, 25);

        lblCantImgTotal = this.tools.addLabel("Cantidad Imágenes Totales:", 25, 345, 250, 40, 13);
        txtCantImgTotal = this.tools.addTextField("", 228, 355, 97, 25);

        lblCantCapa = this.tools.addLabel("Cantidad Capas:", 25, 385, 250, 40, 13);
        txtCantCapa = this.tools.addTextField("", 150, 395, 175, 25);

        lblTabla = this.tools.addLabel("Lista de Clientes:", 350, 40, 250, 40, 13);
        btnBuscarDpi = this.tools.addButton("Buscar", 235, 100, 88, 26);
        btnGenerarLista = this.tools.addButton("Generar Lista", 426, 425, 150, 25);
        btnRegresar = tools.addButton("← Regresar", 535, 15, 117, 20);
        add(lblTitulo);
        add(lblBuscarDpi);
        add(lblNombre);
        add(lblDpi);
        add(lblPassword);
        add(lblCantImgTotal);
        add(lblCantAlbum);
        add(lblImgAlbum);
        add(lblCantCapa);
        add(txtNombre);
        add(txtDpi);
        add(txtPassword);
        add(txtCantAlbun);
        add(txtImgAlbum);
        add(txtCantImgTotal);
        add(txtCantCapa);
        add(lblTabla);
        btnBuscarDpi.addActionListener(this);
        add(btnBuscarDpi);
        btnGenerarLista.addActionListener(this);
        add(btnGenerarLista);
        btnRegresar.addActionListener(this);
        add(btnRegresar);
        if (this.usuarioActual.cargoCliente) {
            agregarCombo();
        }
        repaint();
    }

    public void agregarCombo() {
        String[] opciones = this.administracion.opcionesUser(this.registro);
        comboCliente = this.tools.addComboBox(opciones, 25, 100, 200, 25);
        add(comboCliente);
        repaint();
    }

    private void addTable() {
        String[] columnas = {"DPI", "Nombre", "Cantidad Img"};
        Object[][] datos = this.administracion.tablaClienteImg(this.registro);
        JTable tbl = new JTable(datos, columnas);
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(350, 75, 303, 345);
        add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == this.btnBuscarDpi) {
            NodoB user = this.administracion.buscarUser(this.registro, comboCliente.getSelectedItem().toString());
            if (this.usuarioActual.cargoCliente) {
                if (user != null) {
                    txtDpi.setEditable(true);
                    txtNombre.setEditable(true);
                    txtPassword.setEditable(true);
                    txtCantAlbun.setEditable(true);
                    txtCantImgTotal.setEditable(true);
                    txtCantCapa.setEditable(true);
                    txtImgAlbum.setEditable(true);
                    txtDpi.setText(user.getDpi());
                    txtNombre.setText(user.getNombre());
                    txtPassword.setText(user.getContrasena());
                    if (user.cargoAlbum) {
                        txtCantAlbun.setText(user.getAlbumUser().sizeAlbum());
                        txtImgAlbum.setText(user.getAlbumUser().cantidadImgAlbum());
                    } else {
                        txtCantAlbun.setText("Sin Álbumes");
                        txtImgAlbum.setText("Sin Álbumes");
                    }
                    if (user.cargoCapa) {
                        txtCantCapa.setText(this.administracion.cantidadCapas(user));
                    } else {
                        txtCantCapa.setText("Sin Capas");
                    }
                    if (user.cargoImagen) {
                        txtCantImgTotal.setText(this.administracion.cantidadNodoAVL(user));
                    } else {
                        txtCantImgTotal.setText("Sin Imágenes");
                    }
                    txtDpi.setEditable(false);
                    txtNombre.setEditable(false);
                    txtPassword.setEditable(false);
                    txtCantAlbun.setEditable(false);
                    txtCantImgTotal.setEditable(false);
                    txtCantCapa.setEditable(false);
                    txtImgAlbum.setEditable(false);
                }
            }
        }

        if (AE.getSource() == this.btnGenerarLista) {
            if (this.usuarioActual.cargoCliente) {
                addTable();
            } else {
                JOptionPane.showMessageDialog(this, "Cargar usuarios");
            }
        }

        if (AE.getSource() == this.btnRegresar) {
            this.setVisible(false);
            new VentanaAdministrador(this.administracion, this.usuarioActual, this.registro, this.tools);
            this.dispose();
        }
    }
}
