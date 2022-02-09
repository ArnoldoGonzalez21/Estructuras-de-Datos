package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Ventanilla {
    
    private int id_cliente; 
    private int numVentanilla;
    private boolean ocupada;

    public Ventanilla(int id_cliente, int numVentanilla, boolean ocupada) {
        this.id_cliente = id_cliente;
        this.numVentanilla = numVentanilla;
        this.ocupada = ocupada;
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
}
