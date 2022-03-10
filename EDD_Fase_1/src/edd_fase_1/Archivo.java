package edd_fase_1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arnoldo González
 */
public class Archivo {

    public static String leerArchivoJson() {
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
