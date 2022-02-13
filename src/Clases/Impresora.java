package Clases;

import edd_fase_1.Cola;

/**
 *
 * @author Arnoldo González
 */
public class Impresora {

    private boolean tipoImpresora;/*Color -> true - Byn -> false*/
    private boolean ocupada;
    private Cola colaImagen;

    public Impresora(boolean tipoImpresora, boolean ocupada) {
        this.tipoImpresora = tipoImpresora;
        this.ocupada = ocupada;
        this.colaImagen = new Cola();
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
     * @return the colaImagen
     */
    public Cola getColaImagen() {
        return colaImagen;
    }

    /**
     * @param colaImagen the colaImagen to set
     */
    public void setColaImagen(Cola colaImagen) {
        this.colaImagen = colaImagen;
    }
}
