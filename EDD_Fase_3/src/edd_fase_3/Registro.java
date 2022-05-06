package edd_fase_3;

import Clases.Cliente;

/**
 *
 * @author Arnoldo González
 */
public class Registro { 

    private Cliente cabezaUsuario;
    private Cliente administrador;
    private int size;

    public Registro(String nombre, String contrasena) {
        administrador = new Cliente(nombre, contrasena);
        this.cabezaUsuario = null;
        this.size = 0;
    }

    public Cliente insertarUsuario(String dpi, String nombre, String usuario, String correo, String contrasena, String telefono, String direccion, int idMunicipio) {
        if (existeUsuario(usuario)) {
            System.out.println("El nombre de usuario " + usuario + " ya existe");
            return null;
        }
        this.size++;
        //System.out.println(dpi + " " + nombre + " " + usuario + " " + correo + " " + contrasena + " " + telefono + " " + direccion + " " + idMunicipio);
        Cliente nuevo = new Cliente(dpi, nombre, usuario, correo, contrasena, telefono, direccion, idMunicipio);
        if (this.getCabezaUsuario() == null) {
            this.setCabezaUsuario(nuevo);
        } else {
            if (Long.parseLong(dpi) < Long.parseLong(this.getCabezaUsuario().getDpi())) {
                nuevo.setSiguiente(this.getCabezaUsuario());
                this.setCabezaUsuario(nuevo);
            } else {
                Cliente anterior = this.getCabezaUsuario();
                Cliente aux = this.getCabezaUsuario();
                while (Long.parseLong(dpi) >= Long.parseLong(aux.getDpi()) && aux.getSiguiente() != null) {
                    anterior = aux;
                    aux = aux.getSiguiente();
                }
                if (Long.parseLong(dpi) >= Long.parseLong(aux.getDpi())) {
                    aux.setSiguiente(nuevo);
                    nuevo.setAnterior(aux);
                } else {
                    nuevo.setSiguiente(aux);
                    anterior.setSiguiente(nuevo);
                    nuevo.setAnterior(anterior);
                }
            }
        }
        return nuevo;
    }

    public void recorrerUser() {
        Cliente actual = this.getCabezaUsuario();
        while (actual != null) {
            System.out.println(actual.getUsuario() + " " + actual.getContrasena());
            actual = actual.getSiguiente();
        }
    }

    public void modificarUsuario(String dpi, String usuario, String nombre, String contrasena,  String telefono, String direccion, int idMunicipio, String correo) {
        Cliente actual = this.getCabezaUsuario();
        while (actual != null) {
            if (String.valueOf(actual.getUsuario()).equalsIgnoreCase(usuario)) {
                actual.setDpi(dpi);
                actual.setNombre(nombre);
                actual.setContrasena(contrasena);
                actual.setTelefono(telefono);
                actual.setDireccion(direccion);
                actual.setCorreo(correo);
                actual.setIdMunicipio(idMunicipio);
            }
            actual = actual.getSiguiente();
        }
    }

    public Cliente buscarUsuario(String usuario) {
        Cliente actual = this.getCabezaUsuario();
        while (actual != null) {
            if (String.valueOf(actual.getUsuario()).equalsIgnoreCase(usuario)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public String[] opcionesUser() {
        String nombreUsuario[] = new String[this.size];
        int i = 0;
        Cliente actual = this.getCabezaUsuario();
        while (actual != null) {
            nombreUsuario[i] = actual.getUsuario();
            i++;
            actual = actual.getSiguiente();
        }
        return nombreUsuario;
    }

    public Cliente validarUsuario(String usuario, String contrasena) {
        if (!existeUsuario(usuario)) {
            return null;
        }
        if (getAdministrador().validarUsuario(usuario, contrasena)) {
            return getAdministrador();
        }
        Cliente user = this.getCabezaUsuario();
        while (user != null) {
            if (user.validarUsuario(usuario, contrasena)) {
                return user;
            }
            user = user.getSiguiente();
        }
        return null;
    }

    public boolean existeUsuario(String usuario) {
        if (getAdministrador().getUsuario().equalsIgnoreCase(usuario)) {
            return true;
        }
        Cliente user = this.getCabezaUsuario();
        while (user != null) {
            if (user.getUsuario().equalsIgnoreCase(usuario)) {
                return true;
            }
            user = user.getSiguiente();
        }
        return false;
    }

    public boolean eliminarUsuario(String usuario) {
        Cliente user = this.getCabezaUsuario();
        while (user != null) {
            if (user.getUsuario().equalsIgnoreCase(usuario)) {
                Cliente aux = user.getSiguiente();
                if (user != this.getCabezaUsuario()) {
                    user.getAnterior().setSiguiente(aux);
                    if (aux != null) {
                        aux.setAnterior(user.getAnterior());
                    }
                } else {
                    aux.setAnterior(null);
                    this.setCabezaUsuario(aux);
                }
                user.setSiguiente(null);
                user.setAnterior(null);
                this.size--;
                return true;
            }
            user = user.getSiguiente();
        }
        return false;
    }

    /**
     * @return the cabezaUsuario
     */
    public Cliente getCabezaUsuario() {
        return cabezaUsuario;
    }

    /**
     * @param cabezaUsuario the cabezaUsuario to set
     */
    public void setCabezaUsuario(Cliente cabezaUsuario) {
        this.cabezaUsuario = cabezaUsuario;
    }

    /**
     * @return the administrador
     */
    public Cliente getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(Cliente administrador) {
        this.administrador = administrador;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

}
