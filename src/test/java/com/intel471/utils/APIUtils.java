package com.intel471.utils;

import com.github.javafaker.Faker;
import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class APIUtils {

    private static Faker faker = new Faker();

    public static Response getAllEmployees(){
        baseURI = ConfigurationReader.getProperty("apiUrl");

        return  given().accept(ContentType.JSON)
                .when().get("/employees")
                .then().statusCode(200)
                .and().extract().response();
    }

    public static void numberOfEmployeesOlderThanAge(Response response, int age){
        List<Integer> ids = response.jsonPath().get("data.findAll {it.employee_age>"+age+"}.id");
        System.out.println("id numbers of people older than "+age+" : "+ids);
        System.out.println("number of people older than "+age+" is : "+ids.size());
    }

    public static void createAnEmployeeOderThanAge(int age){
        baseURI = ConfigurationReader.getProperty("apiUrl");

        String firstName = faker.name().firstName();
        int salary = Integer.parseInt(faker.numerify("######"));
        int age1 = faker.number().numberBetween(age+1,99);

        Map<String,Object> map = new LinkedHashMap<>();
        map.put("name",firstName);
        map.put("salary",salary);
        map.put("age",age1);

        Response response = given().contentType(ContentType.JSON)
                .and().body(map)
                .when().post("/create")
                .then().statusCode(200)
                .and().body("message", Matchers.is("Successfully! Record has been added."))
                .and().extract().response();

        String nameOfEmployee = response.jsonPath().getString("data.name");
        int ageOfEmployee = response.jsonPath().getInt("data.age");
        System.out.println("Say Hello to new colleague named : " + nameOfEmployee);
        System.out.println(nameOfEmployee + " is " + ageOfEmployee + " years old.");
    }

    public static void updateAnEmployee(int id, String name, int salary, int age){
        baseURI = ConfigurationReader.getProperty("apiUrl");

        Map<String,Object> map = new LinkedHashMap<>();
        map.put("name",name);
        map.put("salary",salary);
        map.put("age",age);

        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("id",id)
                .and().body(map)
                .when().put("/update/{id}")
                .then().statusCode(200)
                .and().body("message", Matchers.is("Successfully! Record has been updated."))
                .and().extract().response();
        response.prettyPrint();
    }


}
