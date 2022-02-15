package Clases;

import edd_fase_1.Pila;

/**
 *
 * @author Arnoldo González
 */
public class Ventanilla {

    private int id_cliente;
    private int numVentanilla;
    private boolean ocupada;
    private Pila pilaImagen;
    private Cliente cliente;
    private Ventanilla siguiente;

    public Ventanilla(int id_cliente, int numVentanilla, boolean ocupada) {
        this.id_cliente = id_cliente;
        this.numVentanilla = numVentanilla;
        this.ocupada = ocupada;
        this.pilaImagen = new Pila();
        this.cliente = null;
        this.siguiente = null;
    }

    /**
     * @return the id_cliente
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * @param id_cliente the id_cliente to set
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * @return the numVentanilla
     */
    public int getNumVentanilla() {
        return numVentanilla;
    }

    /**
     * @param numVentanilla the numVentanilla to set
     */
    public void setNumVentanilla(int numVentanilla) {
        this.numVentanilla = numVentanilla;
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
     * @return the siguiente
     */
    public Ventanilla getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Ventanilla siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the pilaImagen
     */
    public Pila getPilaImagen() {
        return pilaImagen;
    }

    /**
     * @param pilaImagen the pilaImagen to set
     */
    public void setPilaImagen(Pila pilaImagen) {
        this.pilaImagen = pilaImagen;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
