import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

///Triple comments are intended to be read.
//Double comments are not intended to be read.



public class App {
	public static void main(String[] args) {
		///The Fractal Maze! We set up the Frame.
		JFrame mainframe = new JFrame();
		mainframe.setTitle("The Fractal Maze");
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);
        mainframe.setSize(Fractal.x(100), Fractal.y(100) - 30);

		///We set up the slider.
		JSlider slider = new JSlider(SwingConstants.HORIZONTAL,1,20,3);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		mainframe.add(slider,BorderLayout.SOUTH);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		///We set up the painting.
		Fractal painting = new Fractal();
		mainframe.add(painting, BorderLayout.CENTER);
		mainframe.setVisible(true);
		
		///We set up the event listener which allows it to be repainted each time.
		//addChangeListener runs stateChanged(ChangeEvent e) whenever changes are made to the slider.
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(slider.getValue() != painting.getLevel()) {
					painting.setLevel(slider.getValue());
					mainframe.repaint();
					painting.getGen().clear();
				}
			}
		});
	}
}
