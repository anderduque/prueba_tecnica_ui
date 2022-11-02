package Helpers;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Helpers {

    private static WebDriver driver; // Instancio un Objeto Driver para usar.
    private static WebDriverWait wait;

    public Helpers(WebDriver driver) // Metodo Constructor
    {
        this.driver = driver; //Mi Webdriver
        this.wait = new WebDriverWait(driver, seconds_WebDriverWait);
    }

    public Helpers() // Metodo Constructor
    {

    }


    /**
     * ---------------------------------------------------------------------------------------------------------------
     * METHODS HELPERS // METHODS HELPERS // METHODS HELPERS // METHODS HELPERS // METHODS HELPERS  // METHODS HELPERS
     * ---------------------------------------------------------------------------------------------------------------
     */

    ////////////////

    int seconds_WebDriverWait = 15; //Segundos de espera (Como Maximo) para WebDriverWait/PausasExplicitas

    ////////////////


    public String getXMLParameter(String Key) {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter(Key);
    }

    ////////////////

    public String GetSelectedenvironment() {
                String Selected_Environment = getXMLParameter("environmentURL");
                System.out.println("Environment URL:" + Selected_Environment);
        return Selected_Environment;
    }

    ////////////////

    public void click(By ElementToWaitAndClick) {
        WebDriverWait wait = new WebDriverWait(driver, seconds_WebDriverWait);
        WebElement WebElement;
        WebElement = wait.until(ExpectedConditions.elementToBeClickable(ElementToWaitAndClick));

        if (WebElement.isDisplayed() & WebElement.isEnabled()) {
            driver.findElement(ElementToWaitAndClick).click();
        } else {
            System.out.println("El Elemento " + WebElement.getText() + "No aparece como Displayed or Eneabled");
        }
    }

    ////////////////

    public void sendText(By ElementToWaitAndSendKeys, String text) {
        WebDriverWait wait = new WebDriverWait(driver, seconds_WebDriverWait);
        WebElement WebElement;
        WebElement = wait.until(ExpectedConditions.elementToBeClickable(ElementToWaitAndSendKeys));

        if (WebElement.isDisplayed() == true) {
            driver.findElement(ElementToWaitAndSendKeys).clear();
            driver.findElement(ElementToWaitAndSendKeys).sendKeys(text);
        } else {
            System.out.println("El Elemento " + WebElement.getText() + "No aparece como Displayed");
        }
    }

    ////////////////

    public Boolean explicitWait_PresenceOfElementDisplayed(By ElementoaEsperar) {

        WebDriverWait wait = new WebDriverWait(driver, seconds_WebDriverWait);

        if (wait.until(ExpectedConditions.presenceOfElementLocated(ElementoaEsperar)).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean elementExist(By WebElement){

        if(driver.findElements(WebElement).isEmpty()){
            System.out.println("elementExist ->False");
            return false;
        }else{
            System.out.println("elementExist ->True");
            return true;
        }

    }

    public WebElement returnWebElement(By WebElement){

        WebDriverWait wait = new WebDriverWait(driver, seconds_WebDriverWait);

        return wait.until(ExpectedConditions.presenceOfElementLocated(WebElement));

    }

    public Boolean explicitWait_visibilityOfElementLocated(By ElementoaEsperar) {
        WebDriverWait wait = new WebDriverWait(driver, seconds_WebDriverWait);
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(ElementoaEsperar)).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }


    public void navigateTOUrl(String url) {
        driver.get(url);
    }

    ////////////////

    public void SleepSeconds(int seconds) //El método Thread.sleep (long millis) hace que el subproceso actualmente en ejecución se suspenda n milisegundos.
    {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // dice qué sucedió y en qué parte del código sucedió esto.
    }

    ////////////////

    public String GetDate() {
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH.mm.ss dd-MM-yyyy");
        String valor = hourdateFormat.format(date);
        return valor;
    }

    public void TakeScreenshot(String nombre) {
        String Fecha = GetDate();
        File MyScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(MyScreenshot, new File("src/main/resources/Screenshots/" + nombre + Fecha + ".png"));
            System.out.println("Tomando Screenshot: " + nombre + " Fecha: " + Fecha);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////////////////

    public String TakeScreenshot2(String nombre) {
        String Fecha = GetDate();
        File MyScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "src/main/resources/Screenshots/" + nombre + "--" + Fecha + ".png";

        try {
            System.out.println("Tomando Screenshot: " + nombre + "-" + Fecha + ".png");
            FileUtils.copyFile(MyScreenshot, new File(path));
            SleepSeconds(5);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    ////////////////

    public String readConfigBrowserParameters(String param) {
        Properties prop = new Properties();
        String Value = "NULL";
        try {
            InputStream input = new FileInputStream("src/main/resources/browser.properties");
            prop.load(input);
            Value = prop.getProperty(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Value;
    }
    ////////////////

    public String readConfigBaseParameters(String param) {
        Properties prop = new Properties();
        String Value = "NULL";
        try {
            InputStream input = new FileInputStream("src/main/resources/base.properties");
            prop.load(input);
            Value = prop.getProperty(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Value;
    }


    ////////////////

    // Este metodo se usa para adjuntar la evidencia en el reporte Allure.
    @Attachment(value = "TEST FAIL: Screenshot Evidence", type = "image/png")
    public byte[] takeScreenshotAllureFAIL() {
        // Take a screenshot as byte array and return
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "TEST PASS: Screenshot Evidence", type = "image/png")
    public byte[] takeScreenshotAllurePASS() {
        // Take a screenshot as byte array and return
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    ////////////////

    public void scrollDownToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    /////////////////////////

    public void ClickForJS (By element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(element));
    }





}
