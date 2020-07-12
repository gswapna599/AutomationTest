package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CarRegistrationDetailsTest {
	private WebDriver driver;
	public final static int TIMEOUT = 30;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir") + "//executables//chromedriver"));
		driver = new ChromeDriver();
		driver.get("https://cartaxcheck.co.uk/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);

	}

	@AfterClass
	public void afterClass() {
		System.out.println("Browser is closed");
		driver.quit();
	}

	@Test
	public void freeCarCheck() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		List<String> regNumber = CarRegistrationDetailsTest.readInputTextFile();
		List<String> label_data = new ArrayList<String>();
		List<String> value_data = new ArrayList<String>();
		List<List<String>> collection_data = new ArrayList<List<String>>();

		driver.get("https://cartaxcheck.co.uk/");
		System.out.println("size of registration  numbers: " + regNumber.size());
		for (int i = 0; i < regNumber.size(); i++) {
			System.out.println("Car Reg Number is:" + regNumber.get(i));
			driver.findElement(By.id("vrm-input")).sendKeys(regNumber.get(i));
			Thread.sleep(1000);
			driver.findElement(By.className("jsx-3655351943")).click();
			Thread.sleep(2000);
			for (int j = 1; j <= 5; j++) {
				String label = driver
						.findElement(By.xpath("//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[" + j + "]/dt"))
						.getText();
				System.out.println("vlaue of the label is:" + label);
				if (!label_data.contains(label))
					label_data.add(label);

			}
			System.out.println("label_data array list is: " + label_data);

			for (int k = 1; k <= 5; k++) {
				String value = driver
						.findElement(By.xpath("//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[" + k + "]/dd"))
						.getText();

				System.out.println("vlaue of the value is:" + value);
				if (!value_data.contains(value))
					value_data.add(value);
				System.out.println("vlaue of the value is:" + value_data);

			}

			driver.navigate().back();
		}
//		if (!collection_data.contains(value_data))
//			collection_data.add(value_data);
//		System.out.println("collection data is: " + collection_data);
		int size = 5;
		for (int start = 0; start < value_data.size(); start += size) {
			int end = Math.min(start + size, value_data.size());
			List<String> subList = value_data.subList(start, end);
			// System.out.println("sublist is :" + subList);
			collection_data.add(subList);
			System.out.println("final collection  is :" + collection_data);

		}

	}

	@Test(dependsOnMethods = { "freeCarCheck" })
	public void validateCarCheckResults() throws FileNotFoundException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		List<String> outputValues = CarRegistrationDetailsTest.readOutputTextFile();
		System.out.println("size of output  details: " + outputValues.size());
		for (int i = 0; i < outputValues.size(); i++) {
			System.out.println("Car Reg details are:" + outputValues.get(i));
		}
	}

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
}