package edd_fase_2;

import Clases.Capa;
import Clases.NodoAVL;
import Clases.NodoBinario;
import Clases.NodoB;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Arnoldo González
 */
public class Administrador {

    ListaSimple imagenes = new ListaSimple();
    MatrizDispersa matrizPixeles = new MatrizDispersa(1);
    Utilidades util = new Utilidades();
    ArbolB arbolB = new ArbolB(3, util);

    public boolean cargaMasivaCliente(String Jsontxt, Registro registro) {
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            String dpi = gsonObj.get("dpi").getAsString().trim();
            String nombre = gsonObj.get("nombre_cliente").getAsString().trim();
            String contrasena = gsonObj.get("password").getAsString().trim();
            NodoB user = registro.insertarUsuario(dpi, nombre, contrasena);
            long numDpi = Long.parseLong(dpi);
            if (user != null) {
                arbolB.insertar(numDpi);
            }
            if (user == null) {
                System.out.println("El DPI " + dpi + " ya existe");
            }
        }
        return true;
    }

    public boolean cargaMasivaCapas(String Jsontxt, NodoB usuarioActual) {
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            int id_capa = gsonObj.get("id_capa").getAsInt();
            NodoBinario nodoVerificar = usuarioActual.getCapasUser().inorderBus(usuarioActual.getCapasUser().raiz, this.util, String.valueOf(id_capa));
            if (nodoVerificar.getValor().equals(-1)) {
                JsonArray pixeles = gsonObj.get("pixeles").getAsJsonArray();
                for (JsonElement objt2 : pixeles) {
                    JsonObject gsonObj2 = objt2.getAsJsonObject();
                    int fila = gsonObj2.get("fila").getAsInt();
                    int columna = gsonObj2.get("columna").getAsInt();
                    String color = gsonObj2.get("color").getAsString();
                    this.matrizPixeles.insertar(id_capa, fila, columna, color);
                }
                usuarioActual.getCapasUser().insertar(id_capa, this.matrizPixeles);//insertar en abb las capas            
                this.matrizPixeles = new MatrizDispersa(1);
            } else {
                System.out.println("La Capa " + id_capa + " ya existe");
            }
        }
        return true;
    }

    public boolean cargaMasivaImagen(String Jsontxt, NodoB usuarioActual) {
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            int id = gsonObj.get("id").getAsInt();
            NodoAVL nodoVerificar = usuarioActual.getImagenesUser().inorderBus(usuarioActual.getImagenesUser().raiz, this.util, String.valueOf(id));
            if (nodoVerificar.getValor().equals(-1)) {
                JsonArray capas = gsonObj.get("capas").getAsJsonArray();
                int contador = 0;
                for (JsonElement objt2 : capas) {
                    contador++;
                }
                usuarioActual.getImagenesUser().insertar(id, capas, contador);
            } else {
                System.out.println("La Imagen " + id + " ya existe");
            }
        }
        return true;
    }

    public boolean cargaMasivaAlbum(String Jsontxt, NodoB usuarioActual) {
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            String nombreAlbum = gsonObj.get("nombre_album").getAsString();
            if (!usuarioActual.getAlbumUser().existeAlbum(nombreAlbum)) {
                JsonArray imgs = gsonObj.get("imgs").getAsJsonArray();
                for (JsonElement objt2 : imgs) {
                    int id = objt2.getAsInt();
                    this.imagenes.insertarImagen(id);
                }
                usuarioActual.getAlbumUser().insertarAlbum(nombreAlbum, this.imagenes);
                this.imagenes = new ListaSimple();
            }else{
                System.out.println("El Album con el nombre "+nombreAlbum+ " ya existe");
            }
        }
        return true;
    }

    public String crearImagen(NodoB usuarioActual, String id) {
        this.util.matrizImagenCompleta = new MatrizDispersa(1);
        NodoAVL nodo = usuarioActual.getImagenesUser().inorderBus(usuarioActual.getImagenesUser().raiz, this.util, id);
        String contenido = "";
        if (nodo != null) {
            Capa actual = nodo.capas.getCabezaCapa();
            while (actual != null) {
                NodoBinario nodoABB = usuarioActual.getCapasUser().inorderBus(usuarioActual.getCapasUser().raiz, this.util, String.valueOf(actual.getIdCapa()));
                if (nodoABB != null || !nodoABB.getValor().toString().equals("-1")) {
                    nodoABB.pixeles.recorrerMatriz(this.util, usuarioActual.getImgPixel());
                }
                actual = actual.getSiguiente();
            }
        }
        contenido = this.util.matrizImagenCompleta.graficarMatriz();
        return contenido;
    }

    public String matrizCompleta(NodoB usuarioActual) {
        this.util.cantidad(usuarioActual.getCapasUser());
        usuarioActual.getCapasUser().inorderArregloNodo(usuarioActual.getCapasUser().raiz, util);
        for (int i = 0; i < util.matriz().length; i++) {
            util.matriz()[i].pixeles.recorrerMatriz(util, usuarioActual.getImgPixel());
        }
        String contenido = this.util.matrizImagenCompleta.graficarMatriz();
        return contenido;
    }

    public String matrizNodo(NodoB usuarioActual, String id) {
        NodoBinario nodo = usuarioActual.getCapasUser().inorderBus(usuarioActual.getCapasUser().raiz, this.util, id);
        String contenido = "";
        if (nodo != null || !nodo.getValor().toString().equals("-1")) {
            contenido = nodo.pixeles.graficarMatriz();
        }
        return contenido;
    }

    public String[] capaCombo(NodoB usuarioActual) {
        this.util.cantidad(usuarioActual.getCapasUser());
        usuarioActual.getCapasUser().inorderArregloNodo(usuarioActual.getCapasUser().raiz, util);
        String[] matrizCompleta = new String[util.matriz().length];
        for (int i = 0; i < util.matriz().length; i++) {
            matrizCompleta[i] = util.matriz()[i].getValor().toString();
        }
        return matrizCompleta;
    }

    public String[] imagenCombo(NodoB usuarioActual) {
        this.util.cantidadAVL(usuarioActual.getImagenesUser());
        usuarioActual.getImagenesUser().inorder(usuarioActual.getImagenesUser().raiz, this.util);
        System.out.println("lenght: " + util.matrizImagen().length);
        String[] matrizCompleta = new String[util.matrizImagen().length];
        for (int i = 0; i < util.matrizImagen().length; i++) {
            matrizCompleta[i] = util.matrizImagen()[i].getValor().toString();
        }
        return matrizCompleta;
    }

    public String textRecorrido(NodoB usuarioActual, int tipoRec, int tipoArbol) {//tipoArbol 0->ABB, 1->AVL
        if (tipoArbol == 0) {
            usuarioActual.getCapasUser().imprimirRecorrido(usuarioActual.getCapasUser().raiz, this.util, tipoRec);
        } else if (tipoArbol == 1) {
            usuarioActual.getImagenesUser().imprimirRecorrido(usuarioActual.getImagenesUser().raiz, this.util, tipoRec);
        } else {
            return "";
        }
        return this.util.textRecorrido;
    }

    public String textAltura(NodoB usuarioActual) {//tipoArbol 0->ABB, 1->AVL
        usuarioActual.getImagenesUser().imprimirAltura(usuarioActual.getImagenesUser().raiz, this.util);
        return String.valueOf("  " + (util.alturaAVL + 1));
    }

    public String textCapaHoja(NodoB usuarioActual) {
        usuarioActual.getCapasUser().imprimirCapaHoja(usuarioActual.getCapasUser().raiz, this.util);
        return util.txtCapaHoja;
    }

    public Object[][] tablaTopCinco(NodoB usuarioActual) {
        Object[][] tabla = new Object[5][3];
        NodoAVL[] top = usuarioActual.getImagenesUser().TopCinco(usuarioActual.getImagenesUser().raiz, this.util);
        int j = top.length - 1;
        for (int i = 0; i < tabla.length; i++) {
            if (top[j] == null) {
                continue;
            }
            tabla[i][0] = (i + 1);
            tabla[i][1] = top[j].getValor().toString();
            tabla[i][2] = top[j].numCapas;
            j--;
        }
        return tabla;
    }

    public String[] opcionesUser(Registro registro) {
        return registro.opcionesUser(this.util);
    }

    public NodoB buscarUser(Registro registro, String dpi) {
        return registro.buscarUsuario(dpi);
    }

    public void modificarUser(Registro registro, String dpiViejo, String dpiNuevo, String nombre, String contrasena) {
        registro.modificarUsuario(dpiViejo, dpiNuevo, nombre, contrasena);
        if (!dpiViejo.equals(dpiNuevo)) {
            this.arbolB.eliminar(Long.parseLong(dpiViejo));
            this.arbolB.insertar(Long.parseLong(dpiNuevo));
        }
    }

    public boolean eliminarUsuario(NodoB usuarioActual, Registro registro, String dpi) {
        boolean enRegi = registro.eliminarUsuario(dpi);
        this.arbolB.eliminar(Long.parseLong(dpi));
        return enRegi;
    }

    public String cantidadCapas(NodoB user) {
        return String.valueOf(user.getCapasUser().cantidad());
    }

    public String cantidadNodoAVL(NodoB user) {
        return String.valueOf(user.getImagenesUser().cantidadAVL());
    }

    public Object[][] tablaClienteImg(Registro registro) {
        return registro.recorrerTablaUser();
    }

    public void generarAbb(NodoB usuarioActual, String nombre) {
        usuarioActual.getCapasUser().graficar(nombre);
    }

    public String generarListaAlbum(NodoB usuarioActual) {
        return usuarioActual.getAlbumUser().graficaAlbum();
    }

    public void generarAVL(NodoB usuarioActual, String nombre) {
        usuarioActual.getImagenesUser().graficar(nombre);
    }

    public void generarB(NodoB usuarioActual, String nombre) {
        arbolB.mostrarArbolB();
        usuarioActual.graficar(nombre, this.util);
    }

    public void crearImagenHTML(NodoB nodo, String nombre) {
        nodo.getImgPixel().crearImagenHTML(nombre);
    }

    public void crearGrafico(String contenidoGrafica, String nombre) {
        try {
            FileWriter archivo = new FileWriter(nombre + ".dot", false);
            PrintWriter pw = new PrintWriter(archivo);
            pw.write(contenidoGrafica);
            pw.close();
            archivo.close();
            try {
                Runtime.getRuntime().exec("dot -Tpng " + nombre + ".dot -o " + nombre + ".png");
                //Esperar para evitar posibles errores mas adelante
                Thread.sleep(500);
                System.out.println("Gráfica generada exitosamente");
            } catch (Exception ex) {
                System.err.println("Error al generar la imagen del archivo .dot");
            }
        } catch (IOException e) {
            System.out.println("Error Archivo: " + e.getMessage());
        }
    }
}
