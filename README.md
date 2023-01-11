# Case Study Assessment by Intel471

In this repository, 2 test automation case studies (UI & API) assigned by Intel471 are performed by following Cucumber BBD Framework.  

## Instructions
Test Scenarios are prepared by;

Şükrü Aydın

QA Automation Engineer

sukraydin29@gmail.com

https://www.linkedin.com/in/şükrü-aydın

### Build Tool
```Maven```

### Dependencies
```selenium-java```
```webdrivermanager```
```cucumber-java```
```cucumber-junit```
```reporting-plugin```
```rest-assured```
```javafaker```
```jackson-databind```

### Plugins
```maven-surefire-plugin```

### System Requirements
```Java 8 + SDK```

### Nice to Know
1 - For running the tests;
        - go to CukerRunner.java class (src > test > java > com > intel471 > runners > CukesRunner)
        - specify a tag
                * @ui for UI tests
                * @api for API tests
                * @all for all tests
        - run CukesRunner
    
2 - Having 2 layers;

    1. Business Layer --> src > test > resources > features
    2. Implementation Layer --> src > > test > java > com > intel471 > step_defs
3 - Automated UI case on <ins>amazon.com</ins> by using recently created credentials.

4 - Followed  <ins>Page Object Model</ins> & <ins>Singleton Design Pattern.</ins>

5 - Achieved <ins>Data Driven Testing</ins> by configuration.properties and Cucumber Expressions.

6 - Implemented <ins>Gherkin Language</ins> in feature files.

7 - Utilized <ins>Hooks</ins> class for taking screenshots if a test fails.


