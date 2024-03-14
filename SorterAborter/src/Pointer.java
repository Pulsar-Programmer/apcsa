import java.awt.Color;
import java.awt.Graphics2D;

public class Pointer {
    private double x;
    private double y;
    private Color color;

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public Pointer(Color color, double x, double y){
        this.y = y;
        this.color = color;
        this.x = x;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillPolygon(new int[]{(int)x + 25, (2 * (int)x+50)/2 + 25, (int)x + 50 + 25}, new int[]{(int)y + 50, (int)y, (int)y + 50}, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(new int[]{(int)x + 25, (2 * (int)x+50)/2 + 25, (int)x + 50 + 25}, new int[]{(int)y + 50, (int)y, (int)y + 50}, 3);
    }

    public void move(int x_lerp){
        var original_x = x;
        for(var i = 0; i < 500; i++){
            SortDriver.sleep_safe(1);
            x += (x_lerp - original_x)/500.0;
        }
    }
}
