package home;

import Pages.MenuPage;
import base.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Set de pruebas que comprueban que cada card lleva a la pagina correcta, corroborando su titulo
 */

public class CardsTests extends BaseTest {

    @Test
    public void TestElementsCard(){
        MenuPage menuPage = homePage.clickInElementsCard();
        assertEquals("Elements", menuPage.getMainHeader());
    }

    @Test
    public void TestFormsCard(){
        MenuPage menuPage = homePage.clickInFormsCard();
        assertEquals("Forms", menuPage.getMainHeader());
    }

    @Test
    public void TestAFWCard(){
        MenuPage menuPage = homePage.clickInAFWCard();
        assertEquals("Alerts, Frame & Windows", menuPage.getMainHeader());
    }

    @Test
    public void TestWidgetsCard(){
        MenuPage menuPage = homePage.clickInWidgetsCard();
        assertEquals("Widgets", menuPage.getMainHeader());
    }

    @Test
    public void TestInteractionsCard(){
        MenuPage menuPage = homePage.clickInInteractionsCard();
        assertEquals("Interactions", menuPage.getMainHeader());
    }

    @Test
    public void TestBSACard(){
        MenuPage menuPage = homePage.clickInBSACard();
        assertEquals("Book Store", menuPage.getMainHeader());
    }
}
