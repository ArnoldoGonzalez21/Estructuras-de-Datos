package edd_fase_1;

import Clases.Cliente;

/**
 *
 * @author Arnoldo González
 */
public class ListaCircularDoble {

    private Cliente cabezaDoble;
    int size = 0;

    public ListaCircularDoble() {
        this.cabezaDoble = null;
    }

    public void insertar(Cliente clienteEspera) {
        Cliente nuevo = new Cliente(clienteEspera.getKey_titulo(), clienteEspera.getId(),
                clienteEspera.getNombre(), clienteEspera.getColor(), clienteEspera.getByN());
        this.size++;
        if (this.getCabezaDoble() != null) {
            nuevo.setSiguienteDoble(this.getCabezaDoble().getSiguienteDoble());
            this.getCabezaDoble().setSiguienteDoble(nuevo);
            nuevo.setAnteriorDoble(this.getCabezaDoble());
        }
        this.setCabezaDoble(nuevo);
        this.getCabezaDoble().getSiguienteDoble().setAnteriorDoble(nuevo);
    }

    public void entregarImagen(Clases.Imagen imagen) {
        Cliente actual = this.getCabezaDoble();
        for (int i = 0; i < size; i++) {
            System.out.println("Entre! " + imagen.getIdCliente() + " Tipo: " + imagen.isTipoImpresion());
            System.out.println("Id actual: " + actual.getId());
            if (imagen.getIdCliente() == actual.getId()) {
                actual.setNumImg(actual.getNumImg() + 1);
                actual.getListaImagenes().insertarImagen(imagen);
                return;
            }
            actual = actual.getSiguienteDoble();
        }
    }

    public Cliente terminarClienteEspera() {
        Cliente actual = this.getCabezaDoble();
        for (int i = 0; i < size; i++) {
            if (actual.getNumImg() == (actual.getColor() + actual.getByN())) {
                Cliente auxAtendido = actual;
                Cliente aux = actual.getAnteriorDoble();
                aux.setSiguienteDoble(actual.getSiguienteDoble());
                actual.getSiguienteDoble().setAnteriorDoble(aux);
                actual.setSiguienteDoble(null);
                actual.setAnteriorDoble(null);
                this.size--;
                return auxAtendido;
            }
            actual = actual.getSiguienteDoble();
        }
        return null;
    }

    public void mostrarClienteEspera() {
        Cliente actual = this.getCabezaDoble();
        System.out.println("Size: " + this.size);
        for (int i = 0; i < size; i++) {
            System.out.println("Id: " + actual.getId() + " - Nombre: " + actual.getNombre() + " - NumImg: " + actual.getNumImg());
            actual = actual.getSiguienteDoble();
        }
    }

    /**
     * @return the cabezaDoble
     */
    public Cliente getCabezaDoble() {
        return cabezaDoble;
    }

    /**
     * @param cabezaDoble the cabezaDoble to set
     */
    public void setCabezaDoble(Cliente cabezaDoble) {
        this.cabezaDoble = cabezaDoble;
    }
}
