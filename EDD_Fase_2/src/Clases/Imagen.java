package Clases;

/**
 *
 * @author Arnoldo González
 */
public class Imagen {

    private int id;
    private Imagen siguienteAlbum;

    public Imagen(int id) {
        this.id = id;
        this.siguienteAlbum = null;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the siguienteAlbum
     */
    public Imagen getSiguienteAlbum() {
        return siguienteAlbum;
    }

    /**
     * @param siguienteAlbum the siguienteAlbum to set
     */
    public void setSiguienteAlbum(Imagen siguienteAlbum) {
        this.siguienteAlbum = siguienteAlbum;
    }

}
