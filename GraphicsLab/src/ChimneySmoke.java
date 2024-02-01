import java.awt.Color;
import java.awt.Graphics2D;

public class ChimneySmoke {
    private double x, y;

    public ChimneySmoke(){
        x = NewEngland.x(80) + 15;
        y = NewEngland.y(70) - 100;
    }

    public void draw(Graphics2D g, int windspeed){
        ///We create three ovals in a triangular shape.
        double z = 25;
        g.setColor(Color.lightGray);
        g.fillOval((int)x, (int)y, (int)z, (int)(z/2));
        g.fillOval((int)(x-z/2), (int)(y+z/2), (int)z, (int)(z/2));
        g.fillOval((int)(x+z/2), (int)(y+z/2), (int)z, (int)(z/2));

        ///Depending on the windspeed, the smoke can move differently.
        x += windspeed/20.0;
        y -= 0.125 - windspeed/50.0;
    }

}

