package gui;
import javax.swing.*;
import java.awt.event.MouseEvent;

public class MultithlonGUI {

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    public static JPanel panel;
    public static JFrame frame;
    public static JLabel label;
    public static JButton regLongJump;
    public static JButton regHighJump;
    public static JTextArea longJumpArea;
    public static JTextArea highJumpArea;
    public static JComboBox eventCombo;

    public static void createAndShowGUI() {
        //Create and set up the window.
        panel = new JPanel();
        frame = new JFrame("MultithlonSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Multithlon Handler");
        //frame.getContentPane().add(label);

        regLongJump = new JButton();
        regLongJump.setText("Long Jump");

        eventCombo = new JComboBox();
        eventCombo.addItem("Decathlon");
        eventCombo.addItem("Heptathlon");
        //frame.getContentPane().add(eventCombo);

        panel.add(label);
        panel.add(eventCombo);
        panel.add(regLongJump);

        frame.add(panel);

        //Display the window.
        frame.setSize(500,300);
        frame.pack();
        frame.setVisible(true);

        regLongJump.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                //System.out.println("event!");
                btnClicked(event);
            }
        });
    }

    public static void btnClicked(MouseEvent event) {
        System.out.println("event!");
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}