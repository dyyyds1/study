package org.example.wetalk;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InitAndEnd {
    static WebDriver webDriver;
    static WebDriverWait wait;
    static WebDriver user1Driver;
    static WebDriver user2Driver;
    static WebDriverWait user1Wait;
    static WebDriverWait user2Wait;
//    @BeforeAll
//    static void setUp(){
//        webDriver =new ChromeDriver();
//        wait = new WebDriverWait(webDriver, 10); // 设置最长等待时间为10秒
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito"); // 打开无痕模式
//
//        user1Driver = new ChromeDriver(options);
//        user2Driver = new ChromeDriver(options);
//
//        user1Wait = new WebDriverWait(user1Driver, 10);
//        user2Wait = new WebDriverWait(user2Driver, 10);
//    }

    static ChromeDriver getWebDriver() {
        if (webDriver == null) {
            webDriver = new ChromeDriver();
            wait = new WebDriverWait(webDriver, 10);
        }
        return (ChromeDriver) webDriver;
    }

    static ChromeDriver getUser1Driver() {
        if (user1Driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            user1Driver = new ChromeDriver(options);
            user1Wait = new WebDriverWait(user1Driver, 10);
        }
        return (ChromeDriver) user1Driver;
    }

    static ChromeDriver getUser2Driver() {
        if (user2Driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            user2Driver = new ChromeDriver(options);
            user2Wait = new WebDriverWait(user2Driver, 10);
        }
        return (ChromeDriver) user2Driver;
    }

    @AfterAll
    static void TearDown(){

        if (webDriver!=null){
            webDriver.quit();
        }
        if (user1Driver != null) {
            user1Driver.quit();
        }
        if (user2Driver != null) {
            user2Driver.quit();
        }
    }
}
