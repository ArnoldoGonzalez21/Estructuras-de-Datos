package edd_fase_2;

import Clases.NodoEncabezado;
import Clases.NodoMatriz;

/**
 *
 * @author Arnoldo González
 */
public class MatrizDispersa {

    ListaEncabezado filas, columnas;
    int capa;

    public MatrizDispersa(int capa) {
        filas = new ListaEncabezado("Fila");
        columnas = new ListaEncabezado("Columna");
        this.capa = capa;
    }

    public void insertar(int idCapa, int posX, int posY, String color) {
        NodoMatriz nuevo = new NodoMatriz(idCapa, color, posX, posY);
        //buscar si ya existen encabezados
        NodoEncabezado nodo_X = this.filas.getEncabezado(posX);
        NodoEncabezado nodo_Y = this.columnas.getEncabezado(posY);

        //crearlos
        if (nodo_X == null) {
            nodo_X = new NodoEncabezado(posX);
            this.filas.insertarNodoEncabezado(nodo_X);
        }

        if (nodo_Y == null) {
            nodo_Y = new NodoEncabezado(posY);
            this.columnas.insertarNodoEncabezado(nodo_Y);
        }
        //Insertar nuevo en Fila
        if (nodo_X.getAcceso() == null) { //si no apunta a un nodo
            nodo_X.setAcceso(nuevo);
        } else { //validar si la posicion de la columna del nuevo nodo es menor a la posicion de la columna del acceso 
            if (nuevo.getY() < nodo_X.getAcceso().getY()) {
                nuevo.setSiguiente(nodo_X.getAcceso());
                nodo_X.getAcceso().setAnterior(nuevo);
                nodo_X.setAcceso(nuevo);
            } else { //buscar posicion donde va
                NodoMatriz tmp = nodo_X.getAcceso();
                while (tmp != null) {
                    if (nuevo.getY() < tmp.getY()) {
                        nuevo.setSiguiente(tmp);
                        nuevo.setAnterior(tmp.getAnterior());
                        tmp.getAnterior().setSiguiente(nuevo);
                        tmp.setAnterior(nuevo);
                        break;
                    } else if (nuevo.getX() == tmp.getX() && nuevo.getY() == tmp.getY()) { //repetido
                        break;
                    } else {
                        if (tmp.getSiguiente() == null) {
                            tmp.setSiguiente(nuevo);
                            nuevo.setAnterior(tmp);
                            break;
                        } else {
                            tmp = tmp.getSiguiente();
                        }
                    }
                }
            }
        }

        //Insertar nuevo en Columna
        if (nodo_Y.getAcceso() == null) { //si no apunta a un nodo
            nodo_Y.setAcceso(nuevo);
        } else { //validar si la posicion de la columna del nuevo nodo es menor a la posicion de la columna del acceso 
            if (nuevo.getX() < nodo_Y.getAcceso().getX()) {
                nuevo.setAbajo(nodo_Y.getAcceso());
                nodo_Y.getAcceso().setArriba(nuevo);
                nodo_Y.setAcceso(nuevo);
            } else { //buscar posicion donde va
                NodoMatriz tmp = nodo_Y.getAcceso();
                while (tmp != null) {
                    if (nuevo.getX() < tmp.getX()) {
                        nuevo.setAbajo(tmp);
                        nuevo.setArriba(tmp.getArriba());
                        tmp.getArriba().setAbajo(nuevo);
                        tmp.setArriba(nuevo);
                        break;
                    } else if (nuevo.getX() == tmp.getX() && nuevo.getY() == tmp.getY()) { //repetido
                        break;
                    } else {
                        if (tmp.getAbajo() == null) {
                            tmp.setAbajo(nuevo);
                            nuevo.setArriba(tmp);
                            break;
                        } else {
                            tmp = tmp.getAbajo();
                        }
                    }
                }
            }
        }
    }

    public String enlazarNodo() {
        NodoEncabezado pivote = this.filas.primero;
        String contNodo = "", contEnlaceFila = "", contEnlaceCol = "", contRank = "";
        while (pivote != null) {
            boolean entroPrimFila = false;
            NodoMatriz pivoteCelda = pivote.getAcceso();
            contEnlaceFila += "F" + pivote.getId() + " -> ";
            contRank += "{rank=same;F" + pivote.getId();
            while (pivoteCelda != null) {
                NodoMatriz tmpPivoteCelda = pivoteCelda.getAnterior();
                contNodo += "nodo" + pivoteCelda.getX() + "_" + pivoteCelda.getY();
                contNodo += "[label=\"" + pivoteCelda.getColor() + "\", fillcolor = \"" + pivoteCelda.getColor() + "\", group = " + (pivoteCelda.getY() + 1) + ",fontcolor = \"" + pivoteCelda.getColor() + "\"]\n";
                if (pivote.getId() == pivoteCelda.getX()) {
                    contRank += ";nodo" + pivoteCelda.getX() + "_" + pivoteCelda.getY();
                    if (!entroPrimFila) {
                        contEnlaceFila += "nodo" + pivoteCelda.getX() + "_" + pivoteCelda.getY() + ";\n";
                        entroPrimFila = true;
                    } else {
                        if (tmpPivoteCelda != null) {
                            contEnlaceFila += "nodo" + tmpPivoteCelda.getX() + "_" + tmpPivoteCelda.getY() + " -> " + "nodo" + pivoteCelda.getX() + "_" + pivoteCelda.getY() + ";\n";
                        }
                    }

                }
                pivoteCelda = pivoteCelda.getSiguiente();
            }
            contRank += "}\n";
            pivote = pivote.getSiguiente();
        }

        pivote = this.columnas.primero;
        while (pivote != null) {
            boolean entroPrimCol = false;
            NodoMatriz pivoteCelda = pivote.getAcceso();
            contEnlaceCol += "C" + pivote.getId() + " -> ";
            while (pivoteCelda != null) {
                NodoMatriz tmpPivoteCelda = pivoteCelda.getArriba();
                if (pivote.getId() == pivoteCelda.getY()) {
                    if (!entroPrimCol) {
                        contEnlaceCol += "nodo" + pivoteCelda.getX() + "_" + pivoteCelda.getY() + ";\n";
                        entroPrimCol = true;
                    } else {
                        if (tmpPivoteCelda != null) {
                            contEnlaceCol += "nodo" + tmpPivoteCelda.getX() + "_" + tmpPivoteCelda.getY() + " -> " + "nodo" + pivoteCelda.getX() + "_" + pivoteCelda.getY() + ";\n";
                        }
                    }

                }
                pivoteCelda = pivoteCelda.getAbajo();
            }
            pivote = pivote.getSiguiente();
        }
        contNodo += contEnlaceFila + contRank + contEnlaceCol;
        return contNodo;
    }

