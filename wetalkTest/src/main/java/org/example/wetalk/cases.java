package org.example.wetalk;

import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class cases extends InitAndEnd{

    //输入正确的账号，密码，登陆成功
    @ParameterizedTest
    @CsvFileSource(resources = "LoginSuccess.csv")
    void LoginSuccess(String username,String password,String wetalk_url){
        //打开聊天室登录界面
        webDriver=InitAndEnd.getWebDriver();
        webDriver.get("http://43.139.243.98:8080/login.html");
        //输入账号admin
        webDriver.findElement(By.cssSelector("#loginUsername")).sendKeys(username);
        //输入密码123
        webDriver.findElement(By.cssSelector("#loginPassword")).sendKeys(password);
        //点击提交按钮
        webDriver.findElement(By.cssSelector("#formHolder > div > div.col-sm-6.form > div.login.form-peice > form > div.CTA > input[type=submit]")).click();
        //跳转到主页
        // 使用等待条件等待弹窗出现
        wait.until(ExpectedConditions.alertIsPresent());
        // 获取弹窗并获取弹窗的文本
        Alert alert = webDriver.switchTo().alert();
        String alertText = alert.getText();
        // 断言弹窗的文本
        assertEquals("登录成功!", alertText);
        // 接受弹窗（按下确定键）
        alert.accept();
        // 使用等待条件等待页面跳转
        wait.until(ExpectedConditions.urlToBe("http://43.139.243.98:8080/client.html"));
        //获取当前的url
        String cur_url=webDriver.getCurrentUrl();
        //如果url是这个，登录通过http://43.139.243.98:8080/client.html
        assertEquals("http://43.139.243.98:8080/client.html",cur_url);
        // 等待用户名元素的出现
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div.client-container > div > div.left > div.user")));
        String cur_username=webDriver.findElement(By.cssSelector("body > div.client-container > div > div.left > div.user")).getText();
        assertEquals(username,cur_username);
    }


    //输入正确的账号，密码，登陆失败
    @ParameterizedTest
    @CsvFileSource(resources = "LoginFail.csv")
    void LoginFail(String username,String password,String wetalk_url){
        //打开聊天室登录界面
        webDriver=InitAndEnd.getWebDriver();
        webDriver.get("http://43.139.243.98:8080/login.html");
        //输入账号admin
        webDriver.findElement(By.cssSelector("#loginUsername")).sendKeys(username);
        //输入密码123
        webDriver.findElement(By.cssSelector("#loginPassword")).sendKeys(password);
        //点击提交按钮
        webDriver.findElement(By.cssSelector("#formHolder > div > div.col-sm-6.form > div.login.form-peice > form > div.CTA > input[type=submit]")).click();
        //跳转到主页
        // 使用等待条件等待弹窗出现
        wait.until(ExpectedConditions.alertIsPresent());
        // 获取弹窗并获取弹窗的文本
        Alert alert = webDriver.switchTo().alert();
        String alertText = alert.getText();
        // 断言弹窗的文本
        assertEquals("登录失败!", alertText);
        // 接受弹窗（按下确定键）
        alert.accept();
        // 使用等待条件等待页面跳转
        wait.until(ExpectedConditions.urlToBe("http://43.139.243.98:8080/login.html"));
        //获取当前的url
        String cur_url=webDriver.getCurrentUrl();
        //如果url是这个，登录失败http://43.139.243.98:8080/login.html
        assertEquals("http://43.139.243.98:8080/login.html",cur_url);
    }
    private void loginU1U2(){
        user1Driver=InitAndEnd.getUser1Driver();
        user2Driver=InitAndEnd.getUser2Driver();
        //测试聊天逻辑
        //user1登录
        user1Driver.get("http://43.139.243.98:8080/login.html");
        user1Driver.findElement(By.cssSelector("#loginUsername")).sendKeys("admin");
        user1Driver.findElement(By.cssSelector("#loginPassword")).sendKeys("123");
        user1Driver.findElement(By.cssSelector("#formHolder > div > div.col-sm-6.form > div.login.form-peice > form > div.CTA > input[type=submit]")).click();
        user1Wait.until(ExpectedConditions.alertIsPresent());
        Alert alert1 = user1Driver.switchTo().alert();
        String alertText1 = alert1.getText();
        assertEquals("登录成功!", alertText1);
        alert1.accept();
        user1Wait.until(ExpectedConditions.urlToBe("http://43.139.243.98:8080/client.html"));
        //获取当前的url
        String cur_url1=user1Driver.getCurrentUrl();
        //如果url是这个，登录通过http://43.139.243.98:8080/client.html
        assertEquals("http://43.139.243.98:8080/client.html",cur_url1);

        //user2登录
        user2Driver.get("http://43.139.243.98:8080/login.html");
        user2Driver.findElement(By.cssSelector("#loginUsername")).sendKeys("abc");
        user2Driver.findElement(By.cssSelector("#loginPassword")).sendKeys("123");
        user2Driver.findElement(By.cssSelector("#formHolder > div > div.col-sm-6.form > div.login.form-peice > form > div.CTA > input[type=submit]")).click();
        user2Wait.until(ExpectedConditions.alertIsPresent());
        Alert alert2 = user2Driver.switchTo().alert();
        String alertText2 = alert2.getText();
        assertEquals("登录成功!", alertText2);
        alert2.accept();
        user2Wait.until(ExpectedConditions.urlToBe("http://43.139.243.98:8080/client.html"));
        String cur_url2=user2Driver.getCurrentUrl();
        //如果url是这个，登录通过http://43.139.243.98:8080/client.html
        assertEquals("http://43.139.243.98:8080/client.html",cur_url2);
    }
    @Test
    public void testChat() {
        loginU1U2();
        // user1等待获取好友列表标签元素的出现
        user1Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#getFriend")));
        user1Driver.findElement(By.cssSelector("#getFriend")).click();
        user1Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='friend-list']/li[text()='abc']")));
        WebElement friendElement = user1Driver.findElement(By.xpath("//ul[@id='friend-list']/li[text()='abc']"));
        friendElement.click();
        user1Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#right > div.title")));
        friendElement=user1Driver.findElement(By.cssSelector("#right > div.title"));
        String friendName=friendElement.getText();
        Assertions.assertEquals("abc",friendName);
        user1Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#right > textarea")));
        user1Driver.findElement(By.cssSelector("#right > textarea")).sendKeys("聊天测试 testChat");
        user1Driver.findElement(By.cssSelector("#right > div.ctrl > button")).click();
        user2Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#session-list > li > span")));
        WebElement noReadElement=user2Driver.findElement(By.cssSelector("#session-list > li > span"));
        String noReadCount=noReadElement.getText();
        Assertions.assertEquals("1",noReadCount);
        user2Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"session-list\"]/li/h3")));
        user2Driver.findElement(By.xpath("//*[@id=\"session-list\"]/li/h3[text()='admin']"));
        user2Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"session-list\"]/li/p")));
        WebElement message=user2Driver.findElement(By.xpath("//*[@id=\"session-list\"]/li/p"));
        String messageText = message.getText();
        Assertions.assertTrue(messageText.contains("聊天测试"), "Message does not contain '聊天测试'");
        message.click();

    }

    @Test
    void searchUsers(){
        LoginSuccess("admin","123","http://43.139.243.98:8080/login.html");
        //获取搜索框并进行搜索目标用户abc，然后点击搜索按钮
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#searchInput")));
        webDriver.findElement(By.cssSelector("#searchInput")).sendKeys("abc");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#searchButton")));
        webDriver.findElement(By.cssSelector("#searchButton")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#Users")));
        List<WebElement> users=webDriver.findElements(By.cssSelector("#Users > li"));
        boolean flag=false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i)!=null&&users.get(i).findElement(By.cssSelector(".searchUserLi .nameDiv")).getText().equals("abc")){
                flag=true;
                break;
            }
        }
        Assertions.assertTrue(flag);
    }
    @Test
    void testAddFriend() throws InterruptedException {
        loginU1U2();
        //获取搜索框并进行搜索目标用户abc，然后点击搜索按钮
        user1Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#searchInput")));
        user1Driver.findElement(By.cssSelector("#searchInput")).sendKeys("abc");
        user1Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#searchButton")));
        user1Driver.findElement(By.cssSelector("#searchButton")).click();
        user1Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#Users")));
        List<WebElement> users=user1Driver.findElements(By.cssSelector("#Users > li"));
        boolean flag=false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i)!=null&&users.get(i).findElement(By.cssSelector(".searchUserLi .nameDiv")).getText().equals("abc")){
                users.get(i).findElement(By.cssSelector(".searchUserLi .addDiv")).click();
                flag=true;
                break;
            }
        }
        Assertions.assertTrue(flag);
        sleep(3000);
        user2Driver.findElement(By.cssSelector("#tab-apply")).click();
        user2Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#Appliers > li")));
        List<WebElement> appliers=user2Driver.findElements(By.cssSelector("#Appliers > li"));
        user2Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#Appliers > li > div.agreeTab")));
        boolean flag_applier=false;
        for (int i = 0; i < appliers.size(); i++) {
            if (appliers.get(i)!=null&&appliers.get(i).findElement(By.cssSelector(".applierLi .applyNameDiv")).getText().equals("admin")){
                appliers.get(i).findElement(By.cssSelector(".applierLi .agreeTab")).click();
                flag_applier=true;
                break;
            }
        }
        Assertions.assertTrue(flag_applier);
        user2Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#session-list > li")));
        List<WebElement> messageList=user2Driver.findElements(By.cssSelector("#session-list > li"));
        boolean flag_message=false;
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i)!=null&&messageList.get(i).findElement(By.cssSelector("li h3")).getText().equals("admin")){
                String message=messageList.get(i).findElement(By.cssSelector("li p")).getText();

                Assertions.assertTrue(message.contains("我们已经是好友啦"), "The main string does not contain the expected substring");
                flag_message=true;
            }
        }
        Assertions.assertTrue(flag_message);
    }

    @Test
    void testDelFriend(){
        //先登录双方的账号
        loginU1U2();
        //等待会话列表的加载
        user1Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#session-list")));
        //获取所有会话列表的记录
        List<WebElement> list=user1Driver.findElements(By.cssSelector("#session-list > li"));
        int i=0,j=0;
        //找到目标联系人abc
        for (;i<list.size();i++){
            if (list.get(i).findElement(By.cssSelector("h3")).getText().equals("abc")){
                break;
            }
        }
        //点击联系人
        list.get(i).click();
        user1Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#right > div.title > div")));
        WebElement divMoreElement =user1Driver.findElement(By.cssSelector("#right > div.title > div"));
        //点击更多
        divMoreElement.click();
        //等待出现的下拉框
        user1Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#Dropdown")));
        String displayValue = divMoreElement.getCssValue("display");
        //判断下拉框是否显示，不显示说明有问题
        Assertions.assertEquals("block", displayValue, "moreDiv is not displayed as block");
        //点击删除好友选项
        user1Driver.findElement(By.cssSelector("#Dropdown > div.deleteFriend")).click();
        //再次获取聊天会话列表
        user1Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#session-list .selected")));
        list=user1Driver.findElements(By.cssSelector("#session-list > li"));
        //判断是否存在abc的聊天，不存在说明删除成功
        boolean flag=false;
        for (;j<list.size();j++){
            if (list.get(j)!=null&&list.get(j).findElement(By.cssSelector("h3")).getText().equals("abc")){
                flag=true;
                break;
            }
        }
        Assertions.assertFalse(flag);
        //获取并点击获取好友列表标签
        user1Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#getFriend")));
        user1Driver.findElement(By.cssSelector("#getFriend")).click();
        //获取所有的好友
        user1Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#friend-list")));
        List<WebElement> firendList=user1Driver.findElements(By.cssSelector("#friend-list > li"));
        int k = 0;
        for (; k < firendList.size(); k++) {
            if (firendList.get(k).getText().equals("abc")){
                break;
            }
        }
        //如果为最大索引，说明找不到了，删除了
        Assertions.assertEquals(firendList.size(),k);
        user2Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#session-list")));
        //获取所有会话列表的记录
        List<WebElement> list2=user2Driver.findElements(By.cssSelector("#session-list > li"));
        //找到目标联系人abc
        int w=0;
        for (;w<list2.size();w++){
            if (list2.get(w).findElement(By.cssSelector("h3")).getText().equals("admin")){
                break;
            }
        }
        list2.get(w).click();
        user2Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#right > textarea")));
        user2Driver.findElement(By.cssSelector("#right > textarea")).sendKeys("删除测试");
        user2Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#right > div.ctrl > button")));
        user2Driver.findElement(By.cssSelector("#right > div.ctrl > button")).click();
//        你只能向好友发送消息！
        //等待弹窗出现
        Alert alert = user2Wait.until(ExpectedConditions.alertIsPresent());
        // 获取弹窗文本
        String alertText = alert.getText();
        Assertions.assertEquals("你只能向好友发送消息！",alertText);
    }

    @Test
    void testDelChat(){
        LoginSuccess("admin","123","http://43.139.243.98:8080/login.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#session-list")));
        //获取所有会话列表的记录
        List<WebElement> list=webDriver.findElements(By.cssSelector("#session-list > li"));
        int i=0,j=0;
        //找到目标联系人abc
        for (;i<list.size();i++){
            if (list.get(i).findElement(By.cssSelector("h3")).getText().equals("abc")){
                break;
            }
        }
        //点击联系人
        list.get(i).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#right > div.title > div")));
        WebElement divMoreElement =webDriver.findElement(By.cssSelector("#right > div.title > div"));
        //点击更多
        divMoreElement.click();
        //等待出现的下拉框
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#Dropdown")));
        String displayValue = divMoreElement.getCssValue("display");
        //判断下拉框是否显示，不显示说明有问题
        Assertions.assertEquals("block", displayValue, "moreDiv is not displayed as block");
        //点击删除会话选项
        webDriver.findElement(By.cssSelector("#Dropdown > div.deleteChat")).click();
        //再次获取聊天会话列表
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#session-list .selected")));
        list=webDriver.findElements(By.cssSelector("#session-list > li"));
        //判断是否存在abc的聊天，不存在说明删除成功
        boolean flag=false;
        for (;j<list.size();j++){
            if (list.get(j)!=null&&list.get(j).findElement(By.cssSelector("h3")).getText().equals("abc")){
                flag=true;
                break;
            }
        }
        Assertions.assertFalse(flag);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#getFriend")));
        webDriver.findElement(By.cssSelector("#getFriend")).click();
        //获取所有的好友
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#friend-list")));
        List<WebElement> firendList=webDriver.findElements(By.cssSelector("#friend-list > li"));
        boolean flag_deleteChat=false;
        //如果找到就为true
        for (int k = 0; k < firendList.size(); k++) {
            if (firendList.get(k)!=null&&firendList.get(k).getText().equals("abc")){
                flag_deleteChat=true;
                break;
            }
        }
        //判断是否找到
        Assertions.assertTrue(flag_deleteChat);

    }


}
