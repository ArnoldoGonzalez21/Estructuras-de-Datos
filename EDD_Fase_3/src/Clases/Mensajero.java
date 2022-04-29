package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Mensajero {
    
    private String dpi;
    private String nombre;
    private String apellido;
    private int tipoLicencia; // 0 -> A   1 -> B   2 -> C
    private boolean genero;
    private String telefono;
    private String direccion;
    private Mensajero siguiente;

    public Mensajero(String dpi, String nombre, String apellido, int tipoLicencia, boolean genero, String telefono, String direccion) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoLicencia = tipoLicencia;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
        this.siguiente = null;
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
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the tipoLicencia
     */
    public int getTipoLicencia() {
        return tipoLicencia;
    }

    /**
     * @param tipoLicencia the tipoLicencia to set
     */
    public void setTipoLicencia(int tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    /**
     * @return the genero
     */
    public boolean isGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(boolean genero) {
        this.genero = genero;
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
     * @return the siguiente
     */
    public Mensajero getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Mensajero siguiente) {
        this.siguiente = siguiente;
    }
}
