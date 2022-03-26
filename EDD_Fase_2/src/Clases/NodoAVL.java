package Clases;

import edd_fase_2.ListaSimple;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Arnoldo González
 */
public class NodoAVL {

    private final Comparable valor;

    public ListaSimple capas;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    public int altura; //para balancear el arbol    
    private static int contador = 1;
    private final int id;

    public NodoAVL(Comparable valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
        this.id = contador++;
        this.capas = new ListaSimple();
    }

    void insertar(Comparable val) {
        //Si el valor a insertar es menor que el nodo actual, entonces debería
        //insertarse a la izquierda de este. 
        if (val.compareTo(getValor()) < 0) //Si la izquierda del nodo actual esta desocupada entonces se inserta.
        {
            if (getIzquierdo() == null) {
                setIzquierdo(new NodoAVL(val));
            } //De lo contrario nos desplazamos al nodo izquierdo, en busca de un
            //lugar para insertar el nuevo nodo.
            else {
                getIzquierdo().insertar(val);
            }
        } //Si el valor a insertar es mayor que el nodo actual, entonces debería
        //insertarse a la derecha de este de este.
        else if (val.compareTo(getValor()) > 0) //Si la derecha del nodo actual esta desocupada entonces se inserta.
        {
            if (getDerecho() == null) {
                setDerecho(new NodoAVL(val));
            } //De lo contrario nos desplazamos al nodo derecho, en busca de un
            //lugar para insertar el nuevo nodo.            
            else {
                getDerecho().insertar(val);
            }
        } else {
            System.err.println("No se permiten los valores duplicados: \""
                    + String.valueOf(val) + "\".");
        }
    }

    public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter(path+".dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz());
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo .dot");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.err.println("Error al cerrar el archivo .dot");
            }
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tpng -o " + path + ".png " + path + ".dot");
            //Esperar para evitar errores
            Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo AVL");
        }
    }

    private String getCodigoGraphviz() {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "label = \"Arbol AVL\";\n"
                + "bgcolor = \"#8ECBE5\";\n"
                + "node [shape = record, style=filled, fillcolor=\"#FCFF48\"];\n"
                + getCodigoInterno()
                + "}\n";
    }

    private String getCodigoInterno() {
        String etiqueta;
        if (getIzquierdo() == null && getDerecho() == null) {
            etiqueta = "nodo" + id + " [label =\"" + getValor() + "\"];\n";
        } else {
            etiqueta = "nodo" + id + " [label =\"<N0>|" + getValor() + "|<N1>\"];\n";
        }
        if (getIzquierdo() != null) {
            etiqueta = etiqueta + getIzquierdo().getCodigoInterno()
                    + "nodo" + id + ":N0->nodo" + getIzquierdo().id + "\n";
        }
        if (getDerecho() != null) {
            etiqueta = etiqueta + getDerecho().getCodigoInterno()
                    + "nodo" + id + ":N1->nodo" + getDerecho().id + "\n";
        }
        return etiqueta;
    }

    /**
     * @return the valor
     */
    public Comparable getValor() {
        return valor;
    }

    /**
     * @return the izquierdo
     */
    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    /**
     * @param izquierdo the izquierdo to set
     */
    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    /**
     * @return the derecho
     */
    public NodoAVL getDerecho() {
        return derecho;
    }

    /**
     * @param derecho the derecho to set
     */
    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }

}
