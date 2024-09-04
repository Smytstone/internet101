import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static io.restassured.RestAssured.given;


public class WebTest {

    public void setUp() {
        Configuration.timeout = 10000;
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void shouldGetSuccessOrder() {
        int iterations = 5;
        ElementsCollection resultLinks = $$("[datatest=main_input_street_home_new]");


        for (int i = 0; i < iterations; i++) {
            open("https://piter-online.net/");
            resultLinks.get(2).setValue("Тестовая линия");
            $("[datatest=dropdown_list_main]").click();
            resultLinks.get(1).setValue("1").pressEnter();
            if (i == 0) {
                $(byClassName("app727")).click();
            }
            sleep(2000);
            $("[data-test=providers_button_verify]").click();
            sleep(10000);
            $("[datatest=rates_popup1_from_quiz_input_tel]").setValue("1111111111");
            $("[data-test=rates_popup1_from_quiz_send_phone]").click();
            sleep(5000);
            given()
                    .baseUri("https://piter-online.net/api/orders?type=site101-callback")
                    .when()
                    .get()
                    .then()
                    .statusCode(200);
        }
    }
}
