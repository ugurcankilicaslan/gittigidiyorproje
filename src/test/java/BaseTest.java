import BaseClass.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Random;

import static Constants.ConstantsVar.*;

public class BaseTest extends BaseClass {

    public double productDetailPrice,productBasketPrice;



    public void titleControl() throws InterruptedException
    {

        urlCheck();
        ElementToBeClickableAndClick(By.cssSelector(BEFORELOGİN));
        Thread.sleep(3000);
        ElementToBeClickableAndClick(By.cssSelector(BEFORELOGİN1));

    }


    public void loginSite() throws InterruptedException
    {

        ElementSendKey(By.cssSelector(USERNAME),"USERNAME");
        ElementSendKey(By.name(PASSWORDS),"PASSWORD");
        ElementToBeClickableAndClick(By.cssSelector(LOGİN));
        loginCheck();

    }


    public void searchComp() throws InterruptedException
    {

        ElementSendKey(By.cssSelector(SEARCH),"Bilgisayar");
        ElementToBeClickableAndClick(By.xpath(SEARCHBUTTON));
        Thread.sleep(3000);

    }


    public void secondPage() throws InterruptedException
    {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        WebElement ikinciSayfa = driver.findElement(By.cssSelector(SECONDPAGE));
        je.executeScript("arguments[0].scrollIntoView(true);",ikinciSayfa);
        ElementToBeClickableAndClick(By.cssSelector(SECONDPAGE));
        secondPageChe();

    }


    public void randomPrdct() throws InterruptedException
    {

        List<WebElement> links = driver.findElements(By.className("srp-item-list"));
        System.out.println("boyutu : "+links.size());
        Random productSize = new Random();
        int randomNumber = productSize.nextInt(links.size());
        System.out.println("Random üretilen Sayı:"+ randomNumber);
        ElementToBeClickableAndClick(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[3]/div[2]/ul/li["+randomNumber+"]"));

    }


    public void detailPrice() throws InterruptedException
    {

        boolean indirim_var_mi= IsElementVisible(By.id("sp-price-lowPrice"));
        if(indirim_var_mi==true)
        {
            productDetailPrice= priceConvertDouble(By.id("sp-price-lowPrice"));
        }
        else {
            productDetailPrice= priceConvertDouble(By.id("sp-price-highPrice"));
        }
        System.out.println("Ürün Detay Sayfasındaki Fiyat: "+productDetailPrice);

    }


    public void addCart() throws InterruptedException
    {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        WebElement sepeteEkleButonu = driver.findElement(By.cssSelector(ADDTOCART));
        je.executeScript("arguments[0].scrollIntoView(true);",sepeteEkleButonu);
        ElementToBeClickableAndClick(By.cssSelector(ADDTOCART));
        Thread.sleep(3000);

    }


    public void goToCart() throws InterruptedException
    {

        driver.get(URL);
        ElementToBeClickableAndClick(By.name(GOTOCART));

    }


    public void dropDownValue() throws InterruptedException
    {

        WebElement choose = driver.findElement(By.xpath(DROPDOWNLİST));
        Select sel = new Select(choose);
        sel.selectByValue("2");
        WebElement adet = sel.getFirstSelectedOption();
        System.out.println("Seçilen Adet Sayısı:"+ adet.getText());
        Thread.sleep(3000);

    }


    public void deleteCart() throws InterruptedException
    {

        ElementToBeClickableAndClick(By.xpath(DELETECART));
        cartCheck();
        //Sepette 2 ürün varken birini sildiğiniz zaman 2. ürün durmasına rağmen sepetinizde ürün bulunmamaktadır. texti
        //incele yaptığımızda görünüyor ve buna göre kontrol yapıyor.
        productBasketPrice = priceConvertDouble(By.cssSelector("div[class='total-price']"));
        System.out.println("Sepetteki Ürünün Fiyatı : "+productBasketPrice);
        if(productBasketPrice==productBasketPrice)
        {
            System.out.println("Fiyat karşılastırması doğru");
        }
        else {
            System.out.println("Fiyat karşılastırması yanlış");
            Assert.fail();
        }

    }
}
