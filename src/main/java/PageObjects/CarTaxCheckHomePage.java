package PageObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import Test.CarRegistrationDetailsTest;

public class CarTaxCheckHomePage extends BasePage {
	String Url = "https://cartaxcheck.co.uk/";
	@FindBy(id = "vrm-input")
	public WebElement registrationNumber;

	@FindBy(className = "jsx-3655351943")
	public WebElement freeCheckButton;

	public CarTaxCheckHomePage open() {
		CarRegistrationDetailsTest crt = new CarRegistrationDetailsTest();
		// crt.setupFramework();
		// crt.openBrowser("chrome");
		driver.navigate().to("https://cartaxcheck.co.uk/");
		return (CarTaxCheckHomePage) openPage(CarTaxCheckHomePage.class);

	}

	@Test
	public FreeCarCheckPage freeCarCheck() throws Exception {
		List<String> regNumber = CarTaxCheckHomePage.readInputTextFile();

		System.out.println("size of registration  numbers: " + regNumber.size());
		for (int i = 0; i < regNumber.size(); i++) {
			System.out.println("Car Reg Number is:" + regNumber.get(i));
			registrationNumber.sendKeys(regNumber.get(i));
			Thread.sleep(1000);
			freeCheckButton.click();
			Thread.sleep(2000);

		}
		return (FreeCarCheckPage) openPage(FreeCarCheckPage.class);

	}

	/*
	 * for(
	 * 
	 * int j = 1;j<=5;j++) { String label = driver.findElement(By.xpath(
	 * "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[" + j + "]/dt"))
	 * .getText(); System.out.println("vlaue of the label is:" + label); if
	 * (!label_data.contains(label)) label_data.add(label);
	 * 
	 * }System.out.println("label_data array list is: "+label_data);
	 * 
	 * for( int k = 1;k<=5;k++) { String value = driver.findElement(By.xpath(
	 * "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[" + k + "]/dd"))
	 * .getText();
	 * 
	 * System.out.println("vlaue of the value is:" + value); if
	 * (!value_data.contains(value)) value_data.add(value);
	 * System.out.println("vlaue of the value is:" + value_data);
	 * 
	 * }
	 * 
	 * driver.navigate().back();} // if (!collection_data.contains(value_data)) //
	 * collection_data.add(value_data); // System.out.println("collection data is: "
	 * + collection_data); int size = 5;for( int start =
	 * 0;start<value_data.size();start+=size) { int end = Math.min(start + size,
	 * value_data.size()); List<String> subList = value_data.subList(start, end); //
	 * System.out.println("sublist is :" + subList); collection_data.add(subList);
	 * System.out.println("final collection  is :" + collection_data);
	 * 
	 * }
	 * 
	 * }
	 */
	public static List<String> readInputTextFile() throws FileNotFoundException {
		List<String> list = new ArrayList<String>();
		String str = null;
		Scanner sc = new Scanner(
				new File(System.getProperty("user.dir") + "//src//test//resources//TextFiles//car_input.txt"));
		sc.useDelimiter("[^A-Z0-9][a-z0-9.]+");

		while (sc.hasNextLine()) {

			str = sc.next();
			if (str.length() >= 7) {
				list.add(str);
			}
		}
		sc.close();
		return list;
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(registrationNumber);
	}
}
