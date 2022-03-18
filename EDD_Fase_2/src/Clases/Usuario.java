package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Usuario {

    private String nombre;
    private String contrasena;
    private long dpi;
    Usuario siguiente;
    Usuario anterior;
    RamaArbolB derecha;
    RamaArbolB izquierda;

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
        boolean nombreUs = this.nombre.equalsIgnoreCase(nombre);
        boolean contrasenaUs = this.contrasena.equals(password);
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

}
