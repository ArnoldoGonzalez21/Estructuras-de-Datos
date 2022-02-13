package edd_fase_1;

import Clases.Cliente;
import Clases.Impresora;
import Clases.Ventanilla;

/**
 *
 * @author Arnoldo González
 */
public class ListaSimple {

    private Cliente cabeza;
    private Ventanilla cabezaVen;
    Impresora cabezaImprColor;
    Impresora cabezImprByN;

    public ListaSimple() {
        this.cabeza = null;
        this.cabezaVen = null;
        this.cabezaImprColor = new Impresora(true, false);
        this.cabezImprByN = new Impresora(false, false);
    }

    public void insertarCliente(String key_titulo, int id, String nombre, int color, int bYn) {
        Cliente nuevo = new Cliente(key_titulo, id, nombre, color, bYn, false, 0, 0, false, false);
        if (this.getCabeza() == null) {
            this.setCabeza(nuevo);
        } else {
            Cliente actual = this.getCabeza();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public void insertarVentanilla(int cantidad) {
        int indice = 1;
        while (indice <= cantidad) {
            Ventanilla nuevo = new Ventanilla(0, indice, false);
            if (this.getCabezaVen() == null) {
                this.setCabezaVen(nuevo);
            } else {
                Ventanilla actual = this.getCabezaVen();
                while (actual.getSiguiente() != null) {
                    actual = actual.getSiguiente();
                }
                actual.setSiguiente(nuevo);
            }
            indice++;
        }
    }

    public void pasarVentanilla(Ventanilla actualV) {
        while (actualV != null) {
            if (!actualV.isOcupada()) {
                Cliente actual = this.getCabeza();
                while (actual != null) {
                    if (!actual.isEnVentanilla() && !actual.isEnEspera() && !actual.isAtendido()) {
                        System.out.println("Ingresa a ventanilla -> " + "No. Ventanilla: " + actualV.getNumVentanilla()
                                + " Id Cliente: " + actual.getId() + " Nombre: " + actual.getNombre());
                        actualV.setOcupada(true);
                        actualV.setId_cliente(actual.getId());
                        actual.setEnVentanilla(true);
                        break;
                    }
                    actual = actual.getSiguiente();
                }
            }
            actualV = actualV.getSiguiente();
        }
    }

    public void darImagen(Ventanilla actualV) {
        while (actualV != null) {
            if (actualV.isOcupada()) {
                Cliente actual = this.getCabeza();
                while (actual != null) {
                    if (actual.isEnVentanilla() && actual.getId() == actualV.getId_cliente()) {
                        if (actual.getColor() > 0 && actual.getColor() > actual.getEntregaColor()) {

                            actualV.getPilaImagen().push(actual.getId(), true);
                            actual.setEntregaColor(actual.getEntregaColor() + 1);
                            System.out.println("Entrega imagen Color -> " + "No. Ventanilla: " + actualV.getNumVentanilla()
                                    + " Id Cliente: " + actual.getId() + " Nombre: " + actual.getNombre());
                        } else if (actual.getbYn() > 0 && actual.getbYn() > actual.getEntregaByN()) {

                            actualV.getPilaImagen().push(actual.getId(), false);
                            actual.setEntregaByN(actual.getEntregaByN() + 1);
                            System.out.println("Entrega imagen Byn -> " + "No. Ventanilla: " + actualV.getNumVentanilla()
                                    + " Id Cliente: " + actual.getId() + " Nombre: " + actual.getNombre());
                        } else {
                            System.out.println("Retira de Ventanilla -> " + "No. Ventanilla: " + actualV.getNumVentanilla()
                                    + " Id Cliente: " + actual.getId() + " Nombre: " + actual.getNombre());
                            actual.setEnVentanilla(false);
                            actual.setEnEspera(true);
                            actualV.setOcupada(false);
                            enviarColaImpresion(actualV.getPilaImagen());
                            actualV.setPilaImagen(null);
                        }
                        break;
                    }
                    actual = actual.getSiguiente();
                }
            }
            actualV = actualV.getSiguiente();
        }
    }

    public void generarClientesRandom() {
        String nombres[] = {"Rossmery", "Ronald", "Daniel", "Oscar", "Samuel", "Eric", "Sergio", "Nicolas", "Gabriela",
            "Stephany", "Emiliano", "Karla", "Angel", "Rebeca", "Lionel", "Guillermo", "Raul", "David", "Lucia", "Emma"};
        String apellidos[] = {"Castillo", "Hernandez", "Lopez", "Martinez", "Rodriguez", "Gonzalez", "Garcia", "Silva", "Ruiz",
            "Fernandez", "Sanchez", "Perez", "Diaz", "Gomez", "Morales", "Suarez", "Santos", "Marquez", "Reyes", "Mora"};
        int ultimoId = 0;
        Cliente actual = this.getCabeza();
        while (actual != null) {
            if (actual.getSiguiente() == null) {
                ultimoId = actual.getId() + 1;
            }
            actual = actual.getSiguiente();
        }
        int cantidadClientes = (int) (Math.random() * (3 - 0 + 1) + 0);
        System.out.println("Cantidad nuevos clientes: " + cantidadClientes);
        for (int i = 0; i < cantidadClientes; i++) {
            int nombreRandom = (int) (Math.random() * (19 - 0 + 1) + 0);
            int apellidoRandom = (int) (Math.random() * (19 - 0 + 1) + 0);
            int cantidadColor = (int) (Math.random() * (4 - 0 + 1) + 0);
            int cantidadByN = (int) (Math.random() * (4 - 0 + 1) + 0);
            insertarCliente("Cliente" + (ultimoId + i), (ultimoId + i), nombres[nombreRandom] + " " + apellidos[apellidoRandom],
                    cantidadColor, cantidadByN);
            System.out.println("Cliente" + (ultimoId + i) + " - " + (ultimoId + i) + " - " + nombres[nombreRandom] + " " + apellidos[apellidoRandom]
                    + " - " + cantidadColor + " - " + cantidadByN);
        }
        todoOk();
    }

    public void enviarColaImpresion(Pila pilaImagen) {
        Clases.Imagen imagenActual = pilaImagen.getCabeza();
        System.out.println("*Enviando Pila de Imagenes a Cola de Impresión*");

        while (imagenActual != null) {
            if (imagenActual.isTipoImpresion()) {
                System.out.println(imagenActual.getIdCliente() + " " + imagenActual.isTipoImpresion());
                this.cabezaImprColor.getColaImagen().pushColor(imagenActual.getIdCliente(), true);
            } else {
                System.out.println(imagenActual.getIdCliente() + " " + imagenActual.isTipoImpresion());
                this.cabezImprByN.getColaImagen().pushByN(imagenActual.getIdCliente(), false);
            }
            imagenActual = imagenActual.getSiguiente();
        }

        System.out.println("Size Color: " + this.cabezaImprColor.getColaImagen().sizeColor);
        System.out.println("Size ByN: " + this.cabezImprByN.getColaImagen().sizeByN);
    }

    public void todoOk() {
        System.out.println("Size Color: " + this.cabezaImprColor.getColaImagen().sizeColor);
        System.out.println("Size ByN: " + this.cabezImprByN.getColaImagen().sizeByN);
    }

    public void mostrarDatos() {
        Cliente actual;
        for (actual = this.getCabeza(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("key: " + actual.getKey_titulo() + " id: " + actual.getId()
                    + " nombre: " + actual.getNombre() + " color: " + actual.getColor() + " byn: " + actual.getbYn());
        }
    }

    public void mostrarDatosV() {
        Ventanilla actual;
        for (actual = this.getCabezaVen(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("id: " + actual.getId_cliente() + " numero: " + actual.getNumVentanilla()
                    + " ocupada: " + actual.isOcupada() + " Tamano Pila: " + actual.getPilaImagen().size);
        }
    }

    /**
     * @return the cabeza
     */
    public Cliente getCabeza() {
        return cabeza;
    }

    /**
     * @param cabeza the cabeza to set
     */
    public void setCabeza(Cliente cabeza) {
        this.cabeza = cabeza;
    }

    /**
     * @return the cabezaVen
     */
    public Ventanilla getCabezaVen() {
        return cabezaVen;
    }

    /**
     * @param cabezaVen the cabezaVen to set
     */
    public void setCabezaVen(Ventanilla cabezaVen) {
        this.cabezaVen = cabezaVen;
    }
}
