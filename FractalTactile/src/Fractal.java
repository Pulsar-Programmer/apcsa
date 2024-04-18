import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Fractal extends JPanel {
	private int level;
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
		System.out.println("WAS SeT: " + this.level);
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
		g2d.setStroke(new BasicStroke());
		g2d.drawPolyline(null, null, ABORT);
	}
	
	public void circuit() {
		
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
