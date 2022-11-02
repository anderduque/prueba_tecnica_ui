package Pages.Automation;

import Helpers.Helpers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Page_Wikipedia {

    public WebDriver driver; //Declaro Objeto Driver.
    public Helpers helpers;

    public Page_Wikipedia(WebDriver driver){  //Metodo Constructor de la Clase.
        this.driver = driver;
        helpers = new Helpers(driver);
    }

    /**
     * ---------------------------------------------------------------------------------------------------------
     *  WebElements // WebElements // WebElements // WebElements // WebElements // WebElements // WebElements //
     * ---------------------------------------------------------------------------------------------------------
     */

    public By imputBuscar = By.xpath("//input[@name='q']");
    public By btnBuscarGoogle = By.xpath("(//input[contains(@name,'btnK')])[1]");
    public By h3AutomatizacionInsutrial = By.xpath("//h3[contains(.,'Automatización industrial - Wikipedia, la enciclopedia libre')]");
    public By automatizacionIndustrial = By.xpath("(//cite[contains(.,'https://es.wikipedia.org › wiki › Automatización_indust...')])[1]");
    public By ingresoCorrectoWikipedia = By.xpath("//span[@class='mw-page-title-main'][contains(.,'Automatización industrial')]");
    /**
     * -----------------------------------------------------------------------------------------------------------
     * Funciones // Funciones //  Funciones //  Funciones //  Funciones //  Funciones //  Funciones //  Funciones
     * -----------------------------------------------------------------------------------------------------------
     */

    @Step("Page_Wikipedia: Navego a la Url")
    public void navigateToGoogle() {
        helpers.navigateTOUrl(helpers.GetSelectedenvironment());
        helpers.TakeScreenshot2("IngresoCorrectoGoogle");
    }


    @Step("Page_Wikipedia: Valido ingreso correcto a Google")
    public boolean ValidatePageObject() {
        System.out.println("Page_Wikipedia: Inicio correcto a goggle");
        return helpers.explicitWait_PresenceOfElementDisplayed(imputBuscar);
    }

    @Step("Page_Wikipedia: Busqueda de concepto de Automatizacion ")
    public void ingresoBusqueda(){
        helpers.sendText(imputBuscar,"Automatización");
        helpers.click(btnBuscarGoogle);
    }

    @Step("Page_Wikipedia: Validar Busqueda correcta de Automatizacion")
    public boolean busquedaAutomatizacion (){
        try{
            helpers.explicitWait_visibilityOfElementLocated(h3AutomatizacionInsutrial);
            helpers.TakeScreenshot2("BusquedaAutomatizacionGoogle");
        return true;
        } catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

    @Step("Page_Wikipedia: Ingresar a wikipedia")
    public void ingresoWikipedia (){
       helpers.scrollDownToElement(driver.findElement(h3AutomatizacionInsutrial));
       helpers.TakeScreenshot2("IngresoWikipedia");
       helpers.ClickForJS(automatizacionIndustrial);
    }

    @Step("Page_Wikipedia: Validar ingreso correcto a wikipedia")
    public boolean automatizacionWiki(){
        try {
            helpers.explicitWait_PresenceOfElementDisplayed(ingresoCorrectoWikipedia);
            helpers.TakeScreenshot2("ValidacionBusquedaCorrecta");
            return true;
        }catch (Exception e){
            System.err.println(e);
            return false;
        }
    }
}
