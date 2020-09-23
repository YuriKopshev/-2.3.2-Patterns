package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private static Faker faker = new Faker(new Locale("en"));
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private DataGenerator() {
    }

    static void registerUser(RegistrationDto user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    static String getLogin() {
        return faker.name().username();
    }

    static String getPassword() {
        return faker.internet().password();
    }

    public static class ClientGenerator {

        public static RegistrationDto generateValidRegisteredUser() {
            RegistrationDto user = new RegistrationDto(getLogin(), getPassword(), "active");
            registerUser(user);
            return user;
        }

        public static RegistrationDto generateWrongPasswordRegisteredUser() {
            String login = getLogin();
            RegistrationDto user = new RegistrationDto(login, getPassword(), "active");
            registerUser(user);
            return new RegistrationDto(login, getPassword(), "active");
        }

        public static RegistrationDto generateWrongLoginRegisteredUser() {
            String password = getPassword();
            RegistrationDto user = new RegistrationDto(getLogin(), password, "active");
            registerUser(user);
            return new RegistrationDto(getLogin(), password, "active");
        }

        public static RegistrationDto generateBlockedRegisteredUser() {
            RegistrationDto user = new RegistrationDto(getLogin(), getPassword(), "blocked");
            registerUser(user);
            return user;
        }

        public static RegistrationDto generateNotRegisteredUser() {
            RegistrationDto user = new RegistrationDto(getLogin(), getPassword(), "active");
            return user;
        }


    }
}