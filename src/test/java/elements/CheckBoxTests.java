package elements;

import Pages.CheckBoxPage;
import Pages.DropDownElementsPage;
import Pages.MenuPage;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * pruebas:
 * X 1-Los botones expandir y contraer abren y cierran completamente el arbol respectivamente
 * X 2-seleccionar un padre selecciona automaticamente a todos los hijos y nietos aunque algunos ya esten seleccionados
 * X 3-seleccionar todos los hijos selecciona al padre
 * X 4-seleccionar un n (con n>0) de hijos marca un "-" en el padre
 * X 5-deseleccionar un padre deselecciona todos los hijos y nietos
 * X 6-un padre expandido cambia la direccion de la flecha de derecha a abajo
 * X 7-un padre contraido cambia la direccion de la flecha de abajo a derecha
 * X 8-un padre expandido cambia el color del icono carpeta de azul a blanco
 * X 9-un padre contraido cambia el color del icono carpeta de blanco a azul
 * X 10-clickear cualquier padre de un item lo marca como checkeado
 */
public class CheckBoxTests extends BaseTest {
    /**
     * Prueba positiva:
     *      Comprueba que al presionar el boton para expandir todo el arbol de checkbox
     *      todos los elementos toggle se abran.
     * METODO DE PRUEBA:
     *      Se presiona el boton para expandir todos los items y se le pide
     *      al DOM que devuelva todos los elementos toggle existentes, los cuales
     *      en esta caso son 17. Luego se comprueba que todos los toggles obtenidos
     *      son de la clase determinante de un toggle expandido.
     *      Por ultimo, se verifica que no existan en el dom toggles contraidos.
     */
    @Test
    public void clickOnExpandAllButtonTest(){
        MenuPage menuPage = homePage.clickInElementsCard();
        CheckBoxPage checkBoxPage = new DropDownElementsPage(driver).clickInCheckBox();
        checkBoxPage.clickExpandAllButton();

        List<WebElement> toggleButtons=checkBoxPage.getToggleButtons();

        for(WebElement toggle:toggleButtons){
            assertEquals(true,toggle.getAttribute("class").equals("rct-icon rct-icon-expand-open"));
        }

            assertEquals(0,checkBoxPage.getNumberOfCollapsedToggleButtons());
    }

    /**
     * Prueba positiva:
     *      Se comprueba que al presionar el boton para contraer todo el arbol de checkbox
     *      todos los elementos toggle se cierren.
     * METODO DE PRUEBA:
     *      Antes se presiona el boton para abrirlos todos y luego se presiona
     *      el boton para contraerlos. Luego se le pide al DOM que devuelva la lista
     *      de elementos contraidos y se constata que el unico existente es el perteneciente
     *      al item Home (padre absoluto, el cual no puede desaparecer).
     *      Se verifica que este ultimo toggle este contraido y luego, para asegurarse,
     *      se verifica tambien que no existan toggles expandidos.
     */

    @Test
    public void clickOnCollapseAllButtonTest(){
        MenuPage menuPage = homePage.clickInElementsCard();
        CheckBoxPage checkBoxPage = new DropDownElementsPage(driver).clickInCheckBox();
        checkBoxPage.clickExpandAllButton();
        checkBoxPage.clickCollapseAllButton();

        List<WebElement> toggleButtons=checkBoxPage.getToggleButtons();
        //Comprueba que solo exista un elemento toggle en el dom (el
        // perteneciente al item Home)
        assertEquals(1,toggleButtons.size());

        //  Comprueba que todos los toggles esten contraidos
        for(WebElement toggle:toggleButtons){
            assertEquals(true,toggle.getAttribute("class").equals("rct-icon rct-icon-expand-close"));
        }

        //comprueba que no haya ningun toggle expandido
        assertEquals(0,checkBoxPage.getNumberOfExpandedToggleButtons());
    }

