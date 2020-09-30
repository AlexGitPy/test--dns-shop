package com.dns.test;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import static com.codeborne.selenide.Selenide.*;

public class MainTest {
    @Before
    public void SetUp(){
        Configuration.browser = "chrome";
        Configuration.startMaximized=true;
    }

    @Test
    public void OpenAppliancesPage() throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
        open("https://www.dns-shop.ru/");
        $x("//*[@class='btn btn-additional']").click();
        $x("//*[text()='Бытовая техника']").click();
        $x("//span[text()='Товары для кухни']").click();
        $x("//*[@id='Холодильное оборудование']/..").click();
        $x("//*[@id='Холодильники']/..").click();
        $x("//*[@class='ui-radio left-filters-avails__radio-list']").scrollTo();
        $x("(//*[@class='ui-input-small ui-input-small_list']//input)[2]").setValue("40000");
        $x("//span[text()='Производитель']").scrollTo();
        for (int i = 1; i <= 5; i++) {
            $x("((//*[@class='ui-list-controls__content'])[2]//label[@class='ui-checkbox ui-checkbox_list'])[" + i + "]").click();
        }

//        List<WebElements> checkboxes = $$x("(//*[@class='ui-list-controls__content'])[2]//span[@class='ui-checkbox__box ui-checkbox__box_list']");
//        for (int i=1; i <=5; i++){
//            checkboxes.get(i).click();
//        }

        $x("//*[text()='Количество камер']").click();
        $x("//*[@id='catalog-items-page']/div[5]/div[1]/div[3]/div[4]/div[12]/div[1]/div/div/label[2]/span[2]").click();
        $x("//*[text()='Основной цвет']").click();
        $x("(//i[@class='ui-list-controls__icon ui-list-controls__icon_down'])[2]").click();
        $x("(//*[@class='ui-collapse__content ui-collapse__content_list ui-collapse__content_in'])[1]//label[16]").click();
        Thread.sleep(3000);
        $x("//button[text()='Применить']").click();
        Thread.sleep(3000);
        PrintWriter writer = new PrintWriter("test" + ".txt", "UTF-8");
        writer.println($$x("//div[@class='n-catalog-product__main']").size());
        writer.close();
        Thread.sleep(3000);
        $x("(//[@class='top-filter__icon'])[1]").click();
        $x("(//[@class='ui-radio__content'])[9]").click();
    }

}
