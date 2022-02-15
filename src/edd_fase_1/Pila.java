package edd_fase_1;

import Clases.Imagen;

/**
 *
 * @author Arnoldo González
 */
public class Pila {

    private Imagen cabeza;
    int size;

    public Pila() {
        this.cabeza = null;
        this.size = 0;
    }

    public void push(int idCliente, boolean tipoImpresion) {
        Imagen nuevo = new Imagen(idCliente, tipoImpresion);
        this.size++;
        if (this.getCabeza() == null) {
            this.setCabeza(nuevo);

        } else {
            nuevo.setSiguiente(this.getCabeza());
            this.setCabeza(nuevo);
        }
    }

    public void mostrar() {
        Imagen actual;
        System.out.println("IMAGENES EN PILA");
        for (actual = this.getCabeza(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("id: " + actual.getIdCliente() + " tipo: " + actual.isTipoImpresion());
        }
    }

    /**
     * @return the cabeza
     */
    public Imagen getCabeza() {
        return cabeza;
    }

    /**
     * @param cabeza the cabeza to set
     */
    public void setCabeza(Imagen cabeza) {
        this.cabeza = cabeza;
    }
}
