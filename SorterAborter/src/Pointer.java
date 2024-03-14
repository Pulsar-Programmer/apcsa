import java.awt.Color;
import java.awt.Graphics2D;

public class Pointer {
    private double x;
    private double y;
    private double size;
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
    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
    }

    public Pointer(Color color, double x, double y, double size){
        this.y = y;
        this.color = color;
        this.x = x;
        this.size = size;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillPolygon(new int[]{Piece.perce(x, 0.5, size), Piece.perce(x, 1, size), Piece.perce(x, 1.5, size)}, new int[]{Piece.perce(y, 1., size), (int)y, Piece.perce(y, 1., size)}, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(new int[]{Piece.perce(x, 0.5, size), Piece.perce(x, 1, size), Piece.perce(x, 1.5, size)}, new int[]{Piece.perce(y, 1., size), (int)y, Piece.perce(y, 1., size)}, 3);
    }

    public void move(int x_lerp){
        var original_x = x;
        for(var i = 0; i < 500; i++){
            SortDriver.sleep_safe(1);
            x += (x_lerp - original_x)/500.0;
        }
    }
}
