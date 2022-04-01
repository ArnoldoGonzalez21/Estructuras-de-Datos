package Clases;

import edd_fase_2.ArbolAVL;
import edd_fase_2.ArbolBB;
import edd_fase_2.ListaSimple;
import edd_fase_2.Utilidades;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Arnoldo González
 */
public class NodoB {

    private ListaSimple albumUser;
    private ListaSimple imgPixel;
    private ArbolAVL imagenesUser;
    private ArbolBB capasUser;
    private String dpi;
    private String nombre;
    private String contrasena;
    private NodoB siguiente;
    private NodoB anterior;
    public boolean cargoCapa;
    public boolean cargoImagen;
    public boolean cargoCliente;
    public boolean cargoAlbum;
    public boolean genero;

    public long[] ClavesNodo;
    int GradoMin;
    public NodoB[] hijos;
    public int numClavesNodo; // El número de claves de nodos
    private boolean Hoja; // true -> Hoja , false -> no es hoja

    public NodoB(String dpi, String nombre, String contrasena) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.siguiente = null;
        this.anterior = null;
        this.albumUser = new ListaSimple();
        this.imgPixel = new ListaSimple();
        this.imagenesUser = new ArbolAVL();
        this.capasUser = new ArbolBB();
        this.cargoCapa = false;
        this.cargoImagen = false;
        this.cargoAlbum = false;
        this.genero = false;
    }

    public NodoB(String dpi, String contrasena) {
        this.contrasena = contrasena;
        this.dpi = dpi;
        this.cargoCliente = false;
        this.genero = false;
    }

    public NodoB(int deg, boolean isHoja) {
        this.GradoMin = deg;
        this.Hoja = isHoja;
        this.ClavesNodo = new long[2 * this.GradoMin - 1]; // El nodo tiene como máximo 2 * teclas GradoMin-1
        this.hijos = new NodoB[2 * this.GradoMin];
        this.numClavesNodo = 0;
    }

    public boolean validarUsuario(String dpi, String password) {
        boolean dpiUs = this.getDpi().equalsIgnoreCase(dpi);
        boolean contrasenaUs = this.getContrasena().equals(password);
        boolean correcto = dpiUs && contrasenaUs; //los dos true correcto es true
        return correcto;
    }

    public int EncontrarClave(long key) {// Encuentra el primer índice de posición igual o mayor que la clave
        int indice = 0;
        while (indice < numClavesNodo && ClavesNodo[indice] < key) { //sale al recorrer todo o encontrar la clave o mayor a esta
            ++indice;
        }
        return indice;
    }

    public void eliminar(long key) {
        int indice = EncontrarClave(key);
        if (indice < numClavesNodo && ClavesNodo[indice] == key) { // Encontrar la llave
            if (isHoja()) { // la clave está en el nodo hoja            
                eliminarDeHoja(indice);
            } else {// la clave no está en el nodo hoja            
                elminarNoHoja(indice);
            }
        } else {
            if (isHoja()) { // Si el nodo es un nodo hoja, entonces el nodo no está en el árbol B
                System.out.printf("El dpi %d no existe\n", key);
                return;
            }

            boolean bandera = indice == numClavesNodo;//La clave existe en el subárbol hijo en su ultima pos del nodo

            if (hijos[indice].numClavesNodo < GradoMin) { //Llenar nodo hijo si no esta lleno
                llenar(indice);
            }
            if (bandera && indice > numClavesNodo) { //revisar si hay que entrar a la pos anterior de los hijos o la actual
                hijos[indice - 1].eliminar(key);
            } else {
                hijos[indice].eliminar(key);
            }
        }
    }

    public void eliminarDeHoja(int indice) {
        for (int i = indice + 1; i < numClavesNodo; ++i) {
            ClavesNodo[i - 1] = ClavesNodo[i];
        }
        numClavesNodo--;
    }

    public void elminarNoHoja(int indice) {
        long key = ClavesNodo[indice];
        if (hijos[indice].numClavesNodo >= GradoMin) { //la cantidad de nodos en el hijo es mayor/igual al grado, buscar el pred
            long predecesor = getPredecesor(indice);
            ClavesNodo[indice] = predecesor;
            hijos[indice].eliminar(predecesor);
        } else if (hijos[indice + 1].numClavesNodo >= GradoMin) { //si la posicion siguiente tiene mas claves buscar el sucesor
            long sucesor = getSucesor(indice);
            ClavesNodo[indice] = sucesor;
            hijos[indice + 1].eliminar(sucesor);
        } else { //tienen menos los dos unir
            unir(indice); //
            hijos[indice].eliminar(key);
        }
    }

    public long getPredecesor(int indice) {
        // Moverse al nodo más a la derecha hasta que llegue al nodo hoja
        NodoB aux = hijos[indice];
        while (!aux.isHoja()) {
            aux = aux.hijos[aux.numClavesNodo];
        }
        return aux.ClavesNodo[aux.numClavesNodo - 1];
    }

    public long getSucesor(int idx) {
        //Mover al nodo más a la izquierda de los hijos hasta que llegue al nodo hoja
        NodoB sucesor = hijos[idx + 1];
        while (!sucesor.isHoja()) {
            sucesor = sucesor.hijos[0];
        }
        return sucesor.ClavesNodo[0];
    }

    public void llenar(int indice) {
        if (indice != 0 && hijos[indice - 1].numClavesNodo >= GradoMin) { //nodo hijo tiene mas claves presetar al previo
            prestarPrevio(indice);
        } else if (indice != numClavesNodo && hijos[indice + 1].numClavesNodo >= GradoMin) { //el ulitmo tiene varias claves a el pedir prestado
            prestarSiguiente(indice);
        } else {
            if (indice != numClavesNodo) {
                unir(indice);
            } else {
                unir(indice - 1);
            }
        }
    }

    public void prestarPrevio(int indice) { //obtener una clave del nodo anterior e insertarla en el actual

        NodoB nodoHijo = hijos[indice];
        NodoB nodoHermano = hijos[indice - 1];
        for (int i = nodoHijo.numClavesNodo - 1; i >= 0; --i) {
            nodoHijo.ClavesNodo[i + 1] = nodoHijo.ClavesNodo[i];
        }
        if (!nodoHijo.isHoja()) {
            for (int i = nodoHijo.numClavesNodo; i >= 0; --i) {
                nodoHijo.hijos[i + 1] = nodoHijo.hijos[i];
            }
        }

        nodoHijo.ClavesNodo[0] = ClavesNodo[indice - 1]; //la primera clave del nodo hijo la clave del nodo actual -1
        if (!nodoHijo.isHoja()) {
            nodoHijo.hijos[0] = nodoHermano.hijos[nodoHermano.numClavesNodo];
        }
        ClavesNodo[indice - 1] = nodoHermano.ClavesNodo[nodoHermano.numClavesNodo - 1]; //mover ultimas claves
        nodoHijo.numClavesNodo += 1;
        nodoHermano.numClavesNodo -= 1;
    }

    public void prestarSiguiente(int indice) {
        NodoB nodoHijo = hijos[indice];
        NodoB nodoHermano = hijos[indice + 1];
        nodoHijo.ClavesNodo[nodoHijo.numClavesNodo] = ClavesNodo[indice];
        if (!nodoHijo.isHoja()) {
            nodoHijo.hijos[nodoHijo.numClavesNodo + 1] = nodoHermano.hijos[0];
        }
        ClavesNodo[indice] = nodoHermano.ClavesNodo[0];

        for (int i = 1; i < nodoHermano.numClavesNodo; ++i) {
            nodoHermano.ClavesNodo[i - 1] = nodoHermano.ClavesNodo[i];
        }
        if (!nodoHermano.isHoja()) {
            for (int i = 1; i <= nodoHermano.numClavesNodo; ++i) {
                nodoHermano.hijos[i - 1] = nodoHermano.hijos[i];
            }
        }
        nodoHijo.numClavesNodo += 1;
        nodoHermano.numClavesNodo -= 1;
    }

    public void unir(int indice) { //unir hijo en la posicion siguiente con el actual

        NodoB nodoHijo = hijos[indice];
        NodoB nodoHermano = hijos[indice + 1];

        nodoHijo.ClavesNodo[GradoMin - 1] = ClavesNodo[indice];

        for (int i = 0; i < nodoHermano.numClavesNodo; ++i) {
            nodoHijo.ClavesNodo[i + GradoMin] = nodoHermano.ClavesNodo[i];
        }

        if (!nodoHijo.isHoja()) {
            for (int i = 0; i <= nodoHermano.numClavesNodo; ++i) {
                nodoHijo.hijos[i + GradoMin] = nodoHermano.hijos[i];
            }
        }

        for (int i = indice + 1; i < numClavesNodo; ++i) {
            ClavesNodo[i - 1] = ClavesNodo[i];
        }
        for (int i = indice + 2; i <= numClavesNodo; ++i) {
            hijos[i - 1] = hijos[i];
        }
        nodoHijo.numClavesNodo += nodoHermano.numClavesNodo + 1;
        numClavesNodo--;
    }

    public void insertarNoLleno(long key) {
        int i = numClavesNodo - 1; //derecha a izq
        if (isHoja()) {
            while (i >= 0 && ClavesNodo[i] > key) {
                ClavesNodo[i + 1] = ClavesNodo[i];
                i--;
            }
            ClavesNodo[i + 1] = key;
            numClavesNodo = numClavesNodo + 1;
        } else {//posición del nodo hijo que debe insertarse
            while (i >= 0 && ClavesNodo[i] > key) {
                i--;
            }
            if (hijos[i + 1].numClavesNodo == 2 * GradoMin - 1) { // Cuando el nodo hijo está lleno
                dividirHijo(i + 1, hijos[i + 1]);
                if (ClavesNodo[i + 1] < key) {
                    i++;
                }
            }
            hijos[i + 1].insertarNoLleno(key);
        }
    }

    public void dividirHijo(int i, NodoB nodo) {
        NodoB aux = new NodoB(nodo.GradoMin, nodo.isHoja());
        aux.numClavesNodo = GradoMin - 1;

        for (int j = 0; j < GradoMin - 1; j++) { // Transferir los atributos nodo a aux
            aux.ClavesNodo[j] = nodo.ClavesNodo[j + GradoMin];
        }
        if (!nodo.isHoja()) {
            for (int j = 0; j < GradoMin; j++) {//transferir hijos
                aux.hijos[j] = nodo.hijos[j + GradoMin];
            }
        }
        nodo.numClavesNodo = GradoMin - 1;

        // Insertar el nuevo nodo hijo en el nodo hijo
        for (int j = numClavesNodo; j >= i + 1; j--) {
            hijos[j + 1] = hijos[j];
        }
        hijos[i + 1] = aux;
        // Mover una clave en nodo a este nodo
        for (int j = numClavesNodo - 1; j >= i; j--) {
            ClavesNodo[j + 1] = ClavesNodo[j];
        }
        ClavesNodo[i] = nodo.ClavesNodo[GradoMin - 1];
        numClavesNodo = numClavesNodo + 1;
    }

    public void recorrer() {
        int i;
        for (i = 0; i < numClavesNodo; i++) {
            if (!isHoja()) {
                hijos[i].recorrer();
            }
            System.out.printf(" %d", ClavesNodo[i]);
        }

        if (!isHoja()) {
            hijos[i].recorrer();
        }
    }

    public NodoB buscar(long key) {
        int i = 0;
        while (i < numClavesNodo && key > ClavesNodo[i]) {
            i++;
        }

        if (ClavesNodo[i] == key) {
            return this;
        }
        if (isHoja()) {
            return null;
        }
        return hijos[i].buscar(key);
    }

    public void imprimir(NodoB padre, Utilidades util) {
        System.out.print("[");
        util.contenidoAB += "nodo" + ClavesNodo[0] + "[label =\"";
        if (padre.ClavesNodo[0] != ClavesNodo[0]) {
            util.contenidoEnlaceAB += "nodo" + padre.ClavesNodo[0] + " -> nodo" + ClavesNodo[0] + ";\n";
        }
        for (int i = 0; i < numClavesNodo; i++) {
            if (i < numClavesNodo - 1) {
                util.contenidoAB += "  " + ClavesNodo[i] + " | ";
                System.out.print(ClavesNodo[i] + " | ");
            } else {
                util.contenidoAB += "  " + ClavesNodo[i] + "  ";
                System.out.print(ClavesNodo[i]);
            }
        }
        util.contenidoAB += "\"];\n";
        System.out.print("]");
    }

    public void imprimirPadre(NodoB padre, Utilidades util) {
        System.out.print("[");
        util.contenidoAB += "nodo" + ClavesNodo[0] + "[label =\"<N0>|";
        if (padre.ClavesNodo[0] != ClavesNodo[0]) {
            util.contenidoEnlaceAB += "nodo" + padre.ClavesNodo[0] + " -> nodo" + ClavesNodo[0] + ";\n";
        }
        for (int i = 0; i < numClavesNodo; i++) {
            if (i < numClavesNodo - 1) {
                util.contenidoAB += "  " + ClavesNodo[i] + " | ";
                System.out.print(ClavesNodo[i] + " | ");
            } else {
                util.contenidoAB += "  " + ClavesNodo[i] + "  ";
                System.out.print(ClavesNodo[i]);
            }
        }
        System.out.print("]");
        util.contenidoAB += "|<N1>\"];\n";
    }

    public void graficar(String path, Utilidades util) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter(path + ".dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz(util));
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo arbolB.dot");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.err.println("Error al cerrar el archivo arbolB.dot");
            }
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tpng -o " + path + ".png " + path + ".dot");
            //Esperar para evitar errores
            Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen");
        }
    }

    public String getCodigoGraphviz(Utilidades util) {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "label = \"Arbol B\";\n"
                + "bgcolor = \"#8ECBE5\";\n"
                + "node [shape = record, style=filled, fillcolor=\"#FCFF48\"];\n"
                + util.contenidoAB
                + util.contenidoEnlaceAB
                + "}\n";
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the contraseñan
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the siguiente
     */
    public NodoB getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(NodoB siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the anterior
     */
    public NodoB getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(NodoB anterior) {
        this.anterior = anterior;
    }

    /**
     * @return the albumUser
     */
    public ListaSimple getAlbumUser() {
        return albumUser;
    }

    /**
     * @param albumUser the albumUser to set
     */
    public void setAlbumUser(ListaSimple albumUser) {
        this.albumUser = albumUser;
    }

    /**
     * @return the imagenesUser
     */
    public ArbolAVL getImagenesUser() {
        return imagenesUser;
    }

    /**
     * @param imagenesUser the imagenesUser to set
     */
    public void setImagenesUser(ArbolAVL imagenesUser) {
        this.imagenesUser = imagenesUser;
    }

    /**
     * @return the capasUser
     */
    public ArbolBB getCapasUser() {
        return capasUser;
    }

    /**
     * @param capasUser the capasUser to set
     */
    public void setCapasUser(ArbolBB capasUser) {
        this.capasUser = capasUser;
    }

    /**
     * @return the Hoja
     */
    public boolean isHoja() {
        return Hoja;
    }

    /**
     * @param Hoja the Hoja to set
     */
    public void setHoja(boolean Hoja) {
        this.Hoja = Hoja;
    }

    /**
     * @return the dpi
     */
    public String getDpi() {
        return dpi;
    }

    /**
     * @param dpi the dpi to set
     */
    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    /**
     * @return the imgPixel
     */
    public ListaSimple getImgPixel() {
        return imgPixel;
    }

    /**
     * @param imgPixel the imgPixel to set
     */
    public void setImgPixel(ListaSimple imgPixel) {
        this.imgPixel = imgPixel;
    }
}
