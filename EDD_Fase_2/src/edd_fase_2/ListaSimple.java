package edd_fase_2;

import Clases.Album;
import Clases.Imagen;

/**
 *
 * @author Arnoldo González
 */
public class ListaSimple {

    private Album cabezaAlbum;
    private Imagen cabezaImagen;

    public ListaSimple() {
        this.cabezaAlbum = null;
        this.cabezaImagen = null;
    }

    public void insertarAlbum(String nombre, ListaSimple imagenes) {
        Album nuevo = new Album(nombre, imagenes);
        if (this.getCabezaAlbum() == null) {
            this.setCabezaAlbum(nuevo);
        } else {
            Album actual = this.getCabezaAlbum();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
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
    
    public void imprimirAlbum(){
        System.out.println("IMPRIMIR ALBUM");
        Album actual = cabezaAlbum;
        while(actual != null)
        {
            System.out.println(actual.getNombre());
            if(actual.getListaImagenes() != null){
                Imagen actualImg = actual.getListaImagenes().getCabezaImagen();
                while(actualImg != null){
                    System.out.println(actualImg.getId());
                    actualImg = actualImg.getSiguienteAlbum();
                }
            }
            actual = actual.getSiguiente();
        }
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
}
