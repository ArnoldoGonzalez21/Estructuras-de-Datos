package edd_fase_2;

import Clases.NodoAVL;
import Clases.NodoBinario;

/**
 *
 * @author Arnoldo González
 */
public class Utilidades {

    NodoBinario nodoBuscado = new NodoBinario();
    NodoAVL nodoBuscadoAVL = new NodoAVL(-1, 0);
    NodoAVL nodoPadreEliminar = new NodoAVL(-1, 0);
    int cantidad = 0, contador = 0, contadorAVL = 0, cantidadAVL = 0;
    NodoBinario[] matriz;
    NodoAVL[] matrizImagen;
    MatrizDispersa matrizImagenCompleta = new MatrizDispersa(-1);
    public String textRecorrido = "", txtCapaHoja = "";
    public int alturaAVL = 0, profundidadABB = 0;
    public NodoAVL[] topCapa = new NodoAVL[5];

    public void cantidad(ArbolBB abb) {
        this.cantidad = 0;
        this.contador = 0;
        this.cantidad = abb.cantidad();
        if (cantidad == -1) {
            cantidad = 0;
        }
        System.out.println(cantidad);
        this.matriz = new NodoBinario[cantidad];
    }

    public Clases.NodoBinario[] matriz() {
        return this.matriz;
    }

    public void cantidadAVL(ArbolAVL avl) {
        this.cantidadAVL = 0;
        this.contadorAVL = 0;
        this.cantidadAVL = avl.cantidadNodo;
        System.out.println(cantidadAVL);
        this.matrizImagen = new NodoAVL[cantidadAVL];
    }

    public Clases.NodoAVL[] matrizImagen() {
        return this.matrizImagen;
    }

    public void ordenarArreglo() {
        for (int j = 0; j < topCapa.length; j++) {
            for (int i = 0; i < topCapa.length - j - 1; i++) {
                if (topCapa[i] != null && topCapa[i + 1] != null) {
                    if (topCapa[i].numCapas > topCapa[i + 1].numCapas) {
                        NodoAVL tmp = topCapa[i + 1];
                        topCapa[i + 1] = topCapa[i];
                        topCapa[i] = tmp;
                    }
                }
            }
        }
    }
}
