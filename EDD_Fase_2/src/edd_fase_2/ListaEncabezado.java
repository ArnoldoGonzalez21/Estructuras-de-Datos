package edd_fase_2;

import Clases.NodoEncabezado;

/**
 *
 * @author Arnoldo González
 */
public class ListaEncabezado {

    NodoEncabezado primero;
    NodoEncabezado ultimo;
    String tipo;
    int size;

    public ListaEncabezado(String tipo) {
        primero = null;
        ultimo = null;
        this.tipo = tipo;
        size = 0;
    }

    public void insertarNodoEncabezado(NodoEncabezado nuevo) {
        this.size++;
        if (this.primero == null) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            //nuevo es menor que el primero
            if (nuevo.getId() < this.primero.getId()) {
                nuevo.setSiguiente(this.primero);
                this.primero.setAnterior(nuevo);
                this.primero = nuevo;
            } //nuevo es mayor a ultimo
            else if (nuevo.getId() > ultimo.getId()) {
                ultimo.setSiguiente(nuevo);
                nuevo.setAnterior(ultimo);
                this.ultimo = nuevo;
            } //en medio
            else {
                NodoEncabezado tmp = this.primero;
                while (tmp != null) {
                    if (nuevo.getId() < tmp.getId()) {
                        nuevo.setSiguiente(tmp);
                        nuevo.setAnterior(tmp.getAnterior());
                        tmp.getAnterior().setSiguiente(nuevo);
                        tmp.setAnterior(nuevo);
                        break;
                    } else if (nuevo.getId() > tmp.getId()) {
                        tmp = tmp.getSiguiente();
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public String graficaEncabezado(boolean columna_grafica) {

        NodoEncabezado actual = this.primero;
        String contRank = "", contFila = "", contCol = "";
        if (columna_grafica) {
            contRank = "{rank = same;raiz";
        }
        while (actual != null) {
            if ("Fila".equals(this.tipo)) {
                contFila += "F" + actual.getId() + "[label=\"F" + actual.getId() + "\",group = 1, fillcolor = white];\n";
            } else if ("Columna".equals(this.tipo)) {
                contCol += "C" + actual.getId() + "[label=\"C" + actual.getId() + "\",group= " + (actual.getId() + 1) + ",fillcolor=white];\n";
                contRank += "; C" + actual.getId();
            }
            actual = actual.getSiguiente();

        }
        if (columna_grafica) {
            contRank += "}";
            contCol += contRank;
            return contCol;
        } else {
            return contFila;
        }
    }

    public void mostrarEncabezados() {
        NodoEncabezado tmp = this.primero;
        while (tmp != null) {
            System.out.println("Encabezado: " + this.tipo + " " + tmp.getId());
            tmp = tmp.getSiguiente();
        }
    }

    public NodoEncabezado getEncabezado(int id) {
        NodoEncabezado tmp = this.primero;
        while (tmp != null) {
            if (id == tmp.getId()) {
                return tmp;
            }
            tmp = tmp.getSiguiente();
        }
        return null;
    }

}
