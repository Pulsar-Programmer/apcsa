

import java.awt.Color;
import java.awt.Graphics2D;

public class Tree {
    ///This will track the amount of the tree engulfed by snow.
    private double snow_engulfed_percentage;
    private final double height;

    public Tree(){
        height = 100 + Math.random() * 200;
        snow_engulfed_percentage = height;
    }

    public void draw(Graphics2D g, double snow_speed, int x, int y){
        ///We establish two values (our bounds), a and b.
        var a = x;
        var b = x + 90;
        ///We draw the simple tip of the tree.
        g.setColor(Color.green);
        g.fillPolygon(new int[] {a, (a+b)/2, b}, new int[] {y, y - (int)height, y}, 3);
        ///We draw the stump of the tree by gfinding the midpoint and then going back 15u.
        g.setColor(new Color(150, 75, 0));
        g.fillRect((a+b)/2 - 15, y, 30, 45);
        g.setColor(Color.white);
        ///We stop the snow_engulfed_percentage from going below zero by only moving it when it is greater.
        if(snow_engulfed_percentage >= 0){
            snow_engulfed_percentage -= snow_speed / 100;
        };
        ///We find which snow layer y value corresponds with which x value, with our y value being the snow_engulfed percentage.
        var x_of_y = line_inv(snow_engulfed_percentage, a, b);
        ///We add some pizazz to the function to make it compatible with the bounds by adding an a and subtracting from a b. Now we can draw our snow-filled trees.
        g.fillPolygon(new int[] {a + x_of_y, (a+b)/2, b - x_of_y}, new int[] {y - (int)snow_engulfed_percentage, y - (int)height, y - (int)snow_engulfed_percentage}, 3);
    }

    public int line_inv(double y, double a, double b){
        ///This algorithm takes the two bounds and uses them to find the relative x value that needs to be used to draw the smaller triangles..
        return (int)((b - a) * y / (2 * height));
    }
}

