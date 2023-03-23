import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GoogleMapsTest {
    AppiumDriver<MobileElement> driver;
    
    @BeforeTest
    public void setup() {
        // Appium server kurulumu ve cihaz bilgilerinin belirlenmesi
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("udid", "emulator-5554");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "10.0");
        caps.setCapability("appPackage", "com.google.android.apps.maps");
        caps.setCapability("appActivity", "com.google.android.maps.MapsActivity");
        caps.setCapability("noReset", true);

        // Android cihazda AppiumDriver'ın başlatılması
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }
    
    @Test(priority=1)
    public void verifyHomePageComponents() {
        // Google Maps uygulamasının anasayfasındaki komponentlerin kontrolü
        MobileElement searchBox = driver.findElement(By.id("searchboxinput"));
        MobileElement currentLocationButton = driver.findElement(By.id("mylocation_button"));
        MobileElement directionsButton = driver.findElement(By.id("directions_button"));

        Assert.assertTrue(searchBox.isDisplayed());
        Assert.assertTrue(currentLocationButton.isDisplayed());
        Assert.assertTrue(directionsButton.isDisplayed());
    }
    
    @Test(priority=2)
    public void verifyMapControls() {
        // Harita üzerindeki kontrol butonlarının kontrolü
        MobileElement zoomInButton = driver.findElement(By.id("zoom_in_button"));
        MobileElement zoomOutButton = driver.findElement(By.id("zoom_out_button"));
        MobileElement compassButton = driver.findElement(By.id("google_maps_circular_compass"));
        MobileElement layerButton = driver.findElement(By.id("map_layers_button"));

        Assert.assertTrue(zoomInButton.isDisplayed());
        Assert.assertTrue(zoomOutButton.isDisplayed());
        Assert.assertTrue(compassButton.isDisplayed());
        Assert.assertTrue(layerButton.isDisplayed());
    }
    
    @Test(priority=3)
    public void verifyNavigationFunctionality() {
        // Navigasyon butonunun kontrolü ve navigasyon işleminin başlatılması
        MobileElement directionsButton = driver.findElement(By.id("directions_button"));
        directionsButton.click();

        // Yeni sayfada yer seçimi yapılması
        MobileElement destinationInput = driver.findElement(By.id("directions_destination_input"));
        destinationInput.sendKeys("Kadıköy, İstanbul");

        MobileElement searchButton = driver.findElement(By.id("directions_search_button"));
        searchButton.click();

        // Navigasyon tipi seçimi
        MobileElement directionsTypeButton = driver.findElement(By.id("directions_mode_button"));
        directionsTypeButton.click();

        MobileElement transitButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Transit']"));
        transitButton.click();

        MobileElement selectButton = driver
