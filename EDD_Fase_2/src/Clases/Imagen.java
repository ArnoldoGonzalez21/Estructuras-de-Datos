package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Imagen {

    private int id;
    private Imagen siguiente;

    public Imagen(int id) {
        this.id = id;
        this.siguiente = null;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the siguiente
     */
    public Imagen getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Imagen siguiente) {
        this.siguiente = siguiente;
    }

}
