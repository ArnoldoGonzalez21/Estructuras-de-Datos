package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Registro {

    private Usuario administrador;
    int orden_arbol = 5;
    RamaArbolB raiz;

    public Registro(String nombre, String contrasena) {
        this.administrador = new Usuario(nombre, contrasena);
        this.raiz = null;
    }

    public void insertar(int dpi, String nombre, String contrasena) {
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
                if (nodo.getDpi() == temp.getDpi()) {
                    return null;
                } else if (nodo.getDpi() < temp.getDpi()) {
                    Usuario obj = insertar_en_rama(nodo, temp.izquierda);
                    if (obj instanceof Usuario) {
                        rama.insertar((Usuario) obj);
                        if (rama.contador == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                } else if (temp.siguiente == null) {
                    Usuario obj = insertar_en_rama(nodo, temp.derecha);
                    if (obj instanceof Usuario) {
                        rama.insertar((Usuario) obj);
                        if (rama.contador == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                }
                temp = (Usuario) temp.siguiente;
            } while (temp != null);
        }
        return null;
    }

    private Usuario dividir(RamaArbolB rama) {
        int val = -999;
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
                temp.izquierda = aux.izquierda;
                if (cont == 2) {
                    temp.derecha = aux.siguiente.izquierda;
                } else {
                    temp.derecha = aux.derecha;
                }
                //si la rama posee ramas deja de ser hoja
                if (temp.derecha != null && temp.izquierda != null) {
                    rizquierda.hoja = false;
                }

                rizquierda.insertar(temp);

            } else if (cont == 3) {
                val = aux.getDpi();
                nom = aux.getNombre();
                contra = aux.getContrasena();
            } else {
                temp = new Usuario(aux.getDpi(), aux.getNombre(), aux.getContrasena());
                temp.izquierda = aux.izquierda;
                temp.derecha = aux.derecha;
                //si la rama posee ramas deja de ser hoja
                if (temp.derecha != null && temp.izquierda != null) {
                    rderecha.hoja = false;
                }
                rderecha.insertar(temp);
            }
            aux = aux.siguiente;
        }
        Nuevito = new Usuario(val, nom, contra);
        Nuevito.derecha = rderecha;
        Nuevito.izquierda = rizquierda;
        return Nuevito;
    }

    public void imp(){
        System.out.println("..............");
    }
    
    /**
     * @return the administrador
     */
    public Usuario getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(Usuario administrador) {
        this.administrador = administrador;
    }

}
