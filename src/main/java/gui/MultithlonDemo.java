package gui;

import common.Calculator;
import common.Event;
import common.ExcelPrinter;
import common.Users;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MultithlonDemo {

	public static Scanner scanner = new Scanner(System.in);
	public static String inputString, discipline, result, username;
	public static int rowNr = 1;
	public static double[] values;
	public static int score;
	public static Users user;
	public static boolean on, isValid;
	public static Event theEvent;
	public static Calculator calculator;
	public static MultithlonGUI gui;
	public static XSSFSheet sheetDeca, sheetHepta;
	public static FileInputStream file;

	// -----------MAIN-----------//
	public static void main(String[] args) throws IOException {

		gui = new MultithlonGUI();
		calculator = new Calculator();
		on = true;
		String inputChoice;

		//-- READING EXCEL FILE --//
		/*
		From https://stackoverflow.com/questions/1516144/how-to-read-and-write-excel-file
		and Zeshan's loading methods on the Console branch.
		 */

/*		try {
			file = new FileInputStream("C:\\Users\\eva\\git\\Multithlon\\" + gui.printer.excelName + ".xlsx");
			gui.printer.workbook = new XSSFWorkbook(file);
			sheetDeca = gui.printer.workbook.getSheet("sheetDeca"); //Trying out...
			sheetHepta = gui.printer.workbook.getSheet("sheetHepta"); //Trying out...
			System.out.println("Sheet loaded from file.");

		} catch (IOException ioe) {
			System.out.println("Excel loading failure.");
		}*/

		//------------------------//

		try {
			String[] decathlonColumns = new String[]{"Name", "100m", "Score", "Long jump", "Score", "Shot put", "Score", "High jump", "Score",
					"400m", "Score", "110m hurdles", "Score", "Discus throw", "Score", "Pole vault", "Score",
					"Javelin throw", "Score", "1500m", "Score", "Total"};
			String[] heptathlonColumns = new String[]{"Name", "100m hurdles", "Score", "High jump", "Score", "Shot put", "Score", "200m", "Score",
					"Long jump", "Score", "Javelin throw", "Score", "800m", "Score", "Total"};

			sheetDeca = fillInColumns("Decathlon", decathlonColumns);
			sheetHepta = fillInColumns("Heptathlon", heptathlonColumns);

		} catch (IllegalArgumentException iae) {
			System.out.println("Exception occurred when initiating a new sheet: " + iae.getMessage());
		}

		while (on) {

			System.out.println("-----------\nWelcome.\n" + "1. Choose event (Decathlon or Heptathlon)\n"
					+ "2. Register participant\n" + "3. Enter result\n" + "4. Result table\n" + "5. Quit");

			System.out.print("Enter nr: ");

			inputChoice = scanner.nextLine();

			switch (inputChoice) {
			case "1":
				chooseEvent();
				break;
			case "2":
				registerParticipant(theEvent);
				break;
			case "3":
				registerResult();
				break;
			case "4":
				showResultTable();
				break;
			case "5":
				System.out.println("Ciao");
				on = false;
				break;
			default:
				System.out.println("Invalid choice, please pick a menu option between 1 and 5.");
			}
		}

		// System.out.print("input: " + in);
	}
	// --------------------------//

	private static XSSFSheet fillInColumns(String title, String[] columns) {

		XSSFSheet sheet = gui.printer.workbook.createSheet(title);
		Row row;

		int nr = 0;
		row = sheet.createRow(nr);
		for (String s : columns) {
			Cell cell = row.createCell(nr++);
			cell.setCellValue(s);
		}

		return sheet;
	}

	private static void registerResult() throws IOException {
		System.out.print("Enter participant name: ");
		if (scanner.hasNext()) {
			username = scanner.nextLine();
			System.out.println();

			if (nameExists(username)) {
				System.out.println(username + " exists");
				user = getUser(username);
				theEvent = getEvent(user.getEvent());
				chooseDiscipline(theEvent);
				enterResults(discipline, theEvent, user);
			} else {
				System.out.println(username + " is not registered.");
			}
			;
		}
	}

	/*
	 * TODO: handle outstanding results that are probably wrongly input
	 */
	private static void enterResults(String discipline, Event evt, Users user) throws IOException, NullPointerException {
		System.out.print("Enter result: ");
		if (scanner.hasNext()) {
			result = scanner.nextLine();
			System.out.println();
			System.out.println("In row: " + rowNr);

			isValid = calculator.setResultInput(result);
			
			if (isValid) {
				double res = Double.valueOf(result);
				if (evt.getName().equals("Decathlon")) {
					values = evt.Dc.get(discipline);
				} else if (evt.getName().equals("Heptathlon")) {
					values = evt.Hc.get(discipline);
				}

				try {
					int nrOfDisc = -1;
					int n = 0;

					score = calculator.calculateScore(evt.getName(), discipline, res, values);

					//Adding result to user result array, for easier access
					for(Object[] o : user.resultArray) {
						nrOfDisc++;
						if(discipline.equals(o[0])) {
							o[1] = result;
							o[2] = score;
							user.score += score;
							System.out.println(user.getUsername() + " has " + user.score + " points.");
							n = nrOfDisc;
						}
					}

					System.out.println("score for discipline " + discipline + " result " + res +
							" is " + score + " according to calculator and " + user.resultArray[n][2] +
							" according to user data");

					gui.printer.add(evt, sheetDeca, rowNr);
					//rowNr++;

					gui.printer.write();
				} catch (NullPointerException npe) {
					System.out.println("Are those data applicable?");
				}
			}
		}
	}

	/*
	 * TODO: one should not be able to choose a discipline that is not included in
	 * the event, e.g. 200m for decathlon
	 */
	private static void chooseDiscipline(Event e) {
		System.out.print("For event " + e.getName() + ", enter discipline name: ");
		if (scanner.hasNext()) {
			discipline = scanner.nextLine();
			System.out.println();

			boolean discExists = isValidDiscipline(discipline);
			if (discExists) {
				System.out.println("Chosen discipline: " + discipline);
				calculator.pickDisciplineFromMap(e.getName(), discipline);
				System.out.println("values = " + calculator.getValues());
			} else {
				System.out.println("We cannot find " + discipline + " in " + e.getName() + ", please make sure that you've entered a correct discipline.");
			}
			
		}
	}

	private static boolean isValidDiscipline(String disc) {
		boolean discExists = false;
		for (String d : gui.decathlonDisciplines) {
			if (d.equals(disc)) {
				discExists = true;
			}
			for (String s : gui.heptathlonDisciplines) {
				if (s.equals(disc)) {
					discExists = true;
				}
			}
		}
		return discExists;
	}

	private static void showResultTable() {
		System.out.println("--- CURRENT STANDING ---");
		for(Event e : gui.events) {
			System.out.println(e.eventName);
			for(Users user : e.users) {
				System.out.println(user.getUsername() + " : " + user.score);
			}
			System.out.println();
		}
		System.out.println("------------------------");
	}

	private static void registerParticipant(Event evt) throws NullPointerException {

		System.out.print("Enter participant name: ");
		if (scanner.hasNext()) {
			username = scanner.nextLine();
			System.out.println();

			try {
				user = new Users(username, evt);
				// theEvent.addToUsersList(new Users(username, evt));
				String actualMessage = evt.addUser(username, evt);
				System.out.println(actualMessage);
			} catch (NullPointerException npe) {
				System.out.println("Something failed... no events yet?");
			}
		}
		printParticipants();

	}

	private static void chooseEvent() {
		theEvent = null;
		System.out.print("Choose event (Decathlon or Heptathlon): ");
		if (scanner.hasNext()) {
			inputString = scanner.nextLine().toLowerCase();
			// System.out.println("in: " + inputString);

			System.out.println();

			if (inputString.equals("decathlon")) {
				System.out.println("in: " + inputString);
				theEvent = new Event("Decathlon");
				gui.events.add(theEvent);
			} else if (inputString.equals("heptathlon")) {
				theEvent = new Event("Heptathlon");
				gui.events.add(theEvent);
			} else {
				System.out.println("No such event. Please enter either Decathlon or Heptathlon.\n");
			}

			System.out.println("events for Gui object: ");
			for (Event e : gui.events) {
				System.out.println(e.getName());
			}
			// event = userEvent;
		}
	}

	private static void printParticipants() {

		System.out.println("-----------");
		for (Event evt : gui.events) {
			System.out.println("users in " + evt.getName() + ": ");
			for (Users u : evt.users) {
				System.out.println(u.getUsername());
			}
		}
		System.out.println("-----------");
	}

	private static boolean nameExists(String name) {

		boolean exists = false;
		for (Event evt : gui.events) {
			System.out.println(evt.eventName);
			exists = evt.isAlreadyRegistered(name);
		}

		return exists;
	}

	public static Users getUser(String name) {

		Users user = null;
		for (Event evt : gui.events) {
			for (Users u : evt.users) {
				if (u.getUsername().equals(name)) {
					user = u;
				}
			}
		}

		return user;
	}

	public static Event getEvent(String eventName) {

		theEvent = null;
		for (Event evt : gui.events) {
			if (eventName.equals(evt.getName())) {
				theEvent = evt;
			}
		}

		return theEvent;
	}
}
