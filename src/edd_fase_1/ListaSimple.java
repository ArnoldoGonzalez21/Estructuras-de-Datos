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

    private Cliente cabezaCliente;
    private Ventanilla cabezaVen;
    private Imagen cabezaImg;

    public ListaSimple() {
        this.cabezaCliente = null;
        this.cabezaVen = null;
        this.cabezaImg = null;
    }

    public void insertarClienteAtendido(String key_titulo, int id, String nombre, int color, int bYn, int cantidadPasos) {
        Cliente nuevo = new Cliente(key_titulo, id, nombre, color, bYn, 0, 0, cantidadPasos);
        if (this.getCabezaCliente() == null) {
            this.setCabezaCliente(nuevo);
        } else {
            Cliente actual = this.getCabezaCliente();
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
                if (actual != null) {
                    System.out.println("Ingresa a ventanilla " + actualV.getNumVentanilla()
                            + " el Cliente " + actual.getNombre() + " con Id No. " + actual.getId());
                    actual.setCantidadPasos(actual.getCantidadPasos() + 1);
                    actualV.setOcupada(true);
                    actualV.setId_cliente(actual.getId());
                    colaCliente.popCliente();
                    actualV.setCliente(actual);
                }
            }
            actualV = actualV.getSiguiente();
        }
    }

    public void darImagen(Impresora cabezaImprColor, Impresora cabezaImprByN, ListaCircularDoble clienteEspera) {
        Ventanilla actualV = this.getCabezaVen();
        while (actualV != null) {
            if (actualV.isOcupada() && actualV.getCliente() != null) {
                if (actualV.getCliente().getColor() > 0 && actualV.getCliente().getColor() > actualV.getCliente().getEntregaColor()) {
                    actualV.getPilaImagen().push(actualV.getCliente().getId(), true);
                    actualV.getCliente().setEntregaColor(actualV.getCliente().getEntregaColor() + 1);
                    actualV.getCliente().setCantidadPasos(actualV.getCliente().getCantidadPasos() + 1);
                    System.out.println("No. Ventanilla: " + actualV.getNumVentanilla() + " - Recibe imagen a Color del Cliente "
                            + actualV.getCliente().getNombre() + " con Id No. " + actualV.getCliente().getId());

                } else if (actualV.getCliente().getByN() > 0 && actualV.getCliente().getByN() > actualV.getCliente().getEntregaByN()) {
                    actualV.getPilaImagen().push(actualV.getCliente().getId(), false);
                    actualV.getCliente().setEntregaByN(actualV.getCliente().getEntregaByN() + 1);
                    actualV.getCliente().setCantidadPasos(actualV.getCliente().getCantidadPasos() + 1);
                    System.out.println("No. Ventanilla: " + actualV.getNumVentanilla() + " - Recibe imagen a Blanco y Negro del Cliente "
                            + actualV.getCliente().getNombre() + " con Id No. " + actualV.getCliente().getId());

                } else {
                    System.out.println("\t*Se Retira de Ventanilla " + actualV.getNumVentanilla() + " el Cliente "
                            + actualV.getCliente().getNombre() + " con Id No. " + actualV.getCliente().getId()+"*\n");
                    actualV.getCliente().setCantidadPasos(actualV.getCliente().getCantidadPasos() + 1);
                    actualV.getCliente().setEntregaByN(0);
                    actualV.getCliente().setEntregaColor(0);
                    actualV.setOcupada(false);
                    actualV.setId_cliente(0);
                    enviarColaImpresion(actualV.getPilaImagen(), cabezaImprColor, cabezaImprByN);
                    actualV.setPilaImagen(new Pila());
                    clienteEspera.insertar(actualV.getCliente());
                }
            }
            actualV = actualV.getSiguiente();
        }
    }

    public void enviarColaImpresion(Pila pilaImagen, Impresora cabezaImprColor, Impresora cabezImprByN) {
        Clases.Imagen imagenActual = pilaImagen.getCabeza();
        System.out.println("\t*Enviando Pila de Imagenes a Cola de Impresión*\n");
        while (imagenActual != null) {
            if (imagenActual.isTipoImpresion()) {
                cabezaImprColor.getColaImagen().pushColor(imagenActual.getIdCliente(), true);
            } else {
                cabezImprByN.getColaImagen().pushByN(imagenActual.getIdCliente(), false);
            }
            imagenActual = imagenActual.getSiguiente();
        }
    }

    public String graficaListaVentanilla() {
        int id = 1;
        String nodoCliente = "", nodoImagen = "", filas = "", apuntador = "", apuntadorCliente = "", apuntadorImagen = "", rank = "{rank = same";
        String contenido = "digraph L{\n"
                + "node[shape = note fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Lista de Ventanillas\"\n"
                + "bgcolor = \"#8ECBE5\"\n"
                + "edge[dir = \"right\"]\n";

        Ventanilla actual = this.getCabezaVen();
        for (actual = this.getCabezaVen(); actual != null; actual = actual.getSiguiente()) {

            Boolean primeraImagen = false;
            filas += "Ventanilla" + actual.getNumVentanilla() + "[label = \"Ventanilla " + actual.getNumVentanilla() + "\", fillcolor = \"#F8DEA1\" shape = folder];\n";

            if (actual.getCliente() != null && actual.isOcupada()) {
                nodoCliente += actual.getCliente().getKey_titulo() + "[label = \"Cliente " + actual.getId_cliente() + "\nIMG Color: " + actual.getCliente().getColor()
                        + "\nIMG ByN: " + actual.getCliente().getByN() + "\", fillcolor = \"#FCF8F7\"]\n";
            }

            if (actual.getCliente() != null && actual.isOcupada()) {
                apuntadorCliente += actual.getCliente().getKey_titulo() + " -> " + " Ventanilla" + actual.getNumVentanilla() + ";\n";
                rank += ";Ventanilla" + actual.getNumVentanilla() + ";" + actual.getCliente().getKey_titulo();
            }

            if (actual.getPilaImagen() != null) {
                Imagen actualImg = actual.getPilaImagen().getCabeza();
                while (actualImg != null) {
                    if (actualImg.isTipoImpresion()) {
                        nodoImagen += "nodo" + id + "[label = \"IMG Color\", fillcolor = \"#FCF8F7\"]\n";
                    } else {
                        nodoImagen += "nodo" + id + "[label = \"IMG ByN\", fillcolor = \"#FCF8F7\"]\n";
                    }
                    if (!primeraImagen) {
                        apuntadorImagen += " Ventanilla" + actual.getNumVentanilla() + " -> " + " nodo" + id + ";\n";
                        primeraImagen = true;
                    }
                    if (actualImg.getSiguiente() != null) {
                        apuntadorImagen += " nodo" + id + " -> " + " nodo" + (id + 1) + ";\n";
                    }
                    rank += ";nodo" + id;
                    id++;
                    actualImg = actualImg.getSiguiente();
                }
                rank += "}\n";
            }

            if (actual.getSiguiente() != null) {
                apuntador += "Ventanilla" + actual.getNumVentanilla() + " -> " + " Ventanilla" + actual.getSiguiente().getNumVentanilla() + ";\n";
                rank += "{rank = same";
            }
        }
        contenido += filas + apuntador + nodoCliente + apuntadorCliente + nodoImagen + apuntadorImagen + rank + "}\n}";
        return contenido;
    }

    public String graficaClienteAtendido() {
        int id = 1;
        String contenido = "digraph L{\n"
                + "node[shape = note fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Lista Clientes Atendidos\"\n"
                + "bgcolor = \"#8ECBE5\"\n"
                + "raiz[label = \"Atendidos\" shape = folder]\n"
                + "edge[dir = \"right\"]\n";
        String nodos = "", apuntador_nodo = "", rank = "{rank = same;raiz";
        Cliente actual = this.getCabezaCliente();
        if (actual != null) {
            contenido += "raiz -> nodo" + id + ";\n";
        }
        for (actual = this.getCabezaCliente(); actual != null; actual = actual.getSiguiente()) {
            nodos += "nodo" + id + "[label = \"Id: " + actual.getId() + "\nNombre: " + actual.getNombre() + "\nIMG Color: " + actual.getColor()
                    + "\nIMG ByN: " + actual.getByN() + "\", fillcolor = \"#FCF8F7\", group = " + (id + 1) + "]\n";
            if (actual.getSiguiente() != null) {
                apuntador_nodo += "nodo" + id + " -> " + " nodo" + (id + 1) + ";\n";
            }
            rank += ";nodo" + id;
            id++;
        }
        rank += "}\n";
        contenido += nodos + apuntador_nodo + rank + "}\n}";
        return contenido;
    }

    public void terminarCliente(ListaCircularDoble clienteEspera) {
        Cliente clienteListo = clienteEspera.terminarClienteEspera();
        if (clienteListo != null) {
            System.out.println("\t**El Cliente " + clienteListo.getNombre() + " con Id No. " + clienteListo.getId() + " se Retira con las Impresiones solicitadas**\n");
            insertarClienteAtendido(clienteListo.getKey_titulo(), clienteListo.getId(), clienteListo.getNombre(),
                    clienteListo.getColor(), clienteListo.getByN(), clienteListo.getCantidadPasos());
        }
    }

    public void topImgColor() {
        this.BubbleSortColor();
        int contador = 0;
        Cliente actual = this.getCabezaCliente();
        while (actual != null) {
            if (contador <= 5) {
                System.out.println("Imágenes Color: " + actual.getColor() + " - Id Cliente: "
                        + actual.getId() + " - Nombre: " + actual.getNombre());
            } else {
                return;
            }
            contador++;
            actual = actual.getSiguiente();
        }
    }

    public void BubbleSortColor() {
        Cliente actual = this.getCabezaCliente();
        while (true) {
            actual = this.getCabezaCliente();
            Cliente auxAnt = null;
            Cliente auxSig = actual.getSiguiente();
            Boolean cambio = false;
            while (auxSig != null) {
                if (actual.getColor() < auxSig.getColor()) {
                    cambio = true;
                    Cliente tmp = auxSig.getSiguiente();
                    if (auxAnt != null) {
                        auxAnt.setSiguiente(auxSig);
                        auxSig.setSiguiente(actual);
                        actual.setSiguiente(tmp);
                    } else {
                        this.setCabezaCliente(auxSig);
                        auxSig.setSiguiente(actual);
                        actual.setSiguiente(tmp);
                    }
                    auxAnt = auxSig;
                    auxSig = actual.getSiguiente();
                } else {
                    auxAnt = actual;
                    actual = auxSig;
                    auxSig = auxSig.getSiguiente();
                }
            }
            if (!cambio) {
                break;
            }
        }
    }

    public void topImgByN() {
        this.BubbleSortByN();
        int contador = 0;
        Cliente actual = this.getCabezaCliente();
        while (actual != null) {
            if (contador <= 5) {
                System.out.println("Imágenes Blanco y Negro: " + actual.getByN() + " - Id Cliente: "
                        + actual.getId() + " - Nombre: " + actual.getNombre());
            } else {
                return;
            }
            contador++;
            actual = actual.getSiguiente();
        }
    }

    public void BubbleSortByN() {
        Cliente actual = this.getCabezaCliente();
        while (true) {
            actual = this.getCabezaCliente();
            Cliente auxAnt = null;
            Cliente auxSig = actual.getSiguiente();
            Boolean cambio = false;
            while (auxSig != null) {
                if (actual.getByN() > auxSig.getByN()) {
                    cambio = true;
                    Cliente tmp = auxSig.getSiguiente();
                    if (auxAnt != null) {
                        auxAnt.setSiguiente(auxSig);
                        auxSig.setSiguiente(actual);
                        actual.setSiguiente(tmp);
                    } else {
                        this.setCabezaCliente(auxSig);
                        auxSig.setSiguiente(actual);
                        actual.setSiguiente(tmp);
                    }
                    auxAnt = auxSig;
                    auxSig = actual.getSiguiente();
                } else {
                    auxAnt = actual;
                    actual = auxSig;
                    auxSig = auxSig.getSiguiente();
                }
            }
            if (!cambio) {
                break;
            }
        }
    }

    public void topClientePasos() {
        this.BubbleSortPasos();
        Cliente actual = getCabezaCliente();
        System.out.println("\tId: " + actual.getId() + "\n\tNombre: " + actual.getNombre()
                + "\n\tPasos: " + actual.getCantidadPasos());
    }

    public void BubbleSortPasos() {
        Cliente actual = this.getCabezaCliente();
        while (true) {
            actual = this.getCabezaCliente();
            Cliente auxAnt = null;
            Cliente auxSig = actual.getSiguiente();
            Boolean cambio = false;
            while (auxSig != null) {
                if (actual.getCantidadPasos() < auxSig.getCantidadPasos()) {
                    cambio = true;
                    Cliente tmp = auxSig.getSiguiente();
                    if (auxAnt != null) {
                        auxAnt.setSiguiente(auxSig);
                        auxSig.setSiguiente(actual);
                        actual.setSiguiente(tmp);
                    } else {
                        this.setCabezaCliente(auxSig);
                        auxSig.setSiguiente(actual);
                        actual.setSiguiente(tmp);
                    }
                    auxAnt = auxSig;
                    auxSig = actual.getSiguiente();
                } else {
                    auxAnt = actual;
                    actual = auxSig;
                    auxSig = auxSig.getSiguiente();
                }
            }
            if (!cambio) {
                break;
            }
        }
    }

    public Cliente solicitarInfoCliente(int idClienteSolicitado) {
        Cliente actual = getCabezaCliente();
        while (actual != null) {
            if (actual.getId() == idClienteSolicitado) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void mostrarDatosV() {
        Ventanilla actual;
        for (actual = this.getCabezaVen(); actual != null; actual = actual.getSiguiente()) {
            if (actual.getCliente() != null) {
                System.out.println("id: " + actual.getId_cliente() + " numero: " + actual.getNumVentanilla()
                        + " ocupada: " + actual.isOcupada() + " Cliente: " + actual.getCliente().getNombre());
            } else {
                System.out.println("id: " + actual.getId_cliente() + " numero: " + actual.getNumVentanilla()
                        + " ocupada: " + actual.isOcupada());
            }
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

    /**
     * @return the cabezaCliente
     */
    public Cliente getCabezaCliente() {
        return cabezaCliente;
    }

    /**
     * @param cabezaCliente the cabezaCliente to set
     */
    public void setCabezaCliente(Cliente cabezaCliente) {
        this.cabezaCliente = cabezaCliente;
    }
}
