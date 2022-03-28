package edd_fase_2;

import Interfaz.Autenticacion;
import Interfaz.Tools;

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
//        new Interfaz.VentanaAdministrador(ad);
//        new Interfaz.VentanaCliente(new Administrador(),null, new Registro("admin", "admin"));
        new Autenticacion(new Registro("a", "a"), new Administrador());
//        new Interfaz.VentanaEstructura(new Administrador(), null, null, new Tools());   
//        new Interfaz.VentanaCapa(new Administrador(), null, null, new Tools());   
        
    }
}
