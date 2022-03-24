package Clases;

import java.io.FileWriter;
import java.io.PrintWriter;
import edd_fase_2.MatrizDispersa;

/**
 *
 * @author Arnoldo González
 */
public class NodoBinario {

    public MatrizDispersa pixeles;
    private final Comparable valor;
    private NodoBinario izquierdo;
    private NodoBinario derecho;
    /**
     * Variable privada con la que lleva el control de un correlativo que se le
     * asignará a cada nodo que es creado, este será único para cada nodo y
     * servirá para hacer la gráfica del árbol con graphviz.
     */
    private static int correlativo = 1;
    /**
     * Constante privada que posee cada nodo y es única, funciona como
     * identificador y será útil para hacer la gráfica del árbol con graphviz.
     */
    private final int id;

    public NodoBinario(Comparable valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
        this.id = correlativo++;
        this.pixeles = new MatrizDispersa(1);
    }

    public NodoBinario() {
        this.valor = 0;
        this.izquierdo = null;
        this.derecho = null;
        this.id = -1;
        this.pixeles = new MatrizDispersa(1);
    }

    public NodoBinario insertar(Comparable val) {
        //Si el valor es menor que el nodo actual, entonces insertar a la izquierda de este. 
        if (val.compareTo(getValor()) < 0) { //Si la izquierda del nodo actual null insertar.
            if (getIzquierdo() == null) {
                NodoBinario nuevo = new NodoBinario(val);
                setIzquierdo(nuevo);
                return nuevo;
            } //Si no mover a la izq para buscar donde
            else {
                getIzquierdo().insertar(val);
            }
        } //Si el valor  es mayor que el nodo actual, entonces insertat a la derecha de este de este.         
        else if (val.compareTo(getValor()) > 0) { //Si la derecha del nodo actual es nula insertar
            if (getDerecho() == null) {
                NodoBinario nuevo = new NodoBinario(val);
                setDerecho(nuevo);
                return nuevo;
            } //Si no mover a la derecha         
            else {
                getDerecho().insertar(val);
            }
        } else //Si no es mayor ni menor, significa que es igual, entonces se despliega
        //un mensaje de error de que no se aceptan duplicados en el árbol.
        {
            System.err.println("No se permiten los valores duplicados: \""
                    + String.valueOf(val) + "\".");
        }
        return null;
    }

    /**
     * Método que genera el gráfico del árbol binario de búsqueda con graphviz,
     * considerando como la raíz de dicho árbol el actual Nodo.
     *
     */
    public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter("arbol.dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz());
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo aux_grafico.dot");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.err.println("Error al cerrar el archivo aux_grafico.dot");
            }
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tpng -o " + path + " arbol.dot");
            //Esperar para evitar errores
            Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo aux_grafico.dot");
        }
    }

    public String getCodigoGraphviz() {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "label = Arbol Binario de Búsqueda - ABB\n"
                + "bgcolor = #8ECBE5\n"
                + "node [shape = record, style=filled, fillcolor=#FCFF48];\n"
                + getCodigoInterno()
                + "}\n";
    }

    /**
     * Genera el código interior de graphviz, este método tiene la
     * particularidad de ser recursivo, esto porque recorrer un árbol de forma
     * recursiva es bastante sencillo y reduce el código considerablemente.
     *
     * @return
     */
    private String getCodigoInterno() {
        String etiqueta;
        if (getIzquierdo() == null && getDerecho() == null) {
            etiqueta = "nodo" + id + " [ label =\"" + getValor() + "\"];\n";
        } else {
            etiqueta = "nodo" + id + " [ label =\"<C0>|" + getValor() + "|<C1>\"];\n";
        }
        if (getIzquierdo() != null) {
            etiqueta = etiqueta + getIzquierdo().getCodigoInterno()
                    + "nodo" + id + ":C0->nodo" + getIzquierdo().id + "\n";
        }
        if (getDerecho() != null) {
            etiqueta = etiqueta + getDerecho().getCodigoInterno()
                    + "nodo" + id + ":C1->nodo" + getDerecho().id + "\n";
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
