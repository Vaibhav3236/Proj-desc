package com.flipkart.testcase;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Amazon {
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	@BeforeTest
	public void luanchApp()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.myntra.com/");
	}
	
	@Test
	public void test() throws InterruptedException
	{
		js=(JavascriptExecutor) driver;
	WebElement search=driver.findElement(By.xpath("//input[@class='desktop-searchBar']"));
	//js.executeScript("arguments[0].scrollIntoView(true);",ref);
	search.sendKeys("tshirt");
	driver.findElement(By.xpath("//span[@class='myntraweb-sprite desktop-iconSearch sprites-search']")).click();
	Thread.sleep(1000);

	WebElement next = driver.findElement(By.xpath("//li[@class='pagination-next']"));
	js.executeScript("arguments[0].scrollIntoView();",next);
	next.click();
		
	}
	
	@AfterTest
	public void closeApp()
	{
		driver.quit();
	}

}
