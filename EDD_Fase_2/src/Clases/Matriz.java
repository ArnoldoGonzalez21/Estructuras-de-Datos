package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Matriz {

    NodoMatriz raiz;

    public Matriz() {
        this.raiz = new NodoMatriz("Raiz", -1, -1);
    }

    public NodoMatriz buscarColumna(int x) {
        NodoMatriz actual = this.raiz;
        while (actual != null) {
            if (actual.getX() == x) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public NodoMatriz buscarFila(int y) {
        NodoMatriz actual = this.raiz;
        while (actual != null) {
            if (actual.getY() == y) {
                return actual;
            }
            actual = actual.getAbajo();
        }
        return null;
    }

    public NodoMatriz crearColumna(int x) {
        NodoMatriz nodoColumna = this.raiz;
        NodoMatriz nuevo = new NodoMatriz("COL", x, -1);
        NodoMatriz columna = insertarOrdenColumna(nuevo, nodoColumna);
        return columna;
    }

    public NodoMatriz insertarOrdenColumna(NodoMatriz nuevo, NodoMatriz cabezaColumna) {
        NodoMatriz aux = cabezaColumna;
        boolean insertado = false;
        while (true) {
            if (nuevo.getX() == aux.getX()) {
                aux.setY(nuevo.getY());
                aux.setDato(nuevo.getDato());
                return aux;
            } else if (aux.getX() > nuevo.getX()) {
                insertado = true;
                break;
            }
            if (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            } else {
                insertado = false;
                break;
            }
        }
        if (insertado) {
            nuevo.setSiguiente(aux);
            aux.getAnterior().setSiguiente(nuevo);
            nuevo.setAnterior(aux.getAnterior());
            aux.setAnterior(nuevo);
        } else {
            aux.setSiguiente(nuevo);
            nuevo.setAnterior(aux);
        }
        return nuevo;
    }

    public NodoMatriz crearFila(int y) {
        NodoMatriz nodoFila = this.raiz;
        NodoMatriz nuevo = new NodoMatriz("Fila", -1, y);
        NodoMatriz fila = insertarOrdenFila(nuevo, nodoFila);
        return fila;
    }

    public NodoMatriz insertarOrdenFila(NodoMatriz nuevo, NodoMatriz cabezaFila) {
        NodoMatriz aux = cabezaFila;
        boolean insertado = false;
        while (true) {
            if (nuevo.getY() == aux.getY()) {
                aux.setX(nuevo.getX());
                aux.setDato(nuevo.getDato());
                return aux;
            } else if (aux.getY() > nuevo.getY()) {
                insertado = true;
                break;
            }
            if (aux.getAbajo()!= null) {
                aux = aux.getAbajo();
            } else {
                insertado = false;
                break;
            }
        }
        if (insertado) {
            nuevo.setAbajo(aux);
            aux.getArriba().setAbajo(nuevo);
            nuevo.setArriba(aux.getArriba());
            aux.setArriba(nuevo);
        } else {
            aux.setAbajo(nuevo);
            nuevo.setArriba(aux);
        }
        return nuevo;
    }

}
