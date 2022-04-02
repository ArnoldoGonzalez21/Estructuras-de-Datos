package edd_fase_2;

import Clases.NodoAVL;
import com.google.gson.JsonArray;

/**
 *
 * @author Arnoldo González
 */
public class ArbolAVL {

    public NodoAVL raiz;
    public int cantidadNodo = 0;

    public ArbolAVL() {
        raiz = null;
    }

    public void insertar(Comparable valor, JsonArray capas, int numCapas) {
        raiz = insertar(valor, raiz, capas, numCapas);
    }

    private NodoAVL insertar(Comparable valor, NodoAVL raiz, JsonArray capas, int numCapas) {
        if (raiz == null) {
            this.cantidadNodo++;
            raiz = new NodoAVL(valor, numCapas);
            for (Object objt2 : capas) {
                raiz.capas.insertarCapa(Integer.parseInt(objt2.toString()));
            }
            //Si el nuevo valor es menor que el nodo actual
        } else if (valor.compareTo(raiz.getValor()) < 0) { //Subarbol izquierdo
            raiz.setIzquierdo(insertar(valor, raiz.getIzquierdo(), capas, numCapas));
            //Si el factor de equilibrio == -2 esta desbalanceo y se hace la rotación
            if (altura(raiz.getDerecho()) - altura(raiz.getIzquierdo()) == -2) {
                if (valor.compareTo(raiz.getIzquierdo().getValor()) < 0) {//Si el valor es menor el valor será insertado a la izq.
                    // realizar una rotación simple por la izquierda                
                    raiz = IzquierdaIzquierda(raiz);
                } else { //si se inserta a la derecha es una rotacion doble por la izquierda
                    raiz = IzquierdaDerecha(raiz);
                }
            }
        } else if (valor.compareTo(raiz.getValor()) > 0) { //Subarbol derecho        
            raiz.setDerecho(insertar(valor, raiz.getDerecho(), capas, numCapas));
            //Si el factor de equilibrio == -2 esta desbalanceo y se hace la rotación
            if (altura(raiz.getDerecho()) - altura(raiz.getIzquierdo()) == 2) {
                if (valor.compareTo(raiz.getDerecho().getValor()) > 0) {//Si el valor es menor el valor será insertado a la derecha.
                    // realizar una rotación simple por la derecha         
                    raiz = DerechaDerecha(raiz);
                } else {//si se inserta a la derecha es una rotacion doble por la derecha
                    raiz = DerechaIzquierda(raiz);
                }
            }
        } else {
            System.err.println("No se permiten los valores duplicados: \""
                    + String.valueOf(valor) + "\".");
        }
        //la altura, será la altura del hijo que tiene la altura más grande
        raiz.altura = mayor(altura(raiz.getIzquierdo()), altura(raiz.getDerecho())) + 1;
        return raiz;
    }

    public void graficar(String path) {
        raiz.graficar(path);
    }

    public void inorder(NodoAVL nodo, Utilidades util) {
//        System.out.println("Recorrido inorder del árbol avl:");
        inordenMatriz(nodo, util);
        System.out.println();
    }

    private void inordenMatriz(NodoAVL nodo, Utilidades util) {
        if (nodo == null) {
            return;
        }
        inordenMatriz(nodo.getIzquierdo(), util);
        util.matrizImagen()[util.contadorAVL] = nodo;
        util.contadorAVL++;
        inordenMatriz(nodo.getDerecho(), util);
    }

    public NodoAVL inorderBus(NodoAVL nodo, Utilidades util, String id) {
//        System.out.println("Busqueda inorder del árbol binario de búsqueda:");
        util.nodoBuscadoAVL = new NodoAVL(-1, 0);
        inorderBusqueda(nodo, util, id);
        return util.nodoBuscadoAVL;
    }

    private void inorderBusqueda(NodoAVL nodo, Utilidades util, String id) { //utilizado para buscar un nodo
        if (nodo == null) {
            return;
        }
        inorderBusqueda(nodo.getIzquierdo(), util, id);
        if (nodo.getValor().toString().equals(id)) {
            System.out.println("NODO.GETVALOR: " + nodo.getValor());
            util.nodoBuscadoAVL = nodo;
        }
        inorderBusqueda(nodo.getDerecho(), util, id);
    }

