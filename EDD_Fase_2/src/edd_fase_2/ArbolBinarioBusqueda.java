package edd_fase_2;

import Clases.Capa;

/**
 *
 * @author Arnoldo González
 */
public class ArbolBinarioBusqueda {

    Capa raiz;

    public void ArbolBinarioBusqueda() {
        raiz = new Capa();
    }

    public boolean esVacio() {
        return (raiz == null);
    }

    public Capa insertar(int id) {
        if (esVacio()) {
            Capa nuevo = new Capa();
            nuevo.setIdCapa(id);
            nuevo.setHijoDer(new ArbolBinarioBusqueda());
            nuevo.setHijoIzq(new ArbolBinarioBusqueda());
            raiz = nuevo;
            return nuevo;
        } else {
            if (id > raiz.getIdCapa()) {
                Capa nuevo = (raiz.getHijoDer()).insertar(id);
                return nuevo;
            }
            if (id < raiz.getIdCapa()) {
                Capa nuevo = (raiz.getHijoIzq()).insertar(id);
                return nuevo;
            }
        }
        return null;
    }

    public void preOrder() {
        //raiz, hijoIzquierdo, hijoDerecho 
        if (!esVacio()) {
            System.out.print(raiz.getIdCapa() + ", ");
            raiz.getHijoIzq().preOrder();
            raiz.getHijoDer().preOrder();
        }
    }

//    public void graficaPreOrder(Grafica grafica) {
//        if (!esVacio()) {
//
//            System.out.println("nodo" + raiz.getIdCapa() + "[label = " + raiz.getIdCapa() + " ,fillcolor = \"#FCF8F7\"];");
//            grafica.contenidoABB += "nodo" + raiz.getIdCapa() + "[label = " + raiz.getIdCapa() + " ,fillcolor = \"#FCF8F7\"];\n";
//            if (!raiz.getHijoIzq().esVacio()) {
//                grafica.enlaceABBIzq += "nodo" + raiz.getIdCapa() + " -> ";
//                grafica.tienePadreIzq = true;
//            }
//            if (!raiz.getHijoDer().esVacio()) {
//                grafica.enlaceABBDer += "nodo" + raiz.getIdCapa() + " -> ";
//                grafica.tienePadreDer = true;
//            }
//            raiz.getHijoIzq().graficaPreOrder(grafica);
//            if (grafica.tienePadreIzq) {
//                grafica.enlaceABBIzq += "nodo" + raiz.getIdCapa() + ";\n";
//                grafica.tienePadreIzq = false;
//            } //revisar derecho no sirve
//            raiz.getHijoDer().graficaPreOrder(grafica);
//            if (grafica.tienePadreDer) {
//                grafica.enlaceABBDer += "Dernodo" + raiz.getIdCapa() + ";\n";
//                grafica.tienePadreDer = false;
//            }
//        }
//    }

    public void preOrder2() {
        //raiz, hijoIzquierdo, hijoDerecho 
        if (!esVacio()) {
            System.out.print(raiz.getIdCapa() + ", ");
            //raiz.pixeles.imprimir();
            raiz.getHijoIzq().preOrder2();
            raiz.getHijoDer().preOrder2();
        }
    }

    public void inOrder2() {
        //hijoIzquierdo, raiz, hijoDerecho
        if (!esVacio()) {
            raiz.getHijoIzq().inOrder2();
            System.out.print(raiz.getIdCapa() + ", ");
            raiz.getHijoDer().inOrder2();
        }
    }
    
    public void inOrder(Grafica grafica) {
        //hijoIzquierdo, raiz, hijoDerecho
        if (!esVacio()) {
            raiz.getHijoIzq().inOrder(grafica);
            System.out.print(raiz.getIdCapa() + ", ");
//            grafica.matriz()[grafica.contador] = raiz;
            grafica.contador++; 
            raiz.getHijoDer().inOrder(grafica);
        }
    }

    public void posOrder() {
        //hijoIzquierdo, hijoDerecho, raiz
        if (!esVacio()) {
            raiz.getHijoIzq().posOrder();
            raiz.getHijoDer().posOrder();
            System.out.print(raiz.getIdCapa() + ", ");

        }
    }

    public ArbolBinarioBusqueda buscar(int a) {
        ArbolBinarioBusqueda arbolito = null;
        if (!esVacio()) {
            if (a == raiz.getIdCapa()) {
                return this;
            } else {
                if (a < raiz.getIdCapa()) {
                    arbolito = raiz.getHijoIzq().buscar(a);
                } else {
                    arbolito = raiz.getHijoDer().buscar(a);
                }
            }
        }
        return arbolito;
    }

    public boolean existe(int a) {
        if (!esVacio()) {
            if (a == raiz.getIdCapa()) {
                return true;
            } else {
                if (a < raiz.getIdCapa()) {
                    raiz.getHijoIzq().existe(a);
                } else {
                    raiz.getHijoDer().existe(a);
                }
            }
        }
        return false;
    }

    public int cantidad() {
        if (esVacio()) {
            return 0;
        } else {
            return (1 + raiz.getHijoDer().cantidad() + raiz.getHijoIzq().cantidad());
        }
    }

    public int altura() {
        if (esVacio()) {
            return 0;
        } else {
            return (1 + Math.max(((raiz.getHijoIzq()).altura()), ((raiz.getHijoDer()).altura())));
        }
    }

    public int buscarMin() {
        ArbolBinarioBusqueda arbolActual = this;
        while (!arbolActual.raiz.getHijoIzq().esVacio()) {
            arbolActual = arbolActual.raiz.getHijoIzq();
        }
        int devuelvo = arbolActual.raiz.getIdCapa();
        arbolActual.raiz = null;
        return devuelvo;
    }

    public int buscarMan() {
        ArbolBinarioBusqueda arbolActual = this;
        while (!arbolActual.raiz.getHijoDer().esVacio()) {
            arbolActual = arbolActual.raiz.getHijoDer();
        }
        int devuelvo = arbolActual.raiz.getIdCapa();
        arbolActual.raiz = null;
        return devuelvo;
    }

    public boolean esHoja() {
        boolean hoja = false;
        if (raiz != null) {
            if (raiz.getHijoIzq().esVacio() && raiz.getHijoDer().esVacio()) {
                hoja = true;
            }
        }
        return hoja;
    }

    public void eliminar(int a) {
        ArbolBinarioBusqueda paraEliminar = buscar(a);
        if (!paraEliminar.esVacio()) {
            if (paraEliminar.esHoja()) {
                paraEliminar.raiz = null;
            } else {
                if (!paraEliminar.raiz.getHijoIzq().esVacio() && !paraEliminar.raiz.getHijoDer().esVacio()) {
                    paraEliminar.raiz.setIdCapa(paraEliminar.raiz.getHijoDer().buscarMin());
                } else {
                    if (paraEliminar.raiz.getHijoIzq().esVacio()) {
                        paraEliminar.raiz = paraEliminar.raiz.getHijoDer().raiz;
                    } else {
                        paraEliminar.raiz = paraEliminar.raiz.getHijoIzq().raiz;
                    }
                }
            }
        }
    }
}
