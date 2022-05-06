package edd_fase_3;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import Clases.Cliente;

/**
 *
 * @author Arnoldo González
 */
public class Administrador {

    public boolean cargaMasivaCliente(String Jsontxt, Registro registro) {
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(Jsontxt).getAsJsonArray();
        for (JsonElement objt : gsonArr) {
            JsonObject gsonObj = objt.getAsJsonObject();
            String dpi = gsonObj.get("dpi").getAsString().trim();
            String nombre = gsonObj.get("nombre_cliente").getAsString().trim();

            String usuario = gsonObj.get("username").getAsString().trim();
            String correo = gsonObj.get("email").getAsString().trim();
            String contrasena = gsonObj.get("password").getAsString().trim();
            String telefono = gsonObj.get("phone").getAsString().trim();
            String direccion = gsonObj.get("address").getAsString().trim();
            int idMunicipio = gsonObj.get("department_id").getAsInt();
            registro.insertarUsuario(dpi, nombre, usuario, correo, contrasena, telefono, direccion, idMunicipio);
        }
        return true;
    }

    public String[] opcionesUser(Registro registro) {
        return registro.opcionesUser();
    }

    public void modificarUser(Registro registro, String dpi, String usuario, String nombre, String contrasena, String telefono, String direccion, int idMunicipio, String correo) {
        registro.modificarUsuario(dpi, usuario, nombre, contrasena, telefono, direccion, idMunicipio, correo);
    }

    public Cliente buscarUser(Registro registro, String dpi) {
        return registro.buscarUsuario(dpi);
    }

    public boolean eliminarUsuario(Registro registro, String usuario) {
        boolean eliminado = registro.eliminarUsuario(usuario);
        return eliminado;
    }

    public boolean crearUser(Registro registro, String dpi, String usuario, String nombre, String contrasena, String telefono, String direccion, int idMunicipio, String correo) {
        Cliente user = registro.insertarUsuario(dpi, nombre, usuario, correo, contrasena, telefono, direccion, idMunicipio);
        if(user == null){
            return false;
        }
        return true;
    }
}
