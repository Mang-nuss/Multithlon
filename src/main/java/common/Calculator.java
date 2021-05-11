package common;

import java.util.HashMap;
import java.util.Map;

public class Calculator {

	public static double[] values;
	public static double d1, d2, d3;
	public static double resultInput;
	private static String discipline;
	public static String message;
	public boolean isTrackEvent;
	public String event;
	public static Map<String, double[]> Dc = new HashMap<String, double[]>();
	public static Map<String, double[]> Hc = new HashMap<String, double[]>();
	public static String[] trackEvents = new String[] { "100m", "100m hurdles", "110m hurdles", "200m", "400m", "800m",
			"1500m" };
	public static String[] fieldEvents = new String[] { "High jump", "Long jump", "Discus throw", "Pole vault",
			"Javelin throw", "Shot put" };

	// Constructor
	public Calculator() {

		populateConstantMaps();
		resultInput = 0;
		event = null;
		message = null;
	}

	public boolean setResultInput(String s) {
		boolean isValid = true;
		double r = 0;
		try {
			r = Double.parseDouble(s);
		} catch (NullPointerException e) {
			message = "You need to input a value in order to continue.";
			System.out.println(message);
			isValid = false;
		} catch (NumberFormatException e) {
			message = "Invalid input, only numbers are allowed.";
			System.out.println(message);
			isValid = false;
		}

		if (isValid && r > 0) {
			resultInput = r;
		} else if (isValid && r <= 0) {
			message = "Only positive numbers larger than 0 are allowed.";
		}

		return isValid;
	}

	public int calculateScore(String event, String discipline, double result, double[] constants) {
		System.out.println("in calculate score for event: " + getEvent());

		String d = trackOrFieldEvent(discipline);
		int points = 0;
		if (event.equals("Decathlon")) {
			if (d.equals("track event")) {
				points = (int) (constants[0] * (Math.pow((constants[1] - result), constants[2])));
			} else if (d.equals("field event")) {
				points = (int) (constants[0] * (Math.pow((result - constants[1]), constants[2])));
			}
		} else if (event.equals("Heptathlon")) {
			if (d.equals("track event")) {
				points = (int) (constants[0] * (Math.pow((constants[1] - result), constants[2])));
			} else if (d.equals("field event")) {
				points = (int) (constants[0] * (Math.pow((result - constants[1]), constants[2])));
			}
		}
		// saveToFile(points);
		System.out.println("calculated score is: " + points);
		return points;
	}

	public String trackOrFieldEvent(String discipline) {

		isTrackEvent = false;

		for (String s : trackEvents) {
			if (discipline.equals(s)) {
				isTrackEvent = true;
			}
		}

		if (isTrackEvent) {
			return "track event";
		} else {
			return "field event";
		}
	}

	public void pickDisciplineFromMap(String event, String discipline) {

		setEvent(discipline);
		System.out.println("picking discipline");
		// values = new double[3];
		if (event.equals("Decathlon")) {
			for (Map.Entry m : Dc.entrySet()) {
				if (m.getKey().equals(discipline)) {
					setValues((double[]) m.getValue());
					System.out.println("value 1 = " + values[0]);
				}
			}
		}
		if (event.equals("Heptathlon")) {
			for (Map.Entry m : Hc.entrySet()) {
				if (m.getKey().equals(discipline)) {
					setValues((double[]) m.getValue());
					System.out.println("value 2 = " + values[1]);
				}
			}
		}

		// return values;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String discipline) {
		event = discipline;
	}

	public void populateConstantMaps() {
		Dc.put("100m", new double[] { 25.4347, 18.0, 1.81 });
		Dc.put("Long jump", new double[] { .14354, 220.0, 1.4 });
		Dc.put("Shot put", new double[] { 51.39, 1.5, 1.05 });
		Dc.put("High jump", new double[] { .8465, 75.0, 1.42 });
		Dc.put("400m", new double[] { 1.53775, 82.0, 1.81 });
		Dc.put("110m hurdles", new double[] { 5.74352, 28.5, 1.92 });
		Dc.put("Discus throw", new double[] { 12.91, 4.0, 1.1 });
		Dc.put("Pole vault", new double[] { .2797, 100.0, 1.35 });
		Dc.put("Javelin throw", new double[] { 10.14, 7.0, 1.08 });
		Dc.put("1500m", new double[] { .03768, 480.0, 1.85 });

		Hc.put("100m hurdles", new double[] { 9.23076, 26.7, 1.835 });
		Hc.put("High jump", new double[] { 1.84523, 75.0, 1.348 });
		Hc.put("Shot put", new double[] { 56.0211, 1.5, 1.05 });
		Hc.put("200m", new double[] { 4.99087, 42.5, 1.81 });
		Hc.put("Long jump", new double[] { .188807, 210.0, 1.41 });
		Hc.put("Javelin throw", new double[] { 15.9803, 3.8, 1.04 });
		Hc.put("800m", new double[] { .11193, 254.0, 1.88 });
	}

	public double getResult() {
		return resultInput;
	}

	public static void setValues(double[] array) {
		values = array;
	}

	public double[] getValues() {
		return values;
	}

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
