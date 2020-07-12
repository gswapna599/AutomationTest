package PageObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FreeCarCheckPage extends BasePage {
	String label_xpath_start = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[";

	String label_xpath_end = "]/dt";

	String value_xpath_start = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[";

	String value_xpath_end = "]/dd";

	public WebElement labelText;

	@FindBy(id = "vrm-input")
	public WebElement registrationNumber;

	public CarTaxCheckHomePage VerifyCarCheckDetails() throws FileNotFoundException {
		List<String> label_data = new ArrayList<String>();
		List<String> value_data = new ArrayList<String>();
		List<String> outputValues = FreeCarCheckPage.readOutputTextFile();
		List<List<String>> collection_data = new ArrayList<List<String>>();
		for (int j = 1; j <= 5; j++) {
			String label = driver.findElement(By.xpath(label_xpath_start + j + label_xpath_end)).getText();
			System.out.println("vlaue of the label is:" + label);
			if (!label_data.contains(label))
				label_data.add(label);

		}
		System.out.println("label_data array list is: " + label_data);

		for (int k = 1; k <= 5; k++) {
			String value = driver.findElement(By.xpath(value_xpath_start + k + value_xpath_end)).getText();
			System.out.println("vlaue of the value is:" + value);
			if (!value_data.contains(value))
				value_data.add(value);
			System.out.println("vlaue of the value is:" + value_data);

		}

		driver.navigate().back();
		int size = 5;
		for (int start = 0; start < value_data.size(); start += size) {
			int end = Math.min(start + size, value_data.size());
			List<String> subList = value_data.subList(start, end);
			// System.out.println("sublist is :" + subList);
			collection_data.add(subList);
			System.out.println("final collection  is :" + collection_data);

		}
		return (CarTaxCheckHomePage) openPage(CarTaxCheckHomePage.class);
	}

	public static List<String> readOutputTextFile() throws FileNotFoundException {
		List<String> list = new ArrayList<String>();
		String str = null;
		Scanner sc = new Scanner(
				new File(System.getProperty("user.dir") + "//src//test//resources//TextFiles//car_output.txt"));
		while (sc.hasNextLine()) {
			sc.useDelimiter(",");
			str = sc.nextLine();
			list.add(str);
		}
		sc.close();
		return list;
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(registrationNumber);
	}

}
