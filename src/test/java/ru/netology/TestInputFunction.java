package ru.netology;


import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static ru.netology.DataGenerator.ClientGenerator.*;


public class TestInputFunction {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void checkingTheRegisteredUser() {
        RegistrationDto validRegisteredUser = generateValidRegisteredUser();
        $("[data-test-id=login] input").setValue(validRegisteredUser.getLogin());
        $("[data-test-id=password] input").setValue(validRegisteredUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(".App_appContainer__3jRx1 h2.heading").shouldBe(visible).shouldHave(text("Личный кабинет"));
    }

    @Test
    void checkingTheBlockedUser() {
        RegistrationDto validRegisteredUser = generateBlockedRegisteredUser();
        $("[data-test-id=login] input").setValue(validRegisteredUser.getLogin());
        $("[data-test-id=password] input").setValue(validRegisteredUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Пользователь заблокирован")).waitUntil(visible, 5000);
    }

    @Test
    void checkingTheWrongPasswordUser() {
        RegistrationDto validRegisteredUser = generateWrongPasswordRegisteredUser();
        $("[data-test-id=login] input").setValue(validRegisteredUser.getLogin());
        $("[data-test-id=password] input").setValue(validRegisteredUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 5000);
    }

    @Test
    void checkingTheWrongLoginUser() {
        RegistrationDto validRegisteredUser = generateWrongLoginRegisteredUser();
        $("[data-test-id=login] input").setValue(validRegisteredUser.getLogin());
        $("[data-test-id=password] input").setValue(validRegisteredUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 5000);
    }

    @Test
    void checkingNotRegisteredUser() {
        RegistrationDto validRegisteredUser = generateNotRegisteredUser();
        $("[data-test-id=login] input").setValue(validRegisteredUser.getLogin());
        $("[data-test-id=password] input").setValue(validRegisteredUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 5000);
    }


}