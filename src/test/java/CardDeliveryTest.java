import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldBeBookingDelivery() {
        Configuration.headless = true;
        String date = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        open("http://localhost:9999/");

        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Печкин-Лавочкин Иван");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $(By.className("button__text")).click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(14));
    }

    @Test
    void shouldSelectCityFromList() {
        Configuration.headless = true;
        String date = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String city = "Мо";

        open("http://localhost:9999/");

        $("[data-test-id=city] input").setValue(city);
        $$("[class=menu-item__control]").findBy(Condition.ownText(city)).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Печкин-Лавочкин Иван");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $(By.className("button__text")).click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(14));
    }

    @Test
    void shouldSelectDateFromCalendar() {
        Configuration.headless = true;
        String day = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd"));

        open("http://localhost:9999/");

        $("[data-test-id=city] input").setValue("Москва");
        $("[class=input__icon]").click();
        $$("[role=gridcell]").findBy(text(day)).click();
        $("[data-test-id=name] input").setValue("Печкин-Лавочкин Иван");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $(By.className("button__text")).click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(14));
    }
}
