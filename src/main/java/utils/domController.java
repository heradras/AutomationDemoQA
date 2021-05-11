package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Clase abstracta de metodos utiles referentes a la interaccion con las paginas
 */

public abstract class domController {
    /**
     * Metodo que permite poner elementos en la zona visualizada.
     * Es util cuando no se logra interactuar con un elemento, particularmente
     * por la presencia del footer de la pagina.
     *
     * @param driver El driver del navegador.
     * @param webElement El elemento que se desea traer a la zona de visualizacion.
     */
    public static void scrollToItem(WebDriver driver, WebElement webElement){
        String script = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor)driver).executeScript(script,webElement);
    }
}
