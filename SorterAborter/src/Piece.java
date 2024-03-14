import java.awt.Color;

public class Piece implements Comparable<Piece> {
    private int value;
    private int type;

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

    public Piece(){
        int[] choices = {4, 6, 8, 10, 12, 20};
        type = choices[(int)(Math.random() * choices.length)];
        value = (int)(Math.random() * type);
    }

    public void draw(java.awt.Graphics2D g, int location_x, int size){
        switch (type) {
            case 4 : {
                g.setColor(new Color(0xF26D00));
                g.fillRect(location_x, Table.y(10), size, size);
                break;
            }
            case 6 : {
                g.setColor(new Color(0x27BDD1));
                g.fillRect(location_x, Table.y(10), size, size);
                break;
            }
            case 8 : {
                g.setColor(new Color(0x9334E6));
                g.fillRect(location_x, Table.y(10), size, size);
                break;
            }
            case 10 : {
                g.setColor(new Color(0xE13295));
                g.fillRect(location_x, Table.y(10), size, size);
                break;
            }
            case 12 : {
                g.setColor(new Color(0xD83025));
                g.fillRect(location_x, Table.y(10), size, size);
                break;
            }
            case 20 : {
                g.setColor(new Color(0xF26D00));
                g.fillRect(location_x, Table.y(10), size, size);
                break;
            }
        
            default : break;
        }
    }

    @Override
    public int compareTo(Piece o) {
        return Math.abs(value - o.value);
    }
}
