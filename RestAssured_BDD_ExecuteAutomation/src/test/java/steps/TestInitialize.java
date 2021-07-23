package steps;

import io.cucumber.java.Before;
import utilities.RestAssuredExtension;

public class TestInitialize {

	@Before
	public void testSetup() {
		RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
	}
}
