package edd_fase_1;

import Clases.Imagen;
import Clases.Impresora;


/**
 *
 * @author Arnoldo González
 */
public class Cola {
    
    private Imagen cabezaColor;
    private Imagen cabezaByN;
    
    public void push(boolean tipoImpresora, boolean ocupada, boolean tipoImagen) { 
        /*Tengo que enviar la pila de imagenes a la cola de impresión al acabar y guardarla ahora como una cola y 
        y borrar la pila original*/

        Impresora nuevo = new Impresora(tipoImpresora, ocupada, tipoImagen);
//        if (this.getCabezaColor() == null) {
//            this.setCabezaColor(nuevo);
//        } else {
//            Impresora actual = this.getCabezaColor();
//            while (actual.getSiguiente() != null) {
//                actual = actual.getSiguiente();
//
//            }
//            actual.setSiguiente(nuevo);
//        }
    }
//    public Nodo pop() {
//        Nodo retorno = null;
//        if (this.getCabeza() != null) {
//            retorno = this.getCabeza();
//            if (this.getCabeza().getSiguiente() != null) {
//                this.setCabeza(retorno.getSiguiente());
//            } else {
//                this.setCabeza(null);
//            }
//            return retorno;
//        }
//
//        System.out.println("No tengo datos");
//        return retorno;
//    }

}
