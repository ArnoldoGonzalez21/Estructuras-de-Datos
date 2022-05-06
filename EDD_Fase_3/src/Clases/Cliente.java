package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Cliente {

    private String dpi;
    private String nombre;
    private String usuario;
    private String correo;
    private String contrasena;
    private String telefono;
    private String direccion;
    private int idMunicipio;
    private Cliente siguiente;
    private Cliente anterior;
    public boolean cargoCliente;

    public Cliente(String dpi, String nombre, String usuario, String correo, String contrasena, String telefono, String direccion, int idMunicipio) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.direccion = direccion;
        this.idMunicipio = idMunicipio;
        this.siguiente = null;
        this.anterior = null;
    }

    public Cliente(String usuario, String contrasena) {
        this.contrasena = contrasena;
        this.usuario = usuario;
        this.cargoCliente = false;
    }

    public boolean validarUsuario(String usuario, String password) {
        boolean userUs = this.getUsuario().equalsIgnoreCase(usuario);
        boolean contrasenaUs = this.getContrasena().equals(password);
        boolean correcto = userUs && contrasenaUs; //los dos true correcto es true
        return correcto;
    }

    /**
     * @return the dpi
     */
    public String getDpi() {
        return dpi;
    }

    /**
     * @param dpi the dpi to set
     */
    public void setDpi(String dpi) {
        this.dpi = dpi;
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
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contrasena
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
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the idMunicipio
     */
    public int getIdMunicipio() {
        return idMunicipio;
    }

    /**
     * @param idMunicipio the idMunicipio to set
     */
    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
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
     * @return the anterior
     */
    public Cliente getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(Cliente anterior) {
        this.anterior = anterior;
    }

}
