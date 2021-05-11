package elements;

import Pages.DropDownElementsPage;
import Pages.MenuPage;
import Pages.RadioButtonPage;
import base.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pruebas:
 * 1 - opcion Yes devuelve Yes
 * 2 - Opcion Impressive devuelve Impressive
 * 3 - Opcion No no es clickeable
 * 4 - Si se selecciono una opcion anteriormente y luego se intenta clickear en No, la salida continua
 *      con el valor correspondiente a la opcion anteriormente seleccionada
 * 5 - Seleccionar la opcion Yes y luego la opcion Impressive cambia el estado del icono de seleccion
 *      de Yes y el de Impressive y tambien se modifica la salida
 */

public class RadioButtonTests extends BaseTest {

    /**
     * METODO DE PRUEBA:
     *      Se comprueba que al seleccionar la opcion Yes el radioButton se marque
     *      y el elemento correspondiente a las salidas exprese "Yes"
     */
    @Test
    public void testReturnOfYesOption(){
        MenuPage menuPage = homePage.clickInElementsCard();
        RadioButtonPage radioButtonPage = new DropDownElementsPage(driver).clickInRadioButton();
        radioButtonPage.clickYesOption();
        assertEquals( true  ,   radioButtonPage.getOptionState(driver.findElement(radioButtonPage.getYesOption())) );
        assertEquals("Yes",radioButtonPage.getTextSuccess());
    }

    /**
     * METODO DE PRUEBA:
     *      Se comprueba que al seleccionar la opcion Impresive el radioButton se marque
     *      y el elemento correspondiente a las salidas exprese "Impressive"
     */
    @Test
    public void testReturnOfImpressiveOption(){
        MenuPage menuPage = homePage.clickInElementsCard();
        RadioButtonPage radioButtonPage = new DropDownElementsPage(driver).clickInRadioButton();
        radioButtonPage.clickImpressiveOption();
        assertEquals( true  ,   radioButtonPage.getOptionState(driver.findElement(radioButtonPage.getImpressiveOption())) );
        assertEquals("Impressive",radioButtonPage.getTextSuccess());
    }

    /**
     * METODO DE PRUEBA:
     *      Se comprueba que al seleccionar la opcion No el radioButton no se marque
     *      y el elemento correspondiente a las salidas este vacio. Para esto ultimo
     *      se captura la excepcion NoSuchElementException y, si se genera dicha
     *      excepcion, se envia el mensaje "No such element"
     */
    @Test
    public void testReturnNoOption(){
        MenuPage menuPage = homePage.clickInElementsCard();
        RadioButtonPage radioButtonPage = new DropDownElementsPage(driver).clickInRadioButton();
        radioButtonPage.clickNoOption();
        assertEquals( false  ,   radioButtonPage.getOptionState(driver.findElement(radioButtonPage.getNoOption())) );
        assertEquals("No such element",radioButtonPage.getTextSuccess());
    }

    /**
     * METODO DE PRUEBA:
     *      Se selecciona la opcion Yes y despues se intenta seleccionar la opcion No
     *      Luego se comprueba que la casilla de Yes sigue seleccionada, la opcion No
     *      no esta seleccionada y la salida aun reza "Yes"
     */
    @Test
    public void yesOptionStillsSelectedAfterTrySelectingNoOption(){
        MenuPage menuPage = homePage.clickInElementsCard();
        RadioButtonPage radioButtonPage = new DropDownElementsPage(driver).clickInRadioButton();
        radioButtonPage.clickYesOption();
        radioButtonPage.clickNoOption();
        assertEquals( true,radioButtonPage.getOptionState(driver.findElement(radioButtonPage.getYesOption())) );
        assertEquals( false,radioButtonPage.getOptionState(driver.findElement(radioButtonPage.getNoOption())) );
        assertEquals("Yes",radioButtonPage.getTextSuccess());
    }

    @Test
    public void changeSelectionChangesIconAndResult(){
        MenuPage menuPage = homePage.clickInElementsCard();
        RadioButtonPage radioButtonPage = new DropDownElementsPage(driver).clickInRadioButton();
        radioButtonPage.clickYesOption();
        radioButtonPage.clickImpressiveOption();
        assertEquals(false,radioButtonPage.getOptionState(driver.findElement(radioButtonPage.getYesOption())));
        assertEquals(true,radioButtonPage.getOptionState(driver.findElement(radioButtonPage.getImpressiveOption())));
        assertEquals("Impressive",radioButtonPage.getTextSuccess());
    }
}
