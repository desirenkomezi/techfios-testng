package test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewDepositTest {

	WebDriver driver;

	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://techfios.com/test/billing/?ng=login=adm/");
	}

	@Test
	public void userShouldbeAbleToaddDeposite() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("abc123");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
        String expectedTitle= "Dashboard- TechFios Test Application - Billing";
        
        //Assert
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Dashboard page did not display!");
      
        By TRANSACTIONS_MENU_LOCATOR = By.xpath("//ul[@id='side-menu']/descendant::span[text()='Transactions']");  
      By NEW_DEPOSIT_PAGE_LOCATOR = By.linkText("New Deposit");
      
      driver.findElement(TRANSACTIONS_MENU_LOCATOR).click();	
	  waitForElement(NEW_DEPOSIT_PAGE_LOCATOR, driver, 30);
	  driver.findElement(NEW_DEPOSIT_PAGE_LOCATOR).click();
	
     //Select an account from DropDown
	  Select select = new Select(driver.findElement(By.cssSelector("select#account")));
	select.selectByVisibleText("Homeloan642");  
	Thread.sleep(5000);
	
    
   String expectedDescription = "AutomationTest" + new Random().nextInt(999); 	
	
	driver.findElement(By.id("description")).sendKeys("expectedDescription");
	driver.findElement(By.id("amount")).sendKeys("100,00");
	driver.findElement(By.id("submit")).click();
	
	//writing List
	List<WebElement> descriptionElements =driver.findElements(By.xpath("//table/descendant::a"));
	System.out.println(descriptionElements.get(0).getText());
	
	Thread.sleep(3000);
	
	
	}
	
	private void waitForElement(By locator, WebDriver driver1, int time) {
	new WebDriverWait(driver1, time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}

	@AfterMethod
	public void closeEverything() {
		//driver.close();
		//driver.quit();
		
		

	}

}
