package com.dns.test;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static com.dns.test.BaseTest.start;
import static com.dns.test.FindTest.searchTheBest;

public class DnsTest {

    @Before
    public void setUp() {
        Configuration.startMaximized=true;
        open("https://www.dns-shop.ru/");
    }

    @Test
    public void dnsTest(){
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

//    @Test
//    public void bestProduct() throws SQLException {
//        searchTheBest();
//    }
}
