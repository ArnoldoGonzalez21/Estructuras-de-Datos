package edd_fase_2;

import Clases.Album;
import Clases.Imagen;
import Clases.Capa;
import Clases.PixelImg;
import java.io.FileWriter;

/**
 *
 * @author Arnoldo González
 */
public class ListaSimple {

    private Album cabezaAlbum;
    private Imagen cabezaImagen;
    private Capa cabezaCapa;
    private PixelImg CabezaImgMatriz;
    public int sizeCapa;
    public int sizeAlbum;
    private int cantidadImgAlbum;
    public int xMayor;
    public int yMayor;

    public ListaSimple() {
        this.cabezaAlbum = null;
        this.cabezaImagen = null;
        this.cabezaCapa = null;
        this.CabezaImgMatriz = null;
        this.sizeAlbum = 0;
        this.sizeCapa = 0;
        this.cantidadImgAlbum = 0;
        this.xMayor = 0;
        this.yMayor = 0;
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

    public void insertarImagenMatriz(int x, int y, String color) {
        PixelImg nuevo = new PixelImg(color, x, y);
        if (this.getCabezaImgMatriz() == null) {
            this.setCabezaImgMatriz(nuevo);
        } else {
            PixelImg actual = this.getCabezaImgMatriz();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    private String contenidoHTML() {
        String contenidoImgHTML = "";
        PixelImg actual = this.getCabezaImgMatriz();
        while (actual != null) {
            if (actual.getX() > xMayor) {
                xMayor = actual.getX();
            }
            if (actual.getY() > yMayor) {
                yMayor = actual.getY();
            }
            contenidoImgHTML += "contenido.fillStyle = \"" + actual.getColor() + "\";\n"
                    + "contenido.fillRect(" + (actual.getY() * 20) + ", " + (actual.getX() * 20) + ", 20, 20);\n";
            actual = actual.getSiguiente();
        }
        return contenidoImgHTML;
    }

    public String cantidadImgAlbum() {
        Album actual = getCabezaAlbum();
        while (actual != null) {
            if (actual.getListaImagenes() != null) {
                Imagen actualImg = actual.getListaImagenes().getCabezaImagen();
                while (actualImg != null) {
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

    public void crearImagenHTML(String nombre) {
        String contenido = contenidoHTML();
        String css = "<style>section{ \n "
                + "width:550px; "
                + "position:relative;"
                + "margin:auto;"
                + "} </style>";
        String head = "<head><title>Imagen</title>" + css + "</head>\n";
        String body = "<body>\n<section id = \"dibujo\">";
        body += "<canvas id = \"lienzo\" width = \"" + (yMayor * 20) + "\" height = \"" + (xMayor * 20) + "\"></canvas></section><script>\n";
        body += "var c = document.getElementById(\"lienzo\");\nvar contenido = c.getContext(\"2d\");\n";
        body += contenido + "</script></body>";
        String html = "<html>\n" + head + body + "</html>";
        try {
            FileWriter archivo = new FileWriter(nombre + ".html", false);
            archivo.write(html);
            archivo.close();
        } catch (Exception e) {
            System.out.println("error al generar html imagen");
        } finally {
            com.aspose.html.HTMLDocument document = new com.aspose.html.HTMLDocument(nombre + ".html");
            try {
                // Initialize ImageSaveOptions
                com.aspose.html.saving.ImageSaveOptions options = new com.aspose.html.saving.ImageSaveOptions(com.aspose.html.rendering.image.ImageFormat.Png);
                // Convert HTML to PNG
                com.aspose.html.converters.Converter.convertHTML(document, options, nombre + ".png");
            } finally {
                if (document != null) {
                    document.dispose();
                }
            }
        }
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

    /**
     * @return the CabezaImgMatriz
     */
    public PixelImg getCabezaImgMatriz() {
        return CabezaImgMatriz;
    }

    /**
     * @param CabezaImgMatriz the CabezaImgMatriz to set
     */
    public void setCabezaImgMatriz(PixelImg CabezaImgMatriz) {
        this.CabezaImgMatriz = CabezaImgMatriz;
    }
}
