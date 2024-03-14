import java.awt.Color;
import java.awt.Graphics2D;

public class Pointer {
    private int x;
    private int y;
    private Color color;

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public Pointer(){}

    public void draw(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillPolygon(new int[]{x, x/2 + 50, x + 100}, new int[]{y + 100, y, y + 100}, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(new int[]{x, x/2 + 50, x + 100}, new int[]{y + 100, y, y + 100}, 3);
    }

    public void move(int x_lerp){
        var original_x = x;
        for(var i = 0; i < 1000; i++){
            x += (x_lerp - original_x)/1000;
        }
    }
}
