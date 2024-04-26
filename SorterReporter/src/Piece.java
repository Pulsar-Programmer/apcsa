import java.awt.Color;

public class Piece implements Comparable<Piece> {
    private int value;
    private int type;

    private double x;
    private double y;

    private int size;
    private double dot_offset;

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
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
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public double getDot_offset() {
        return dot_offset;
    }
    public void setDot_offset(double dot_offset) {
        this.dot_offset = dot_offset;
    }
    
    

    public Piece(){
        ///We initialize properties and roll the dice.
        int[] choices = {4, 6, 8, 10, 12, 20};
        type = choices[(int)(Math.random() * choices.length)];
        value = (int)(Math.random() * type + 1);
        y = Table.y(50);
        dot_offset = Math.random() * Math.PI;
    }

    public void draw(java.awt.Graphics2D g){
        ///We change the shape depending on the solid of the dice.
        int x = (int)this.x;
        int y = (int)this.y;
        switch (type) {
            case 4 : {
                g.setColor(new Color(0x24A146));
                g.fillPolygon(new int[]{x, perce(x, 0.5, size), x + size}, new int[]{y + size, y, y + size}, 3);
                break;
            }
            case 6 : {
                g.setColor(new Color(0x27BDD1));
                g.fillRect(x, y, size, size);
                break;
            }
            case 8 : {
                g.setColor(new Color(0x9334E6));
                g.fillPolygon(new int[]{perce(x, 0.08, size), perce(x, 0.07, size), perce(x, 0.5, size), perce(x, 0.93, size), perce(x, 0.92, size), perce(x, 0.5, size)}, new int[]{perce(y, 0.3, size), perce(y, 0.7, size), y + size, perce(y, 0.7, size), perce(y, 0.3, size), y}, 6);
                break;
            }
            case 10 : {
                g.setColor(new Color(0xE13295));
                g.fillPolygon(new int[]{x, x, perce(x, 0.5, size), x + size, x + size, perce(x, 0.5, size), x}, new int[]{perce(y, 0.3, size), perce(y, 0.7, size), y + size, perce(y, 0.7, size), perce(y, 0.3, size), y}, 6);
                break;
            }
            case 12 : {
                g.setColor(new Color(0xD83025));
                int[] xPoints = new int[10];
                int[] yPoints = new int[10];
                for (int i = 0; i < 10; i++) {
                    double angle = Math.toRadians(i * 36); // Convert degrees to radians
                    xPoints[i] = perce(x, 0.5, size) + (int) (size/2 * Math.cos(angle));
                    yPoints[i] = perce(y, 0.5, size) + (int) (size/2 * Math.sin(angle));
                }
                g.fillPolygon(xPoints, yPoints, 10);
                break;
            }
            case 20 : {
                g.setColor(new Color(0xF26D00));
                g.fillPolygon(new int[]{perce(x, 0.07, size), perce(x, 0.08, size), perce(x, 0.5, size), perce(x, 0.92, size), perce(x, 0.93, size), perce(x, 0.5, size)}, new int[]{perce(y, 0.3, size), perce(y, 0.7, size), y + size, perce(y, 0.7, size), perce(y, 0.3, size), y}, 6);
                break;
            }
        
            default : break;
        }
        ///We set the color to black and draw a circle of dots for the dice values.
        g.setColor(Color.black);
        for(var i = 0; i < value; i += 1){
            var _x = 20 * Math.floor(1 + i / (value * 2. * Math.PI)) * Math.cos(i * 2./value * Math.PI + dot_offset) + perce(x, 0.5, size) - size/20.;
            var _y = 20 * Math.floor(1 + i / (value * 2. * Math.PI)) * Math.sin(i * 2./value * Math.PI + dot_offset) + perce(y, 0.5, size) - size/20.;
            g.fillOval((int)_x, (int)_y, (int)(size/10.0), (int)(size/10.0));
        }
    }

    ///A helper function that acts as a weighted midpoint.
    public static int perce(double x, double per, double size){
        return (int)(x + per * size);
    }

    ///To pick up an object.
    public void pick(){
        for(var i = 0; i < 500; i++){
            SortDriver.sleep_safe(1);
            y -= size/500.0;
        }
    }

    ///To move or slowly lerp an object.
    public void move(int x_lerp){
        var original_x = x;
        for(var i = 0; i < 500; i++){
            SortDriver.sleep_safe(1);
            x += (x_lerp - original_x)/500.0;
        }
    }

    ///Put an object back down.
    public void place(){
        for(var i = 0; i < 500; i++){
            SortDriver.sleep_safe(1);
            y += size/500.0;
        }
    }

    @Override
    public int compareTo(Piece o) {
        return value - o.value;
    }
}
