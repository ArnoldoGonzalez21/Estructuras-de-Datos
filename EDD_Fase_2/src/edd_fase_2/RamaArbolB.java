package edd_fase_2;

import Clases.Usuario;

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
                            aux.setAnterior(nuevo);
                            nuevo.setSiguiente(aux);
                            //ramas del nodo
                            aux.setIzquierda(nuevo.getDerecha());
                            nuevo.setDerecha(null);
                            primero = nuevo;
                            contador++;
                            break;
                        } else {//------------->insertar en medio;
                            nuevo.setSiguiente(aux);
                            //ramas del nodo
                            aux.setIzquierda(nuevo.getDerecha());
                            nuevo.setDerecha(null);

                            nuevo.setAnterior(aux.getAnterior());
                            aux.getAnterior().setSiguiente(nuevo);
                            aux.setAnterior(nuevo);
                            contador++;
                            break;
                        }
                    } else if (aux.getSiguiente() == null) {//------------->insertar al final
                        aux.setSiguiente(nuevo);
                        nuevo.setAnterior(aux);
                        contador++;
                        break;
                    }
                }
                aux = aux.getSiguiente();
            }

        }
    }
}
