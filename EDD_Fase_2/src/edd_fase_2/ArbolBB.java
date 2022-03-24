package edd_fase_2;

import Clases.NodoBinario;

/**
 *
 * @author Arnoldo González
 */
public class ArbolBB {

    public NodoBinario raiz;
    private int cantidad = -1;

    public ArbolBB() {
        raiz = null;
    }

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

    public void inorden() {
        System.out.println("Recorrido inorden del árbol binario de búsqueda:");
        inorden(raiz);
        System.out.println();
    }

    public void inorden(NodoBinario nodo, Grafica grafica) { //se encargada de introducir los nodos en el arreglo
        if (nodo == null) {
            return;
        }
        inorden(nodo.getIzquierdo(), grafica);
        System.out.print(nodo.getValor() + ",");
        grafica.matriz()[grafica.contador] = nodo;
        grafica.contador++;
        inorden(nodo.getDerecho(), grafica);
    }

    private void inorden(NodoBinario nodo) { //utilizado para saber la cantidad de nodos del abb
        if (nodo == null) {
            return;
        }
        inorden(nodo.getIzquierdo());
        System.out.print(nodo.getValor() + ",");
        this.cantidad++;
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
        inorden(raiz);
        return cantidad;
    }
}
