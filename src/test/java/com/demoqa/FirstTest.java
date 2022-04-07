package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FirstTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage() {
        Selenide.open("/automation-practice-form");
    }

    @AfterEach
    void closeDriver() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    void formFilling() {
        String name = "Максим";
        String surname = "Голяницкий";
        String sex = "Male";
        String email = "golyanitskiy@gmail.com";
        String phoneNumber = "9176255571";
        String address = "Shrewsbury, MA 01545";
        String state = "Rajasthan";
        String city = "Jaipur";

        $("#firstName").setValue(name);
        $("#lastName").setValue(surname);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(sex)).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").selectOption("1987");
        $$(".react-datepicker__day").findBy(text("16")).click();
        $("#subjectsInput").setValue("Co");
        $$("[id^='react-select-2-option']").findBy(text("Computer Science")).click();
        $$("#hobbiesWrapper").findBy(text("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("image.jpg");
        $("#currentAddress").setValue(address);
        zoom(0.75); // иначе не дает кликнуть по штату и городу
        $("#state").click();
        $$("[id^='react-select-3-option']").findBy(text("Rajasthan")).click();
        $("#city").click();
        $$("[id^='react-select-4-option']").findBy(text("Jaipur")).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(name + ' ' + surname),
                text(email),
                text(sex),
                text(phoneNumber),
                text("16 September,1987"),
                text("Computer Science"),
                text("Reading"),
                text("image.jpg"),
                text(address),
                text(state + ' ' + city)
        );

        $("#closeLargeModal").click();
    }
}
