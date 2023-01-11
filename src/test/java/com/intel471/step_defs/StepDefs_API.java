package com.intel471.step_defs;

import com.intel471.utils.APIUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class StepDefs_API {

    //setting some fields as class level --> to be able to use them in other methods
    private Response allEmployees;
    private int id;

    @Given("User sends GET request to retrieve all employees")
    public void userSendsGETRequestToRetrieveAllEmployees() {
        //gets all employees as Response object, sets it as class level
        allEmployees = APIUtils.getAllEmployees();
        //allEmployees.prettyPrint();
    }

    @When("User finds the number of employees older than {int} and their ID values")
    public void userFindsTheNumberOfEmployeesOlderThanAndTheirIDValues(int age) {
        //prints number&ids of employees older than given age
        APIUtils.numberOfEmployeesOlderThanAge(allEmployees,age);
    }

    @Given("User sends POST request to create new employee older than {int}")
    public void userSendsPOSTRequestToCreateNewEmployeeOlderThan(int age) {
        //creates a new employee, sets its id number as class level
        id = APIUtils.createAnEmployeeOlderThanAge(age);
    }

    @Given("User updates the recently created employee {string}, {int}, {int}")
    public void userUpdatesTheRecentlyCreatedEmployee(String name, int salary, int age) {
        //updates recently created employee's data
        APIUtils.updateAnEmployee(id,name,salary,age);
    }

    @Then("User deletes recently created employee \\(id number : {int} )")
    public void userDeletesRecentlyCreatedEmployeeIdNumber(int id) {
        //removes employee by given id
        APIUtils.deleteAnEmployee(id);
    }
}
