import java.awt.Color;
import java.awt.Font;

public class Piece implements Comparable<Piece> {
    private int value;
    private int type;

    private int x;
    private int y;

    private int size;

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
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    
    

    public Piece(){
        int[] choices = {4, 6, 8, 10, 12, 20};
        type = choices[(int)(Math.random() * choices.length)];
        value = (int)(Math.random() * type + 1);
        y = Table.y(50);
    }

    public void draw(java.awt.Graphics2D g){
        final var centerX = x + size / 2;
        final var centerY = y + size / 2;
        switch (type) {
            case 4 : {
                g.setColor(new Color(0x24A146));
                g.fillPolygon(new int[]{x, centerX, x + size}, new int[]{y + size, y, y + size}, 3);
                break;
            }
            case 6 : {
                g.setColor(new Color(0x27BDD1));
                g.fillRect(x, y, size, size);
                break;
            }
            case 8 : {
                g.setColor(new Color(0x9334E6));
                g.fillRect(x, y, size, size);
                break;
            }
            case 10 : {
                g.setColor(new Color(0xE13295));
                g.fillRect(x, y, size, size);
                break;
            }
            case 12 : {
                g.setColor(new Color(0xD83025));
                g.fillRect(x, y, size, size);
                break;
            }
            case 20 : {
                g.setColor(new Color(0xF26D00));
                g.fillRect(x, y, size, size);
                break;
            }
        
            default : break;
        }

        // Set the font (optional, adjust size as needed)
        g.setFont(new Font("Arial", Font.BOLD, 12));

        g.setColor(Color.black);
        g.drawString("" + value, centerX - 3, centerY + 3);
    }

    public void pick(){
        for(var i = 0; i < 1000; i++){
            y -= size/1000;
        }
    }

    public void move(int x_lerp){
        var original_x = x;
        for(var i = 0; i < 1000; i++){
            x += (x_lerp - original_x)/1000;
        }
    }
    
    public void place(){
        for(var i = 0; i < 1000; i++){
            y += size/1000;
        }
    }

    @Override
    public int compareTo(Piece o) {
        return value - o.value;
    }
}
