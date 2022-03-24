package edd_fase_2;

/**
 *
 * @author Arnoldo González
 */
public class Utilidades {

    int cantidad = 0, contador = 0;
    Clases.NodoBinario[] matriz;
    MatrizDispersa imagenCompleta = new MatrizDispersa(-1);

    public void cantidad(ArbolBB abb) {
        this.cantidad = abb.cantidad();
        if(cantidad == -1)cantidad = 0;
        this.matriz = new Clases.NodoBinario[cantidad];
    }

    public Clases.NodoBinario[] matriz() {
        return this.matriz;
    }

    public MatrizDispersa matrizDispera() {
        return imagenCompleta;
    }
}
