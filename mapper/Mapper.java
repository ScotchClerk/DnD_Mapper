/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import javax.swing.JFrame;
import javax.swing.JTextField;


/**
 *
 * @author James
 */
public class Mapper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        mapPanel panel = new mapPanel();

        JFrame frame = new JFrame("Gam3");
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setVisible(true);
        panel.run();
    }
}
