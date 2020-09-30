package com.dns.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.dns.test.BaseTest.connectToDB;

public class FindTest {


    public static void searchTheBest() throws SQLException {
        final Connection connection = connectToDB();
        String search = null;
        Double rating = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT name, rating FROM dns_shop WHERE rating = (SELECT MAX(rating) FROM dns_shop) \n" +
                "AND price = (SELECT MIN(price) FROM dns_shop \n" +
                "WHERE rating = (SELECT MAX(rating) FROM dns_shop)) ")) {

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                search = resultSet.getString(1);
                rating = resultSet.getDouble(2);
            }


        } finally {
            connection.close();
        }
        $x("(//input[@name='q'])[2]").setValue(search).pressEnter();
        $x("//span[@itemprop='ratingValue']").shouldHave(text(String.valueOf(rating)));
    }

}
