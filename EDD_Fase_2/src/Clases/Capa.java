package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Capa {

    private int idCapa;
    private Capa siguiente;

    public Capa(int idCapa) {
        this.idCapa = idCapa;
        this.siguiente = null;
    }

    /**
     * @return the idCapa
     */
    public int getIdCapa() {
        return idCapa;
    }

    /**
     * @param idCapa the idCapa to set
     */
    public void setIdCapa(int idCapa) {
        this.idCapa = idCapa;
    }

    /**
     * @return the siguiente
     */
    public Capa getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Capa siguiente) {
        this.siguiente = siguiente;
    }
}
