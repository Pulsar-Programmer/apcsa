import java.awt.BorderLayout;
import java.awt.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
//Note: Double comments are not meant to be read.
///Note: Triple comments are meant to be read.

public class EnglandDriver {
    public static void main(String[] args) throws Exception {
        ///We use this to execute different actions during different intervals.
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        ///We propose a default snowflake density. If asking the user succeeds, then we overwrite it.
        byte snowflake_density = 127;
        try{snowflake_density = Byte.parseByte(JOptionPane.showInputDialog("Enter the density of the snowstorm:"));} catch(Exception _e) {};
        if(snowflake_density < 0){
            snowflake_density = 127;
        }

        ///We prompt for a sky color.
        Color sky_color = JColorChooser.showDialog(null, "Choose a color for the sky!", new Color(0x00, 0x00, 0x44));

        ///We create our frame and add settings.
        JFrame foundation = new JFrame();
        foundation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        foundation.setVisible(true);
        foundation.setSize(NewEngland.x(100), NewEngland.y(100));

        ///We create a slider for the wind speed.
        var wind_slider = new JSlider(0, 120, 0);
        wind_slider.setMajorTickSpacing(10);
        wind_slider.setMinorTickSpacing(1);
        wind_slider.setPaintTicks(true);
        wind_slider.setPaintLabels(true);
        foundation.add(wind_slider, BorderLayout.SOUTH);
        
        // ///We create the button on a new frame for later viewing.
        // JFrame for_button = new JFrame();
        // for_button.setSize(new Dimension(100, 50));
        // JButton button = new JButton("Restart");
        // for_button.add(button);
        // for_button.setVisible(true);

        ///We create and add the components.
        var components = new NewEngland(snowflake_density, sky_color);
        foundation.add(components);

        ///Every four (4) seconds we must add a chimney smoke to the house.
        executor.scheduleAtFixedRate(()->{
            components.push_chimney_smoke();
        }, 0, 4, TimeUnit.SECONDS);

        ///Every four (4) seconds after sixteen (16) seconds have passed, we need to remove the chimney smoke to avoid lag.
        executor.scheduleAtFixedRate(()->{
            components.pop_chimney_smoke();
        }, 16, 4, TimeUnit.SECONDS);

        ///This is the main game loop where we update the components.
        while(true){
            components.set_wind(wind_slider.getValue());
            foundation.repaint();
        }
    }
    ///Returns the time from nanoseconds to seconds.
    public static int sec(int n){
        return n * 1_000_000_000;
    }
}