package Baseclass;

/**
 * -----------------------------------------------------------------------------------------------------------
 * IMPORTS // IMPORTS // IMPORTS // IMPORTS // IMPORTS // IMPORTS // IMPORTS // IMPORTS // IMPORTS // IMPORTS
 * -----------------------------------------------------------------------------------------------------------
 */

import Helpers.Helpers;
import Helpers.BrowserConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;


/**
 * -----------------------------------------------------------------------------------------------------
 * BaseClass Structure @TestNG:
 *
 *				BeforeSuite      Se ejecuta ANTES de toda la suite de test.
 * 				BeforeTest       Se ejecuta una sola vez ANTES de ejecutar todos los test de una Suite.
 * 				-------------
 * 				BeforeMethod     Se ejecuta ANTES de cada test.
 * 				TEST 1,TEST 2
 * 				AfterMethod      Se ejecuta DESPUES de cada Test.
 * 				-------------
 * 			    AfterClass       Se ejecuta luego de que se ejecuten todos los test de la clase Actual.
 * 			    -------------
 * 				AfterTest        Se ejecuta una sola vez DESPEUS de ejecutar todos los test de una Suite.
 * 				AfterSuite       Se ejecuta DESPUES de toda la suite de test.
 * -----------------------------------------------------------------------------------------------------
 */


public class BaseClass {
    public WebDriver driver;
    Helpers helpers = new Helpers();


    //Se usa para Obtener el browser configurado e instanciar el driver.
    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        BrowserConfig browserConfig = new BrowserConfig();
        this.driver = browserConfig.setUpBrowser(helpers.getXMLParameter("browser"));
    }


    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            Helpers helpers = new Helpers(driver);
            helpers.TakeScreenshot("AT FAIL------" + result.getName());
            helpers.takeScreenshotAllureFAIL();
            driver.quit();
        }
        else if (result.getStatus() == ITestResult.SKIP){
            Helpers helpers = new Helpers(driver);
            helpers.TakeScreenshot("AT SKIP------" + result.getName());
            helpers.takeScreenshotAllureFAIL();
            driver.quit();
        }
        else if (result.getStatus() == ITestResult.SUCCESS){
            Helpers helpers = new Helpers(driver);
            helpers.takeScreenshotAllurePASS();
            driver.quit();
        }

        else {
            driver.quit();
        }
    }


}
