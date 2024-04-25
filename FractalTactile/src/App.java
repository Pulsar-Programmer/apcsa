import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class App {
	static int level = 0;
	public static void main(String[] args) {
		
		JFrame mainframe = new JFrame();
		mainframe.setTitle("The Fractal Circle");
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);
        mainframe.setSize(Fractal.x(100), Fractal.y(100) - 30);
		
		JSlider slider = new JSlider(SwingConstants.HORIZONTAL,1,20,3);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		mainframe.add(slider,BorderLayout.SOUTH);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		Fractal painting = new Fractal();
		mainframe.add(painting, BorderLayout.CENTER);
		mainframe.setVisible(true);
		
		
		//addChangeListener runs stateChanged(ChangeEvent e) whenever changes are made to the slider.
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(slider.getValue() != level) {
					level = slider.getValue();
					painting.setLevel(level);
					mainframe.repaint();
					painting.getGen().clear();
				}
			}
		});
	}
}
