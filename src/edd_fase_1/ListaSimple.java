package edd_fase_1;

import Clases.Cliente;

/**
 *
 * @author Arnoldo González
 */
public class ListaSimple {

    private Cliente cabeza;

    public ListaSimple() {
        this.cabeza = null;
    }

    public void insertarCliente(String key_titulo, int id, String nombre, int color, int bYn) {
        Cliente nuevo = new Cliente(key_titulo, id, nombre, color, bYn);
        if (this.getCabeza() == null) {
            this.setCabeza(nuevo);
        } else {
            Cliente actual = this.getCabeza();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public void mostrarDatos() {
        Cliente actual;
        for (actual = this.getCabeza(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("key: " + actual.getKey_titulo()+ " id: "+actual.getId()+
                    " nombre: "+actual.getNombre()+ " color: "+actual.getColor()+" byn: "+actual.getbYn());
            
        }
    }
    
    /**
     * @return the cabeza
     */
    public Cliente getCabeza() {
        return cabeza;
    }

    /**
     * @param cabeza the cabeza to set
     */
    public void setCabeza(Cliente cabeza) {
        this.cabeza = cabeza;
    }
}
