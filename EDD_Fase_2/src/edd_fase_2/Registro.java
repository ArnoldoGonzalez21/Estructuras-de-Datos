package edd_fase_2;

import Clases.NodoB;

/**
 *
 * @author Arnoldo González
 */
public class Registro {

    private NodoB cabezaUsuario;
    private NodoB administrador;
    private int size;

    public Registro(String nombre, String contrasena) {
        administrador = new NodoB(nombre, contrasena);
        this.cabezaUsuario = null;
        this.size = 0;
    }

    public NodoB insertarUsuario(String dpi, String nombre, String contrasena) {
        if (existeUsuario(dpi)) {
            return null;
        }
        this.size++;
        NodoB nuevo = new NodoB(dpi, nombre, contrasena);
        if (this.getCabezaUsuario() == null) {
            this.setCabezaUsuario(nuevo);
        } else {
            if (Long.parseLong(dpi) < Long.parseLong(this.getCabezaUsuario().getDpi())) {
                nuevo.setSiguiente(this.getCabezaUsuario());
                this.setCabezaUsuario(nuevo);
            } else {
                NodoB anterior = this.getCabezaUsuario();
                NodoB aux = this.getCabezaUsuario();
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

    public void mostrarDatos() {
        NodoB actual;
        for (actual = this.getCabezaUsuario(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("Actual: " + actual.getDpi());
            if (actual.getSiguiente() != null) {
                System.out.println("Siguiente: " + actual.getSiguiente().getDpi());
            }
            if (actual.getAnterior() != null) {
                System.out.println("Anterior: " + actual.getAnterior().getDpi());
            }
            System.out.println("------------------------");
        }
        System.out.println("\n");
    }

    public void recorrerUser() {
        NodoB actual = this.getCabezaUsuario();
        while (actual != null) {
            System.out.println(actual.getDpi() + " " + actual.getContrasena());
            actual = actual.getSiguiente();
        }
    }

    public void modificarUsuario(String dpiViejo, String dpiNuevo, String nombre, String contrasena) {
        NodoB actual = this.getCabezaUsuario();
        while (actual != null) {
            if (String.valueOf(actual.getDpi()).equalsIgnoreCase(dpiViejo)) {
                actual.setDpi(dpiNuevo);
                actual.setNombre(nombre);
                actual.setContrasena(contrasena);
            }
            actual = actual.getSiguiente();
        }
    }

    public NodoB buscarUsuario(String dpi) {
        NodoB actual = this.getCabezaUsuario();
        while (actual != null) {
            if (String.valueOf(actual.getDpi()).equalsIgnoreCase(dpi)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public String[] opcionesUser(Utilidades util) {
        util.dpiUsuario = new String[this.size];
        int i = 0;
        NodoB actual = this.getCabezaUsuario();
        while (actual != null) {
            util.dpiUsuario[i] = actual.getDpi();
            i++;
            actual = actual.getSiguiente();
        }
        return util.dpiUsuario;
    }

    public Object[][] recorrerTablaUser() {
        Object[][] tabla = new Object[this.getSize()][3];
        int i = 0;
        NodoB actual = this.getCabezaUsuario();
        while (actual != null) {
            if (tabla[i] == null) {
                continue;
            }
            tabla[i][0] = actual.getDpi();
            tabla[i][1] = actual.getNombre();
            tabla[i][2] = actual.getImagenesUser().cantidadAVL();
            i++;
            actual = actual.getSiguiente();
        }
        return tabla;
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
            if (user.getDpi().equalsIgnoreCase(dpi)) {
                return true;
            }
            user = user.getSiguiente();
        }
        return false;
    }

    public boolean eliminarUsuario(String dpi) {
        NodoB user = this.getCabezaUsuario();
        while (user != null) {
            if (user.getDpi().equalsIgnoreCase(dpi)) {
                NodoB aux = user.getSiguiente();
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

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

}
