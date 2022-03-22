package edd_fase_2;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author Arnoldo González
 */
public class Administrador {

    ListaSimple album = new ListaSimple();
    ListaSimple imagenes = new ListaSimple();
    Matriz capas = new Matriz();

    public boolean cargaMasivaCliente() {

        String Jsontxt = Archivo.leerArchivoJsonConsola();
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            String entradaDpi = gsonObj.get("dpi").getAsString().trim();
            String nombre = gsonObj.get("nombre_cliente").getAsString();
            String contrasena = gsonObj.get("password").getAsString();

            long dpi = Long.parseLong(entradaDpi);
            System.out.println("dpi: " + dpi + " nombre: " + nombre + " contrasena: " + contrasena);

//            if (!this.agregarProfesor(codigo, nombre, apellido, correo, "1234", gen)) 
        }
        return true;
    }

    public boolean cargaMasivaCapas() {
        String Jsontxt = Archivo.leerArchivoJsonConsola();
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
                this.capas.insertarNodo(id_capa, fila, columna, color);
                System.out.println("id_capa: " + id_capa + " fila: " + fila + " columna: " + columna + " color: " + color);
            }
        }
        return true;
    }

    public boolean cargaMasivaImagen() {

        String Jsontxt = Archivo.leerArchivoJsonConsola();
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            int id = gsonObj.get("id").getAsInt();
            JsonArray capas = gsonObj.get("capas").getAsJsonArray();
            System.out.println("id: " + id + " capas: " + capas);
        }
        return true;
    }

    public boolean cargaMasivaAlbum() {

        String Jsontxt = Archivo.leerArchivoJsonConsola();
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            String nombreAlbum = gsonObj.get("nombre_album").getAsString();
            JsonArray imgs = gsonObj.get("imgs").getAsJsonArray();
            //System.out.println("nombreAlbum: " + nombreAlbum + " imgs: " + imgs);
            for (JsonElement objt2 : imgs) {
                int id = objt2.getAsInt();
                this.imagenes.insertarImagen(id);
            }
            this.album.insertarAlbum(nombreAlbum, this.imagenes);
            this.imagenes = new ListaSimple();
        }
        return true;
    }
}
