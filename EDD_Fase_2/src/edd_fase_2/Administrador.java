package edd_fase_2;

import Clases.NodoBinario;
import Clases.Usuario;
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
//    MatrizDispersa matrizD = new MatrizDispersa(1);
    MatrizDispersa matrizPixeles = new MatrizDispersa(1);
    Utilidades util = new Utilidades();

    public boolean cargaMasivaCliente(String Jsontxt, Registro registro) {
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            Comparable dpi = gsonObj.get("dpi").getAsString().trim();
            String nombre = gsonObj.get("nombre_cliente").getAsString().trim();
            String contrasena = gsonObj.get("password").getAsString().trim();
            Usuario user = registro.insertarUsuario(dpi, nombre, contrasena);
            if (user != null) {
//                System.out.println("dpi: " + dpi + " nombre: " + nombre + " contrasena: " + contrasena);
            } else {
//                System.out.println("El DPI " + dpi + " ya existe");
            }
        }
        return true;
    }

    public boolean cargaMasivaCapas(String Jsontxt, Usuario usuarioActual) {
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            int id_capa = gsonObj.get("id_capa").getAsInt();
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
        }
        return true;
    }

    public boolean cargaMasivaImagen(String Jsontxt, Usuario usuarioActual) {
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            int id = gsonObj.get("id").getAsInt();
            JsonArray capas = gsonObj.get("capas").getAsJsonArray();
            System.out.println("id: " + id + " capas: " + capas);
            usuarioActual.getImagenesUser().insertar(id, capas);
        }
        return true;
    }

    public boolean cargaMasivaAlbum(String Jsontxt, Usuario usuarioActual) {
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            String nombreAlbum = gsonObj.get("nombre_album").getAsString();
            JsonArray imgs = gsonObj.get("imgs").getAsJsonArray();
            System.out.println("nombreAlbum: " + nombreAlbum + " imgs: " + imgs);
            for (JsonElement objt2 : imgs) {
                int id = objt2.getAsInt();
                this.imagenes.insertarImagen(id);
            }
            usuarioActual.getAlbumUser().insertarAlbum(nombreAlbum, this.imagenes);
            this.imagenes = new ListaSimple();
        }
        return true;
    }
    
    public void crearImagen(String id){
        
    }

    public String matrizCompleta(Usuario usuarioActual) {
        this.util.cantidad(usuarioActual.getCapasUser());
        usuarioActual.getCapasUser().inorden(usuarioActual.getCapasUser().raiz, util);
        for (int i = 0; i < util.matriz().length; i++) {
            util.matriz()[i].pixeles.recorrerMatriz(util);
        }
        String contenido = this.util.matrizImagenCompleta.graficarMatriz();
        return contenido;
    }

    public String matrizNodo(Usuario usuarioActual, String id) {
        NodoBinario nodo = usuarioActual.getCapasUser().inordenBus(usuarioActual.getCapasUser().raiz, this.util, id);
        String contenido = "";
        if (nodo != null || !nodo.getValor().toString().equals("-1")) {
            contenido = nodo.pixeles.graficarMatriz();
        }
        System.out.println(contenido);
        return contenido;
    }

    public String[] capaCombo(Usuario usuarioActual) {
        this.util.cantidad(usuarioActual.getCapasUser());
        usuarioActual.getCapasUser().inorden(usuarioActual.getCapasUser().raiz, util);
        String[] matrizCompleta = new String[util.matriz().length];
        for (int i = 0; i < util.matriz().length; i++) {
            matrizCompleta[i] = util.matriz()[i].getValor().toString();
        }
        return matrizCompleta;
    }

    public String[] imagenCombo(Usuario usuarioActual) {
        this.util.cantidadAVL(usuarioActual.getImagenesUser());
        usuarioActual.getImagenesUser().inorden(usuarioActual.getImagenesUser().raiz, this.util);
        System.out.println("lenght: " + util.matrizImagen().length);
        String[] matrizCompleta = new String[util.matrizImagen().length];
        for (int i = 0; i < util.matrizImagen().length; i++) {
            matrizCompleta[i] = util.matrizImagen()[i].getValor().toString();
        }
        return matrizCompleta;
    }

    public void generarAbb(Usuario usuarioActual, String nombre) {
        usuarioActual.getCapasUser().graficar(nombre);
    }

    public String generarListaAlbum(Usuario usuarioActual) {
        return usuarioActual.getAlbumUser().graficaAlbum();
    }

    public void generarAVL(Usuario usuarioActual, String nombre) {
        usuarioActual.getImagenesUser().graficar(nombre);
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
