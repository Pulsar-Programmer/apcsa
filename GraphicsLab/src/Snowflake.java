import java.awt.Color;

public class Snowflake {
    private double x;
    private double y;
    private final double z;

    public Snowflake(){
        x = Math.random() * NewEngland.x(100);
        y = Math.random() * NewEngland.y(100);
        z = Math.random();
    }

    public void draw(java.awt.Graphics2D g, int windspeed){
        var z = this.z * 10;
        ///We draw the simple snow.
        g.setColor(Color.white);
        g.fillOval((int)x, (int)y, (int) z, (int) z);

        ///Based on the windspeed and size, we change the position.
        y += z / 15.0 + windspeed / 100.0;
        x += z / 15.0 * windspeed / 10.0;
        ///Additionally, if we find we must wrap the sum, we do.
        if(y >= NewEngland.y(100)){
            y = 0;
        }
        if(x >= NewEngland.x(100)){
            x = 0;
        }
    }
}



