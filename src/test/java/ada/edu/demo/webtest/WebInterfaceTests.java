package ada.edu.demo.webtest;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WebInterfaceTests {

	@Autowired
	private WebDriver webDriver;

	// @BeforeAll // Or @BeforeEach if you need this before each test
    // public void setupWebDriver() {
    //     System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app");	 // Replace with the actual path to chromedriver
    //     webDriver = new ChromeDriver();
    // }

	@LocalServerPort
	private int port;

	@Test
	@Order(1)
	@DisplayName("Create a user")
	public void CreateUser() {
		webDriver.get("http://localhost:"+port+"/student/new");

		WebElement studentIdInput = webDriver.findElement(By.id("studentId"));
		WebElement firstNameInput = webDriver.findElement(By.id("firstName"));
		WebElement lastNameInput = webDriver.findElement(By.id("lastName"));
		WebElement emailInput = webDriver.findElement(By.id("email"));

		// Check if such a field exists
		assertNotNull(firstNameInput);

		try {
			studentIdInput.sendKeys("12098");
			Thread.sleep(500);
			firstNameInput.sendKeys("Gabil");
			Thread.sleep(500);
			lastNameInput.sendKeys("Gurbanov");
			Thread.sleep(500);
			emailInput.sendKeys("ggurbanov12098@ada.edu.az");
			Thread.sleep(500);
		}
		catch (Exception ex) {
			System.out.println(ex);
		}

		// Find and submit the form (assuming there's a submit button with a specific attribute)
		WebElement submitButton = webDriver.findElement(By.id("submit"));
		submitButton.click();
	}

	@Test
	@Order(2)
	@DisplayName("Check the created user")
	public void CheckUser() {
		// Check if the student is added
		webDriver.get("http://localhost:"+port+"/student/list");
		List<WebElement> bodyElementFName = webDriver.findElements(By.xpath("//*[contains(text(), 'Gabil')]"));
		List<WebElement> bodyElementLName = webDriver.findElements(By.xpath("//*[contains(text(), 'Gurbanov')]"));
		System.out.println("Element result"+bodyElementLName);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		// Check if the text "Gabil" is present in the page content
		assert(bodyElementFName.size() == 1);
		assert(bodyElementLName.size() == 1);
	}


// ///////////////////////// Experimental Zone /////////////////////////


	// @Test
	// @Order(3)
	// @DisplayName("update User Credentials")
	// // public void test() {
	// // 	// Check if the student is added
	// // 	webDriver.get("http://localhost:"+port+"/student/list");
	// // 	List<WebElement> bodyElementFName = webDriver.findElements(By.xpath("//*[contains(text(), 'Gabil')]"));
	// // 	List<WebElement> bodyElementLName = webDriver.findElements(By.xpath("//*[contains(text(), 'Gurbanov')]"));
	// // 	System.out.println("Element result"+bodyElementLName);
	// // 	try {
	// // 		Thread.sleep(1000);
	// // 	} catch (InterruptedException e) {
	// // 		throw new RuntimeException(e);
	// // 	}
	// // 	assert(bodyElementFName.size() == 1);
	// // 	assert(bodyElementLName.size() == 1);
	// // }




	// public void updateUser(){
	// 	webDriver.get("http://localhost:"+port+"/student/");
	// 	// Find and submit the form (assuming there's a submit button with a specific attribute)
		
	// 	WebElement inputField = webDriver.findElement(By.name("id"));
	// 	inputField.sendKeys("12098");
		
	// 	WebElement searchButton = webDriver.findElement(By.id("submit"));
	// 	searchButton.click();
	// }
}
