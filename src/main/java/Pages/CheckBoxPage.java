package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.scrollToItem;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxPage {
    private WebDriver driver;
    private By expandAllButton = By.xpath("//*[@title=\"Expand all\"]/*");
    private By collapseAllButton = By.xpath("//*[@title=\"Collapse all\"]/*");

    private By toggleButtons = By.xpath("//*[@title=\"Toggle\"]/*");
    private By expandedToggleButtons = By.xpath("//*[@title=\"Toggle\"]/*[@class=\"rct-icon rct-icon-expand-open\"]");
    private By collapsedToggleButtons=By.xpath("//*[@title=\"Toggle\"]/*[@class=\"rct-icon rct-icon-expand-close\"]");

    private By checkedItem = By.xpath("//*[@class=\"rct-icon rct-icon-check\"]");

    private String halfCheckedItemClass = "rct-icon rct-icon-half-check";

    private String rightDirectionArrowClass ="rct-icon rct-icon-expand-close";
    private String downDirectionArrowClass ="rct-icon rct-icon-expand-open";

    private String closedFolderIconClass="rct-icon rct-icon-parent-close";
    private String openFolderIconClass="rct-icon rct-icon-parent-open";

    private By results = By.cssSelector("#result .text-success");

    public CheckBoxPage(WebDriver driver){

        this.driver=driver;
    }

    public void clickExpandAllButton(){
        driver.findElement(expandAllButton).click();
    }

    public void clickCollapseAllButton(){
        driver.findElement(collapseAllButton).click();
    }

    public List<WebElement> getToggleButtons(){
        return driver.findElements(toggleButtons);
    }

    public int getNumberOfExpandedToggleButtons(){
        return driver.findElements(expandedToggleButtons).size();
    }

    public int getNumberOfCollapsedToggleButtons(){
        return driver.findElements(collapsedToggleButtons).size();
    }

    public void clickCheckBoxItem(String itemName){
        new scrollToItem(driver,driver.findElement(By.xpath("//*[@for=\"tree-node-"+itemName+"\"]/*[@class=\"rct-checkbox\"]/*")));
        driver.findElement(By.xpath("//*[@for=\"tree-node-"+itemName+"\"]/*[@class=\"rct-checkbox\"]/*")).click();
    }

    public void clickItemToggleButton(String item){
        driver.findElement(By.xpath("//*[@for=\"tree-node-home\"]/../button/*")).click();
    }

    public List<String> getCheckedItemsText(){
        List<String> list=new ArrayList<>();
        for(WebElement res:driver.findElements(checkedItem)){
            list.add(res.findElement(By.xpath("./../../span[@class=\"rct-title\"]")).getText()
                    .replace(" ","").replace(".doc","").toLowerCase());
        }
        return list;
    }

    public List<String> getResultListText(){
        List<String> list = new ArrayList<>();
        for(WebElement result:driver.findElements(results)){
            list.add(result.getText().toLowerCase());
        }
        return list;
    }

    public Boolean getItemMinusSignByText(String item){
        var element = driver.findElement(By.xpath("//*[@for=\"tree-node-"+item+"\"]"));
        if(element.findElement(By.xpath(".//*[@class=\"rct-checkbox\"]/*")).getAttribute("class").
                equals(halfCheckedItemClass)){
            return true;
        }
        return false;
    }

    public String getArrowDirection(String item){
        var element = driver.findElement(By.xpath("//*[@for=\"tree-node-"+item+"\"]/../button/*"));
        if(element.getAttribute("class").equals(rightDirectionArrowClass)){
            return "right";
        }
        else if (element.getAttribute("class").equals(downDirectionArrowClass)){
            return "down";
        }
        else
            return "unknown";

    }


    public String getFolderIcon(String item){
        var element = driver.findElement(By.xpath("//*[@for=\"tree-node-"+item+"\"]/*[@class=\"rct-node-icon\"]/*"));
        if(element.getAttribute("class").equals(closedFolderIconClass)){
            return "blue";
        }
        else if(element.getAttribute("class").equals(openFolderIconClass)){
            return "transparent";
        }
        else
            return "unknown";
    }

    public void clickOnItemTitle(String item){
        new scrollToItem(driver,driver.findElement(By.xpath("//*[@for=\"tree-node-"+item+"\"]")));
        driver.findElement(By.xpath("//*[@for=\"tree-node-"+item+"\"]/span[@class=\"rct-title\"]")).click();
    }

    public void clickOnItemIcon(String item){
        new scrollToItem(driver,driver.findElement(By.xpath("//*[@for=\"tree-node-"+item+"\"]")));
        driver.findElement(By.xpath("//*[@for=\"tree-node-"+item+"\"]/span[@class=\"rct-node-icon\"]")).click();
    }





}
