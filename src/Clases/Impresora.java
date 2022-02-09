
package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Impresora {
    
    private boolean tipoImpresora;
    private boolean ocupada;
    private boolean tipoImagen;

    public Impresora(boolean tipoImpresora, boolean ocupada, boolean tipoImagen) {
        this.tipoImpresora = tipoImpresora;
        this.ocupada = ocupada;
        this.tipoImagen = tipoImagen;
    }

    /**
     * @return the tipoImpresora
     */
    public boolean isTipoImpresora() {
        return tipoImpresora;
    }

    /**
     * @param tipoImpresora the tipoImpresora to set
     */
    public void setTipoImpresora(boolean tipoImpresora) {
        this.tipoImpresora = tipoImpresora;
    }

    /**
     * @return the ocupada
     */
    public boolean isOcupada() {
        return ocupada;
    }

    /**
     * @param ocupada the ocupada to set
     */
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    /**
     * @return the tipoImagen
     */
    public boolean isTipoImagen() {
        return tipoImagen;
    }

    /**
     * @param tipoImagen the tipoImagen to set
     */
    public void setTipoImagen(boolean tipoImagen) {
        this.tipoImagen = tipoImagen;
    }
    
}
