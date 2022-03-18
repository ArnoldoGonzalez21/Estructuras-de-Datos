package edd_fase_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static String leerArchivoJsonConsola() {
        String Jsontext = "";
        Scanner lector = new Scanner(System.in);
        String ruta = lector.nextLine();
        lector = new Scanner(System.in);
        try {
            Scanner entrada = new Scanner(new File(ruta));
            while (entrada.hasNextLine()) {
                Jsontext += entrada.nextLine() + "\n";
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error en el archivo Json");
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return Jsontext;
    }
}
