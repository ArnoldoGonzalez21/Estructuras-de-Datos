package Clases;

import edd_fase_2.RamaArbolB;

/**
 *
 * @author Arnoldo González
 */
public class Usuario {

    private String nombre;
    private String contrasena;
    private long dpi;
    private Usuario siguiente;
    private Usuario anterior;
    private RamaArbolB derecha;
    private RamaArbolB izquierda;

    public Usuario(long dpi, String nombre, String contrasena) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.siguiente = null;
        this.anterior = null;
        this.derecha = null;
        this.izquierda = null;
    }

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public boolean validarUsuario(String nombre, String password) {
        boolean nombreUs = this.getNombre().equalsIgnoreCase(nombre);
        boolean contrasenaUs = this.getContrasena().equals(password);
        boolean correcto = nombreUs && contrasenaUs;
        return correcto;
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
     * @return the contraseñan
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the dpi
     */
    public long getDpi() {
        return dpi;
    }

    /**
     * @param dpi the dpi to set
     */
    public void setDpi(long dpi) {
        this.dpi = dpi;
    }

    /**
     * @return the siguiente
     */
    public Usuario getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Usuario siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the anterior
     */
    public Usuario getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(Usuario anterior) {
        this.anterior = anterior;
    }

    /**
     * @return the derecha
     */
    public RamaArbolB getDerecha() {
        return derecha;
    }

    /**
     * @param derecha the derecha to set
     */
    public void setDerecha(RamaArbolB derecha) {
        this.derecha = derecha;
    }

    /**
     * @return the izquierda
     */
    public RamaArbolB getIzquierda() {
        return izquierda;
    }

    /**
     * @param izquierda the izquierda to set
     */
    public void setIzquierda(RamaArbolB izquierda) {
        this.izquierda = izquierda;
    }

}
