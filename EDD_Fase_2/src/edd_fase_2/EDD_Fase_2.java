package edd_fase_2;

import Clases.Usuario;
import Interfaz.Autenticacion;
import Interfaz.Tools;
import edd_fase_2.ArbolB;

/**
 *
 * @author Arnoldo González
 */
public class EDD_Fase_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        System.out.println("--------------------------");
//        ArbolB arbol = new ArbolB("admin", "EDD2022");
//        arbol.insertar(2, "raul", "sda");
//        arbol.insertar(4, "pedro", "sda");
//        arbol.insertar(6, "maria", "sda");
//        arbol.insertar(12, "mercedes", "sda");
//        arbol.insertar(8, "saul", "sda");
//        arbol.insertar(0, "roxana", "sda");
//        arbol.insertar(11, "ximena", "sda");
//        arbol.insertar(14, "santiago", "sda");
//        arbol.insertar(1, "emilio", "sda");
//        arbol.insertar(9, "susana", "sda");
//        arbol.insertar(3, "susana", "sda");
//        arbol.inorden();

//        Registro ls = new Registro("", "");//
//        ls.insertarUsuario(2, "raul", "sda");
//        ls.insertarUsuario(4, "pedro", "sda");
//        ls.insertarUsuario(6, "maria", "sda");
//        ls.insertarUsuario(12, "mercedes", "sda");
//        ls.insertarUsuario(8, "saul", "sda");
//        ls.insertarUsuario(0, "roxana", "sda");
//        ls.insertarUsuario(11, "ximena", "sda");
//        ls.insertarUsuario(14, "santiago", "sda");
//        ls.insertarUsuario(1, "emilio", "sda");
//        ls.insertarUsuario(9, "susana", "sda");
//        ls.insertarUsuario(3, "susana", "sda");
//        ls.recorrerUser();
//        System.out.println("------------------");
        Administrador ad = new Administrador();
//    new Interfaz.VentanaAdministrador(ad);
//        ArbolBinarioBusqueda abb = new ArbolBinarioBusqueda();
//        abb.insertar(4);
//        abb.insertar(8);
//        abb.insertar(2);
//        abb.insertar(5);
//        abb.insertar(0);
//        abb.insertar(3);
//        abb.preOrder();
//        abb.posOrder();
//        abb.inOrder();
//        new Interfaz.VentanaCliente(ad, new Usuario("", ""), new Registro("admin", "admin"));
//        ArbolBB arbol_numeros=new ArbolBB();
//        arbol_numeros.insertar(12);
//        arbol_numeros.insertar(5);
//        arbol_numeros.insertar(26);
//        arbol_numeros.insertar(33);
//        arbol_numeros.insertar(59);
//        arbol_numeros.insertar(27);
//        arbol_numeros.insertar(15);      
//        arbol_numeros.graficar("arbol_numeros.jpg");
//        arbol_numeros.inorden();
//        ArbolAVL arbol_numeros = new ArbolAVL();
//        arbol_numeros.insertar(12);
//        arbol_numeros.insertar(5);
//        arbol_numeros.insertar(26);
//        arbol_numeros.insertar(33);
//        arbol_numeros.insertar(59);
//        arbol_numeros.insertar(27);
//        arbol_numeros.insertar(15);
//        arbol_numeros.insertar(47);
//        arbol_numeros.insertar(74);
//        arbol_numeros.insertar(84);
//        arbol_numeros.insertar(88);
//        arbol_numeros.insertar(90);
//        arbol_numeros.insertar(124);
//        arbol_numeros.insertar(612);
//        arbol_numeros.graficar("arbol_numeros.jpg");
//        arbol_numeros.inorden();
        new Autenticacion(new Registro("a", "a"), ad);

    }

}
