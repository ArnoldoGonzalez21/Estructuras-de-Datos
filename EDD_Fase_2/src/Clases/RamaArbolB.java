package Clases;

/**
 *
 * @author Arnoldo González
 */
public class RamaArbolB {

    boolean hoja;
    int contador;//identificar la cantidad de elementos que tiene la rama
    Usuario primero;

    public RamaArbolB() {
        this.primero = null;
        this.hoja = true;
        this.contador = 0;
    }

    public void insertar(Usuario nuevo) {
        if (primero == null) {
            //primero en la lista
            primero = nuevo;
            contador++;
        } else {
            //recorrer e insertar
            Usuario aux = primero;
            while (aux != null) {
                if (aux.getDpi() == nuevo.getDpi()) {
                    System.out.println("El ID " + nuevo.getDpi() + " ya existe");
                    break;
                } else {
                    if (aux.getDpi() > nuevo.getDpi()) {
                        if (aux == primero) {//------------->insertar al inicio
                            aux.anterior = nuevo;
                            nuevo.siguiente = aux;
                            //ramas del nodo
                            aux.izquierda = nuevo.derecha;
                            nuevo.derecha = null;
                            primero = nuevo;
                            contador++;
                            break;
                        } else {//------------->insertar en medio;
                            nuevo.siguiente = aux;
                            //ramas del nodo
                            aux.izquierda = nuevo.derecha;
                            nuevo.derecha = null;

                            nuevo.anterior = aux.anterior;
                            aux.anterior.siguiente = nuevo;
                            aux.anterior = nuevo;
                            contador++;
                            break;
                        }
                    } else if (aux.siguiente == null) {//------------->insertar al final
                        aux.siguiente = nuevo;
                        nuevo.anterior = aux;
                        contador++;
                        break;
                    }
                }
                aux = aux.siguiente;
            }

        }
    }
}
