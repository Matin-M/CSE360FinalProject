import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author matin massoudi
 *
 */
class FeatureTesting extends JFrame{

	static int caseCounter = 0;
	
	//Test cases:
	
	@Test
	void testRosterManager() {
		RosterManager rosterManager = new RosterManager();
	}
	
	@Test 
	void testAttendanceManager() {
		
	}
	
	@Test
	void testDataStructures() {
		
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Testing begins.");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Testing completed.");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Test Case #"+caseCounter);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
