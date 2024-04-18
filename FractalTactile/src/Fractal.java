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
		Rectangle r = new Rectangle(0,0, getWidth(), getHeight());
		System.out.println("MYLEVEL: " + this.level);
		circuit(g,r,0);
	}
	
	public void circuit(Graphics g, Rectangle r, int lev) {
		int red = (int)(Math.random() * 256);
		int gre = (int)(Math.random() * 256);
		int blu = (int)(Math.random() * 256);
		Color c = new Color(red, gre, blu);
		g.setColor(c);
		((Graphics2D)g).fill(r);

		System.out.println(r.x + ", " + r.y);
		if (lev >= this.level) return;

		
		if (Math.random() > 0.5) {
			Rectangle top = new Rectangle(r.x,r.y,r.width,r.height / 2);
			Rectangle bot = new Rectangle(r.x,r.y + r.height / 2,r.width,r.height / 2);
			circuit(g,top,lev+1);
			circuit(g,bot,lev+1);
		} else {
			Rectangle top = new Rectangle(r.x,r.y,r.width / 2,r.height);
			Rectangle bot = new Rectangle(r.x + r.width / 2,r.y,r.width / 2,r.height);
			circuit(g,top,lev+1);
			circuit(g,bot,lev+1);
		}
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
