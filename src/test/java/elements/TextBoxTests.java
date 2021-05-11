package elements;

import Pages.DropDownElementsPage;
import Pages.MenuPage;
import Pages.TextBoxPage;
import base.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TextBoxTests extends BaseTest {
    /**
     * Prueba positiva:
     *      Se comprueba que los campos se rellenen correctamente.
     * METODO DE PRUEBA:
     *      Se rellenan todos los campos, obligatorios y no obligatorios y se comprueba
     *      que se devuelven los datos correctos
     */
    @Test
    void successSubmitForm(){
        MenuPage menuPage = homePage.clickInElementsCard();
        TextBoxPage textBoxPage = new DropDownElementsPage(driver).clickInTextBox();
        textBoxPage.fillUserNameField("Joaquin Grassi");
        textBoxPage.filluserEmailField("userEmail@example.com");
        textBoxPage.fillCurrentAddressField("Avenida Siempreviva 742");
        textBoxPage.fillPermanentAddresField("Calle Baker 221B");
        textBoxPage.clickEnSubmit();

        assertEquals(4,textBoxPage.getNumberOfResponseItems());
        assertEquals(true, textBoxPage.getUserNameResponse().contains("Joaquin Grassi"));
        assertEquals(true, textBoxPage.getUserEmailResponse().contains("userEmail@example.com"));
        assertEquals(true, textBoxPage.getCurrentAddressResponse().contains("Avenida Siempreviva 742"));
        assertEquals(true, textBoxPage.getPermanentAddressResponse().contains("Calle Baker 221B"));
    }
    /**
     * Prueba positiva:
     *      Se comprueba que se devuelvan los datos correctos al agregar
     *      solo un elemento.
     * METODO DE PRUEBA:
     *      Se rellena solo el campo Username y se comprueba
     *      que el resultado sea solo el nombre de usuario del usuario
     */
    @Test
    public void onlyUserNameFillFieldSuccessSubmit(){
        MenuPage menuPage = homePage.clickInElementsCard();
        TextBoxPage textBoxPage = new DropDownElementsPage(driver).clickInTextBox();
        textBoxPage.fillUserNameField("Joaquin Grassi");
        textBoxPage.clickEnSubmit();
        assertEquals(1,textBoxPage.getNumberOfResponseItems());
        assertEquals(true, textBoxPage.getUserNameResponse().contains("Joaquin Grassi"));
    }



    /**
     * Prueba positiva:
     *      Se comprueba que se devuelva el dato correcto solo
     *      agregando el mail del usuario.
     * METODO DE PRUEBA:
     *      Se rellena solo el campo Email y se comprueba
     *      que el resultado sea solo el Email del usuario
     */
    @Test
    void onlyEmailFillFieldSuccessSubmitForm(){
        MenuPage menuPage = homePage.clickInElementsCard();
        TextBoxPage textBoxPage = new DropDownElementsPage(driver).clickInTextBox();
        textBoxPage.filluserEmailField("userEmail@example.com");
        textBoxPage.clickEnSubmit();
        assertEquals(1,textBoxPage.getNumberOfResponseItems());
        assertEquals(true,textBoxPage.getUserEmailResponse().contains("userEmail@example.com"));
    }

    /**
     * Prueba positiva:
     *      Se comprueba que se devuelva el dato correcto solo
     *      agregando la direccion actual del usuario.
     * METODO DE PRUEBA:
     *      Se rellena solo el campo CurrentAddress y se comprueba
     *      que el resultado sea solo el nombre de usuario del usuario
     */
    @Test
    public void onlyCurrentAddressFillFieldSuccessSubmit(){
        MenuPage menuPage = homePage.clickInElementsCard();
        TextBoxPage textBoxPage = new DropDownElementsPage(driver).clickInTextBox();
        textBoxPage.fillUserNameField("Avenida Siempreviva 742");
        textBoxPage.clickEnSubmit();
        assertEquals(1,textBoxPage.getNumberOfResponseItems());
        assertEquals(true, textBoxPage.getUserNameResponse().contains("Avenida Siempreviva 742"));
    }

    /**
     * Prueba positiva:
     *      Se comprueba que se devuelva el dato correcto solo
     *      agregando la direccion permanente del usuario.
     * METODO DE PRUEBA:
     *      Se rellena solo el campo PermanentAddress y se comprueba
     *      que el resultado sea solo el nombre de usuario del usuario
     */
    @Test
    public void onlyPermanentAddressFillFieldSuccessSubmit(){
        MenuPage menuPage = homePage.clickInElementsCard();
        TextBoxPage textBoxPage = new DropDownElementsPage(driver).clickInTextBox();
        textBoxPage.fillUserNameField("Calle Baker 221B");
        textBoxPage.clickEnSubmit();
        assertEquals(1,textBoxPage.getNumberOfResponseItems());
        assertEquals(true, textBoxPage.getUserNameResponse().contains("Calle Baker 221B"));
    }

    /**
     * Prueba positiva:
     *      Se comprueba que no se devuelvan datos al no agregar
     *      ningun dato de usuario.
     * METODO DE PRUEBA
     *      No se rellena ningun campo y se espera que no haya
     *      elementos en el campo output
     */
    @Test
    void notEmailFillFieldSubmitForm(){
        MenuPage menuPage = homePage.clickInElementsCard();
        TextBoxPage textBoxPage = new DropDownElementsPage(driver).clickInTextBox();
        textBoxPage.clickEnSubmit();

        assertEquals(0,textBoxPage.getNumberOfResponseItems());
    }

    /**
     * Prueba negativa:
     *      Se comprueba que se visualice el marco de error en el
     *      campo Email omitiendo la introduccion de la arroba.
     * METODO DE PRUEBA
     *      Se coloca un mail sin el caracter '@' y se espera que no haya elementos
     *      en el campo output y que el error se marque dibujando un marco rojo
     *      en el campo userEmail
     */
    @Test
    public void userEmailFieldWithoutAtSign(){
        MenuPage menuPage = homePage.clickInElementsCard();
        TextBoxPage textBoxPage = new DropDownElementsPage(driver).clickInTextBox();
        textBoxPage.filluserEmailField("userEmailexample.com");
        textBoxPage.clickEnSubmit();

        assertEquals(0,textBoxPage.getNumberOfResponseItems());
        assertEquals(true,textBoxPage.getErrorFrameInUserEmailField());
    }

    /**
     * Prueba negativa:
     *      Se comprueba que se visualice el marco de error en el
     *      campo Email omitiendo la introduccion del punto.
     * METODO DE PRUEBA:
     *      Se coloca un mail sin el caracter '.' y se espera que no haya elementos
     *      en el campo output y que el error se marque dibujando un marco rojo
     *      en el campo userEmail
     */
    @Test
    public void userEmailFieldWithOutOfPlaceDot(){
        MenuPage menuPage = homePage.clickInElementsCard();
        TextBoxPage textBoxPage = new DropDownElementsPage(driver).clickInTextBox();
        textBoxPage.filluserEmailField("userEmail@examplecom");
        textBoxPage.clickEnSubmit();

        assertEquals(0,textBoxPage.getNumberOfResponseItems());
        assertEquals(true,textBoxPage.getErrorFrameInUserEmailField());
    }

}
