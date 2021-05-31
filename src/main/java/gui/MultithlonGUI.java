package gui;

//import jdk.internal.jimage.ImageStrings;

import common.Event;
import common.ExcelPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MultithlonGUI {

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    public static String result;
    public static JPanel inputPanel;
    public static JPanel resultPanel;
    public static JFrame frame;
    public static JLabel label;
    public static JButton calculateButton;
    public static JTextField resultField;
    public static JTextArea pointsArea;
    public static JComboBox eventCombo;
    public static String[] decathlonDisciplines =
            {"Long jump",
            "High jump",
            "Shot put",
            "110 hurdles",
            "Javelin throw",
            "400m",
            "1500m",
            "100m",
            "Pole vault",
            "Discus throw"};
    public static String[] heptathlonDisciplines =
            {"Long jump",
            "High jump",
            "Shot put",
            "100 hurdles",
            "Javelin throw",
            "800m",
            "200m"};

    public ArrayList<common.Event> events;
    public ExcelPrinter printer;

    public MultithlonGUI() {

        events = new ArrayList<>();
        printer = new ExcelPrinter("excel printer");
    }

    public Event getEventByName(String name) {
        Event evt = null;
        for (Event e : events) {
            if (e.getName().equals(name)) {
                evt = e;
            }
        }
        return evt;
    }

    public static void createAndShowGUI(String contest) {

        //Create and set up the window.
        inputPanel = new JPanel(new BorderLayout());
        //resultPanel = new JPanel();
        frame = new JFrame("MultithlonSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(0,4);

        label = new JLabel("Multithlon Handler");
        //frame.getContentPane().add(label);

        eventCombo = new JComboBox();
        eventCombo.addItem("Decathlon");
        eventCombo.addItem("Heptathlon");
        //frame.getContentPane().add(eventCombo);

        inputPanel.setLayout(grid);
        inputPanel.setBackground(Color.darkGray);

        frame.add(inputPanel);

/*        pointsArea = new JTextArea("The result");
        resultPanel.add(pointsArea);
        resultPanel.setBackground(Color.darkGray);

        frame.add(resultPanel);*/

        switch (contest) {
            case "Decathlon":
                initComponents(decathlonDisciplines);
                break;
            case "Heptathlon":
                initComponents(heptathlonDisciplines);
                break;
        }

        //Display the window.
        frame.setSize(500,300);
        frame.pack();
        frame.setVisible(true);
    }

    public static void btnClicked(String s, JTextField field, MouseEvent event) {
        String result = field.getText();
        System.out.println("result: " + result);
        int points = calculatePoints(s, 100);
        System.out.println("event: " + s + ", result: " + points);
        System.out.println(event.toString());
    }

    public static int calculatePoints(String event, int result) {

        return result;
    }

    public static int calculatePoints2(String event, int result) {

        return result;
    }

    public static void initComponents(String[] events) {

        for(String s : events) {
            inputPanel.add(new JButton(s));
            resultField = new JTextField();
/*            resultField.addActionListener(new java.awt.event.KeyAdapter() {
                public void textInput() {
                    result = resultField.getText();
                }
            });*/
            inputPanel.add(resultField);
            calculateButton = new JButton("Calculate");
            calculateButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(MouseEvent event) {
                    //System.out.println("event!");
                    btnClicked(s, resultField, event);
                }
            });
            inputPanel.add(calculateButton);
            pointsArea = new JTextArea("");
            inputPanel.add(pointsArea);
        }
    }

    public static ActionListener textListener;

    public static boolean isValid(String s) {

        boolean valid = true;
        String a = "ABC";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for(int n = 0; n < a.length(); n++) {
                if (!a.contains(String.valueOf(c))) {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }

/*    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.

*//*        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI("Heptathlon");
            }
        });*//*

*//*        String s = "Kalle";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(isLetter(c));
        }

        s = "AA";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(isLetter(c));
        }*//*

        System.out.println(isValid("Kalle"));
        System.out.println(isValid("AA"));
    }*/
}