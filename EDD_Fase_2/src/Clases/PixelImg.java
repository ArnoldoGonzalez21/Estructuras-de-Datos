package Clases;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Arnoldo González
 */
public class PixelImg {

    private String color;
    private int x;
    private int y;
    private PixelImg siguiente;
    private final int i = 10;

    public PixelImg(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.siguiente = null;
    }

    public void pintar(Graphics g) {
        g.setColor(Color.decode(getColor()));
        g.fillRect(getX() * i, getY() * i, i, i);
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the siguiente
     */
    public PixelImg getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(PixelImg siguiente) {
        this.siguiente = siguiente;
    }

}
