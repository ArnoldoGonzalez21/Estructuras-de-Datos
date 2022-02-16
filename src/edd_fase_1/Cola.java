package edd_fase_1;

import Clases.Cliente;
import Clases.Imagen;

/**
 *
 * @author Arnoldo González
 */
public class Cola {

    private Cliente cabeza;
    private Imagen cabezaColor;
    private Imagen cabezaByN;
    int sizeColor;
    int sizeByN;

    public Cola() {
        this.cabeza = null;
        this.cabezaColor = null;
        this.cabezaByN = null;
    }

    public void insertarCliente(String key_titulo, int id, String nombre, int color, int bYn) {
        Cliente nuevo = new Cliente(key_titulo, id, nombre, color, bYn, 0, 0);
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

    public Cliente popCliente() {
        Cliente retorno = null;
        if (this.getCabeza() != null) {
            retorno = this.getCabeza();
            if (this.getCabeza().getSiguiente() != null) {
                this.setCabeza(retorno.getSiguiente());
            } else {
                this.setCabeza(null);
            }
            return retorno;
        }
        //System.out.println("No tengo datos");
        return retorno;
    }

    public Imagen popColor() {
        Imagen retorno = null;
        if (this.getCabezaColor() != null) {
            retorno = this.getCabezaColor();
            if (this.getCabezaColor().getSiguiente() != null) {
                this.setCabezaColor(retorno.getSiguiente());
            } else {
                this.setCabezaColor(null);
            }
            return retorno;
        }
        //System.out.println("No tengo datos");
        return retorno;
    }

    public Imagen existeColor() {
        Imagen retorno = null;
        if (this.getCabezaColor() != null) {
            retorno = this.getCabezaColor();
            return retorno;
        }
        return retorno;
    }

    public Imagen popByN() {
        Imagen retorno = null;
        if (this.getCabezaByN() != null) {
            retorno = this.getCabezaByN();
            if (this.getCabezaByN().getSiguiente() != null) {
                this.setCabezaByN(retorno.getSiguiente());
            } else {
                this.setCabezaByN(null);
            }
            return retorno;
        }
        //System.out.println("No tengo datos");
        return retorno;
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

    public String graficaClienteRecepcion() {
        int id = 1;
        String contenido = "digraph L{\n"
                + "node[shape = note fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Cola Recepción\"\n"
                + "bgcolor = \"#8ECBE5\"\n"
                + "raiz[label = \"Recepción\" shape = folder]\n"
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

    public String graficaColaColor() {
        mostrarColor();
        int id = 1;
        String contenido = "digraph L{\n"
                + "node[shape = note fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Cola Impresora Color\"\n"
                + "bgcolor = \"#8ECBE5\"\n"
                + "raiz[label = \"Impresora Color\" shape = folder]\n"
                + "edge[dir = \"right\"]\n";
        String nodos = "", apuntador_nodo = "", rank = "{rank = same;raiz";
        Imagen actual = this.getCabezaColor();
        if (actual != null) {
            contenido += "raiz -> nodo" + id + ";\n";
        }
        for (actual = this.getCabezaColor(); actual != null; actual = actual.getSiguiente()) {
            nodos += "nodo" + id + "[label = \"IMG Color \n Id Cliente: " + actual.getIdCliente() + "\", fillcolor = \"#FCF8F7\", group = " + (id + 1) + "]\n";
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

    public String graficaColaByN() {
        mostrarByN();
        int id = 1;
        String contenido = "digraph L{\n"
                + "node[shape = note fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Cola Impresora Blanco y Negro\"\n"
                + "bgcolor = \"#8ECBE5\"\n"
                + "raiz[label = \"Impresora Blanco Y Negro\" shape = folder]\n"
                + "edge[dir = \"right\"]\n";
        String nodos = "", apuntador_nodo = "", rank = "{rank = same;raiz";
        Imagen actual = this.getCabezaByN();
        if (actual != null) {
            contenido += "raiz -> nodo" + id + ";\n";
        }
        for (actual = this.getCabezaByN(); actual != null; actual = actual.getSiguiente()) {
            nodos += "nodo" + id + "[label = \"IMG Blanco y Negro \n Id Cliente: " + actual.getIdCliente() + "\", fillcolor = \"#FCF8F7\", group = " + (id + 1) + "]\n";
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

    public void mostrarDatos() {
        Cliente actual;
        for (actual = this.getCabeza(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("key: " + actual.getKey_titulo() + " id: " + actual.getId()
                    + " nombre: " + actual.getNombre() + " color: " + actual.getColor() + " byn: " + actual.getByN());
        }
    }

    public void mostrarColor() {
        Imagen actual;
        System.out.println("Cola Color");
        for (actual = this.getCabezaColor(); actual != null; actual = actual.getSiguiente()) {
            System.out.println("Id: " + actual.getIdCliente() + " tipo: " + actual.isTipoImpresion() + " num paso: " + actual.getNumPaso());
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
}
