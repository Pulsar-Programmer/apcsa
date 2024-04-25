import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.HashSet;

import javax.swing.JPanel;
import java.awt.geom.Line2D;

public class Fractal extends JPanel {
	private double level;
	private HashSet<Line2D> gen;
	
	public double getLevel() {
		return level;
	}
	
	public void setLevel(double level) {
		this.level = level;
	}
	public Fractal() {
		setBackground(Color.white);
		this.setSize(getWidth(), getHeight());
		level = 3;
		gen = new HashSet();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(5));
		g2d.fillOval(x(50) - 25, y(50) - 25, 50, 50);
		var start = new Point(x(50), y(50));
		if(gen.isEmpty()){
			circuit(g2d, start, flank_above(start));
			circuit(g2d, start, flank_below(start));
			circuit(g2d, start, flank_left(start));
			circuit(g2d, start, flank_right(start));
		}
		for (Line2D line2d : gen) {
			final var color = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
			g2d.setColor(color);
			g2d.drawLine((int)line2d.getX1(), (int)line2d.getY1(), (int)line2d.getX2(), (int)line2d.getY2());
		}
	}
	
	public void circuit(Graphics2D g2d, Point prev, Point curr) {
		if(gen.size() > 1000) return;
		var line = new Line2D.Float(prev, curr);
		if(gen.contains(line)) return;
		if(Math.random() >= dist_from_origin(prev)) return;
		
		
		gen.add(line);
		circuit(g2d, curr, flank_above(curr));
		circuit(g2d, curr, flank_below(curr));
		circuit(g2d, curr, flank_left(curr));
		circuit(g2d, curr, flank_right(curr));
	}

	private double dist_from_origin(Point point){
		final var dist = Math.pow(Math.pow(point.x - x(50), 2) + Math.pow(point.y - y(50), 2), 0.5);
		return Math.exp(-0.5 -dist/(-level * 5 + 250.)) - 0.3 * Math.exp(-Math.pow((dist - 110 + 4 * level)/20., 2));
	}

	///Creates a point one below the input.
    private Point flank_below(Point p){
        Point point = (Point)p.clone();
        point.y -= (int)(300. / level);
        return point;
    }
    ///Creates a point one above the input.
    private Point flank_above(Point p){
        Point point = (Point)p.clone();
        point.y += (int)(300. / level);
        return point;
    }
    ///Creates a point one left of the input.
    private Point flank_left(Point p){
        Point point = (Point)p.clone();
        point.x -= (int)(300. / level);
        return point;
    }
    ///Creates a point one right of the input.
    private Point flank_right(Point p){
        Point point = (Point)p.clone();
        point.x += (int)(300. / level);
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

	public HashSet<Line2D> getGen() {
		return gen;
	}

	public void setGen(HashSet<Line2D> gen) {
		this.gen = gen;
	}
}
