/**
 * 
 */
package com.example.demo.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author PRATAP
 *
 */
public class SeleniumTest {

	private static String username = "vish@gmail.com";
	private static String password = "Admin@123";
	private static String stateName = "West Bengal";
	private static String cityName = "Kolkata";
	private static String hospital = "Calcutta National Medical College, Kolkata";
	private static String vaccine = "Vaccine_New1";
	private static String dosage = "2";
	private static String vials = "250";

	public static void main(String[] args) {

		WebDriver driver = null;
		try {
			System.setProperty("webdriver.chrome.driver",
					"Driver\\chromedriver.exe");

			// Instantiate a ChromeDriver class.
			driver = new ChromeDriver();

			// Launch Website
			driver.navigate().to("http://localhost:4200/");

			// Maximize the browser
			driver.manage().window().maximize();

			// Scroll down the webpage by 5000 pixels
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scrollBy(0, 5000)");

			// Waiting for the page to load
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Click on the Account Manage button
			driver.findElement(By.id("navbardrop")).click();
			driver.findElement(By.id("Login")).click();
			driver.findElement(By.id("email")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("Log-in")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			WebElement Element = driver.findElement(
					By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/app-admin-vaccine-update/div[2]/div/div/form"));
			js.executeScript("arguments[0].scrollIntoView();", Element);

			WebElement stateDropDown = driver.findElement(By.id("state"));
			Select dropdown = new Select(stateDropDown);
			dropdown.selectByVisibleText(stateName);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			WebElement cityDropDown = driver.findElement(By.id("city"));
			Select ctyDropdown = new Select(cityDropDown);
			ctyDropdown.selectByVisibleText(cityName);

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			WebElement hospitalDropDown = driver.findElement(By.id("hospital"));
			Select hospDropdown = new Select(hospitalDropDown);
			hospDropdown.selectByVisibleText(hospital);

			driver.findElement(By.id("vaccine")).sendKeys(vaccine);

			WebElement dosageDropDown = driver.findElement(By.id("dosage"));
			Select dosDropdown = new Select(dosageDropDown);
			dosDropdown.selectByVisibleText(dosage);

			driver.findElement(By.id("vaccineNo")).sendKeys(vials);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			driver.findElement(By.id("Update")).click();

			driver.manage().timeouts().setScriptTimeout(1, TimeUnit.MINUTES);

			System.out.println(driver.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Closing the driver...");
			if (driver != null)
				driver.close();
		}

	}
}