    /**
     * Prueba positiva:
     *      Comprueba que seleccionar a un padre selecciona a todos los hijos.
     * METODO DE PRUEBA:
     *      Se consulta al DOM sobre los objetos que actualmente tienen
     * una tilde de seleccion y se compara con los elementos devueltos en el
     * area de result del DOM. Para esta prueba solo pueden seleccionarse elementos
     * que sean padres, a saber "home", "desktop", "documents", "workspace", "office" y "downloads".
     */
    @Test
    public void testSelectAllChildsBySelectingANotSelectedParent(){
        MenuPage menuPage = homePage.clickInElementsCard();
        CheckBoxPage checkBoxPage = new DropDownElementsPage(driver).clickInCheckBox();
        checkBoxPage.clickExpandAllButton();
        String item = "downloads";
        checkBoxPage.clickCheckBoxItem(item);

        List<String> result = checkBoxPage.getResultListText();
        List<String> itemText = checkBoxPage.getCheckedItemsText();
        Collections.sort(result);
        Collections.sort(itemText);
        assertEquals(result,itemText);

    }

    /**
     * Prueba positiva:
     *      Comprueba que seleccionar a todos los hijos seleccione al padre.
     * METODO DE PRUEBA:
     *      Se seleccionan todos los hijos de un padre y se comprueba que
     *      el item padre se marque con una tilde. Las pruebas se llevan
     *      a cabo en todos los items hijos (items file) y, por ultimo, se
     *      verifica que el item padre absoluto (item home) tambien se marque
     *      como checked.
     */
    @Test
    public void testParentSelectedBySelectinAllChilds(){
        MenuPage menuPage = homePage.clickInElementsCard();
        CheckBoxPage checkBoxPage = new DropDownElementsPage(driver).clickInCheckBox();
        checkBoxPage.clickExpandAllButton();

        String childItem="notes";
        String parentItem="desktop";
        checkBoxPage.clickCheckBoxItem("notes");
        childItem="commands";
        checkBoxPage.clickCheckBoxItem("commands");
        assertEquals(true, checkBoxPage.getResultListText().contains("desktop"));

        checkBoxPage.clickCheckBoxItem("react");
        checkBoxPage.clickCheckBoxItem("angular");
        checkBoxPage.clickCheckBoxItem("veu");
        assertEquals(true, checkBoxPage.getResultListText().contains("workspace"));
        checkBoxPage.clickCheckBoxItem("public");
        checkBoxPage.clickCheckBoxItem("private");
        checkBoxPage.clickCheckBoxItem("classified");
        checkBoxPage.clickCheckBoxItem("general");
        assertEquals(true, checkBoxPage.getResultListText().contains("office"));
        assertEquals(true, checkBoxPage.getResultListText().contains("documents"));

        checkBoxPage.clickCheckBoxItem("wordFile");
        checkBoxPage.clickCheckBoxItem("excelFile");

        assertEquals(true, checkBoxPage.getResultListText().contains("downloads"));
        assertEquals(true,checkBoxPage.getResultListText().contains("home"));

    }

    /**
     * Prueba positiva:
     *      Comprueba que al seleccionar una cantidad de hijos menor a la cantidad total,
     *      el padre se marca con un "-".
     * METODO DE PRUEBA:
     *      Se selecciona un numero menor a la cantidad total de items hijos
     *      y se verifica que el/los items padres se marquen con un signo "-".
     *      Las pruebas se llevan a cabo en todos los items hijos (items file)
     *      y por ultimo, se verifica que el item padre absoluto (item home)
     *      tambien se marque con el signo "-".
     *
     */
    @Test
    public void testChildSelectionMarksMinusSignInParent(){
        MenuPage menuPage = homePage.clickInElementsCard();
        CheckBoxPage checkBoxPage = new DropDownElementsPage(driver).clickInCheckBox();
        checkBoxPage.clickExpandAllButton();

        String childItem="notes";
        String parentItem="desktop";
        checkBoxPage.clickCheckBoxItem(childItem);
        assertEquals(true, checkBoxPage.getItemMinusSignByText(parentItem));

        childItem="react";
        parentItem="workspace";
        checkBoxPage.clickCheckBoxItem(childItem);
        assertEquals(true,checkBoxPage.getItemMinusSignByText(parentItem));
        parentItem="documents";
        assertEquals(true,checkBoxPage.getItemMinusSignByText(parentItem));

        childItem="public";
        parentItem="office";
        checkBoxPage.clickCheckBoxItem(childItem);
        assertEquals(true,checkBoxPage.getItemMinusSignByText(parentItem));

        childItem="wordFile";
        parentItem="downloads";
        checkBoxPage.clickCheckBoxItem(childItem);
        assertEquals(true,checkBoxPage.getItemMinusSignByText(parentItem));
        parentItem="home";
        assertEquals(true, checkBoxPage.getItemMinusSignByText(parentItem));

    }

