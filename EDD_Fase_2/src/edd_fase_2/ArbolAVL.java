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
//                System.out.println("id: " + raiz.getValor() + " no: " + Integer.parseInt(objt2.toString()));
            }
            //Si el nuevo valor es menor que el nodo actual
        } else if (valor.compareTo(raiz.getValor()) < 0) {
            //Se llama recursivamente al método para explorar el subarbol izquierdo porque el valor es menor
            raiz.setIzquierdo(insertar(valor, raiz.getIzquierdo(), capas, numCapas));
            if (altura(raiz.getDerecho()) - altura(raiz.getIzquierdo()) == -2) //Si el factor de equilibrio esta desbalanceado, hay que hacer 
            //rotación de nodos, como el factor de equilibrio = -2 hay dos posibilidades de 
            //rotación dependiendo de:
            {
                if (valor.compareTo(raiz.getIzquierdo().getValor()) < 0) //Si el nuevo valor fuera menor que la izquierda del nodo des-balanceado, 
                //se sabe que el nuevo nodo será insertado a la 
                //izquierda de la actual izquierda, entonces tenemos una rotación 
                //simple por la izquierda o sea una IzquierdaIzquierda.
                {
                    raiz = IzquierdaIzquierda(raiz);
                } else //de lo contrario, se sabe que el nuevo nodo será insertado 
                //a la derecha del la actual izquierda, por lo que se tiene 
                //un caso de rotación doble por la izquierda 
                //o sea una IzquierdaDerecha.
                {
                    raiz = IzquierdaDerecha(raiz);
                }
            }
        } else if (valor.compareTo(raiz.getValor()) > 0) //Si el nuevo valor fuera mayor que el nodo de la actual entonces:
        {
            //Se llama recursivamente al método para explorar el subarbol derecho
            //porque el valor a insertar es mayor que el del nodo actual.            
            raiz.setDerecho(insertar(valor, raiz.getDerecho(), capas, numCapas));
            if (altura(raiz.getDerecho()) - altura(raiz.getIzquierdo()) == 2) //Si el factor de equilibrio esta desbalanceado, hay que hacer 
            //rotación de nodos, como el fe=2 hay dos posibilidades de 
            //rotación dependiendo de:                
            {
                if (valor.compareTo(raiz.getDerecho().getValor()) > 0) //Si el nuevo valor fuera mayor que la derecha del nodo des-
                //balanceado, se sabe que el nuevo nodo será insertado a la 
                //derecha de la actual derecha, entonces tenemos una rotación 
                //simple por la derecha o sea una DerechaDerecha.                    
                {
                    raiz = DerechaDerecha(raiz);
                } else //de lo contrario, se sabe que el nuevo nodo será insertado 
                //a la izquierda del la actual derecha, por lo que se tiene 
                //un caso de rotación doble por la derecha
                //o sea una DerechaIzquierda.
                {
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

    private NodoAVL eliminar(NodoAVL nodo, NodoAVL raiz, Utilidades util, String valor) {
        if (nodo.getDerecho() == null && nodo.getIzquierdo() == null) { //es una hoja
            nodo = null;
        } else if (nodo.getDerecho() == null && nodo.getIzquierdo() != null) { //conectar el padre del nodo a eliminar con el hijo
            encontrarPadre(nodo, raiz, util, valor);

        } else if (nodo.getDerecho() != null && nodo.getIzquierdo() == null) { //conectar el padre del nodo a eliminar con el hijo
            encontrarPadre(nodo, raiz, util, valor);
        }
        raiz.altura = mayor(altura(raiz.getIzquierdo()), altura(raiz.getDerecho())) + 1;
        return raiz;
    }

    public void encontrarPadre(NodoAVL nodoEliminar, NodoAVL raiz, Utilidades util, String valor) {
        encontrarNodoRecorrido(nodoEliminar, raiz, util, valor);
    }

    public void encontrarNodoRecorrido(NodoAVL nodoEliminar, NodoAVL nodoRaiz, Utilidades util, String valor) {
        //hijoIzquierdo, hijoDerecho, raiz
        if (nodoEliminar == null) {
            return;
        }
        encontrarNodoRecorrido(nodoRaiz.getIzquierdo(), nodoRaiz, util, valor);
        encontrarNodoRecorrido(nodoRaiz.getDerecho(), nodoRaiz, util, valor);
        util.textRecorrido += " - " + nodoRaiz.getValor().toString();
        if (nodoRaiz.getDerecho() != null) {
            if (nodoRaiz.getDerecho().getValor().toString().equals(valor)) {
                util.nodoPadreEliminar = nodoRaiz;
                return;
            }
        }
        if (nodoRaiz.getIzquierdo() != null) {
            if (nodoRaiz.getIzquierdo().getValor().toString().equals(valor)) {
                util.nodoPadreEliminar = nodoRaiz;
                return;
            }
        }
        System.out.print(nodoRaiz.getValor() + ", ");
    }

    public void graficar(String path) {
        raiz.graficar(path);
    }

    public void inorden(NodoAVL nodo, Utilidades util) {
//        System.out.println("Recorrido inorden del árbol avl:");
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

    public NodoAVL inordenBus(NodoAVL nodo, Utilidades util, String id) {
        System.out.println("Recorrido inorden del árbol binario de búsqueda:");
        inordenBusqueda(nodo, util, id);
        return util.nodoBuscadoAVL;
    }

    private void inordenBusqueda(NodoAVL nodo, Utilidades util, String id) { //utilizado para buscar un nodo
        if (nodo == null) {
            return;
        }
        inordenBusqueda(nodo.getIzquierdo(), util, id);
        if (nodo.getValor().toString().equals(id)) {
            System.out.println("NODO.GETVALOR: " + nodo.getValor());
            util.nodoBuscadoAVL = nodo;
        }
        inordenBusqueda(nodo.getDerecho(), util, id);
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

    public void postOrderPrintRec(NodoAVL nodo, Utilidades util) {
        //hijoIzquierdo, hijoDerecho, raiz
        if (nodo == null) {
            return;
        }
        postOrderPrintRec(nodo.getIzquierdo(), util);
        postOrderPrintRec(nodo.getDerecho(), util);
        util.textRecorrido += " - " + nodo.getValor().toString();
        System.out.print(nodo.getValor() + ", ");
    }

    public void preOrderPrintRec(NodoAVL nodo, Utilidades util) {
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

    public void postOrderAltura(NodoAVL nodo, Utilidades util) {
        //hijoIzquierdo, hijoDerecho, raiz
        if (nodo == null) {
            return;
        }
        postOrderAltura(nodo.getIzquierdo(), util);
        postOrderAltura(nodo.getDerecho(), util);
        if (util.alturaAVL < nodo.altura) {
            util.alturaAVL = nodo.altura;
        }
        System.out.print(nodo.getValor() + " , - alt: " + nodo.altura);
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
//        System.out.println("Id: " + nodo.getValor() + " Size: " + nodo.capas.sizeCapa + "\n");
//        Clases.Capa actual = nodo.capas.getCabezaCapa();
//        int contador = 0;
//        while (actual != null) {
//            contador++;
////            System.out.print(actual.getIdCapa() + ", ");
//            actual = actual.getSiguiente();
//        }
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
}
