package edd_fase_2;

/**
 *
 * @author Arnoldo González
 */
public class Grafica {
    
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
}
/*3, 0, 2, 1, 4,
         3
     0      4
        2
      1
*/