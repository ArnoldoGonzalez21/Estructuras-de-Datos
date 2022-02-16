package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Imagen {

    private int idCliente;
    private boolean tipoImpresion; //Color -> true    ByN -> false
    private int numPaso;
    private Imagen siguiente;

    public Imagen(int idCliente, boolean tipoImpresion) {
        this.idCliente = idCliente;
        this.tipoImpresion = tipoImpresion;
        this.numPaso = 0;
        this.siguiente = null;
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
    public boolean isTipoImpresion() {
        return tipoImpresion;
    }

    /**
     * @param tipoImpresion the tipoImpresion to set
     */
    public void setTipoImpresion(boolean tipoImpresion) {
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

    /**
     * @return the numPaso
     */
    public int getNumPaso() {
        return numPaso;
    }

    /**
     * @param numPaso the numPaso to set
     */
    public void setNumPaso(int numPaso) {
        this.numPaso = numPaso;
    }
}
