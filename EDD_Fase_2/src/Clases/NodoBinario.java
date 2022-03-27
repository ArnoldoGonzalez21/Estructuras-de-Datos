package Clases;

import java.io.FileWriter;
import java.io.PrintWriter;
import edd_fase_2.MatrizDispersa;

/**
 *
 * @author Arnoldo González
 */
public class NodoBinario {

    public MatrizDispersa pixeles = new MatrizDispersa(1);
    private final Comparable valor;
    private NodoBinario izquierdo;
    private NodoBinario derecho;
    private static int correlativo = 1;
    private final int id;

    public NodoBinario(Comparable valor, MatrizDispersa pixeles) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
        this.id = correlativo++;
        this.pixeles = pixeles;
    }

    public NodoBinario() {
        this.valor = -1; //cambie estaba en 0
        this.izquierdo = null;
        this.derecho = null;
        this.id = -1;
        this.pixeles = new MatrizDispersa(1);
    }

    public NodoBinario insertar(Comparable val, MatrizDispersa pixeles) {
        //Si el valor es menor que el nodo actual, entonces insertar a la izquierda de este. 
        if (val.compareTo(getValor()) < 0) { //Si la izquierda del nodo actual null insertar.
            if (getIzquierdo() == null) {
                NodoBinario nuevo = new NodoBinario(val, pixeles);
                setIzquierdo(nuevo);
                return nuevo;
            } //Si no mover a la izq para buscar donde
            else {
                getIzquierdo().insertar(val, pixeles);
            }
        } //Si el valor  es mayor que el nodo actual, entonces insertat a la derecha de este de este.         
        else if (val.compareTo(getValor()) > 0) { //Si la derecha del nodo actual es nula insertar
            if (getDerecho() == null) {
                NodoBinario nuevo = new NodoBinario(val, pixeles);
                setDerecho(nuevo);
                return nuevo;
            } //Si no mover a la derecha         
            else {
                getDerecho().insertar(val, pixeles);
            }
        } else //Si no es mayor ni menor, significa que es igual, entonces se despliega
        //un mensaje de error de que no se aceptan duplicados en el árbol.
        {
            System.err.println("No se permiten los valores duplicados: \""
                    + String.valueOf(val) + "\".");
        }
        return null;
    }

    public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter(path + ".dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz());
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo arbolABB.dot");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.err.println("Error al cerrar el archivo arbolABB.dot");
            }
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tpng -o " + path + ".png " + path + ".dot");
            //Esperar para evitar errores
            Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen");
        }
    }

    public String getCodigoGraphviz() {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "label = \"Arbol Binario de Búsqueda - ABB\";\n"
                + "bgcolor = \"#8ECBE5\";\n"
                + "node [shape = record, style=filled, fillcolor=\"#FCFF48\"];\n"
                + getCodigoInterno()
                + "}\n";
    }

    private String getCodigoInterno() {
        String etiqueta;
        if (getIzquierdo() == null && getDerecho() == null) {
            etiqueta = "nodo" + id + "[label =\"" + getValor() + "\"];\n";
        } else {
            etiqueta = "nodo" + id + "[label =\"<N0>|" + getValor() + "|<N1>\"];\n";
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
     * @return the izquierdo
     */
    public NodoBinario getIzquierdo() {
        return izquierdo;
    }

    /**
     * @param izquierdo the izquierdo to set
     */
    public void setIzquierdo(NodoBinario izquierdo) {
        this.izquierdo = izquierdo;
    }

    /**
     * @return the derecho
     */
    public NodoBinario getDerecho() {
        return derecho;
    }

    /**
     * @param derecho the derecho to set
     */
    public void setDerecho(NodoBinario derecho) {
        this.derecho = derecho;
    }

    /**
     * @return the valor
     */
    public Comparable getValor() {
        return valor;
    }
}
