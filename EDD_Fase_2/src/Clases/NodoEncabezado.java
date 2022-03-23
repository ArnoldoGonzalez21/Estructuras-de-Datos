/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Arnoldo González
 */
public class NodoEncabezado {
    private int id;
    private NodoEncabezado siguiente;
    private NodoEncabezado anterior;
    private NodoMatriz acceso;

    public NodoEncabezado(int id) {
        this.id = id;
        this.siguiente = null;
        this.anterior = null;
        this.acceso = null;
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
    public NodoEncabezado getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(NodoEncabezado siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the anterior
     */
    public NodoEncabezado getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(NodoEncabezado anterior) {
        this.anterior = anterior;
    }

    /**
     * @return the acceso
     */
    public NodoMatriz getAcceso() {
        return acceso;
    }

    /**
     * @param acceso the acceso to set
     */
    public void setAcceso(NodoMatriz acceso) {
        this.acceso = acceso;
    }
    
    
}
