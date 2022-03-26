package edd_fase_2;

import Clases.Usuario;

/**
 *
 * @author Arnoldo González
 */
public class Registro {

    private Usuario cabezaUsuario;
    private Usuario administrador;

    public Registro(String nombre, String contrasena) {
        administrador = new Usuario(nombre, contrasena);
        this.cabezaUsuario = null;
    }

    public Usuario insertarUsuario(Comparable dpi, String nombre, String contrasena) {
        if(existeUsuario(dpi.toString())){
            return null;
        }
        Usuario nuevo = new Usuario(dpi, nombre, contrasena);
        if (this.getCabezaUsuario() == null) {
            this.setCabezaUsuario(nuevo);
        } else {
            Usuario actual = this.getCabezaUsuario();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        return nuevo;
    }

    public void recorrerUser() {
        Usuario actual = this.getCabezaUsuario();
        while (actual != null) {
            System.out.println(actual.getDpi() + " "+actual.getContrasena());
            actual = actual.getSiguiente();
        }
    }

    public Usuario validarUsuario(String dpi, String contrasena) {
        if (!existeUsuario(dpi)) {
            return null;
        }
        if (getAdministrador().validarUsuario(dpi, contrasena)) {
            return getAdministrador();
        }
        Usuario user = this.getCabezaUsuario();
        while (user != null) {
            if (user.validarUsuario(dpi, contrasena)) {
                return user;
            }
            user = user.getSiguiente();
        }
        return null;
    }

    public boolean existeUsuario(String dpi) {
        if (getAdministrador().getDpi().toString().equalsIgnoreCase(dpi)) {
            return true;
        }
        Usuario user = this.getCabezaUsuario();
        while (user != null) {
            if (user.getDpi().toString().equalsIgnoreCase(dpi)) {
                return true;
            }
            user = user.getSiguiente();
        }
        return false;
    }

    /**
     * @return the cabezaUsuario
     */
    public Usuario getCabezaUsuario() {
        return cabezaUsuario;
    }

    /**
     * @param cabezaUsuario the cabezaUsuario to set
     */
    public void setCabezaUsuario(Usuario cabezaUsuario) {
        this.cabezaUsuario = cabezaUsuario;
    }

    /**
     * @return the administrador
     */
    public Usuario getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(Usuario administrador) {
        this.administrador = administrador;
    }

}
