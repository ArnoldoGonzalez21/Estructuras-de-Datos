package edd_fase_2;

import Clases.Album;
import Clases.Imagen;
import Clases.Capa;

/**
 *
 * @author Arnoldo González
 */
public class ListaSimple {

    private Album cabezaAlbum;
    private Imagen cabezaImagen;
    private Capa cabezaCapa;
    public int sizeCapa = 0;
    public int sizeAlbum;
    private int cantidadImgAlbum;

    public ListaSimple() {
        this.cabezaAlbum = null;
        this.cabezaImagen = null;
        this.cabezaCapa = null;
        this.sizeAlbum = 0;
        this.cantidadImgAlbum = 0;
    }

    public Album insertarAlbum(String nombre, ListaSimple imagenes) {
        Album nuevo = new Album(nombre, imagenes);
        this.sizeAlbum++;
        if (this.getCabezaAlbum() == null) {
            this.setCabezaAlbum(nuevo);
        } else {
            Album actual = this.getCabezaAlbum();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        return nuevo;
    }

    public void insertarImagen(int id) {
        Imagen nuevo = new Imagen(id);
        if (this.getCabezaImagen() == null) {
            this.setCabezaImagen(nuevo);
        } else {
            Imagen actual = this.getCabezaImagen();
            while (actual.getSiguienteAlbum() != null) {
                actual = actual.getSiguienteAlbum();
            }
            actual.setSiguienteAlbum(nuevo);
        }
    }

    public String cantidadImgAlbum() {
        Album actual = getCabezaAlbum();
        while (actual != null) {
            if (actual.getListaImagenes() != null) {
                Imagen actualImg = actual.getListaImagenes().getCabezaImagen();
                while (actualImg != null) {
                    System.out.println(actualImg.getId());
                    cantidadImgAlbum++;
                    actualImg = actualImg.getSiguienteAlbum();
                }
            }
            actual = actual.getSiguiente();
        }
        return String.valueOf(this.cantidadImgAlbum);
    }

    public void insertarCapa(int id) {
        Capa nuevo = new Capa(id);
        this.sizeCapa++;
        if (this.getCabezaCapa() == null) {
            this.setCabezaCapa(nuevo);
        } else {
            Capa actual = this.getCabezaCapa();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public void imprimirAlbum() {
        System.out.println("IMPRIMIR ALBUM");
        Album actual = cabezaAlbum;
        while (actual != null) {
            System.out.println(actual.getNombre());
            if (actual.getListaImagenes() != null) {
                Imagen actualImg = actual.getListaImagenes().getCabezaImagen();
                while (actualImg != null) {
                    System.out.println(actualImg.getId());
                    actualImg = actualImg.getSiguienteAlbum();
                }
            }
            actual = actual.getSiguiente();
        }
    }

    public String graficaAlbum() {
        int id = 1;
        String nodoImagen = "", columnas = "", apuntador = "", apuntadorImagen = "", rank = "{rank = same";
        String contenido = "digraph L{\n"
                + "node[shape = note fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Álbumes\"\n"
                + "bgcolor = \"#8ECBE5\"\n"
                + "edge[dir = \"right\"]\n";

        Album actual = this.getCabezaAlbum();
        for (int i = 0; i < this.sizeAlbum; i++) {

            Boolean primeraImagen = false;
            columnas += "Album" + actual.getId() + "[label = \"Álbum " + actual.getNombre() + "\", fillcolor = \"#F8DEA1\" shape = folder];\n";

            if (actual.getListaImagenes() != null) {
                Clases.Imagen actualImg = actual.getListaImagenes().getCabezaImagen();
                while (actualImg != null) {
                    nodoImagen += "nodo" + id + "[label = \"Imagen " + actualImg.getId() + "\", fillcolor = \"#FCF8F7\"]\n";

                    if (!primeraImagen) {
                        apuntadorImagen += "Album" + actual.getId() + " -> " + " nodo" + id + ";\n";
                        primeraImagen = true;
                    }
                    if (actualImg.getSiguienteAlbum() != null) {
                        apuntadorImagen += " nodo" + id + " -> " + " nodo" + (id + 1) + ";\n";
                    }
                    id++;
                    actualImg = actualImg.getSiguienteAlbum();
                }
            }

            if (actual.getSiguiente() != null) {
                apuntador += "Album" + actual.getId() + " -> " + "Album" + actual.getSiguiente().getId() + ";\n";
            }
            rank += ";" + "Album" + actual.getId();
            actual = actual.getSiguiente();
        }
        rank += "}\n";
        contenido += columnas + apuntador + nodoImagen + apuntadorImagen + rank + "}\n}";
        return contenido;
    }

    public String sizeAlbum() {
        return String.valueOf(this.sizeAlbum);
    }

    /**
     * @return the cabezaAlbum
     */
    public Album getCabezaAlbum() {
        return cabezaAlbum;
    }

    /**
     * @param cabezaAlbum the cabezaAlbum to set
     */
    public void setCabezaAlbum(Album cabezaAlbum) {
        this.cabezaAlbum = cabezaAlbum;
    }

    /**
     * @return the cabezaImagen
     */
    public Imagen getCabezaImagen() {
        return cabezaImagen;
    }

    /**
     * @param cabezaImagen the cabezaImagen to set
     */
    public void setCabezaImagen(Imagen cabezaImagen) {
        this.cabezaImagen = cabezaImagen;
    }

    /**
     * @return the cabezaCapa
     */
    public Capa getCabezaCapa() {
        return cabezaCapa;
    }

    /**
     * @param cabezaCapa the cabezaCapa to set
     */
    public void setCabezaCapa(Capa cabezaCapa) {
        this.cabezaCapa = cabezaCapa;
    }
}
