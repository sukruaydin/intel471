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


    /**
     * sends GET request, returns all employees in Response object
     * @return
     */
    public static Response getAllEmployees(){
        baseURI = ConfigurationReader.getProperty("apiUrl");

        return  given().accept(ContentType.JSON)
                .when().get("/employees")
                .then().statusCode(200)
                .and().extract().response();
    }


    /**
     * prints id values of employees older than given age
     * prints number of employees older than given age
     * @param response
     * @param age
     */
    public static void numberOfEmployeesOlderThanAge(Response response, int age){
        List<Integer> ids = response.jsonPath().get("data.findAll {it.employee_age>"+age+"}.id");
        System.out.println("id numbers of people older than "+age+" : "+ids);
        System.out.println("number of people older than "+age+" is : "+ids.size());
    }


    /**
     * sends POST request, creates a random employee older than given age
     * asserts statusCode is 200
     * asserts response message is "Successfully! Record has been added."
     * prints the name and age of the recently created employee
     * returns recently created employee id
     * @param age
     * @return
     */
    public static int createAnEmployeeOlderThanAge(int age){
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
        int id = response.jsonPath().getInt("data.id");
        System.out.println("Say Hello to new colleague named : " + nameOfEmployee);
        System.out.println(nameOfEmployee + " is " + ageOfEmployee + " years old.");

        return id;
    }


    /**
     * sends PUT request, updates the employee given by id with the provided values of name, salary, age
     * asserts statusCode is 200
     * asserts response message is "Successfully! Record has been updated."
     * prints the response body
     * @param id
     * @param name
     * @param salary
     * @param age
     */
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


    /**
     * sends DELETE request, deletes the employee by given id
     * asserts statusCode is 200
     * asserts response message is "Successfully! Record has been deleted"
     * prints the response body
     * @param id
     */
    public static void deleteAnEmployee(int id){
        baseURI = ConfigurationReader.getProperty("apiUrl");

        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("id",id)
                .when().delete("/delete/{id}")
                .then().statusCode(200)
                .and().body("message", Matchers.is("Successfully! Record has been deleted"))
                .and().extract().response();
        response.prettyPrint();
    }


}
