package com.cybertek.officeHours.Practice_09_29_2021.adidasTask;

import com.cybertek.pages.AdidasPage;
import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.ConfigReader;
import com.cybertek.utility.Driver;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AdidasWithPOM extends TestBase {


    @Test
    public void adidas(){

    //SHOP: https://www.demoblaze.com/index.html
        Driver.getDriver().get(ConfigReader.read("adidas"));

        AdidasPage adidasPage=new AdidasPage();


        int expectedPrice=0;
    //• Customer navigation through product categories: Phones, Laptops and Monitors
    //• Navigate to "Laptops" → "Sony vaio i5" and click on "Add to cart". Accept pop up confirmation.
        expectedPrice+=adidasPage.addProduct("Laptops","Sony vaio i5");
        System.out.println("expectedPrice = " + expectedPrice);


        //• Navigate to "Phones" → "Samsung galaxy s6" and click on "Add to cart". Accept pop up confirmation.
        expectedPrice+=adidasPage.addProduct("Phones","Samsung galaxy s6");
        System.out.println("expectedPrice = " + expectedPrice);

        //• Navigate to "Monitors" → "Apple monitor 24" and click on "Add to cart". Accept pop up confirmation.
        expectedPrice+=adidasPage.addProduct("Monitors","Apple monitor 24");
        System.out.println("expectedPrice = " + expectedPrice);

        //• Navigate to "Cart" → Delete "Apple monitor 24" from cart.
        adidasPage.cart.click();
        expectedPrice-=adidasPage.removeProduct("Apple monitor 24");
        System.out.println("expectedPrice = " + expectedPrice);

    //• Click on "Place order".
          adidasPage.placeOrder.click();
    //• Fill in all web form fields.

        adidasPage.fillForm();
    //• Click on "Purchase"
        adidasPage.purchaseButton.click();
        BrowserUtil.waitFor(2);

    //• Capture and log purchase Id and Amount.
        String confirmationMessage = adidasPage.confirmation.getText();
        System.out.println("confirmationMessage = " + confirmationMessage);

        //manipulaton
        String[] confirmationArray = confirmationMessage.split("\n");
        System.out.println("Arrays.toString(confirmationArray) = " + Arrays.toString(confirmationArray));

        //ORder ID
        String orderID=confirmationArray[0].substring(confirmationArray[0].indexOf(" ")+1);
        System.out.println("orderID = " + orderID);

        //Total Price
        String[] totalPrice = confirmationArray[1].split(" ");
        System.out.println("Arrays.toString(totalPrice) = " + Arrays.toString(totalPrice));

        int actualPrice = Integer.parseInt(totalPrice[1]);

        System.out.println("actualPrice = " + actualPrice);
        System.out.println("expectedPrice = " + expectedPrice);
        //• Assert purchase amount equals expected.

        Assertions.assertEquals(expectedPrice, actualPrice);


    //• Click on "Ok"
        adidasPage.OK.click();
        BrowserUtil.waitFor(2);

    //• Verify that there is no product in the cart
        System.out.println(adidasPage.allProductFromCart.size());
        Assertions.assertTrue(adidasPage.allProductFromCart.size()==0);




    }
}