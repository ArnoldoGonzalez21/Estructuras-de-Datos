package edd_fase_1;

import Clases.Imagen;

/**
 *
 * @author Arnoldo González
 */
public class Cola {

    private Imagen cabezaColor;
    private Imagen cabezaByN;
    int sizeColor;
    int sizeByN;

    public void pushColor(int idCliente, boolean tipoImpresion) {
        Imagen nuevo = new Imagen(idCliente, tipoImpresion);
        this.sizeColor++;
        if (this.getCabezaColor() == null) {
            this.setCabezaColor(nuevo);
        } else {
            Imagen actual = this.getCabezaColor();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();

            }
            actual.setSiguiente(nuevo);
        }
    }

    public void pushByN(int idCliente, boolean tipoImpresion) {
        Imagen nuevo = new Imagen(idCliente, tipoImpresion);
        this.sizeByN++;
        if (this.getCabezaByN() == null) {
            this.setCabezaByN(nuevo);
        } else {
            Imagen actual = this.getCabezaByN();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();

            }
            actual.setSiguiente(nuevo);
        }
    }

    public void mostrarColor() {
        Imagen actual;
        System.out.println("Cola Color");
        for (actual = this.getCabezaColor(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("Id: " + actual.getIdCliente() + " tipo: " + actual.isTipoImpresion());
        }
    }

    public void mostrarByN() {
        Imagen actual;
        System.out.println("Cola ByN");
        for (actual = this.getCabezaByN(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("Id: " + actual.getIdCliente() + " tipo: " + actual.isTipoImpresion());
        }
    }

    /**
     * @return the cabezaColor
     */
    public Imagen getCabezaColor() {
        return cabezaColor;
    }

    /**
     * @param cabezaColor the cabezaColor to set
     */
    public void setCabezaColor(Imagen cabezaColor) {
        this.cabezaColor = cabezaColor;
    }

    /**
     * @return the cabezaByN
     */
    public Imagen getCabezaByN() {
        return cabezaByN;
    }

    /**
     * @param cabezaByN the cabezaByN to set
     */
    public void setCabezaByN(Imagen cabezaByN) {
        this.cabezaByN = cabezaByN;
    }
}
