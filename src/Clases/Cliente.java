package Clases;

import edd_fase_1.ListaSimple;

/**
 *
 * @author Arnoldo González
 */
public class Cliente {

    private String key_titulo;
    private int id;
    private String nombre;
    private int color;
    private int ByN;
    private int entregaColor;
    private int entregaByN;
    private Cliente siguiente;
    private Cliente siguienteDoble;
    private Cliente anteriorDoble;
    private ListaSimple listaImagenes;
    private int numImg;

    public Cliente(String key_titulo, int id, String nombre, int color, int bYn, int entregaColor, int entregaByN) {
        this.key_titulo = key_titulo;
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.ByN = bYn;
        this.entregaColor = entregaColor;
        this.entregaByN = entregaByN;
        this.siguiente = null;
        this.siguienteDoble = this;
        this.anteriorDoble = null;
    }

    public Cliente(String key_titulo, int id, String nombre, int color, int bYn) {
        this.key_titulo = key_titulo;
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.ByN = bYn;
        this.siguienteDoble = this;
        this.anteriorDoble = null;
        this.listaImagenes = new ListaSimple();
        this.numImg = 0;
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
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * @return the ByN
     */
    public int getByN() {
        return ByN;
    }

    /**
     * @param ByN the ByN to set
     */
    public void setByN(int ByN) {
        this.ByN = ByN;
    }

    /**
     * @return the siguiente
     */
    public Cliente getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Cliente siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the key_titulo
     */
    public String getKey_titulo() {
        return key_titulo;
    }

    /**
     * @param key_titulo the key_titulo to set
     */
    public void setKey_titulo(String key_titulo) {
        this.key_titulo = key_titulo;
    }

    /**
     * @return the entregaColor
     */
    public int getEntregaColor() {
        return entregaColor;
    }

    /**
     * @param entregaColor the entregaColor to set
     */
    public void setEntregaColor(int entregaColor) {
        this.entregaColor = entregaColor;
    }

    /**
     * @return the entregaByN
     */
    public int getEntregaByN() {
        return entregaByN;
    }

    /**
     * @param entregaByN the entregaByN to set
     */
    public void setEntregaByN(int entregaByN) {
        this.entregaByN = entregaByN;
    }

    /**
     * @return the siguienteDoble
     */
    public Cliente getSiguienteDoble() {
        return siguienteDoble;
    }

    /**
     * @param siguienteDoble the siguienteDoble to set
     */
    public void setSiguienteDoble(Cliente siguienteDoble) {
        this.siguienteDoble = siguienteDoble;
    }

    /**
     * @return the anteriorDoble
     */
    public Cliente getAnteriorDoble() {
        return anteriorDoble;
    }

    /**
     * @param anteriorDoble the anteriorDoble to set
     */
    public void setAnteriorDoble(Cliente anteriorDoble) {
        this.anteriorDoble = anteriorDoble;
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
     * @return the numImg
     */
    public int getNumImg() {
        return numImg;
    }

    /**
     * @param numImg the numImg to set
     */
    public void setNumImg(int numImg) {
        this.numImg = numImg;
    }
}
