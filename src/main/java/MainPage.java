import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class MainPage {

    public MainPage OpenMainPage() {
        open("https://www.dns-shop.ru/");
        $x("//*[text()='Бытовая техника']/../../span").click();
        return new MainPage();
    }


}
