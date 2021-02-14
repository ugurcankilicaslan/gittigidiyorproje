package BaseClass;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Constants.ConstantsVar.*;

public class BaseClass {
    public WebDriver driver;

    public void urlCheck()
    {

        Assert.assertTrue(URL.contains("https://www.gittigidiyor.com/"));
        System.out.println("AnaSayfa Kontrolü Yapıldı."+URL);

    }
    public void loginCheck()
    {

        Assert.assertNotNull(LOGİNCHECK);
        System.out.println("Giriş Kontrolü Yapıldı"+LOGİNCHECK);

    }

    public void secondPageChe()
    {

        Assert.assertTrue(SECPAGECHE.contains("https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2"));
        System.out.println("İkinci Sayfaya Geçiş Kontrol Edilir."+SECPAGECHE);

    }

    public void cartCheck()
    {

        Assert.assertTrue(URUNBULUNMAMAKTADIR.contains("Sepetinizde ürün bulunmamaktadır."));
        System.out.println("Ürün Bulunmamaktadır Text'i Kontrol Edilir"+URUNBULUNMAMAKTADIR);

    }

    public void ElementToBeClickableAndClick(By element)
    {

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        System.out.println(element+" ");

    }

    public void ElementSendKey(By element,String sendkey)
    {

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(sendkey);
        System.out.println(element+" Sayfada tıklanabilir olması beklendi ve tıklandı.");

    }
    public boolean IsElementVisible(By element)
    {

        try
        {
            WebDriverWait wait = new WebDriverWait(driver,1);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
            return false;
        }
        catch (Exception e)
        {
            return true;
        }

    }
    private WebElement findElement(By element)
    {

        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        WebElement webElement = webDriverWait.
                until(ExpectedConditions.presenceOfElementLocated(element));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;

    }
    public double priceConvertDouble(By priceText)
    {

        String[] elementStringList = findElement(priceText).getText().trim().split(" ");
        String elementString = elementStringList[0].replaceAll(",", "");
        return Double.parseDouble(elementString);

    }

    @Before
    public void setUp()
    {

        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.gittigidiyor.com/");
        driver.manage().window().maximize();

    }


    @After
    public void driverquit() throws InterruptedException {

        Thread.sleep(1000);
        driver.quit();

    }

}
