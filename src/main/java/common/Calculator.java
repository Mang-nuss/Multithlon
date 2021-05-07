package common;

import java.util.HashMap;
import java.util.Map;

public class Calculator {

    public static double[] values;
    public static double d1, d2, d3;
    public static double resultInput;
    public static Map<String,Double[]> Dc = new HashMap<>();
    public static Map<String,Double[]> Hc = new HashMap<>();
    public static String[] trackEvents =
            new String[]{"100m", "100m hurdles", "110m hurdles", "200m", "400m", "800m", "1500m" };
    public static String[] fieldEvents =
            new String[]{"High jump", "Long jump", "Discus throw", "Pole vault", "Javelin throw", "Shot put"};

    //Constructor
    public Calculator() {

        populateConstantMaps();
        resultInput = 0;
    }

    public static void setResultInput(double r) {
        resultInput = r;
    }

    public static int calculateScore(double result, double[] constants) {
        System.out.println("in calculate score");
        return 0;
    }

    public static void pickDisciplineFromMap(String event, String discipline) {
        System.out.println("picking discipline");
        //values = new double[3];
        if(event.equals("Decathlon")) {
            for(Map.Entry m : Dc.entrySet()) {
                if(m.getKey().equals(discipline)) {
/*                    values[0] = ((double[]) m.getValue())[0];
                    values[1] = ((double[]) m.getValue())[1];
                    values[2] = ((double[]) m.getValue())[2];*/
                    //return (double[]) m.getValue();
                    setValues((double[]) m.getValue());
                    //Object d = m.getValue();
                    //System.out.println("value = " + d.getClass());
                    d1 = ((double[]) m.getValue())[0];
                    d2 = ((double[]) m.getValue())[1];
                    d3 = ((double[]) m.getValue())[2];
                }
            }
        }
        if(event.equals("Heptathlon")) {
            for(Map.Entry m : Hc.entrySet()) {
                if(m.getKey().equals(discipline)) {
                    setValues((double[]) m.getValue());
/*                    values[0] = ((double[]) m.getValue())[0];
                    values[1] = ((double[]) m.getValue())[1];
                    values[2] = ((double[]) m.getValue())[2];*/
                }
            }
        }

        //return values;
    }

    public void populateConstantMaps() {
        Dc.put("100m", new Double[]{25.4347, 18.0, 1.81});
        Dc.put("Long jump", new Double[]{.14354, 220.0, 1.4});
        Dc.put("Shot put", new Double[]{51.39, 1.5, 1.05});
        Dc.put("High jump", new Double[]{.8465, 75.0, 1.42});
        Dc.put("400m", new Double[]{1.53775, 82.0, 1.81});
        Dc.put("110m hurdles", new Double[]{5.74352, 28.5, 1.92});
        Dc.put("Discus throw", new Double[]{12.91, 4.0, 1.1});
        Dc.put("Pole vault", new Double[]{.2797, 100.0, 1.35});
        Dc.put("Javelin throw", new Double[]{10.14, 7.0, 1.08});
        Dc.put("1500m", new Double[]{.03768, 480.0, 1.85});

        Hc.put("100m hurdles", new Double[]{9.23076, 26.7, 1.835});
        Hc.put("High jump", new Double[]{1.84523, 75.0, 1.348});
        Hc.put("Shot put", new Double[]{56.0211, 1.5, 1.05});
        Hc.put("200m", new Double[]{4.99087, 42.5, 1.81});
        Hc.put("Long jump", new Double[]{.188807, 210.0, 1.41});
        Hc.put("Javelin throw", new Double[]{15.9803, 3.8, 1.04});
        Hc.put("800m", new Double[]{.11193, 254.0, 1.88});
    }

    public double getResult() { return resultInput; }

    public static void setValues(double[] array) { values = array; }

    public double[] getValues() { return values; }


//	Array[][]
	
//	private void calculatePoints(double A, double B, double C, double P) {
//        double Points = 0;
//        int points = 0;
//        if (competition.equals("   Decathlon")) {
//            Points = A * (Math.pow((B - P), C));
//            points = (int) Points;
//        } else {
//            Points = A * (Math.pow((P - B), C));
//            points = (int) Points;
//        }
//        saveToFile(points);
//    }
}
