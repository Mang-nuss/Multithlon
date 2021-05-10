package console;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



public class MethodsTest {
	
	Methods m = new Methods();

	public MethodsTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of main method, of class Multithlon.
	 */


	/**
	 * Test of nameValidations method, of class Multithlon.
	 */
	@Test
	public void testNameValidationsNameAlreadyTaken() {
		System.out.println("nameValidations");
		String name = "Eva";
		boolean expResult = false;
		boolean result = m.nameValidations(name);
		assertEquals(expResult, result);

	}
	@Test
	public void testNameValidationsNewName() {
		System.out.println("nameValidations");
		String name = "Harun";
		boolean expResult = true;
		boolean result = m.nameValidations(name);
		assertEquals(expResult, result);
		
		
	}
	@Test
	public void testNameValidationsNumbers() {
		System.out.println("nameValidationsWithNumbers");
		String name = "1234456";
		boolean expResult = false;
		boolean result = m.nameValidations(name);
		assertEquals(expResult, result);

	}
	
	@Test
	public void testNameValidationsSpecialCharacters() {
		System.out.println("nameValidationsWithSpecialCharacters");
		String name = "..=?????";
		boolean expResult = false;
		boolean result = m.nameValidations(name);
		assertEquals(expResult, result);

	}
	
	@Test
	public void testNameValidationsLongUserName() {
		System.out.println("nameValidationsWithLongUser");
		String name = "abcdefghijklmnopqrstuv";
		boolean expResult = false;
		boolean result = m.nameValidations(name);
		assertEquals(expResult, result);

	}


	/**
	 * Test of competitionValidation method, of class Multithlon.
	 */
	@Test
	public void testCompetitionValidationWrongCompetitionName() {
		System.out.println("competitionValidation");
		String competition = "Bike Race";
		boolean expResult = false;
		boolean result = m.competitionValidation(competition);
		assertEquals(expResult, result);

	}
	@Test
	public void testCompetitionValidationRightCompetitionName() {
		System.out.println("competitionValidation");
		String competition = "decathlon";
		boolean expResult = true;
		boolean result = m.competitionValidation(competition);
		assertEquals(expResult, result);

	}
	
   
    
    

}
