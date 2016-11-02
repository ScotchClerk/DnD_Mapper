import javax.swing.JFrame;
import javax.swing.JTextField;


/**
 *
 * @author James Phelan and Michael Phelan
 */
public class Mapper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        MapPanel panel = new MapPanel();

        JFrame frame = new JFrame("Gam3");
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        panel.run();
    }
}
