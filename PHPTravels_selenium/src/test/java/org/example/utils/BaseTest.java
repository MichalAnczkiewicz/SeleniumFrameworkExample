package org.example.utils;

import org.example.utilities.CredentialsImporter;
import org.example.utilities.EnvironmentImporter;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {


    private CredentialsImporter credentialsImporter;
    private EnvironmentImporter environmentImporter;

    protected static WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor jse;

    String environmentLocation = "src/main/resources/environment.json";
    String credentialsLocation = "src/main/resources/credentials.json";


    @BeforeTest
    public void setup() throws IOException, ParseException {


        credentialsImporter = new CredentialsImporter(credentialsLocation);
        environmentImporter = new EnvironmentImporter(environmentLocation);

        String mode = environmentImporter.getEnvironment().getMode();
        String chromedriverLocation = environmentImporter.getEnvironment().getChromeDriverLocation();

        String URL = credentialsImporter.getCredentials().getUrl();
        String username = credentialsImporter.getCredentials().getUsername();
        String password = credentialsImporter.getCredentials().getPassword();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-gpu");
        System.setProperty("webdriver.chrome.driver", chromedriverLocation);
        switch (mode) {
            case "headless":
                options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu",
                        "--window-size=1920,1080", "--ignore-certificate-errors");
                break;
            default:
                options.addArguments("--window-size=1920,1080", "--ignore-certificate-errors");
                break;
        }

        driver = new ChromeDriver(options);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 30, 100);
        jse = ((JavascriptExecutor) driver);

        driver.manage().window().maximize();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        switch (mode) {
            case ("1"):
                driver.manage().window().setPosition(new Point(0, -1080));
                break;
            case ("2"):
                driver.manage().window().setPosition(new Point(-1920, -1070));
                break;
            case ("3"):
                driver.manage().window().setPosition(new Point(-980, -1080));
                break;
            default:
                break;
        }
        driver.get(URL);
    }
}



