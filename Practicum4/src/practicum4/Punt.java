/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practicum4;

/**
 *
 * @author Dark
 */
public class Punt {
    int x;
    int y;
    Punt()
    {
        this.x = 0;
        this.y = 0;
    }
    Punt(int x_coord, int y_coord)
    {
        this.x = x_coord;
        this.y = y_coord;
    }
    void Reset()
    {
        this.x = 0;
        this.y = 0;
    }
    void Punt(int x_coord, int y_coord)
    {
        this.x = x_coord;
        this.y = y_coord;
    }
    String PrintPunt()
    {
        return "("+this.x+","+this.y+")";
    }
    void Verschuif(int dx, int dy)
    {
        this.x += dx;
        this.y += dy;
    }
}
