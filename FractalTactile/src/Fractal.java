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
		///We create our Graphics instance.
		Graphics2D g2d = (Graphics2D)g;
		///We set the stroke of it with a width of five, and draw a starter circle in the middle.
		g2d.setStroke(new BasicStroke(5));
		g2d.fillOval(x(50) - 25, y(50) - 25, 50, 50);
		///We get the start point and check if we have generation yet.
		var start = new Point(x(50), y(50));
		if(gen.isEmpty()){
			///If no generation is found, we start our circuit-chain by recursively calling each method in each direction.
			circuit(start, flank_above(start));
			circuit(start, flank_below(start));
			circuit(start, flank_left(start));
			circuit(start, flank_right(start));
		}
		///Each redrawing, we regenerate all the colors and draw all the generated lines.
		for (Line2D line2d : gen) {
			final var color = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
			g2d.setColor(color);
			g2d.drawLine((int)line2d.getX1(), (int)line2d.getY1(), (int)line2d.getX2(), (int)line2d.getY2());
		}
	}
	///This is our circuit.
	public void circuit(Point prev, Point curr) {
		///We get the previous and current point to draw and make a line.
		///If the generation is filled, we should stop.
		if(gen.size() > 1000) return;
		///Otherwise, lets make a line and check if it is already in the generation.
		var line = new Line2D.Float(prev, curr);
		///If the line has already been made, we can remove it.
		if(gen.contains(line)) return;
		///If the distance from the origin is great enough, we decrease the odds of developing a new span.
		if(Math.random() >= dist_from_origin(prev)) return;
		
		///We add the generation to the gen since it cleared the tests.
		gen.add(line);
		///Now we continue, drawing our other variants.
		circuit(curr, flank_above(curr));
		circuit(curr, flank_below(curr));
		circuit(curr, flank_left(curr));
		circuit(curr, flank_right(curr));
	}

	///This is a probability function that determines the probability dependent on the distance the line is away from the origin.
	///This is truly fascinating. If you want, you can plug it into desmos and see how it evolves.
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