    /**
     * Prueba positiva:
     *      Comprueba que al deseleccionar un padre, todos los hijos se deseleccionan.
     *
     * METODO DE PRUEBA:
     *      Se selecciona el item Home, el cual es padre de todos los items y
     *      luego se lo deselecciona, con lo cual sus 3 hijos se deseleccionaran tambien
     *      y por lo tanto, deseleccionaran a todos sus respectivos hijos. Este comportamiento
     *      entre padres e hijos se da automaticamente en forma recursiva.
     *      Se comprueba entonces que no haya elementos seleccionados en base a obtener
     *      el tamaño de la lista de strings correspondiente a los nombres de los items
     *      que se encuentren seleccionados.
     *      La prueba debe devolver 0, ya que no se encuentran en el arbol elementos
     *      seleccionados.
     */
    @Test
    public void testDeselectAllChildsByDeselectingASelectedParent(){
        MenuPage menuPage = homePage.clickInElementsCard();
        CheckBoxPage checkBoxPage = new DropDownElementsPage(driver).clickInCheckBox();
        checkBoxPage.clickExpandAllButton();

        checkBoxPage.clickCheckBoxItem("home");
        checkBoxPage.clickCheckBoxItem("home");

        assertEquals(0,checkBoxPage.getCheckedItemsText().size());

    }

    /**
     * Prueba positiva:
     *      Comprueba que un item padre extendido cambia la direccion de la flecha
     *      indicadora de contraccion y expansion de derecha a abajo y su icono
     *      de carpeta de azul a transparente y viceversa.
     *
     * METODO DE PRUEBA:
     *      Se expande el padre item y se comprueba que su flecha ahora apunta
     *      hacia abajo y que su icono de carpeta es ahora transparente.
     *      Luego se lo vuelve a contraer y se verifica que su flecha apunta ahora
     *      hacia la derecha y que su icono de carpeta es azul.
     */
    @Test
    public void testArrowDirection(){
        MenuPage menuPage = homePage.clickInElementsCard();
        CheckBoxPage checkBoxPage = new DropDownElementsPage(driver).clickInCheckBox();

        checkBoxPage.clickItemToggleButton("home");
        assertEquals("down",checkBoxPage.getArrowDirection("home"));
        assertEquals("transparent",checkBoxPage.getFolderIcon("home"));

        checkBoxPage.clickItemToggleButton("home");
        assertEquals("right",checkBoxPage.getArrowDirection("home"));
        assertEquals("blue",checkBoxPage.getFolderIcon("home"));
    }

    /**
     * Prueba positiva:
     *      Comprueba que al clickear en cualquier parte del item, el mismo se marca
     *      como checkeado.
     * METODO DE PRUEBA:
     *      Se clickea sobre el rectangulo que contiene al titulo del item y
     *      se verifica que en la lista devuelta de items marcados como checked
     *      se encuentre el perteneciente al item.
     */
    @Test
    public void checkItemByClickingAnywhere(){
        MenuPage menuPage = homePage.clickInElementsCard();
        CheckBoxPage checkBoxPage = new DropDownElementsPage(driver).clickInCheckBox();
        checkBoxPage.clickExpandAllButton();
        String item="excelFile";

        checkBoxPage.clickOnItemTitle(item);

        assertEquals(true, checkBoxPage.getCheckedItemsText().contains(item.toLowerCase()));
        checkBoxPage.clickOnItemTitle(item);
        checkBoxPage.clickOnItemIcon(item);

        assertEquals(true, checkBoxPage.getCheckedItemsText().contains(item.toLowerCase()));
    }

}
