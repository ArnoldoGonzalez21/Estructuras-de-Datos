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

    public void inorderArregloNodo(NodoBinario nodo, Utilidades util) { //se encargada de introducir los nodos en el arreglo
        if (nodo == null) {
            return;
        }
        inorderArregloNodo(nodo.getIzquierdo(), util);
        util.matriz()[util.contador] = nodo;
        util.contador++;
        inorderArregloNodo(nodo.getDerecho(), util);
    }

    public int cantidad() {
        this.cantidad = 0;
        inorderCantidad(raiz);
        return cantidad;
    }
    
    private void inorderCantidad(NodoBinario nodo) { //utilizado para saber la cantidad de nodos del abb
        if (nodo == null) {
            return;
        }
        inorderCantidad(nodo.getIzquierdo());
        this.cantidad++;
        inorderCantidad(nodo.getDerecho());
    }

    public NodoBinario inorderBus(NodoBinario nodo, Utilidades util, String id) {
//        System.out.println("Búsqueda inorden del árbol binario de búsqueda:");
        util.nodoBuscado = new NodoBinario();
        inorderBusqueda(nodo, util, id);
        return util.nodoBuscado;
    }

    private void inorderBusqueda(NodoBinario nodo, Utilidades util, String id) { //utilizado para buscar un nodo
        if (nodo == null) {
            return;
        }
        inorderBusqueda(nodo.getIzquierdo(), util, id);
        if (nodo.getValor().toString().equals(id)) {
            System.out.println("Nodo Valor: " + nodo.getValor());
            util.nodoBuscado = nodo;
        }
        inorderBusqueda(nodo.getDerecho(), util, id);
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
    
    //utilizados para obtener el recorrido txt inorder
    private void inorderPrintRec(NodoBinario nodo, Utilidades util) {
        //hijoIzquierdo, raiz, hijoDerecho
        if (nodo == null) {
            return;
        }
        inorderPrintRec(nodo.getIzquierdo(), util);
        util.textRecorrido += " - " + nodo.getValor().toString();
        inorderPrintRec(nodo.getDerecho(), util);
    }

    private void postOrderPrintRec(NodoBinario nodo, Utilidades util) {
        //hijoIzquierdo, hijoDerecho, raiz
        if (nodo == null) {
            return;
        }
        postOrderPrintRec(nodo.getIzquierdo(), util);
        postOrderPrintRec(nodo.getDerecho(), util);
        util.textRecorrido += " - " + nodo.getValor().toString();
    }

    private void preOrderPrintRec(NodoBinario nodo, Utilidades util) {
        //raiz, hijoIzquierdo, hijoDerecho 
        if (nodo == null) {
            return;
        }
        util.textRecorrido += " - " + nodo.getValor().toString();
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
            util.txtCapaHoja += " [" + nodo.getValor().toString() + "] ";
        }
        inorderCapaHoja(nodo.getDerecho(), util);
    }
}
