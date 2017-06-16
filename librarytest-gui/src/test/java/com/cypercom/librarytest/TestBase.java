/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cypercom.librarytest;

import static com.codeborne.selenide.Selenide.open;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author testautom-nack
 */
public class TestBase {

    @Before
    public void setupp() {

        System.setProperty("webdriver.chrome.driver", "/home/testautom-nack/testautom-nack/seleniumdrivers/chromedriver");
        System.setProperty("selenide.browser", "Chrome");
        open("http://localhost:8080/librarytest");

    }

    @After
    public void teardown() {

    }

}
