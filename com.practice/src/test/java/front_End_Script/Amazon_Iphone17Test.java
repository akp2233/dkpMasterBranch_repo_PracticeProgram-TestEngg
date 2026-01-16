package front_End_Script;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Amazon_Iphone17Test {

	@Test
	public void iphone17PinkTest() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		/*search iphone 17*/
		WebElement searchBox=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchBox.clear();
		searchBox.sendKeys("iphone 17",Keys.ENTER);
		
		/*select pink iphone 17 click on that */
		driver.findElement(By.xpath("//a[@class='a-link-normal s-line-clamp-2 s-line-clamp-3-for-col-12 s-link-style a-text-normal']/descendant::span[contains(text(),'iPhone 17 Pro 256 GB: 15.93 cm (6.3″) Display with Promotion up to 120Hz')and contains(text(),' Cosmic Orange')]")).click();
		
		String expectedUrl="https://www.amazon.in/iPhone-Pro-256-Promotion-Breakthrough";
		Set<String> allWin = driver.getWindowHandles();
		for(String id:allWin) {
			driver.switchTo().window(id);
			String currentUrl = driver.getCurrentUrl();
			if(currentUrl.contains(expectedUrl)) {
				break;
			}
		}
		/*click on exchange */
		driver.findElement(By.xpath("//i[@class='a-icon a-accordion-radio a-icon-radio-inactive']")).click();
		driver.findElement(By.xpath("//input[@aria-labelledby='chooseButton-announce']")).click();
		
		/* click on mobile brand select -->samsung */
		driver.findElement(By.xpath("//span[text()='Select Brand']")).click();
        
		WebElement phoneExchange = driver.findElement(By.xpath("//a[text()='Samsung']"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", phoneExchange);
		
		/*click on mobile model select --> Galaxy S24 Ultra 5G  */
		driver.findElement(By.xpath("//span[@id='SamsungId']")).click();
		WebElement mobileModel = driver.findElement(By.xpath("//a[text()='Galaxy S24 Ultra 5G']"));
		js.executeScript("arguments[0].click();", mobileModel);
		
		/*select ram and storage this element store inside select tag*/
		
		Select sl=new Select(driver.findElement(By.xpath("//select[@id='SamsungGalaxy S24 Ultra 5G']")));
		sl.selectByValue("12GB | 512GB");
		
		/*select no damage */
		driver.findElement(By.xpath("//label[@for='noBodyDamageCheckbox']")).click();
		
		/*click on continue button */
		
		WebElement continueButton = driver.findElement(By.xpath("//span[contains(text(),'Continue')]"));
		//js.executeScript("arguments[0].scrollIntoView(true);", continueButton);
		Actions ac=new Actions(driver);
		ac.scrollByAmount(606, 557);
		continueButton.click();
		
		/*capture the exchange value */
		 String exchangeValue=driver.findElement(By.xpath("//span[@id='valueCommensurateOfferAppliedSuccessText']")).getText();
		Reporter.log(exchangeValue,true);
		
  
	}
}
