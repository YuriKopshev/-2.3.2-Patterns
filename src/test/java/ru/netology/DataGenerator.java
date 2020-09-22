package ru.netology;

import com.github.javafaker.Faker;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private DataGenerator() {}


    @Data
    public static class ClientGenerator {
        public static RegistrationDto generateUser() {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationDto(faker.name().firstName(),
                    faker.number().digits(4),
                    "active" );
        }
    }
}