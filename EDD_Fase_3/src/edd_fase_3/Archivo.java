package edd_fase_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Arnoldo González
 */
public class Archivo {

    public static String leerArchivoJson(JFrame ventana) {
        String Jsontext = "";
        try {
            JFileChooser selectorArchivos = new JFileChooser();
            selectorArchivos.showOpenDialog(ventana);
            File archivo = selectorArchivos.getSelectedFile();

            if (archivo != null) {
                FileReader lector = null;
                try {
                    lector = new FileReader(archivo);
                    BufferedReader leer = new BufferedReader(lector);
                    String auxiliar = "";
                    while ((auxiliar = leer.readLine()) != null) {
                        Jsontext += auxiliar + "\n";
                    }
                    leer.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(ventana, e + "\nArchivo no encontrado", "", JOptionPane.WARNING_MESSAGE);
                } finally {
                    try {
                        lector.close();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(ventana, e + "\nArchivo no encontrado", "", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, e + "\nArchivo no encontrado", "", JOptionPane.WARNING_MESSAGE);
        }
        return Jsontext;
    }
}
