package stepDefinitions;

import common.Calculator;
import common.Event;
import common.Users;
import gui.MultithlonGUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CalculationDefinitions {

	public MultithlonGUI gui = new MultithlonGUI();
	public static int rowNr = 0;
	public String event;
	public String discipline;
	public double result;
	public double[] values;
	public Calculator calculator = new Calculator();
	public Users user;
	public Event decathlon = new Event("Decathlon");
	public Event heptathlon = new Event("Heptathlon");
	public Event evt;
	public boolean isValid;

	@Given("I am a registered user in {string}")
	public void i_am_a_registered_user_in_event(String e) throws IOException {
		gui.events.add(decathlon);
		gui.events.add(heptathlon);

		evt = gui.getEventByName(e);
		user = new Users("Eva", evt);
		System.out.println("You're registered in " + user.getEvent() + " and compete in: ");
		for(Object[] o : user.resultArray) {
			System.out.println(o[0]);
		}
	}

	@When("I choose a relevant {string}")
	public void i_choose_a_relevant_discipline(String disc) {
		discipline = disc;
		calculator.pickDisciplineFromMap(evt.getName(), disc);
		System.out.println("values = " + calculator.getValues());
	}
	
	@When("I insert correct {string} input")
	public void i_insert_correct_input(String s) throws IOException {
		int nrOfDisc = -1;
		int n = 0;

		isValid = calculator.setResultInput(s);
		if (isValid) {
			result = Double.valueOf(s);

			for(Object[] o : user.resultArray) {
				nrOfDisc++;
				if(discipline.equals(o[0])) {
					o[1] = result;
					n = nrOfDisc;
				}
			}

			if(evt.getName().equals("Decathlon")) { values = evt.Dc.get(discipline); }
			else if(evt.getName().equals("Heptathlon")) { values = evt.Hc.get(discipline); }
			System.out.println("result (calculator) = " + calculator.getResult() +
					"\nresult (user data) = " + user.resultArray[n][1]);
		}		
	}

	@Then("I can see a correct {int}")
	public void i_can_see_a_correct_result(int score) throws IOException {

		System.out.println("in then line");

		Row row;
		XSSFSheet sheet;
		sheet = gui.printer.workbook.createSheet("data");


		int nr = 0;
		String[] cols;
		cols = new String[]{"name", "disc1", "disc2"};
		row = sheet.createRow(nr);
		int rowCount = 0;
		for(String s : cols) {
			Cell cell = row.createCell(nr++);
			cell.setCellValue(s);
		}


		int expected = calculator.calculateScore(evt.getName(), discipline, result, values);
		Object[][] testData = {{user.getUsername()},{calculator.getResult()},{expected}};
		gui.printer.add(testData, sheet, rowNr);
		//rowNr++;
		gui.printer.write();
/*		System.out.println("\nprintout:" +
				gui.printer.getCellInfo(gui.printer.excelName,0,0,0) + ", " +
				gui.printer.getCellInfo(gui.printer.excelName,0,0,1));*/
		assertEquals(expected, score);
	}
	
	@When("I insert invalid {string} input")
	public void i_insert_invalid_input(String s) {		
		isValid = calculator.setResultInput(s);
		if (isValid) {
			result = Double.valueOf(s);
			if(evt.getName().equals("Decathlon")) { values = evt.Dc.get(discipline); }
			else if(evt.getName().equals("Heptathlon")) { values = evt.Hc.get(discipline); }
			System.out.println("result = " + calculator.getResult());
		}
	}

	@Then("I can see an error {string}")
	public void i_can_see_an_error_message(String message) {
		assertEquals(message, calculator.message);
	}
}
