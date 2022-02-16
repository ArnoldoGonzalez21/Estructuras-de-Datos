package edd_fase_1;

import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Arnoldo González
 */
public class app {

    Cola clientes = new Cola();
    ListaSimple ventanillas = new ListaSimple();
    ListaSimple imagenes = new ListaSimple();
    ListaSimple clientesAtendidos = new ListaSimple();
    ListaCircularDoble clientesEspera = new ListaCircularDoble();
    Clases.Impresora cabezaImprColor = new Clases.Impresora(true, false);
    Clases.Impresora cabezImprByN = new Clases.Impresora(false, false);
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
                    if (this.clientes != null) {
                        crearGrafico(this.clientes.graficaClienteRecepcion(), "ColaClientes");
                    }
                    if (this.cabezaImprColor.getColaImagen() != null) {
                        crearGrafico(this.cabezaImprColor.getColaImagen().graficaColaColor(), "ColaColor");
                    }
                    if (this.cabezImprByN.getColaImagen() != null) {
                        crearGrafico(this.cabezImprByN.getColaImagen().graficaColaByN(), "ColaByN");
                    }
                    crearGrafico(this.ventanillas.graficaListaVentanilla(), "ListaVentanilla");

                    //System.out.println("Clientes espera: " + clientesEspera.size);
                    //clientesEspera.mostrarClienteEspera();
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

    public void s() {
//        System.out.println("Ventanilla");
//        ventanillas.mostrarDatosV();
        System.out.println("Clientes en Recepción");
        clientes.mostrarDatos();
        System.out.println("Clientes Espera");
        clientesEspera.mostrarClienteEspera();
        System.out.println("Imagenes Impresora:");
        if (cabezaImprColor.getColaImagen() != null) {
            cabezaImprColor.getColaImagen().mostrarColor();
        }
        if (cabezImprByN.getColaImagen() != null) {
            cabezImprByN.getColaImagen().mostrarByN();
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
    }

    public boolean cargaMasivaJson() {
        String Jsontxt = Archivo.leerArchivoJson();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(Jsontxt); // Convertir el texto a JsonElement
        JsonObject obj = element.getAsJsonObject(); //JsonElement a JsonObject
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object

        for (Map.Entry<String, JsonElement> entry : entries) {
            String key = entry.getKey(); //Claves del Json 
            String valor = "[" + entry.getValue().toString(); //Informacin del cliente 
            valor += "]";
            JsonArray gsonArr = parser.parse(valor).getAsJsonArray(); //Info -> JsonArray
            for (JsonElement objt : gsonArr) {
                JsonObject gsonObj = objt.getAsJsonObject();
                int id_cliente = gsonObj.get("id_cliente").getAsInt();
                String nombre_cliente = gsonObj.get("nombre_cliente").getAsString();
                int img_color = gsonObj.get("img_color").getAsInt();
                int img_bw = gsonObj.get("img_bw").getAsInt();
                clientes.insertarCliente(key, id_cliente, nombre_cliente, img_color, img_bw);
            }
        }
        clientes.mostrarDatos();
        return true;
    }

    private void ejecutarPaso() {
        System.out.println("NO. PASO -> " + pasos);
        pasos++;
        clientesAtendidos.terminarCliente(this.clientesEspera);

        Clases.Imagen imagenColorExiste = this.cabezaImprColor.getColaImagen().existeColor();
        if (imagenColorExiste != null) {
            if (imagenColorExiste.getNumPaso() >= 1) {
                Clases.Imagen imagenColor = this.cabezaImprColor.getColaImagen().popColor();
                this.clientesEspera.entregarImagen(imagenColor);
            } else {
                imagenColorExiste.setNumPaso(imagenColorExiste.getNumPaso() + 1);
            }
        }

        Clases.Imagen imagenByN = this.cabezImprByN.getColaImagen().popByN();
        if (imagenByN != null) {
            this.clientesEspera.entregarImagen(imagenByN);
        }
        ventanillas.darImagen(this.cabezaImprColor, this.cabezImprByN, clientesEspera);
        ventanillas.pasarVentanilla(clientes);
        //clientes.generarClientesRandom();
        s();
    }

    public void crearGrafico(String contenidoGrafica, String nombre) {
        try {
            FileWriter archivo = new FileWriter(nombre + ".dot", false);
            PrintWriter pw = new PrintWriter(archivo);
            pw.write(contenidoGrafica);
            pw.close();
            archivo.close();
            Runtime.getRuntime().exec("dot -Tpng " + nombre + ".dot -o " + nombre + ".png");
            System.out.println("Gráfica generada exitosamente");

        } catch (IOException e) {
            System.out.println("Error Archivo: " + e.getMessage());
        }
    }
}
