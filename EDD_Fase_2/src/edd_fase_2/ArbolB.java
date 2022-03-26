package edd_fase_2;

import Clases.Usuario;
import edd_fase_2.RamaArbolB;

/**
 *
 * @author Arnoldo González
 */
public class ArbolB {

    private Usuario admin;
    int orden_arbol = 5;
    RamaArbolB raiz;

    public ArbolB(String nombre, String contrasena) {
        this.admin = new Usuario(nombre, contrasena);
        this.raiz = null;
    }

    public void insertar(Comparable dpi, String nombre, String contrasena) {
        Usuario nodo = new Usuario(dpi, nombre, contrasena);
        if (raiz == null) {
            raiz = new RamaArbolB();
            raiz.insertar(nodo);
        } else {
            Usuario obj = insertar_en_rama(nodo, raiz);
            if (obj != null) {
                //si devuelve algo el metodo de insertar en rama quiere decir que creo una nueva rama, y se debe insertar en el arbol
                raiz = new RamaArbolB();
                raiz.insertar(obj);
                raiz.hoja = false;
            }
        }
    }

    private Usuario insertar_en_rama(Usuario nodo, RamaArbolB rama) {
        if (rama.hoja) {
            rama.insertar(nodo);
            if (rama.contador == orden_arbol) {
                //si ya se insertaron todos los elementos posibles se debe dividir la rama
                return dividir(rama);
            } else {
                return null;
            }
        } else {
            Usuario temp = rama.primero;
            do {
                if (nodo.getDpi().equals(temp.getDpi())) {
                    return null;
                } else if (nodo.getDpi().compareTo(temp.getDpi()) < 0) {
                    Usuario obj = insertar_en_rama(nodo, temp.getIzquierda());
                    if (obj instanceof Usuario) {
                        rama.insertar((Usuario) obj);
                        if (rama.contador == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                } else if (temp.getSiguiente() == null) {
                    Usuario obj = insertar_en_rama(nodo, temp.getDerecha());
                    if (obj instanceof Usuario) {
                        rama.insertar((Usuario) obj);
                        if (rama.contador == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                }
                temp = (Usuario) temp.getSiguiente();
            } while (temp != null);
        }
        return null;
    }

    private Usuario dividir(RamaArbolB rama) {
        Comparable val = " ";
        String nom = "";
        String contra = "";
        Usuario temp, Nuevito;
        Usuario aux = rama.primero;
        RamaArbolB rderecha = new RamaArbolB();
        RamaArbolB rizquierda = new RamaArbolB();

        int cont = 0;
        while (aux != null) {
            cont++;
            //implementacion para dividir unicamente ramas de 4 nodos
            if (cont < 3) {
                temp = new Usuario(aux.getDpi(), aux.getNombre(), aux.getContrasena());
                temp.setIzquierda(aux.getIzquierda());
                if (cont == 2) {
                    temp.setDerecha(aux.getSiguiente().getIzquierda());
                } else {
                    temp.setDerecha(aux.getDerecha());
                }
                //si la rama posee ramas deja de ser hoja
                if (temp.getDerecha() != null && temp.getIzquierda() != null) {
                    rizquierda.hoja = false;
                }

                rizquierda.insertar(temp);

            } else if (cont == 3) {
                val = aux.getDpi();
                nom = aux.getNombre();
                contra = aux.getContrasena();
            } else {
                temp = new Usuario(aux.getDpi(), aux.getNombre(), aux.getContrasena());
                temp.setIzquierda(aux.getIzquierda());
                temp.setDerecha(aux.getDerecha());
                //si la rama posee ramas deja de ser hoja
                if (temp.getDerecha() != null && temp.getIzquierda() != null) {
                    rderecha.hoja = false;
                }
                rderecha.insertar(temp);
            }
            aux = aux.getSiguiente();
        }
        Nuevito = new Usuario(val, nom, contra);
        Nuevito.setDerecha(rderecha);
        Nuevito.setIzquierda(rizquierda);
        return Nuevito;
    }

    public void inorden() {
        System.out.println("Recorrido inorden del árbol b :");
        inorden(raiz);
        System.out.println();
    }

    private void inorden(RamaArbolB nodo) { //izq, raiz, der
        if (nodo == null) {
            return;
        }
        inorden(nodo.primero.getIzquierda());
        Usuario actual = nodo.primero;
        while (actual != null) {
            if (!nodo.hoja) {
                actual = actual.getSiguiente();
                System.out.println("nodo Actual "+ actual.getDpi());
                System.out.print(actual.getDpi() + ", ");
                break;
            }
            System.out.print(actual.getDpi() + ", ");
            actual = actual.getSiguiente();
        }
//        inorden(nodo.getDerecha());
    }

//      private void inorden(RamaArbolB nodo) { //izq, raiz, der
//        if (nodo == null) {
//            return;
//        }
//        inorden(nodo.primero.getIzquierda());
//        Usuario actual = nodo.primero.getSiguiente();
//        System.out.print(nodo.primero.getDpi() + ", ");
//        while (actual != null) {
//            System.out.print(actual.getDpi() + ", ");
//            actual = actual.getSiguiente();
//        }
//        inorden(nodo.primero.getDerecha());
//    }
    /**
     * @return the admin
     */
    public Usuario getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Usuario admin) {
        this.admin = admin;
    }

}
