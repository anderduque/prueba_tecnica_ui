package Tests.Suite_Automation;

import Baseclass.BaseClass;
import Helpers.RetryAnalyzer;
import Pages.Automation.Page_Wikipedia;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Automation_Tests extends BaseClass {

    String ClassName = "[" + getClass().getName() + "]: "; //Se usa para ordenar el reporte.


    //Descripcion: Valida el correcto ingreso a google
    @Test(groups = { "regression" }, description = "Valido Ingreso Correcto a Google",retryAnalyzer = RetryAnalyzer.class, priority = 1 )
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test-01 Ingreso correcto a Google")
    public void test_SuccessfulLogin() {
        Page_Wikipedia page_wikipedia = new Page_Wikipedia(driver);
        page_wikipedia.navigateToGoogle();
        Assert.assertTrue(page_wikipedia.ValidatePageObject(),"No se pudo ingresar correctamente a Google");
    }

    //Descripcion: Validar busqueda en google "Automatizacion"
    @Test(groups = { "regression" }, description = "Validar busqueda en google",retryAnalyzer = RetryAnalyzer.class, priority = 1 )
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test-02 Busqueda en Google")
    public void test_BusquedaToGoogle() {
        Page_Wikipedia page_wikipedia = new Page_Wikipedia(driver);
        page_wikipedia.navigateToGoogle();
        Assert.assertTrue(page_wikipedia.ValidatePageObject(),"No se pudo ingresar correctamente a Google");
        page_wikipedia.ingresoBusqueda();
        Assert.assertTrue(page_wikipedia.busquedaAutomatizacion(),"La busqueda no es correcta");
    }

    //Descripcion: Valida el correcto ingreso a la busqueda de Wikipedia
    @Test(groups = { "regression" }, description = "Valido Ingreso Correcto a Wikipedia",retryAnalyzer = RetryAnalyzer.class, priority = 1 )
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test-01 Ingreso correcto a Wikipedia")
    public void test_IngresoWikipedia() {
        Page_Wikipedia page_wikipedia = new Page_Wikipedia(driver);
        page_wikipedia.navigateToGoogle();
        Assert.assertTrue(page_wikipedia.ValidatePageObject(),"No se pudo ingresar correctamente a Google");
        page_wikipedia.ingresoBusqueda();
        Assert.assertTrue(page_wikipedia.busquedaAutomatizacion(),"La busqueda no es correcta");
        page_wikipedia.ingresoWikipedia();
        Assert.assertTrue(page_wikipedia.automatizacionWiki(),"El ingreso a wikipedia no fue correcto");
    }

}
