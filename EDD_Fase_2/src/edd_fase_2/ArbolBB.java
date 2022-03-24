package edd_fase_2;

import Clases.NodoBinario;

/**
 *
 * @author Arnoldo González
 */
public class ArbolBB {

    /**
     * Nodo raíz del árbol.
     */
    private NodoBinario raiz;
    private int cantidad = 0;
    /**
     * Constructor de la clase, incialmente la raíz es nula porque el árbol está
     * vacío.
     */
    public ArbolBB() {
        raiz = null;
    }

    /**
     * Método que se encarga de insertar un valor en el árbol binario de
     * búsqueda.
     *
     * @param val Valor específico que se desea insertar.
     */
    public NodoBinario insertar(Comparable val) {
        if (raiz == null) {
            raiz = new NodoBinario(val);
            cantidad++;
        } else {
            raiz.insertar(val);
        }
        return raiz;
    }

    /**
     * Método que genera una imagen del árbol binario de búsqueda en la ruta que
     * se le indica.
     *
     * @param path Ruta específica en la que se guardará la imagen generada.
     */
    public void graficar(String path) {
        raiz.graficar(path);
    }

    /**
     * Método que imprime el recorrido inorden del árbol binario de búsqueda.
     */
    public void inorden() {
        System.out.println("Recorrido inorden del árbol binario de búsqueda:");
        inorden(raiz);
        System.out.println();
    }

    /**
     * Método privado que ejecuta la tarea de hacer un recorrido inorden del
     * árbol binario de búsqueda.
     *
     * @param a Nodo específico que se recorrerá conforme el método se llama
     * recursivamente.
     */
    private void inorden(NodoBinario nodo) {
        if (nodo == null) {
            return;
        }
        inorden(nodo.getIzquierdo());
        System.out.print(nodo.getValor() + ",");
        inorden(nodo.getDerecho());
    }

    public void posOrder(NodoBinario nodo) {
        //hijoIzquierdo, hijoDerecho, raiz
        if (nodo == null) {
            return;
        }
        posOrder(nodo.getIzquierdo());
        posOrder(nodo.getDerecho());
        System.out.print(nodo.getValor() + ", ");
    }

    public void preOrder(NodoBinario nodo) {
        //raiz, hijoIzquierdo, hijoDerecho 
        if (nodo == null) {
            return;
        }
        System.out.print(nodo.getValor() + ", ");
        preOrder(nodo.getIzquierdo());
        preOrder(nodo.getDerecho());
    }

    public int cantidad() {
        return cantidad;
    }
}
