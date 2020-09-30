package com.dns.test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class BaseTest {

    public static void start() throws InterruptedException, FileNotFoundException, UnsupportedEncodingException, SQLException {
        openPage();
        setProperties();
        Thread.sleep(3000);
        countWriter("file");
        sortResult();
        Thread.sleep(3000);
        firstFiveResultsInDB();
    }

    public static void openPage() {
        $x("//*[@class='btn btn-additional']").click();
        $x("//a[@href='/catalog/17a8e9b716404e77/bytovaya-texnika/']").hover();
        $x("//a[@href='/catalog/093d2768390b7fd7/xolodilnoe-oborudovanie/']").hover();
        $x("//a[@href='/catalog/4e2a7cdb390b7fd7/xolodilniki/']").click();

        /*
        $x("//span[text()='Товары для кухни']").click();
        $x("//span[text()='Холодильное оборудование']").click();
        $x("//span[text()='Холодильники']").click();*/
    }

    public static void setProperties() throws InterruptedException {
        $x("//*[@class='ui-radio left-filters-avails__radio-list']").scrollTo();                                    //Скролл
        $x("(//input[@class='ui-input-small__input ui-input-small__input_list'])[2]").scrollTo().setValue("50000"); //Ввод до 40000
        $x("//span[text()='Производитель']").scrollTo();                                                            //Скролл
        for (int i = 1; i <= 5; i++) {
            $x("/html/body/div[1]/div/div[5]/div[1]/div[3]/div[4]/div[5]/div/div/div[2]/label[" + i + "]/span[1]").click();
        }
        $x("(//span[text()='Основной цвет'])[1]").click();                                                          //Раскрыть список цветов
        $x("(//input[@class='ui-input-search__input ui-input-search__input_list'])[2]").setValue("черный");         //Ввод черный
        //       $x("(//span[text()='Основной цвет'])[1]").scrollTo();
        //       Thread.sleep(1000);
        $x("(//div[@class='ui-checkbox-group ui-checkbox-group_list'])[3]/label[17]/span[2]").click();              //Выбор черного цвета
        $x("//div[@data-id='f[cyg]']/a").click();                                                                   //выпадающий список количества камер
        $x("(//span[@class='ui-checkbox__box ui-checkbox__box_list'])[88]").click();                                //2
        Thread.sleep(3000);
        $x("//button[text()='Применить']").scrollIntoView(false).click();                                                                 //применить
    }

    public static void countWriter(String filename) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(filename + ".txt", "UTF-8");
        writer.println($$x("//div[@class='n-catalog-product__main']").size());
        writer.close();
    }

    public static void sortResult(){
        $x("(//*[@class='top-filter__icon'])[1]").click();
        $x("(//*[@class='ui-radio__content'])[9]").click();
    }

    public static void firstFiveResultsInDB() throws SQLException {
        int uiid = getMaxUiId();
        int cycles = 5;
        int resCount = $$x("//div[@class='n-catalog-product__main']").size();

        if (resCount<cycles){
            cycles = resCount;
        }

        for (int i = 1; i <= cycles; i++) {

            String name = $x("(//div[@class='product-info__title-link'])[" + i + "]/a").getText();
            double score = Double.parseDouble($x("(//div[@class='product-info__rating'])[" + i + "]").getAttribute("data-rating"));
            String pricestr = $x("(//div[@class='product-min-price__current'])["+ i +"]").getText().replace(" ", "");
            int price = Integer.parseInt(pricestr.substring(0,pricestr.length()-1));

            try {
                int id = getMaxId();
                insertInfoInDB(id, uiid, name, price, score);
            } catch (NumberFormatException e) {
                insertInfoInDB(1, 1, name, price, score);
            }


        }
    }

    public static Connection connectToDB() throws SQLException {
        final String user = "postgres";
        final String password = "1234";
        final String url = "jdbc:postgresql://localhost:5432/dns_test";

        final Connection connection = DriverManager.getConnection(url, user, password);

        return  connection;
    }

    public static int getMaxUiId() throws SQLException {

        final Connection connection = connectToDB();

        try (PreparedStatement statement = connection.prepareStatement("SELECT MAX(uuid) FROM dns_shop")) {



            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                try {
                    return Integer.parseInt(resultSet.getString(1))+1;
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
            else return 0;

        } finally {
            connection.close();
        }
    }

    public static int getMaxId() throws SQLException {

        final Connection connection = connectToDB();

        try (PreparedStatement statement = connection.prepareStatement("SELECT MAX(id) FROM dns_shop")) {

            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                try {
                    return Integer.parseInt(resultSet.getString(1))+1;
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
            else return 0;

        } finally {
            connection.close();
        }
    }

    public static void insertInfoInDB(int id, int uiid, String name, int price, double score) throws SQLException {

        final Connection conn = connectToDB();

        // Employees (id, full_name, gender, hire_date)
        // ID: Auto Increase
        String sql = "Insert into dns_shop " //
                + " (uuid, name, price, rating, id) " //
                + " values " //
                + " (?, ?, ?, ?, ?)";

        // Создать объект PreparedStatement.
        PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

//        pstm.setInt(1, 2);
        pstm.setInt(1, uiid);
        pstm.setString(2, name);
        pstm.setInt(3, price);
        pstm.setDouble(4,score);
        pstm.setDouble(5,id);

        // Execute!
        pstm.execute();

    }


}

