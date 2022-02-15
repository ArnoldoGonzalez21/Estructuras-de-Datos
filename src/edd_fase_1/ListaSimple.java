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

    private Cliente cabeza;
    private Ventanilla cabezaVen;
    private Imagen cabezaImg;

    public ListaSimple() {
        this.cabeza = null;
        this.cabezaVen = null;
        this.cabezaImg = null;
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

    public void darImagen(Ventanilla actualV, Impresora cabezaImprColor, Impresora cabezaImprByN, ListaCircularDoble clienteEspera) {
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

    public void terminarCliente(ListaCircularDoble clienteEspera) {
        Cliente clienteListo = clienteEspera.terminarClienteEspera();
        if (clienteListo != null) {
            Cliente actual = this.getCabeza();
            while (actual != null) {
                if (actual.getId() == clienteListo.getId()) {
                    actual.setEnEspera(false);
                    actual.setAtendido(true);
                    terminarCliente(clienteEspera);
                }
                actual = actual.getSiguiente();
            }
        }
    }

    public String contenidoGrafica() {
        int id = 1;
        String inicio = "digraph L{\n"
                + "node[shape = folder fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Graphviz Carné\"\n"
                + "bgcolor = \"#398D9C\"\n"
                + "raiz[label = \"Cabeza\"]\n"
                + "edge[dir = \"right\"]\n"
                + "raiz -> nodo" + id + ";\n";
        String nodos = "", apuntador_nodo = "", rank = "{rank = same;raiz";
        Cliente actual;
//        for (actual = this.getCabeza(); actual != null; actual = actual.getSiguiente()) {
//            nodos += "nodo" + id + "[label = \"" + actual.getNumero() + "\", fillcolor = green, group = " + (id + 1) + "]\n";
//            if (actual.getSiguiente() != null) {
//                apuntador_nodo += "nodo" + id + " -> " + " nodo" + (id + 1) + ";\n";
//                System.out.println("siguiente: " + actual.getSiguiente().getNumero() + "\n");
//            }
//            rank += ";nodo" + id;
//            id++;
//        }
        rank += "}\n";
        String finalGraph = "}\n}";
        String contenidoGrafo = inicio + nodos + apuntador_nodo + rank + finalGraph;
        System.out.println(inicio + nodos + apuntador_nodo + rank + finalGraph);
        return contenidoGrafo;
    }

    public String graficaClienteRecepcion() {
        int id = 1;
        String contenido = "digraph L{\n"
                + "node[shape = note fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Cola Impresora Color\"\n"
                + "bgcolor = \"#8ECBE5\"\n"
                + "raiz[label = \"Impresora Color\" shape = folder]\n"
                + "edge[dir = \"right\"]\n";
        String nodos = "", apuntador_nodo = "", rank = "{rank = same;raiz";
        Cliente actual = this.getCabeza();
        if (actual != null) {
            contenido += "raiz -> nodo" + id + ";\n";
        }
        for (actual = this.getCabeza(); actual != null; actual = actual.getSiguiente()) {
            nodos += "nodo" + id + "[label = \"" + actual.getKey_titulo() + "\nIMG Color: " + actual.getColor()
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

    public void todoOk(Impresora cabezaImprColor, Impresora cabezImprByN) {
        System.out.println("Tamaño en Impresora Color: " + cabezaImprColor.getColaImagen().sizeColor);
        System.out.println("Tamaño en Impresora ByN: " + cabezImprByN.getColaImagen().sizeByN);
    }

    public void mostrarDatos() {
        Cliente actual;
        for (actual = this.getCabeza(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("key: " + actual.getKey_titulo() + " id: " + actual.getId()
                    + " nombre: " + actual.getNombre() + " color: " + actual.getColor() + " byn: " + actual.getByN()
                    + " espera: " + actual.isEnEspera() + " atendido: " + actual.isAtendido());
        }
    }

    public void mostrarDatosV() {
        Ventanilla actual;
        for (actual = this.getCabezaVen(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("id: " + actual.getId_cliente() + " numero: " + actual.getNumVentanilla()
                    + " ocupada: " + actual.isOcupada());
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
