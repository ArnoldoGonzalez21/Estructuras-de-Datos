package edd_fase_2;

import Clases.NodoB;

/**
 *
 * @author Arnoldo González
 */
public class Registro {

    private NodoB cabezaUsuario;
    private NodoB administrador;

    public Registro(String nombre, String contrasena) {
        administrador = new NodoB(nombre, contrasena);
        this.cabezaUsuario = null;
    }

    public NodoB insertarUsuario(String dpi, String nombre, String contrasena) {
        if(existeUsuario(dpi)){
            return null;
        }
        NodoB nuevo = new NodoB(dpi, nombre, contrasena);
        if (this.getCabezaUsuario() == null) {
            this.setCabezaUsuario(nuevo);
        } else {
            NodoB actual = this.getCabezaUsuario();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        return nuevo;
    }

    public void recorrerUser() {
        NodoB actual = this.getCabezaUsuario();
        while (actual != null) {
            System.out.println(actual.getDpi() + " "+actual.getContrasena());
            actual = actual.getSiguiente();
        }
    }

    public NodoB validarUsuario(String dpi, String contrasena) {
        if (!existeUsuario(dpi)) {
            return null;
        }
        if (getAdministrador().validarUsuario(dpi, contrasena)) {
            return getAdministrador();
        }
        NodoB user = this.getCabezaUsuario();
        while (user != null) {
            if (user.validarUsuario(dpi, contrasena)) {
                return user;
            }
            user = user.getSiguiente();
        }
        return null;
    }

    public boolean existeUsuario(String dpi) {
        if (getAdministrador().getDpi().equalsIgnoreCase(dpi)) {
            return true;
        }
        NodoB user = this.getCabezaUsuario();
        while (user != null) {
            if (String.valueOf(user.getDpi()).equalsIgnoreCase(dpi)) {
                return true;
            }
            user = user.getSiguiente();
        }
        return false;
    }

    /**
     * @return the cabezaUsuario
     */
    public NodoB getCabezaUsuario() {
        return cabezaUsuario;
    }

    /**
     * @param cabezaUsuario the cabezaUsuario to set
     */
    public void setCabezaUsuario(NodoB cabezaUsuario) {
        this.cabezaUsuario = cabezaUsuario;
    }

    /**
     * @return the administrador
     */
    public NodoB getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(NodoB administrador) {
        this.administrador = administrador;
    }

}
