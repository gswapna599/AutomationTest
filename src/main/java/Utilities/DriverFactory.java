package Utilities;

public class DriverFactory {
	private static String chromeDriverExePath;
	private static String geckoDriverExePath;
	private static String ieDriverExePath;
	private static boolean isRemote;

	public static String getChromeDriverExePath() {
		return chromeDriverExePath;
	}

	public static void setChromeDriverExePath(String chromeDriverExePath) {
		DriverFactory.chromeDriverExePath = chromeDriverExePath;
	}

	public static String getGeckoDriverExePath() {
		return geckoDriverExePath;
	}

	public static void setGeckoDriverExePath(String geckoDriverExePath) {
		DriverFactory.geckoDriverExePath = geckoDriverExePath;
	}

	public static String getIeDriverExePath() {
		return ieDriverExePath;
	}

	public static void setIeDriverExePath(String ieDriverExePath) {
		DriverFactory.ieDriverExePath = ieDriverExePath;
	}

	public static boolean isRemote() {
		return isRemote;
	}

	public static void setRemote(boolean isRemote) {
		DriverFactory.isRemote = isRemote;
	}

}