    private int altura(NodoAVL nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return nodo.altura;
        }
    }

    private int mayor(int n1, int n2) {
        if (n1 > n2) {
            return n1;
        }
        return n2;
    }

    //Rotación simple izquierda izquierda para balanceo.
    private NodoAVL IzquierdaIzquierda(NodoAVL n1) {
        NodoAVL n2 = n1.getIzquierdo();
        n1.setIzquierdo(n2.getDerecho());
        n2.setDerecho(n1);
        n1.altura = mayor(altura(n1.getIzquierdo()), altura(n1.getDerecho())) + 1;
        n2.altura = mayor(altura(n2.getIzquierdo()), n1.altura) + 1;
        return n2;
    }

    //Rotación simple derecha derecha para balanceo.     
    private NodoAVL DerechaDerecha(NodoAVL n1) {
        NodoAVL n2 = n1.getDerecho();
        n1.setDerecho(n2.getIzquierdo());
        n2.setIzquierdo(n1);
        n1.altura = mayor(altura(n1.getIzquierdo()), altura(n1.getDerecho())) + 1;
        n2.altura = mayor(altura(n2.getDerecho()), n1.altura) + 1;
        return n2;
    }

    //Rotación doble izquierda derecha para balanceo.
    private NodoAVL IzquierdaDerecha(NodoAVL n1) {
        n1.setIzquierdo(DerechaDerecha(n1.getIzquierdo()));
        return IzquierdaIzquierda(n1);
    }

    //Rotación doble derecha izquierda para balanceo.
    private NodoAVL DerechaIzquierda(NodoAVL n1) {
        n1.setDerecho(IzquierdaIzquierda(n1.getDerecho()));
        return DerechaDerecha(n1);
    }

    public void imprimirRecorrido(NodoAVL nodo, Utilidades util, int tipo) { //tipo 0 -> preorder, 1 -> postorder, 2 -> inorder
        util.textRecorrido = "";
        switch (tipo) {
            case 0:
                System.out.println("Recorrido preorder del árbol AVL:");
                preOrderPrintRec(nodo, util);
                break;
            case 1:
                System.out.println("Recorrido postorder del árbol AVL:");
                postOrderPrintRec(nodo, util);
                break;
            case 2:
                System.out.println("Recorrido inorder del árbol AVL:");
                inorderPrintRec(nodo, util);
                break;
        }
    }

    private void inorderPrintRec(NodoAVL nodo, Utilidades util) {
        if (nodo == null) {
            return;
        }
        inorderPrintRec(nodo.getIzquierdo(), util);
        util.textRecorrido += " - " + nodo.getValor().toString();
        System.out.print(nodo.getValor() + ",");
        inorderPrintRec(nodo.getDerecho(), util);
    }

    private void postOrderPrintRec(NodoAVL nodo, Utilidades util) {
        //hijoIzquierdo, hijoDerecho, raiz
        if (nodo == null) {
            return;
        }
        postOrderPrintRec(nodo.getIzquierdo(), util);
        postOrderPrintRec(nodo.getDerecho(), util);
        util.textRecorrido += " - " + nodo.getValor().toString();
        System.out.print(nodo.getValor() + ", ");
    }

    private void preOrderPrintRec(NodoAVL nodo, Utilidades util) {
        //raiz, hijoIzquierdo, hijoDerecho 
        if (nodo == null) {
            return;
        }
        util.textRecorrido += " - " + nodo.getValor().toString();
        System.out.print(nodo.getValor() + ", ");
        preOrderPrintRec(nodo.getIzquierdo(), util);
        preOrderPrintRec(nodo.getDerecho(), util);
    }

    public void imprimirAltura(NodoAVL nodo, Utilidades util) { //tipo 0 -> preorder, 1 -> postorder, 2 -> inorder
        util.alturaAVL = 0;
        System.out.println("Recorrido Altura del árbol AVL:");
        postOrderAltura(nodo, util);
    }

    private void postOrderAltura(NodoAVL nodo, Utilidades util) {
        //hijoIzquierdo, hijoDerecho, raiz
        if (nodo == null) {
            return;
        }
        postOrderAltura(nodo.getIzquierdo(), util);
        postOrderAltura(nodo.getDerecho(), util);
        if (nodo.altura > util.alturaAVL) {
            util.alturaAVL = nodo.altura;
        }
        System.out.print(" ,Valor: " + nodo.getValor() + " - altura: " + nodo.altura);
    }

    public NodoAVL[] TopCinco(NodoAVL nodo, Utilidades util) {
        inorderBuscarTopCinco(nodo, util);
        util.ordenarArreglo();
        return util.topCapa;
    }

    private void inorderBuscarTopCinco(NodoAVL nodo, Utilidades util) {
        if (nodo == null) {
            return;
        }
        inorderBuscarTopCinco(nodo.getIzquierdo(), util);
        boolean entro = false;
        for (int i = 0; i < util.topCapa.length; i++) {
            if (util.topCapa[i] == null) {
                util.topCapa[i] = nodo;
                System.out.println("valor: " + nodo.getValor() + " - num: " + nodo.numCapas);
                entro = true;
                break;
            }
        }
        if (!entro) {
            for (int i = 0; i < util.topCapa.length; i++) {
                if (util.topCapa[i] != null) {
                    if (nodo.numCapas > util.topCapa[i].numCapas) {
                        util.topCapa[i] = nodo;
                        System.out.println("valor: " + nodo.getValor() + " - num: " + nodo.numCapas);
                        break;
                    }
                }
            }
        }
        util.ordenarArreglo();
        inorderBuscarTopCinco(nodo.getDerecho(), util);
    }

    public int cantidadAVL() {
        return this.cantidadNodo;
    }
}
