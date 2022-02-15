package edd_fase_1;

import Clases.Cliente;
import Clases.Imagen;
import Clases.Impresora;
import Clases.Ventanilla;

/**
 *
 * @author Arnoldo González
 */
public class ListaSimple {
 //quizas un tipo Cliente en Ventanilla y que ese sea la raiz para la grafica
    //entonces ese cliente que paso a ventanilla ya le puedo hacer un pop de la cola
    //y cuando salga de ventanilla ya lo mando a la de espera, nunca estaria duplicado en algun lugar
    //cambiar la logica de los id en las ventanillas para que sea el id del cliente que esta actualmente y no recorrer toda la cola
    private Ventanilla cabezaVen;
    private Imagen cabezaImg;

    public ListaSimple() {
        this.cabezaVen = null;
        this.cabezaImg = null;
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

    public void insertarImagen(Imagen imagen) {
        Imagen nuevo = new Imagen(imagen.getIdCliente(), imagen.isTipoImpresion());
        if (this.getCabezaImg() == null) {
            this.setCabezaImg(nuevo);
        } else {
            Imagen actual = this.getCabezaImg();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public void pasarVentanilla(Cola colaCliente) {
        Ventanilla actualV = this.getCabezaVen();
        while (actualV != null) {
            if (!actualV.isOcupada()) {
                Cliente actual = colaCliente.getCabeza();
                while (actual != null) {
                    if (!actual.isEnVentanilla() && !actual.isEnEspera() && !actual.isAtendido()) {
                        System.out.println("Ingresa a ventanilla -> " + "No. Ventanilla: " + actualV.getNumVentanilla()
                                + " Id Cliente: " + actual.getId() + " Nombre: " + actual.getNombre());
                        actualV.setOcupada(true);
                        actualV.setId_cliente(actual.getId());
                        actual.setEnVentanilla(true);
                        colaCliente.popCliente();
                        break;
                    }
                    actual = actual.getSiguiente();
                }
            }
            actualV = actualV.getSiguiente();
        }
    }

    public void darImagen(Cola colaCliente, Impresora cabezaImprColor, Impresora cabezaImprByN, ListaCircularDoble clienteEspera) {
        Ventanilla actualV = this.getCabezaVen();
        while (actualV != null) {
            if (actualV.isOcupada()) {
                Cliente actual = colaCliente.getCabeza();
                while (actual != null) {
                    if (actual.isEnVentanilla() && actual.getId() == actualV.getId_cliente()) {
                        if (actual.getColor() > 0 && actual.getColor() > actual.getEntregaColor()) {

                            actualV.getPilaImagen().push(actual.getId(), true);
                            actual.setEntregaColor(actual.getEntregaColor() + 1);
                            System.out.println("Entrega imagen Color -> " + "No. Ventanilla: " + actualV.getNumVentanilla()
                                    + " Id Cliente: " + actual.getId() + " Nombre: " + actual.getNombre());
                        } else if (actual.getByN() > 0 && actual.getByN() > actual.getEntregaByN()) {

                            actualV.getPilaImagen().push(actual.getId(), false);
                            actual.setEntregaByN(actual.getEntregaByN() + 1);
                            System.out.println("Entrega imagen Byn -> " + "No. Ventanilla: " + actualV.getNumVentanilla()
                                    + " Id Cliente: " + actual.getId() + " Nombre: " + actual.getNombre());
                        } else {
                            System.out.println("Retira de Ventanilla -> " + "No. Ventanilla: " + actualV.getNumVentanilla()
                                    + " Id Cliente: " + actual.getId() + " Nombre: " + actual.getNombre());
                            actual.setEntregaByN(0);
                            actual.setEntregaColor(0);
                            actual.setEnVentanilla(false);
                            actual.setEnEspera(true);
                            actualV.setOcupada(false);
                            enviarColaImpresion(actualV.getPilaImagen(), cabezaImprColor, cabezaImprByN);
                            actualV.setPilaImagen(null);
                            clienteEspera.insertar(actual);
                        }
                        break;
                    }
                    actual = actual.getSiguiente();
                }
            }
            actualV = actualV.getSiguiente();
        }
    }

    public void enviarColaImpresion(Pila pilaImagen, Impresora cabezaImprColor, Impresora cabezImprByN) {
        Clases.Imagen imagenActual = pilaImagen.getCabeza();
        System.out.println("*Enviando Pila de Imagenes a Cola de Impresión*");
        while (imagenActual != null) {
            if (imagenActual.isTipoImpresion()) {
                cabezaImprColor.getColaImagen().pushColor(imagenActual.getIdCliente(), true);
            } else {
                cabezImprByN.getColaImagen().pushByN(imagenActual.getIdCliente(), false);
            }
            System.out.println("Id enviado: " + imagenActual.getIdCliente() + " Tipo: " + imagenActual.isTipoImpresion());
            imagenActual = imagenActual.getSiguiente();
        }
        System.out.println("Tamaño en Impresora Color: " + cabezaImprColor.getColaImagen().sizeColor);
        System.out.println("Tamaño en Impresora ByN: " + cabezImprByN.getColaImagen().sizeByN);
    }

    public void todoOk(Impresora cabezaImprColor, Impresora cabezImprByN) {
        System.out.println("Tamaño en Impresora Color: " + cabezaImprColor.getColaImagen().sizeColor);
        System.out.println("Tamaño en Impresora ByN: " + cabezImprByN.getColaImagen().sizeByN);
    }

    public void mostrarDatosV() {
        Ventanilla actual;
        for (actual = this.getCabezaVen(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("id: " + actual.getId_cliente() + " numero: " + actual.getNumVentanilla()
                    + " ocupada: " + actual.isOcupada());
        }
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

    /**
     * @return the cabezaImg
     */
    public Imagen getCabezaImg() {
        return cabezaImg;
    }

    /**
     * @param cabezaImg the cabezaImg to set
     */
    public void setCabezaImg(Imagen cabezaImg) {
        this.cabezaImg = cabezaImg;
    }
}
