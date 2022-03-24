package edd_fase_2;

/**
 *
 * @author Arnoldo González
 */
public class Grafica {
    int cantidad = 0;
    Clases.Capa [] matriz;
    MatrizDispersa imagenCompleta = new MatrizDispersa(-1);;
    int contador = 0;
    String contenidoABB = "digraph L{\n"
                + "node[shape = circle fillcolor = \"#F8DEA1\" style = filled]\n"
                + "subgraph cluster_p{\n"
                + "label = \"Arbol Binario Capas\"\n"
                + "bgcolor = \"#8ECBE5\"\n"
                + "edge[dir = \"right\"]\n";
    String enlaceABBIzq = "";
    String enlaceABBDer = "";
    boolean tienePadreIzq = false;
    boolean tienePadreDer = false;
    
    public void cantidad(ArbolBinarioBusqueda abb){
        this.cantidad = abb.cantidad();
        this.matriz = new Clases.Capa[cantidad];
    }
    
    public Clases.Capa[] matriz(){
        return this.matriz;
    }
    public MatrizDispersa matrizDispera(){
        return imagenCompleta;
    }
}
/*3, 0, 2, 1, 4,
         3
     0      4
        2
      1
*/