package Interfaz;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Arnoldo González
 */
public class Tools {

    public JLabel addLabelTitulo(String texto, int x, int y, int ancho, int altura, int tamano) {
        JLabel lbl = new JLabel(texto);
        lbl.setBounds(x, y, ancho, altura);
        lbl.setFont(new Font("Verdana", Font.BOLD, tamano));
        return lbl;
    }

    public JLabel addLabel(String texto, int x, int y, int ancho, int altura, int tamano) {
        JLabel lbl = new JLabel(texto);
        lbl.setBounds(x, y, ancho, altura);
        lbl.setFont(new Font("Verdana", Font.PLAIN, tamano));
        return lbl;
    }
    
    public JButton addButton(String texto, int x, int y, int ancho, int altura) {
        JButton btn = new JButton(texto);
        btn.setBounds(x, y, ancho, altura);
        return btn;
    }

    public JTextField addTextField(String texto, int x, int y, int ancho, int altura) {
        JTextField txt = new JTextField(texto);
        txt.setBounds(x, y, ancho, altura);
        txt.setForeground(Color.black);
        return txt;
    }

    public JPasswordField addPasswordField(String texto, int x, int y, int ancho, int altura) {
        JPasswordField txt = new JPasswordField(texto);
        txt.setBounds(x, y, ancho, altura);
        return txt;
    }

    public JComboBox addComboBox(String[] opciones, int x, int y, int ancho, int altura) {
        JComboBox combo = new JComboBox(opciones);
        combo.setBounds(x, y, ancho, altura);
        return combo;
    }

}
