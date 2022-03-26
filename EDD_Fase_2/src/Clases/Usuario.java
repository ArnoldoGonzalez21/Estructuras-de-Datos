package Clases;

import edd_fase_2.ArbolAVL;
import edd_fase_2.ArbolBB;
import edd_fase_2.ListaSimple;
import edd_fase_2.RamaArbolB;

/**
 *
 * @author Arnoldo González
 */
public class Usuario {

    private String nombre;
    private String contrasena;
    private final Comparable dpi;
    private Usuario siguiente;
    private Usuario anterior;
    private RamaArbolB derecha;
    private RamaArbolB izquierda;
    private ListaSimple albumUser;
    private ArbolAVL imagenesUser;
    private ArbolBB capasUser;
    public boolean cargoCapa;
    public boolean cargoImagen;

    public Usuario(Comparable dpi, String nombre, String contrasena) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.siguiente = null;
        this.anterior = null;
        this.derecha = null;
        this.izquierda = null;
        this.albumUser = new ListaSimple();
        this.imagenesUser = new ArbolAVL();
        this.capasUser = new ArbolBB();
        this.cargoCapa = false;
        this.cargoImagen = false;
    }

    public Usuario(String dpi, String contrasena) {
        this.dpi = dpi;
        this.contrasena = contrasena;
    }

    public boolean validarUsuario(String dpi, String password) {
        boolean dpiUs = this.getDpi().toString().equalsIgnoreCase(dpi);
        boolean contrasenaUs = this.getContrasena().equals(password);
        boolean correcto = dpiUs && contrasenaUs;
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
    public Comparable getDpi() {
        return dpi;
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

    /**
     * @return the albumUser
     */
    public ListaSimple getAlbumUser() {
        return albumUser;
    }

    /**
     * @param albumUser the albumUser to set
     */
    public void setAlbumUser(ListaSimple albumUser) {
        this.albumUser = albumUser;
    }

    /**
     * @return the imagenesUser
     */
    public ArbolAVL getImagenesUser() {
        return imagenesUser;
    }

    /**
     * @param imagenesUser the imagenesUser to set
     */
    public void setImagenesUser(ArbolAVL imagenesUser) {
        this.imagenesUser = imagenesUser;
    }

    /**
     * @return the capasUser
     */
    public ArbolBB getCapasUser() {
        return capasUser;
    }

    /**
     * @param capasUser the capasUser to set
     */
    public void setCapasUser(ArbolBB capasUser) {
        this.capasUser = capasUser;
    }

}
