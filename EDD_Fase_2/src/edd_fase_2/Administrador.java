package edd_fase_2;

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

    ListaSimple album = new ListaSimple();
    ListaSimple imagenes = new ListaSimple();
//    ArbolBinarioBusqueda abb = new ArbolBinarioBusqueda();
    ArbolBB arbolbb = new ArbolBB();
    MatrizDispersa matrizD = new MatrizDispersa(-1);
    Grafica grafica = new Grafica();

    public boolean cargaMasivaCliente(String Jsontxt) {
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

    public boolean cargaMasivaCapas(String Jsontxt) {
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            int id_capa = gsonObj.get("id_capa").getAsInt();
            //Clases.Capa nodoCapa = this.abb.insertar(id_capa); //insertar en abb las capas
            Clases.NodoBinario nodoAbb = this.arbolbb.insertar(id_capa);
            JsonArray pixeles = gsonObj.get("pixeles").getAsJsonArray();
            for (JsonElement objt2 : pixeles) {
                JsonObject gsonObj2 = objt2.getAsJsonObject();
                int fila = gsonObj2.get("fila").getAsInt();
                int columna = gsonObj2.get("columna").getAsInt();
                String color = gsonObj2.get("color").getAsString();
                nodoAbb.pixeles.insertar(id_capa, fila, columna, color);
            }
        }
//        this.arbolbb.inorden();
        // this.abb.preOrder();
        // this.abb.graficaPreOrder(grafica);
        // System.out.println(grafica.contenidoABB + grafica.enlaceABBIzq + grafica.enlaceABBDer);
        return true;
    }

    public boolean cargaMasivaImagen(String Jsontxt) {
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

    public boolean cargaMasivaAlbum(String Jsontxt) {
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

    public String matriz() {
        this.grafica.cantidad(this.arbolbb);
        this.arbolbb.inorden(this.arbolbb.raiz, grafica);
        System.out.println("lenght: " + grafica.matriz().length);
        for (int i = 0; i < grafica.matriz().length; i++) {
            matrizD = grafica.matriz()[i].pixeles.recorrerMatriz(this.grafica);

        }
        String contenido = matrizD.GraficarMatriz();
        System.out.println(contenido);
        return contenido;
    }
    
    public void generarAbb(){
        this.arbolbb.graficar("arbol_abb.png");
    }

//    public String matriz() {
//        Clases.Capa nodoCapa = this.abb.raiz;
////        String contenido = nodoCapa.pixeles.GraficarMatriz();
//        System.out.println("cantidad " + this.abb.cantidad());
//        this.grafica.cantidad(abb);
//        this.abb.inOrder(grafica);
//        System.out.println("lenght: " + grafica.matriz().length);
//        for (int i = 0; i < grafica.matriz().length; i++) {
//            System.out.println("ID CAPA: " + grafica.matriz()[i].getIdCapa());
//            matrizD = grafica.matriz()[i].pixeles.recorrerMatriz(this.grafica);
//
//        }
//        String contenido = matrizD.GraficarMatriz();
//        //System.out.println("aca vamos de nuevo");
//        //matrizD.recorrerMatriz(this.grafica);
//        return contenido;
//    }
    public void crearGrafico(String contenidoGrafica, String nombre) {
        try {
            FileWriter archivo = new FileWriter(nombre + ".dot", false);
            PrintWriter pw = new PrintWriter(archivo);
            pw.write(contenidoGrafica);
            pw.close();
            archivo.close();
            try {
                Runtime.getRuntime().exec("dot -Tpng " + nombre + ".dot -o " + nombre + ".png");
                //Esperamos medio segundo para dar tiempo a que la imagen se genere.
                //Para que no sucedan errores en caso de que se decidan graficar varios
                //árboles sucesivamente.
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
