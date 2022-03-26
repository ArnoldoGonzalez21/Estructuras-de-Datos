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

    public NodoBinario insertar(Comparable val, MatrizDispersa pixeles) {
        if (raiz == null) {
            raiz = new NodoBinario(val, pixeles);
            cantidad++;
        } else {
            raiz.insertar(val, pixeles);
        }
        return raiz;
    }

    public void graficar(String path) {
        raiz.graficar(path);
    }

    public void inorden() {
        System.out.println("Recorrido inorden del árbol binario de búsqueda:");
        inorden(raiz);
        System.out.println();
    }

    public void inorden(NodoBinario nodo, Utilidades util) { //se encargada de introducir los nodos en el arreglo
        if (nodo == null) {
            return;
        }
        inorden(nodo.getIzquierdo(), util);
//        System.out.print(nodo.getValor() + ",");
        util.matriz()[util.contador] = nodo;
        util.contador++;
        inorden(nodo.getDerecho(), util);
    }

    private void inorden(NodoBinario nodo) { //utilizado para saber la cantidad de nodos del abb
        if (nodo == null) {
            return;
        }
        inorden(nodo.getIzquierdo());
//        System.out.print(nodo.getValor() + ",");
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
        this.cantidad = 0;
        inorden(raiz);
        return cantidad;
    }

    public NodoBinario inordenBus(NodoBinario nodo, Utilidades util, String id) {
        System.out.println("Recorrido inorden del árbol binario de búsqueda:");
        inordenBusqueda(nodo, util, id);
        return util.nodoBuscado;
    }

    private void inordenBusqueda(NodoBinario nodo, Utilidades util, String id) { //utilizado para buscar un nodo
        if (nodo == null) {
            return;
        }
        inordenBusqueda(nodo.getIzquierdo(), util, id);
        if (nodo.getValor().toString().equals(id)) {
            System.out.println("NODO.GETVALOR: " + nodo.getValor());
            util.nodoBuscado = nodo;
        }
        inordenBusqueda(nodo.getDerecho(), util, id);
    }
}
