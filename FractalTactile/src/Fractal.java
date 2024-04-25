import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JPanel;
import java.awt.geom.Line2D;

public class Fractal extends JPanel {
	private double level;
	
	public double getLevel() {
		return level;
	}
	
	public void setLevel(double level) {
		this.level = level;
	}
	public Fractal() {
		setBackground(Color.white);
		this.setSize(getWidth(), getHeight());
		level = 0;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(20));
		g2d.fillOval(x(50) - 25, y(50) - 25, 50, 50);
		var start = new Point(x(50), y(50));
		circuit(g2d, start, flank_above(start));
		circuit(g2d, start, flank_below(start));
		circuit(g2d, start, flank_left(start));
		circuit(g2d, start, flank_right(start));
	}
	
	public void circuit(Graphics2D g2d, Point prev, Point curr) {
		if(Math.random() >= 0.2) return;
		var color = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
		g2d.setColor(color);
		g2d.drawLine(prev.x, prev.y, curr.x, curr.y);
		circuit(g2d, curr, flank_above(curr));
		circuit(g2d, curr, flank_below(curr));
		circuit(g2d, curr, flank_left(curr));
		circuit(g2d, curr, flank_right(curr));
	}

	///Creates a point one below the input.
    private Point flank_below(Point p){
        Point point = (Point)p.clone();
        point.y -= 200 / level;
        return point;
    }
    ///Creates a point one above the input.
    private Point flank_above(Point p){
        Point point = (Point)p.clone();
        point.y += 200 / level;
        return point;
    }
    ///Creates a point one left of the input.
    private Point flank_left(Point p){
        Point point = (Point)p.clone();
        point.x -= 200 / level;
        return point;
    }
    ///Creates a point one right of the input.
    private Point flank_right(Point p){
        Point point = (Point)p.clone();
        point.x += 20 / level;
        return point;
    }

	///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int x(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * pct / 100.0);
    }
    ///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int y(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * pct / 100.0);
    }
}
