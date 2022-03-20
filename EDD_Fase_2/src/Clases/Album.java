package Clases;

import edd_fase_2.ListaSimple;

/**
 *
 * @author Arnoldo González
 */
public class Album {

    private String nombre;
    private ListaSimple listaImagenes = new ListaSimple();
    private Album siguiente;

    public Album(String nombre, ListaSimple imagenes) {
        this.nombre = nombre;
        this.listaImagenes = imagenes;
        this.siguiente = null;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the listaImagenes
     */
    public ListaSimple getListaImagenes() {
        return listaImagenes;
    }

    /**
     * @param listaImagenes the listaImagenes to set
     */
    public void setListaImagenes(ListaSimple listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    /**
     * @return the siguiente
     */
    public Album getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Album siguiente) {
        this.siguiente = siguiente;
    }

}
