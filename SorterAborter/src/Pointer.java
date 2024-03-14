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

    public Pointer(Color color, int x, int y){
        this.y = y;
        this.color = color;
        this.x = x;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillPolygon(new int[]{x, (2 * x+50)/2, x + 50}, new int[]{y + 50, y, y + 50}, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(new int[]{x, (2 * x+50)/2, x + 50}, new int[]{y + 50, y, y + 50}, 3);
    }

    public void move(int x_lerp){
        var original_x = x;
        for(var i = 0; i < 1000; i++){
            SortDriver.sleep_safe(1);
            x += (x_lerp - original_x)/1000;
        }
    }
}
