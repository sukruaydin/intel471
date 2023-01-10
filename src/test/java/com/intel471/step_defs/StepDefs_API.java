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
        allEmployees = APIUtils.getAllEmployees();
        //allEmployees.prettyPrint();
    }

    @When("User finds the number of employees older than {int} and their ID values")
    public void userFindsTheNumberOfEmployeesOlderThanAndTheirIDValues(int age) {
        APIUtils.numberOfEmployeesOlderThanAge(allEmployees,age);
    }

    @Given("User sends POST request to create new employee older than {int}")
    public void userSendsPOSTRequestToCreateNewEmployeeOlderThan(int age) {
        id = APIUtils.createAnEmployeeOlderThanAge(age);
    }

    @Given("User updates the recently created employee {string}, {int}, {int}")
    public void userUpdatesTheRecentlyCreatedEmployee(String name, int salary, int age) {
        APIUtils.updateAnEmployee(id,name,salary,age);
    }

    @Then("User deletes recently created employee")
    public void userDeletesRecentlyCreatedEmployee() {
        APIUtils.deleteAnEmployee(id);
    }


}
