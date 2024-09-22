package com.cybertek.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

// page_url = https://www.amazon.com/
public class ExamplePage {
    @FindBy(css = "[data-csa-c-type = 'link']")
    public WebElement link;

    @FindBy(xpath = "//a[@data-csa-c-slot-id=\"nav_cs_1\"]")
    public WebElement link2;


    public ExamplePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}