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
        new Autenticacion(new Registro("admin", "EDD2022"), new Administrador());        
    }
}
