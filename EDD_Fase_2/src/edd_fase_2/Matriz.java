package edd_fase_2;

import Clases.NodoMatriz;

/**
 *
 * @author Arnoldo González
 */
public class Matriz {

    NodoMatriz raiz;

    public Matriz() {
        this.raiz = new NodoMatriz(-1, "Cabeza", -1, -1);
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

    public NodoMatriz crearColumna(int idCapa, int x) {
        NodoMatriz nodoColumna = this.raiz;
        NodoMatriz nuevo = new NodoMatriz(idCapa, "Columna", x, -1);
        NodoMatriz columna = insertarOrdenColumna(nuevo, nodoColumna);
        return columna;
    }

    public NodoMatriz insertarOrdenColumna(NodoMatriz nuevo, NodoMatriz cabezaColumna) {
        NodoMatriz aux = cabezaColumna;
        boolean insertado = false;
        while (true) {
            if (nuevo.getX() == aux.getX()) {
                aux.setY(nuevo.getY());
                aux.setColor(nuevo.getColor());
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

    public NodoMatriz crearFila(int idCapa, int y) {
        NodoMatriz nodoFila = this.raiz;
        NodoMatriz nuevo = new NodoMatriz(idCapa, "Fila", -1, y);
        NodoMatriz fila = insertarOrdenFila(nuevo, nodoFila);
        return fila;
    }

    public NodoMatriz insertarOrdenFila(NodoMatriz nuevo, NodoMatriz cabezaFila) {
        NodoMatriz aux = cabezaFila;
        boolean insertado = false;
        while (true) {
            if (nuevo.getY() == aux.getY()) {
                aux.setX(nuevo.getX());
                aux.setColor(nuevo.getColor());
                return aux;
            } else if (aux.getY() > nuevo.getY()) {
                insertado = true;
                break;
            }
            if (aux.getAbajo() != null) {
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

    public void insertarNodo(int idCapa, int x, int y, String dato) {
        NodoMatriz nuevo = new NodoMatriz(idCapa, dato, x, y);
        NodoMatriz nodoColumna = buscarColumna(x);
        NodoMatriz nodoFila = buscarFila(y);
        if (nodoFila == null && nodoColumna == null) {
            //System.out.println("No existe fila ni col");
            nodoColumna = crearColumna(idCapa, x);
            nodoFila = crearFila(idCapa, y);
            nuevo = insertarOrdenColumna(nuevo, nodoFila);
            nuevo = insertarOrdenFila(nuevo, nodoColumna);

        } else if (nodoFila == null && nodoColumna != null) {
            //System.out.println("No fila, si columna");
            nodoFila = crearFila(idCapa, y);
            nuevo = insertarOrdenColumna(nuevo, nodoFila);
            nuevo = insertarOrdenFila(nuevo, nodoColumna);

        } else if (nodoFila != null && nodoColumna == null) {
            //System.out.println("Si fila, no columna");
            nodoColumna = crearColumna(idCapa, x);
            nuevo = insertarOrdenColumna(nuevo, nodoFila);
            nuevo = insertarOrdenFila(nuevo, nodoColumna);

        } else if (nodoFila != null && nodoColumna != null) {
            //System.out.println("Si los dos");
            nuevo = insertarOrdenColumna(nuevo, nodoFila);
            nuevo = insertarOrdenFila(nuevo, nodoColumna);
        }
    }
    
    public void columnaMayor(){
        
    }

    /*
    1 2 3  4  5 6 
    7 8 9 10 11 12
    13...
     */
    public void graficarMatriz() {
        String contenido = "digraph L{\n"
                + "node[shape = circle fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Matriz Colores\"\n"
                + "bgcolor = \"#8ECBE5\"\n"
                + "edge[dir = \"both\"]\n";
        String nodo = "", enlace = "";
        NodoMatriz actual = raiz;
        while (actual != null) {
            NodoMatriz aux = actual;
            while (aux != null) {
                if (aux.getColor() != "Cabeza" ||aux.getColor() != "Fila" ||aux.getColor() != "Columna" ) {

                    String coordenada = aux.getX() + "_" + aux.getY();
                    nodo += "nodo" + coordenada + "[label = \"" + coordenada + "\", fillcolor = \"" + aux.getColor() + "\"]\n";
                    if (aux.getSiguiente() != null && aux.getSiguiente().getColor() != "Cabeza") {
                        enlace += "nodo" + coordenada + " -> nodo" + aux.getSiguiente().getX() + "_" + aux.getSiguiente().getY() + ";\n";
                    }
//                    if (aux.getAnterior() != null && aux.getAnterior().getColor() != "Cabeza") {
//                        enlace += "nodo" + aux.getAnterior().getX() + "_" + aux.getAnterior().getY() + " -> nodo" + coordenada + ";\n";
//                    }
                    if (aux.getAbajo() != null && aux.getAbajo().getColor() != "Cabeza") {
                        enlace += "nodo" + coordenada + " -> nodo" + aux.getAbajo().getX() + "_" + aux.getAbajo().getY() + ";\n";
                    }
                }
                aux = aux.getSiguiente();
            }
            actual = actual.getAbajo();
        }
        String rank = graficarMatrizRank();
        System.out.println(contenido + nodo + enlace + rank);
    }

    public String graficarMatrizRank() {
        String rank = "";
        NodoMatriz actual = raiz;
        while (actual != null) {
            NodoMatriz aux = actual;
            while (aux != null) {
                boolean primero = true;
                rank += "{rank = same";
                if (aux.getColor() != "Cabeza") {
                    String coordenada = aux.getX() + "_" + aux.getY();
                    if (primero) {
                        rank += ";" + coordenada;
                        primero = false;
                    } else {
                        rank += "," + coordenada;
                    }
                }
                rank += ";\n";
                aux = aux.getSiguiente();
            }
            actual = actual.getAbajo();
        }
        return rank;
    }

    public void imprimir() {
        NodoMatriz aux = raiz;
        while (aux != null) {
            String tex = "";
            NodoMatriz aux2 = aux;
            while (aux2 != null) {
                tex += "[" + (aux2.getX()) + " / " + aux2.getY() + "]";
                aux2 = aux2.getSiguiente();
            }
            System.out.println(tex);
            aux = aux.getAbajo();
        }
    }

}
