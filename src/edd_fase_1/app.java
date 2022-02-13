package edd_fase_1;

import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Arnoldo González
 */
public class app {

    ListaSimple clientes = new ListaSimple();
    ListaSimple ventanillas = new ListaSimple();
    int pasos = 1;

    public app() {
        Menu();
    }

    private void Menu() {
        int opcion;
        opcion = 0;
        Scanner lector = new Scanner(System.in);
        do {
            System.out.println("\n==============================================");
            System.out.println("^         <<<<<<<<<< MENU >>>>>>>>>>         ^");
            System.out.println("^  1) Parámetros iniciales                   ^");
            System.out.println("^  2) Ejecutar paso                          ^");
            System.out.println("^  3) Estado en memoria de las estructuras   ^");
            System.out.println("^  4) Generar Reportes                       ^");
            System.out.println("^  5) Acerca de                              ^");
            System.out.println("^  0) Salir                                  ^");
            System.out.println("==============================================");
            System.out.print("Escriba aquí: ");
            opcion = lector.nextInt();
            switch (opcion) {
                case 1:
                    ParamatrosIniciales();
                    break;
                case 2:
                    ejecutarPaso();
                    break;
                case 3:
                    clientes.generarClientesRandom();
                    break;
                case 4:
                    ventanillas.mostrarDatosV();
                    break;
                case 5:
                    DatosEstudiante();
                    break;
                case 0:
                    System.out.println("*Saliendo del programa*");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n<<<<<<<Opción inválida>>>>>>>");
            }
        } while (opcion != 0);
    }

    private void ParamatrosIniciales() {
        int opcion;
        opcion = 0;
        Scanner lector = new Scanner(System.in);
        System.out.println("\n==============================================");
        System.out.println("^    <<<<<<< PARAMETROS INICIALES >>>>>>>    ^");
        System.out.println("^  1) Carga masiva de clientes               ^");
        System.out.println("^  2) Cantidad de ventanillas                ^");
        System.out.println("==============================================");
        System.out.print("Escriba aquí: ");
        opcion = lector.nextInt();
        switch (opcion) {
            case 1:
                System.out.print("Escriba la ruta del archivo Json: ");
                cargaMasivaJson();
                break;
            case 2:
                System.out.print("Digite la cantidad de ventanillas: ");
                cantidadVentanilla();
                break;
            default:
                System.out.println("\n<<<<<<< Opción Inválida >>>>>>>");
        }
    }

    private void DatosEstudiante() {
        System.out.println("\n>>> Arnoldo Luis Antonio González Camey");
        System.out.println(">>> Carné: 20170148");
        System.out.println(">>> Ingeniería en Ciencias y Sistemas");
        System.out.println(">>> Estructuras de Datos");
    }

    private void cantidadVentanilla() {

        Scanner lector = new Scanner(System.in);
        int numeroVentanillas = lector.nextInt();
        ventanillas.insertarVentanilla(numeroVentanillas);
//        ventanillas.mostrarDatosV();
    }

    public boolean cargaMasivaJson() {

        String Jsontxt = Archivo.leerArchivoJson();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(Jsontxt); // Convertir el texto a JsonElement
        JsonObject obj = element.getAsJsonObject(); //JsonElement a JsonObject
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object

        for (Map.Entry<String, JsonElement> entry : entries) {
            //System.out.println(entry.getKey());
            String key = entry.getKey(); //Claves del Json 
            //System.out.println("value: " + entry.getValue());
            String valor = "[" + entry.getValue().toString(); //Informacin del cliente 
            valor += "]";
            JsonArray gsonArr = parser.parse(valor).getAsJsonArray(); //Info -> JsonArray
            for (JsonElement objt : gsonArr) {
                JsonObject gsonObj = objt.getAsJsonObject();
                int id_cliente = gsonObj.get("id_cliente").getAsInt();
                String nombre_cliente = gsonObj.get("nombre_cliente").getAsString();
                int img_color = gsonObj.get("img_color").getAsInt();
                int img_bw = gsonObj.get("img_bw").getAsInt();
                //System.out.println(key + id_cliente + nombre_cliente + img_color + img_bw);
                clientes.insertarCliente(key, id_cliente, nombre_cliente, img_color, img_bw);
            }
        }
        clientes.mostrarDatos();
        //https://stackoverflow.com/questions/31094305/java-gson-getting-the-list-of-all-keys-under-a-jsonobject
        return true;
    }

    private void ejecutarPaso() {
        System.out.println("NO. PASO -> " + pasos);
        pasos++;
        clientes.darImagen(ventanillas.getCabezaVen());
        clientes.pasarVentanilla(ventanillas.getCabezaVen());
        //ventanillas.mostrarDatosV();
        //clientes.generarClientesRandom();
        //clientes.mostrarDatos();

    }
}
