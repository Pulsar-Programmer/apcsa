import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Toolkit;

/**
 * A class used to draw all the components on the screen.
 */
public class NewEngland extends JPanel {
    //inner components:
    ///Here, we list all our relevant components.
    private Snowflake[] snowflakes;
    private Cloud[] clouds;
    private Tree[] trees;
    private ArrayList<ChimneySmoke> chimneySmokes;
    private Sleigh sleigh;
    private int wind_intensity;
    private double snow_tree_speed;
    private static Color sky_color = new Color(0x00, 0x00, 0x44);
    
    public NewEngland(byte snow_density, Color sc){
        setOpaque(true);
        ///We set the sky color.
        sky_color = sc;
        ///We create a new sleigh.
        sleigh = new Sleigh();
        ///We determine the speed at which trees fill up with snow.
        snow_tree_speed = snow_density / 100.;
        ///We determine the amount of Snowflakes.
        snowflakes = new Snowflake[5 * snow_density];
        for(var i = 0; i < snowflakes.length; i++){
            snowflakes[i] = new Snowflake();
        }
        ///We make some clouds.
        clouds = new Cloud[(int)(Math.random() * 10.0 + 5)];
        for(var i = 0; i < clouds.length; i++){
            clouds[i] = new Cloud();
        }
        ///We make some trees.
        trees = new Tree[14];
        for(var i = 0; i < trees.length; i++){
            trees[i] = new Tree();
        }
        ///We make a house for ChimneySmokes to push and pull from later.
        chimneySmokes = new ArrayList<ChimneySmoke>();
    }
    ///This sets the wind given from the slider.
    public void set_wind(int value){
        wind_intensity = value;
    }

    // public void setSnow_density(byte snow_density) {
    //     this.snow_density = snow_density;
    // }


    


    ///This is the overridden paintComponent.
    @Override
    protected void paintComponent(Graphics g) {
        ///We do the relevant graphics things.
        super.paintComponent(g);
        var g2d = (Graphics2D) g;
        ///We draw the background.
        draw_backdrop(g2d);
        ///We draw the clouds.
        for(var i = 0; i < clouds.length; i++){
            clouds[i].draw(g2d, wind_intensity);
        }
        ///We draw the trees.
        for(var i = 0; i < trees.length; i++){
            ///We must give where we expect the tree to be, its speed at which it should accumulate snow, and more.
            trees[i].draw(g2d, snow_tree_speed * (1 + wind_intensity / 50.0), x(i * 100.0/14.0), y(55));
        }
        ///We draw the house.
        draw_house(g2d);
        ///We draw the chimney smokes.
        for(var i = 0; i < chimneySmokes.size(); i++){
            chimneySmokes.get(i).draw(g2d, wind_intensity);
        }
        ///We draw the sleigh.
        sleigh.draw(g2d);
        ///We draw the snowflakes.
        for(var i = 0; i < snowflakes.length; i++){
            snowflakes[i].draw(g2d, wind_intensity);
        }
    }

    

    // public static void draw_shrub(Graphics2D g){
    //     g.setColor(new Color(0x76, 0x4E, 0x6B));
    //     for(var i = 0; i<10; i++){
    //         var rand = (int) (Math.random() * 80) + 20;
    //         g.fillRect(x(80)+i*10 + (int)(Math.random() * 5), y(0) - (int) (rand * (85.0/100.0)) - 90, 2, rand);
    //     }
    // }
    ///We draw the boundary between land and sky.
    public static void draw_boundary(Graphics2D g){
        g.setColor(new Color(0xCB, 0xDA, 0xFA));
        g.fillPolygon(new int[] {x(0), x(0), x(65), x(100), x(100), x(0)}, new int[] {y(100), y(50), y(45), y(35), y(100), y(100)}, 6);
    }
    ///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int x(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * pct / 100.0);
    }
    ///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int y(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * pct / 100.0);
    }

    ///Here we draw the background.
    public static void draw_backdrop(Graphics2D g){
        g.setColor(sky_color);
        g.fillRect(0, 0, x(100), y(100));

        draw_boundary(g);
        // draw_shrub(g);
    }
    ///Here we draw the house.
    public static void draw_house(Graphics2D g){
        ///We draw the chimney.
        g.setColor(new Color(130, 55, 0));
        g.fillRect(x(80) + 10, y(70) - 60, 20, 50);
        ///We draw the house.
        g.setColor(Color.red);
        g.fillRect(x(80), y(70), 100, 100);
        ///We draw the roof.
        g.setColor(new Color(150, 75, 0));
        g.fillPolygon(new int[]{x(80), x(80) + 50, x(80) + 100}, new int[]{y(70), y(70) - 50, y(70)}, 3);
        ///We draw the door.
        g.setColor(new Color(130, 55, 0));
        g.fillRect(x(80) + 35, y(70) + 60, 30, 40);
    }

    ///We push a smoke of chimney to the vector.
    public void push_chimney_smoke(){
        chimneySmokes.add(new ChimneySmoke());
        // System.out.println(chimneySmokes.size());
    }

    ///We remove a smoke of chimney to the vector.
    public void pop_chimney_smoke(){
        chimneySmokes.remove(0);
    }
}