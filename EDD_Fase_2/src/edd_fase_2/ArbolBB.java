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

    public void imprimirRecorrido(NodoBinario nodo, Utilidades util, int tipo) { //tipo 0 -> preorder, 1 -> postorder, 2 -> inorder
        util.textRecorrido = "";
        switch (tipo) {
            case 0:
                System.out.println("Recorrido preorder del árbol binario de búsqueda:");
                preOrderPrintRec(nodo, util);
                break;
            case 1:
                System.out.println("Recorrido postorder del árbol binario de búsqueda:");
                postOrderPrintRec(nodo, util);
                break;
            case 2:
                System.out.println("Recorrido inorder del árbol binario de búsqueda:");
                inorderPrintRec(nodo, util);
                break;
        }
    }

    private void inorderPrintRec(NodoBinario nodo, Utilidades util) { //utilizado para saber la cantidad de nodos del abb
        if (nodo == null) {
            return;
        }
        inorderPrintRec(nodo.getIzquierdo(), util);
        util.textRecorrido += " - " + nodo.getValor().toString();
        System.out.print(nodo.getValor() + ",");
        inorderPrintRec(nodo.getDerecho(), util);
    }

    public void postOrderPrintRec(NodoBinario nodo, Utilidades util) {
        //hijoIzquierdo, hijoDerecho, raiz
        if (nodo == null) {
            return;
        }
        postOrderPrintRec(nodo.getIzquierdo(), util);
        postOrderPrintRec(nodo.getDerecho(), util);
        util.textRecorrido += " - " + nodo.getValor().toString();
        System.out.print(nodo.getValor() + ", ");
    }

    public void preOrderPrintRec(NodoBinario nodo, Utilidades util) {
        //raiz, hijoIzquierdo, hijoDerecho 
        if (nodo == null) {
            return;
        }
        util.textRecorrido += " - " + nodo.getValor().toString();
        System.out.print(nodo.getValor() + ", ");
        preOrderPrintRec(nodo.getIzquierdo(), util);
        preOrderPrintRec(nodo.getDerecho(), util);
    }

    public void imprimirCapaHoja(NodoBinario nodo, Utilidades util) { 
        util.textRecorrido = "";
        System.out.println("Recorrido preorder del árbol binario de búsqueda:");
        inorderCapaHoja(nodo, util);
    }

    private void inorderCapaHoja(NodoBinario nodo, Utilidades util) { //utilizado para saber que nodos son hojas
        if (nodo == null) {
            return;
        }
        inorderCapaHoja(nodo.getIzquierdo(), util);
        if (nodo.getDerecho() == null && nodo.getIzquierdo() == null) {
            util.txtCapaHoja += " - " + nodo.getValor().toString();
//            System.out.print(nodo.getValor() + ",");
        }
        inorderCapaHoja(nodo.getDerecho(), util);
    }

}
