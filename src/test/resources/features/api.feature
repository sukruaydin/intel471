@api @all
Feature: Homework RestAPI

  Using http://dummy.restapiexample.com provide a test framework that tests the following:

  # I created 4 different scenario for you to clearly and easily find out the console output.

  # @1 --> successfully retrieves all employees and counts the number of employees with age number higher than 30
  # @2 -->successfully adds new employee with age higher than 30 and assert that operation is successful
  # @3 -->successfully updates the employee and asserts that operation is successful
  # @4 -->successfully deletes the employee that he added and asserts the operation is successful

  successfully retrieves all employees and asserts that employees with age number higher than 30 has modified
  # this scenario can't be implemented because --> when we send a POST request for creating a new employee,
  #                                                It is not actually created.
  #                                                We can NOT see/return that specific employee by using GET method.

  @1
  Scenario: 1
    Given User sends GET request to retrieve all employees
    When User finds the number of employees older than 30 and their ID values

  @2
  Scenario: 2
    Given User sends POST request to create new employee older than 30

  @3
  Scenario: 3
    Given User updates the recently created employee "SUKRU", 148000, 38

  @4
  Scenario: 4
    Then User deletes recently created employee