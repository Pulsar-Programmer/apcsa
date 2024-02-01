import java.awt.Color;
import java.awt.Graphics2D;

public class Sleigh {
    private double x, y;
    private double t;

    public Sleigh(){
        x = NewEngland.x(80);
        y = NewEngland.y(50);
        // z = 1;
        // reflected = true;
        // rotation = 0;
    }
    ///We draw the sleigh.
    public void draw(Graphics2D g){
        // g.rotate(Math.toRadians(15));
        // g.setColor(null); //
        ///We create the caboose.
        g.setColor(new Color(0x00, 0xBB, 0xBB));
        g.fillRect((int)x, (int)y, 50, 100);

        // g.setColor(Color.blue);
        ///We create the body.
        g.fillRect((int)x + 50, (int)y + 50, 100, 50);

        // g.setColor(Color.YELLOW);
        ///We create the tip.
        g.fillPolygon(new int[] {(int)x + 150, (int)x + 150, (int)x + 210}, new int[] {(int)y + 100, (int)y + 50, (int)y}, 3);


        ///We change the x and y position based on the time.
        if(0 <= t && t <= 100){
            x = NewEngland.x(75 - t * (75./100.));
            y = NewEngland.y(50);
            // z = 1;
        } 
        else if(100 <= t && t <= 200){
            x = NewEngland.x(-500);
            y = NewEngland.y(60);
            // z = 4;
            // reflected = false;
        }
        else if(200 <= t && t <= 300){
            x = NewEngland.x(t - 200);
        }
        else if(300 <= t && t <= 400){
            x = NewEngland.x(500);
            y = NewEngland.y(50);
            // z = 1;
            // reflected = true;
        }
        else if(500 <= t && t <= 600){
            x = NewEngland.x(100 - (t-500) * (25.0/100.0) );
        }
        else if(t > 600){
            t = 0;
        }
        t += 0.25;

        // x += x(t);
        // y += y(t);
    }

}



