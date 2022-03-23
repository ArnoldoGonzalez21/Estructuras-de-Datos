package Clases;

/**
 *
 * @author Arnoldo González
 */
public class NodoMatriz {
    
    private int idCapa;
    private String color;
    private int x;
    private int y;
    private NodoMatriz siguiente;
    private NodoMatriz anterior;
    private NodoMatriz arriba;
    private NodoMatriz abajo;

    public NodoMatriz(int idCapa, String color, int x, int y) {
        this.idCapa = idCapa;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the siguiente
     */
    public NodoMatriz getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(NodoMatriz siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the anterior
     */
    public NodoMatriz getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(NodoMatriz anterior) {
        this.anterior = anterior;
    }

    /**
     * @return the arriba
     */
    public NodoMatriz getArriba() {
        return arriba;
    }

    /**
     * @param arriba the arriba to set
     */
    public void setArriba(NodoMatriz arriba) {
        this.arriba = arriba;
    }

    /**
     * @return the abajo
     */
    public NodoMatriz getAbajo() {
        return abajo;
    }

    /**
     * @param abajo the abajo to set
     */
    public void setAbajo(NodoMatriz abajo) {
        this.abajo = abajo;
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

}
