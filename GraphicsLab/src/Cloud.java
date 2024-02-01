import java.awt.Color;

public class Cloud {
    private double x, y, z, speed;

    public Cloud() {
        x = Math.random() * NewEngland.x(100);
        y = Math.random() * NewEngland.y(20);
        z = Math.random() * 90;
        speed = Math.random() / 2; 
    }

    public void draw(java.awt.Graphics2D g, int windspeed){
        ///We create a black outline and a gray inline. This is to provide some depth to the clouds.
        ///You may also notice that the POW function is used here. This is to make bigger clouds wider.
        g.setColor(Color.black);
        g.drawOval((int)x, (int)y, (int)(Math.pow(z, 1.6)), (int)(z * 1.5));
        g.setColor(Color.gray);
        g.fillOval((int)x, (int)y, (int)(Math.pow(z, 1.6)), (int)(z * 1.5));

        ///The position increases with windspeed and regular cloud speed.
        x += speed + windspeed/100.0;
        // z = Math.cos(x/500) * 90;
        // speed += Math.random() * 5 - 2;
        ///If the `x` value reaches the end, we must wrap back around to a reasonable value.
        if(x >= NewEngland.x(100)){
            x = -100;
        }
    }


}