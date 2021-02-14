import org.junit.Test;

public class MainTest extends BaseTest {
    @Test
    public void testAndCheck() throws InterruptedException
    {

        titleControl();
        loginSite();
        searchComp();
        secondPage();
        randomPrdct();
        detailPrice();
        addCart();
        goToCart();
        dropDownValue();
        deleteCart();

    }

}
