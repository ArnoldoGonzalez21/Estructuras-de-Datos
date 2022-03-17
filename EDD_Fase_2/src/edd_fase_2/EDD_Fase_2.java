package edd_fase_2;

import Clases.Matriz;

/**
 *
 * @author Arnoldo González
 */
public class EDD_Fase_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("--------------------------");
//        Matriz matrizAux = new Matriz();
//        matrizAux.insertarNodo(0, 0, "hola");
//        matrizAux.insertarNodo(1, 1, "hola");
//        matrizAux.insertarNodo(2, 2, "hola");
//        matrizAux.insertarNodo(3, 3, "hola");
//        matrizAux.insertarNodo(1, 3, "hola");
//        matrizAux.insertarNodo(5, 1, "hola");
//        matrizAux.imprimir();
        Clases.Registro arbol = new Clases.Registro("", "");
        arbol.insertar(10, "dsa", "sda");
        arbol.insertar(20, "dsa", "sda");
        arbol.insertar(5, "dsa", "sda");
        arbol.insertar(9, "dsa", "sda");
        arbol.insertar(12, "dsa", "sda");
        arbol.insertar(18, "dsa", "sda");
        arbol.insertar(25, "dsa", "sda");
        arbol.insertar(65, "dsa", "sda");
        arbol.insertar(92, "dsa", "sda");
        arbol.insertar(99, "dsa", "sda");
        arbol.imp();
        System.out.println("------------------");
    }

}
