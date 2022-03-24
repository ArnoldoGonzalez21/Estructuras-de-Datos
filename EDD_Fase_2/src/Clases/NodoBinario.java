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
    /**
     * Variable que almacena el valor específico del nodo.
     */
    private final Comparable valor;
    /**
     * Variable que apunta hacia el nodo izquierdo de este nodo.
     */
    private NodoBinario izquierdo;
    /**
     * Variable que apunta hacia el nodo derecho de este nodo.
     */
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

    /**
     * Constructor de la clase NodoBinario.
     *
     * @param valor Valor específico que el nodo almacenará.
     */
    public NodoBinario(Comparable valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
        this.id = correlativo++;
        this.pixeles = new MatrizDispersa(1);
    }

    /**
     * Método que inserta un Nodo en el árbol binario de búsqueda.
     *
     * @param val Valor que se desea insertar.
     * @return 
     */
    public NodoBinario insertar(Comparable val) {
        //Si el valor a insertar es menor que el nodo actual, entonces debería
        //insertarse a la izquierda de este. 
        if (val.compareTo(getValor()) < 0) { //Si la izquierda del nodo actual esta desocupada entonces se inserta.
            if (getIzquierdo() == null) {
                NodoBinario nuevo = new NodoBinario(val);
                setIzquierdo(nuevo);
                return nuevo;
            } //De lo contrario nos desplazamos al nodo izquierdo, en busca de un
            //lugar para insertar el nuevo nodo.
            else {
                getIzquierdo().insertar(val);
            }
        } //Si el valor a insertar es mayor que el nodo actual, entonces debería
        //insertarse a la derecha de este de este.         
        else if (val.compareTo(getValor()) > 0){ //Si la derecha del nodo actual esta desocupada entonces se inserta.
            if (getDerecho() == null) {
                NodoBinario nuevo = new NodoBinario(val);
                setDerecho(nuevo);
                return nuevo;
            } //De lo contrario nos desplazamos al nodo derecho, en busca de un
            //lugar para insertar el nuevo nodo.            
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
     * @param path Ruta de la imagen que se generará.
     */
    public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter("aux_grafico.dot");
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
            rt.exec("dot -Tjpg -o " + path + " aux_grafico.dot");
            //Esperamos medio segundo para dar tiempo a que la imagen se genere.
            //Para que no sucedan errores en caso de que se decidan graficar varios
            //árboles sucesivamente.
            Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo aux_grafico.dot");
        }
    }

    /**
     * Método que retorna el código que grapviz usará para generar la imagen del
     * árbol binario de búsqueda.
     *
     * @return
     */
    public String getCodigoGraphviz() {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "node [shape = record, style=filled, fillcolor=seashell2];\n"
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
