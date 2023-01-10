package com.intel471.step_defs;

import com.intel471.utils.APIUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;

public class StepDefs_API {

    private Response allEmployees;

    @Given("User sends GET request to retrieve all employees")
    public void userSendsGETRequestToRetrieveAllEmployees() {
        allEmployees = APIUtils.getAllEmployees();
        //allEmployees.prettyPrint();
    }

    @When("User finds the number of employees older than {int} and their ID values")
    public void userFindsTheNumberOfEmployeesOlderThanAndTheirIDValues(int age) {
        APIUtils.numberOfEmployeesOlderThanAge(allEmployees,age);
    }


    @Given("User sends POST request to create new employee older than {int}")
    public void userSendsPOSTRequestToCreateNewEmployeeOlderThan(int age) {
        APIUtils.createAnEmployeeOderThanAge(35);
    }


    @Given("User updates employee whose id number is {int}, {string}, {int}, {int}")
    public void userUpdatesEmployeeWhoseIdNumberIs(int id, String name, int salary, int age) {
        APIUtils.updateAnEmployee(id,name,salary,age);
    }
}
