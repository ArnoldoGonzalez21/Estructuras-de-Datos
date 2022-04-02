package edd_fase_2;

import Interfaz.Autenticacion;

/**
 *
 * @author Arnoldo González
 */
public class EDD_Fase_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        "admin", "EDD2022"
        new Autenticacion(new Registro("a", "a"), new Administrador());        
    }
}
