package unfoldings;

import Pages.MenuPage;
import base.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Set de pruebas que comprueba que el numero de items de cada elemento (accedidos desde las respectivas
 * cards de la homepage), sea el correcto
 */
public class correctNumberOfItemsTests extends BaseTest {
    @Test
    public void testDropDownElements() throws InterruptedException {
        MenuPage menuPage = homePage.clickInElementsCard();
        assertEquals(9, menuPage.getNumberOfItems());
    }

    @Test
    public void testDropDownForms() throws InterruptedException {
        MenuPage menuPage = homePage.clickInFormsCard();
        assertEquals(1, menuPage.getNumberOfItems());
    }

    @Test
    public void testDropDownAFW() throws InterruptedException {
        MenuPage menuPage = homePage.clickInAFWCard();
        assertEquals(5, menuPage.getNumberOfItems());    }

    @Test
    public void testDropDownWidgets() throws InterruptedException {
        MenuPage menuPage = homePage.clickInWidgetsCard();
        Thread.sleep(1000);
        assertEquals(9, menuPage.getNumberOfItems());    }

    @Test
    public void testDropDownInteractions() throws InterruptedException {
        MenuPage menuPage = homePage.clickInInteractionsCard();
        Thread.sleep(1000);
        assertEquals(5, menuPage.getNumberOfItems());    }

    @Test
    public void testDropDownBSA() throws InterruptedException {
        MenuPage menuPage = homePage.clickInBSACard();
        Thread.sleep(1000);
        assertEquals(4, menuPage.getNumberOfItems());    }



}
