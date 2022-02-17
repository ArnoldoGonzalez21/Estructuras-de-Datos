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
                clienteEspera.getNombre(), clienteEspera.getColor(), clienteEspera.getByN(), clienteEspera.getCantidadPasos());
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
            if (imagen != null && actual != null) {
                if (imagen.getIdCliente() == actual.getId()) {
                    System.out.println("Se Entrega Impresión al Cliente " + actual.getNombre() + " con Id No. " + actual.getId());
                    actual.setNumImg(actual.getNumImg() + 1);
                    actual.getListaImagenes().insertarImagen(imagen);
                    return;
                }
                actual = actual.getSiguienteDoble();
            }
        }
    }

    public Cliente terminarClienteEspera() {
        if (this.getCabezaDoble() != null) {
            Cliente aux = this.getCabezaDoble();
            Cliente ant = null;
            for (int i = 0; i < this.size; i++) {
                if (aux.getNumImg() == (aux.getColor() + aux.getByN())) {
                    if (ant == null) {
                        if (aux.getSiguienteDoble() == this.getCabezaDoble()) {
                            Cliente atendido = aux;
                            this.setCabezaDoble(null);
                            size--;
                            return atendido;
                        } else {
                            Cliente atendido = aux;
                            ant = aux.getAnteriorDoble();
                            ant.setSiguienteDoble(aux.getSiguienteDoble());
                            aux = aux.getSiguienteDoble();
                            aux.setAnteriorDoble(ant);
                            this.setCabezaDoble(aux);
                            ant = null;
                            size--;
                            return atendido;
                        }
                    } else {
                        Cliente atendido = aux;
                        aux.setAnteriorDoble(null);
                        ant.setSiguienteDoble(aux.getSiguienteDoble());
                        aux = aux.getSiguienteDoble();
                        aux.setAnteriorDoble(ant);
                        size--;
                        return atendido;
                    }
                } else {
                    ant = aux;
                    aux = aux.getSiguienteDoble();
                }
            }
        }
        return null;
    }

    public String graficaListaEspera() {
        int id = 1;
        String nodoImagen = "", columnas = "", apuntador = "", apuntadorImagen = "", rank = "{rank = same";
        String contenido = "digraph L{\n"
                + "node[shape = note fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Lista de Espera\"\n"
                + "bgcolor = \"#8ECBE5\"\n"
                + "edge[dir = \"right\"]\n";

        Cliente actual = this.getCabezaDoble();
        for (int i = 0; i < this.size; i++) {

            Boolean primeraImagen = false;
            columnas += actual.getKey_titulo() + "[label = \"Cliente " + actual.getId() + "\nIMG Color: "
                    + actual.getColor() + "\nIMG ByN: " + actual.getByN() + "\", fillcolor = \"#F8DEA1\" shape = folder];\n";

            if (actual.getListaImagenes() != null) {
                Clases.Imagen actualImg = actual.getListaImagenes().getCabezaImg();
                while (actualImg != null) {
                    if (actualImg.isTipoImpresion()) {
                        nodoImagen += "nodo" + id + "[label = \"IMG Color\", fillcolor = \"#FCF8F7\"]\n";
                    } else {
                        nodoImagen += "nodo" + id + "[label = \"IMG ByN\", fillcolor = \"#FCF8F7\"]\n";
                    }
                    if (!primeraImagen) {
                        apuntadorImagen += actual.getKey_titulo() + " -> " + " nodo" + id + ";\n";
                        primeraImagen = true;
                    }
                    if (actualImg.getSiguiente() != null) {
                        apuntadorImagen += " nodo" + id + " -> " + " nodo" + (id + 1) + ";\n";
                    }
                    id++;
                    actualImg = actualImg.getSiguiente();
                }
            }

            if (actual.getAnteriorDoble() != null && actual.getSiguienteDoble() != null) {
                apuntador += actual.getKey_titulo() + " -> " + actual.getSiguienteDoble().getKey_titulo() + ";\n"
                        + actual.getKey_titulo() + " -> " + actual.getAnteriorDoble().getKey_titulo() + ";\n";

            }
            rank += ";" + actual.getKey_titulo();
            actual = actual.getSiguienteDoble();
        }
        rank += "}\n";
        contenido += columnas + apuntador + nodoImagen + apuntadorImagen + rank + "}\n}";
        return contenido;
    }

    public void agregarPasoClienteEspera() {
        Cliente actual = this.getCabezaDoble();
        for (int i = 0; i < size; i++) {
            actual.setCantidadPasos(actual.getCantidadPasos() + 1);
            actual = actual.getSiguienteDoble();
        }
    }

    public void mostrarClienteEspera() {
        Cliente actual = this.getCabezaDoble();
        for (int i = 0; i < size; i++) {
            System.out.println("Key: " + actual.getKey_titulo() + " - Id: " + actual.getId() + " - Nombre: " + actual.getNombre() + " - NumImg: " + actual.getNumImg());
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
