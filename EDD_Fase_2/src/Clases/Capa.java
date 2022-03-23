package Clases;

import edd_fase_2.ArbolBinarioBusqueda;
import edd_fase_2.Matriz;
import edd_fase_2.MatrizDispersa;

/**
 *
 * @author Arnoldo González
 */
public class Capa {

    private ArbolBinarioBusqueda hijoDer;
    private ArbolBinarioBusqueda hijoIzq;
    private int idCapa;
    public MatrizDispersa pixeles;

    public Capa() {
        this.hijoDer = null;
        this.hijoIzq = null;
        this.idCapa = 0;
        this.pixeles = new MatrizDispersa(1);
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
     * @return the hijoDer
     */
    public ArbolBinarioBusqueda getHijoDer() {
        return hijoDer;
    }

    /**
     * @param hijoDer the hijoDer to set
     */
    public void setHijoDer(ArbolBinarioBusqueda hijoDer) {
        this.hijoDer = hijoDer;
    }

    /**
     * @return the hijoIzq
     */
    public ArbolBinarioBusqueda getHijoIzq() {
        return hijoIzq;
    }

    /**
     * @param hijoIzq the hijoIzq to set
     */
    public void setHijoIzq(ArbolBinarioBusqueda hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

}
