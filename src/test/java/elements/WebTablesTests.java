package elements;

import Pages.DropDownElementsPage;
import Pages.MenuPage;
import base.BaseTest;
import org.junit.jupiter.api.Test;

/**
 * Pruebas:
 *  -las busquedas se efectuan en todos los campos del
 */

public class WebTablesTests extends BaseTest {

    @Test
    public void prueba(){
        MenuPage menuPage = homePage.clickInElementsCard();
        var webTablesPage = new DropDownElementsPage(driver).clickInWebTables();
        for(int i=0;i<10;i++){
            webTablesPage.inputGenerator("Joaquin","Grassi","28","joaquin.grassi92@gmail.com",
                    "120000","QA Automation");
        }

    }

}
