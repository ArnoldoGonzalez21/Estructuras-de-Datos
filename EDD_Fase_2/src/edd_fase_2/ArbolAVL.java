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

    public void insertar(Comparable valor, JsonArray capas) {
        raiz = insertar(valor, raiz, capas);
    }

    private NodoAVL insertar(Comparable valor, NodoAVL raiz, JsonArray capas) {
        if (raiz == null) {
            this.cantidadNodo++;
            raiz = new NodoAVL(valor);
            for (Object objt2 : capas) {
                raiz.capas.insertarCapa(Integer.parseInt(objt2.toString()));
//                System.out.println("id: " + raiz.getValor() + " no: " + Integer.parseInt(objt2.toString()));
            }
            //Si el nuevo valor es menor que el nodo actual
        } else if (valor.compareTo(raiz.getValor()) < 0) {
            //Se llama recursivamente al método para explorar el subarbol izquierdo porque el valor es menor
            raiz.setIzquierdo(insertar(valor, raiz.getIzquierdo(), capas));
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
            raiz.setDerecho(insertar(valor, raiz.getDerecho(), capas));
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

    private void inordenPrint(NodoAVL nodo, Utilidades util) {
        if (nodo == null) {
            return;
        }
        inorden(nodo.getIzquierdo(), util);
        System.out.println("Id: " + nodo.getValor() + " Size: " + nodo.capas.sizeCapa + "\n");
        Clases.Capa actual = nodo.capas.getCabezaCapa();
        while (actual != null) {
            System.out.print(actual.getIdCapa() + ", ");
            actual = actual.getSiguiente();
        }
        inorden(nodo.getDerecho(), util);
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
}
