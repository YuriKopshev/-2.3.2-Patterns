package ru.netology;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestInputFunction {
    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();



    @Test
    void shouldTestCreateUser(){
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto("Ivan", "1111", "active")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    @Test
    void shouldTestCreateNewUser(){
        given()
                .spec(requestSpec)
                .body(new RegistrationDto("Alex", "3434", "active"))
                .when() //
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldTestBlockUser(){
        given() // "дано"
                .spec(requestSpec)
                .body(new RegistrationDto("Ivan", "1111", "blocked"))
                .when() // "когда"
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

   @Test
    void shouldActivateUser() {
        given()
        .spec(requestSpec)
                .body(new RegistrationDto("Ivan", "1111", "active"))
               .when()
               .post("/api/system/users")
               .then()
               .statusCode(200)


        ;
    }




}
