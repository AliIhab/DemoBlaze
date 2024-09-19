import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class demoBlaze {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void openBrowser() throws InterruptedException {
        // Use WebDriverManager to handle WebDriver binaries automatically
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize WebDriverWait with a timeout of 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 2)
    public void signUp() throws InterruptedException {
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.id("signin2")).click();

        // Wait for the sign-up username input to be visible
        WebElement signUpUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));
        signUpUsername.sendKeys("ali_ihab");

        driver.findElement(By.id("sign-password")).sendKeys("password123");

        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sign up')]")));
        signUpButton.click();

        // Wait for the alert to be present
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept(); // Accept the alert
    }

    @Test(priority = 4)
    public void signIn() throws InterruptedException {
        driver.navigate().refresh();
        driver.findElement(By.id("login2")).click();

        // Wait for the login username input to be visible
        WebElement loginUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        loginUsername.sendKeys("ali_ihab");

        driver.findElement(By.id("loginpassword")).sendKeys("password123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Log in')]")));
        loginButton.click();

        // Wait for the "nameofuser" element to be displayed and assert the user is logged in
        WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        Assert.assertTrue(userElement.isDisplayed(), "User is not logged in.");
    }

    @Test(priority = 6)
    public void addItemToCart() throws InterruptedException {
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.xpath("//a[contains(text(),'Laptops')]")).click();

        // Wait for the laptop item to be clickable
        WebElement sonyVaioLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sony vaio i7')]")));
        sonyVaioLink.click();

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add to cart')]")));
        addToCartButton.click();

        // Wait for the alert to be present
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Test(priority = 8)
    public void placeOrder() throws InterruptedException {
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.id("cartur")).click();

        // Wait for the "Place Order" button to be clickable
        WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Place Order')]")));
        placeOrderButton.click();
        WebElement purchaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Purchase')]")));
        // Fill in the order form
        driver.findElement(By.id("name")).sendKeys("Ali Ihab");
        driver.findElement(By.id("country")).sendKeys("Egypt");
        driver.findElement(By.id("city")).sendKeys("Cairo");
        driver.findElement(By.id("card")).sendKeys("1234567891234567");
        driver.findElement(By.id("month")).sendKeys("03");
        driver.findElement(By.id("year")).sendKeys("2026");


        purchaseButton.click();
    }

    @AfterClass
    public void quit() throws InterruptedException {
        if (driver != null) {
            driver.quit();
        }
    }
}
