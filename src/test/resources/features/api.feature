Feature:


  @a
  Scenario:
    Given User sends GET request to retrieve all employees
    When User finds the number of employees older than 30 and their ID values

  @b
  Scenario:
    Given User sends POST request to create new employee older than 30

  @c
  Scenario:
    Given User updates employee whose id number is 8, "SUKRU", 95000, 31