package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Cliente {

    private String key_titulo;
    private int id;
    private String nombre;
    private int color;
    private int bYn;
    private Cliente siguiente;

    public Cliente(String key_titulo, int id, String nombre, int color, int bYn) {
        this.key_titulo = key_titulo;
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.bYn = bYn;
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
     * @return the bYn
     */
    public int getbYn() {
        return bYn;
    }

    /**
     * @param bYn the bYn to set
     */
    public void setbYn(int bYn) {
        this.bYn = bYn;
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

}
