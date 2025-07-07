package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomUtils {
    private static final Faker faker = new Faker(new Locale("en-GB"));

    public static String getRandomFirstName(){
        return faker.name().firstName();
    }

    public static String getRandomLastName(){
        return faker.name().lastName();
    }

    public static String getRandomEmail(){
        return faker.internet().emailAddress();
    }

    public static String getRandomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public static String getRandomNumberPhone() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public static String getRandomBirthDay() {
        return String.format("%02d", faker.number().numberBetween(1, 28));
    }

    public static String getRandomBirthMonth() {
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }

    public static String getRandomBirthYear() {
        return String.valueOf(faker.number().numberBetween(1950, 2010));
    }

    public static String getRandomSubject() {
        return faker.options().option(
                "Chemistry", "Maths", "Physics", "Arts", "English",
                "Biology", "History", "Economics", "Computer Science");
    }

    public static String getRandomHobby() {

        return faker.options().option("Sports", "Reading", "Music");
    }

    public static String getRandomAddress(){
        return faker.address().streetAddress();
    }

    public static String getRandomState() {
        return faker.options().option(
                "NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public static String getRandomCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> "Unknown";
        };
    }

}
