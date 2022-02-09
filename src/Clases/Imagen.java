package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Imagen {
   
    private int idCliente;
    private int tipoImpresion;
    private Imagen siguiente;
    
    public Imagen(int idCliente, int tipoImpresion) {
        this.idCliente = idCliente;
        this.tipoImpresion = tipoImpresion;
    }

    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the tipoImpresion
     */
    public int getTipoImpresion() {
        return tipoImpresion;
    }

    /**
     * @param tipoImpresion the tipoImpresion to set
     */
    public void setTipoImpresion(int tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
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