    public String enlazarNodo2() {
        NodoEncabezado pivote = this.filas.primero;
        String contNodo = "", contEnlaceFila = "", contEnlaceCol = "", contRank = "", contEnlaceFilaNodo = "", contEnlaceColumnaNodo = "";
        String contFila = "", contColumna = "", contRankCol = "{rank = same";
        boolean unEnlaceC = false, unaVez = false;
        int contadorF = 0;
        int n = this.columnas.ultimo.getId();
        int m = this.filas.ultimo.getId();
        while (contadorF <= m) {
            contFila += "F" + contadorF + "[label=\"F" + contadorF + "\",group = 1, fillcolor = white];\n";
            int contadorC = 0;
            contEnlaceFilaNodo += "F" + contadorF + " -> nodo" + contadorF + "_0;\n";
            contRank += "{rank=same;F" + contadorF;
            while (contadorC <= n) {
                contNodo += "nodo" + contadorF + "_" + contadorC + "[label = \"white\" fillcolor=\"white\", group = " + (contadorC + 1) + ", fontcolor = white];\n";
                contRank += ";nodo" + contadorF + "_" + contadorC;
                if (!unaVez) {
                    if (contadorF + 1 <= m) {
                        contEnlaceColumnaNodo += "nodo" + contadorF + "_" + contadorC + " -> nodo" + (contadorF + 1) + "_" + contadorC + ";";
                    }
                }
                if (!unEnlaceC) {
                    contColumna += "C" + contadorC + "[label=\"C" + contadorC + "\",group= " + (contadorC + 1) + ",fillcolor=white];\n";
                    contRankCol += "; C" + contadorC;
                    if (contadorC + 1 <= n) {
                        contEnlaceCol += "C" + contadorC + " -> nodo" + contadorF + "_" + contadorC + ";\n";
                        contEnlaceCol += "C" + contadorC + " -> C" + (contadorC + 1) + ";";
                    }
                }
                if (contadorC + 1 <= n) {
                    contEnlaceFila += "nodo" + contadorF + "_" + contadorC + " -> nodo" + contadorF + "_" + (contadorC + 1) + ";\n";

                }
                contadorC++;
            }
            contRank += "}\n";
            unEnlaceC = true;
            if (contadorF + 1 <= m) {
                contEnlaceFila += "F" + contadorF + " -> F" + (contadorF + 1) + ";";
            }
            contadorF++;
        }

        contRankCol += "}\n";

        while (pivote != null) {
            NodoMatriz pivoteCelda = pivote.getAcceso();
            while (pivoteCelda != null) {
                contNodo += "nodo" + pivoteCelda.getX() + "_" + pivoteCelda.getY();
                contNodo += "[label=\"" + pivoteCelda.getColor() + "\", fillcolor = \"" + pivoteCelda.getColor() + "\", group = " + (pivoteCelda.getY() + 1) + ", fontcolor = \"" + pivoteCelda.getColor() + "\"]\n";
                pivoteCelda = pivoteCelda.getSiguiente();
            }
            pivote = pivote.getSiguiente();
        }

        contNodo += contEnlaceFila + contRank + contEnlaceCol + contEnlaceFilaNodo + contEnlaceColumnaNodo;
        contNodo += contFila + contColumna + contRankCol;
        return contNodo;
    }

    public String graficarMatriz() {
        String contenido = "digraph L{\n"
                + "node[shape = box fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Matriz Colores\"\n"
                + "bgcolor = \"#8ECBE5\"\n"
                + "edge[dir = \"both\"]\n";
        String encabezados = this.filas.graficaEncabezado(false);
        encabezados += this.columnas.graficaEncabezado(true);
        String nodos = this.enlazarNodo();
        String final_graphviz = "\n\t}\n}";
        contenido += nodos + encabezados + final_graphviz;
        System.out.println(contenido);
        return contenido;
    }

    public MatrizDispersa recorrerMatriz(Utilidades util) {
        NodoEncabezado pivote = this.filas.primero;
        while (pivote != null) {
            NodoMatriz pivoteCelda = pivote.getAcceso();
            while (pivoteCelda != null) {
                System.out.println(pivoteCelda.getX() + "_" + pivoteCelda.getY());
                util.matrizImagenCompleta.insertar(-1, pivoteCelda.getX(), pivoteCelda.getY(), pivoteCelda.getColor());
                pivoteCelda = pivoteCelda.getSiguiente();
            }
            pivote = pivote.getSiguiente();
        }
        return util.matrizImagenCompleta;
    }

}
