package com.flipkart.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class Base {
	WebDriver driver;
	WebDriverWait wait;
 JavascriptExecutor js;
 Select sel;
	@BeforeTest
	public void openbrowse()
	{
		//To launch chrome browser;
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://www.flipkart.com/");
		
		//to handle popup
		try {
			WebElement popup = driver.findElement(By.xpath("//span[text()='✕']"));
			popup.click();
			
		} catch (Exception e) {
			System.out.println("Login popup disabled");
		}
	}
	
	@Test(priority = 1)
	public void selectProduct() throws InterruptedException
	{
		//search for the product;
		WebElement searchbox = driver.findElement(By.xpath("//input[@name='q']"));
		searchbox.sendKeys("Iphone 16");
		searchbox.submit();
		
	}
	
	@Test(priority = 2)
	public void buyProduct() throws InterruptedException
	{
		//Buy product;
		js=(JavascriptExecutor) driver;
		WebElement product = driver.findElement(By.xpath("//div[contains(text(), 'Apple iPhone 16 (Black, 128 GB)')]"));
		product.click();	
		
		for(String windowhandle: driver.getWindowHandles())
		{
			driver.switchTo().window(windowhandle);
		}
		
		Thread.sleep(2000);
		WebElement buy = driver.findElement(By.xpath("//span[@class='OGrnIL']"));
		js.executeScript("arguments[0].scrollIntoView(true);",buy);
		//wait.until(ExpectedConditions.elementToBeClickable(cart)).click();
		buy.click();
		Thread.sleep(2000);
	}
	
	@Test(priority = 3)
	public void credentials() throws InterruptedException, AWTException
	{ 
		//to add credentials for login
		
	js=(JavascriptExecutor) driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//WebElement mob= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Enter Email/Mobile number']")));
      WebElement mob=driver.findElement(By.xpath("//input[@type='text']"));
		mob.sendKeys("vaibhavmkumakale@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		WebElement otp = driver.findElement(By.xpath("//span[.='Enter OTP']"));
		Thread.sleep(30000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	@Test(priority = 4)
	public void addAddress() throws InterruptedException
	{
		driver.findElement(By.xpath("(//button[normalize-space()='Deliver Here']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[.='CONTINUE']")).click();
		
		driver.findElement(By.xpath("//button[.='Accept & Continue']")).click();
	}
	
	@Test(priority = 5)
	public void cardDetails() throws InterruptedException
	{
		js=(JavascriptExecutor) driver;
		WebElement card = driver.findElement(By.xpath("//label[@for='CREDIT']//div[@class='_9-suWS']"));
		js.executeScript("arguments[0].scrollIntoView(true);", card);
		card.click();
		
		WebElement cardno = driver.findElement(By.xpath("//input[@name='cardNumber']"));
		cardno.sendKeys("4242424242424242");
		WebElement month = driver.findElement(By.xpath("//select[@name='month']"));
		sel=new Select(month);
		sel.selectByVisibleText("09");
		WebElement year = driver.findElement(By.xpath("//select[@name='year']"));
		sel=new Select(year);
		sel.selectByVisibleText("25");
		driver.findElement(By.xpath("//input[@name='cvv']")).sendKeys("123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@name='billing_pincode'])[1]")).sendKeys("560055");
		WebElement country = driver.findElement(By.xpath("(//select[@name='billing_country'])[1]"));
		sel=new Select(country);
		sel.selectByVisibleText("India");
		driver.findElement(By.xpath("//input[@name='billing_city']")).sendKeys("Bangalore");
		driver.findElement(By.xpath("//input[@name='billing_state']")).sendKeys("Bangalore");
		driver.findElement(By.xpath("//textarea[@name='billing_address']")).sendKeys("Bangalore");
		driver.findElement(By.xpath("//button[@type='button']")).click();
		
		Thread.sleep(2000);
	}
	
   @AfterTest
	public void closebrowse()
	{
		driver.quit();
	}

}
