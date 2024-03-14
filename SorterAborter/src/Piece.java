import java.awt.Color;
import java.awt.Font;

public class Piece implements Comparable<Piece> {
    private int value;
    private int type;

    private int x;
    private int y;

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
    

    public Piece(){
        int[] choices = {4, 6, 8, 10, 12, 20};
        type = choices[(int)(Math.random() * choices.length)];
        value = (int)(Math.random() * type + 1);
    }

    public void draw(java.awt.Graphics2D g, int size){
        switch (type) {
            case 4 : {
                g.setColor(new Color(0x24A146));
                g.fillRect(x, Table.y(50), size, size);
                break;
            }
            case 6 : {
                g.setColor(new Color(0x27BDD1));
                g.fillRect(x, Table.y(50), size, size);
                break;
            }
            case 8 : {
                g.setColor(new Color(0x9334E6));
                g.fillRect(x, Table.y(50), size, size);
                break;
            }
            case 10 : {
                g.setColor(new Color(0xE13295));
                g.fillRect(x, Table.y(50), size, size);
                break;
            }
            case 12 : {
                g.setColor(new Color(0xD83025));
                g.fillRect(x, Table.y(50), size, size);
                break;
            }
            case 20 : {
                g.setColor(new Color(0xF26D00));
                g.fillRect(x, Table.y(50), size, size);
                break;
            }
        
            default : break;
        }
        // Calculate the center of the rectangle
        final var centerX = x + size / 2;
        final var centerY = Table.y(50) + size / 2;

        // Set the font (optional, adjust size as needed)
        g.setFont(new Font("Arial", Font.BOLD, 12));

        g.setColor(Color.black);
        g.drawString("" + value, centerX - 3, centerY + 3);
    }

    @Override
    public int compareTo(Piece o) {
        return value - o.value;
    }
}
