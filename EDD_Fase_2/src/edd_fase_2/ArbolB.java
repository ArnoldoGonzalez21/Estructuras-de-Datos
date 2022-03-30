package edd_fase_2;

import Clases.NodoB;

/**
 *
 * @author Arnoldo González
 */
public class ArbolB {

    NodoB raiz;
    int grado;
    Utilidades util;

    public ArbolB(int deg, Utilidades util) {
        this.util = util;
        this.raiz = null;
        this.grado = deg;
    }

    public void recorrer() {
        if (raiz != null) {
            raiz.recorrer();
        }
    }

    public NodoB buscar(long key) {
        return raiz == null ? null : raiz.buscar(key);
    }

    public void insertar(long key) {

        if (raiz == null) {
            raiz = new NodoB(grado, true);
            raiz.ClavesNodo[0] = key;
            raiz.numClavesNodo = 1;
        } else {
            if (raiz.numClavesNodo == 2 * grado - 1) {// Cuando el nodo raíz está lleno, el árbol crecerá más alto
                NodoB nuevo = new NodoB(grado, false);
                nuevo.hijos[0] = raiz; // La raiz se convierte en hijo de la nueva raiz 
                nuevo.dividirHijo(0, raiz);
                int i = 0;
                if (nuevo.ClavesNodo[0] < key) {
                    i++;
                }
                nuevo.hijos[i].insertarNoLleno(key);

                raiz = nuevo;
            } else {
                raiz.insertarNoLleno(key);
            }
        }
    }

    public void eliminar(long key) {
        if (raiz == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        raiz.eliminar(key);

        if (raiz.numClavesNodo == 0) {
            if (raiz.isHoja()) {
                raiz = null;
            } else {
                raiz = raiz.hijos[0]; //Si tiene un nodo hijo,el primer nodo hijo es el nuevo nodo raíz
            }
        }
    }

    public void mostrarArbolB() {
        recorrerGrafica(raiz, 0, raiz);
    }

    private void recorrerGrafica(NodoB nodo, int h, NodoB padre) {    //Print en preorder
        System.out.print("Level " + h + ": ");
        if (nodo.hijos[0] != null) {
            nodo.imprimirPadre(padre, this.util);
        } else {
            nodo.imprimir(padre, this.util);
        }
        if (!nodo.isHoja()) {
            for (int j = 0; j <= nodo.numClavesNodo; j++) { //recorre los nodos hijos
                if (nodo.hijos[j] != null) {
                    System.out.println();
                    recorrerGrafica(nodo.hijos[j], h + 1, nodo);
                }
            }
        }
    }
}
