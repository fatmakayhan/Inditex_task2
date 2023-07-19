package com.inditex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Ejercicio2 {

    public static void main(String[] args) {


        // Inicializar el navegador Chrome
        WebDriver driver = new ChromeDriver();

        try {
            // Paso 1: Buscar en Google la palabra "automatización"
            driver.get("https://www.google.com");
            //aceptar cookies
            driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div")).click();
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("automatización");
            searchBox.submit();

            // Esperar a que se carguen los resultados
            Thread.sleep(2000);

            // Paso 2: Buscar el link de la Wikipedia resultante
            WebElement wikipediaLink = driver.findElement (By.xpath("//h3[@class='LC20lb MBeuO xvfwl']"));
            wikipediaLink.click();

            // Paso 3: Comprobar en qué año se hizo el primer proceso automático

            WebElement a = driver.findElement(By.xpath("//p[contains(normalize-space(),'primer proceso')]"));

            String[]  b = a.getText().split("\\.");
            for (String eachSentence : b) {

                if(eachSentence.contains("primer proceso")){

                    Pattern pattern = Pattern.compile("\\b\\d{4}\\b");
                    Matcher matcher = pattern.matcher(eachSentence);

                    while (matcher.find()) {
                        String text = matcher.group();
                        int year = Integer.parseInt(text);
                        System.out.println("El primer proceso automático se hizo en el año: " + year);

                    }
                }
            }


            // Paso 4: Realizar un screenshot de la página de Wikipedia
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);

            // Guardar el screenshot en un archivo
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenshotPath = "target/screenshot.png";
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
            System.out.println("Screenshot guardado en: " + screenshotPath);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el navegador
            driver.quit();
        }
    }
}

