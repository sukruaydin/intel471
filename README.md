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
1 - For running tests scenarios;

        - go to CukerRunner.java class (src > test > java > com > intel471 > runners > CukesRunner)
        - specify a tag in CucumberOptions
                * @ui for UI tests
                * @api for API tests
                * @all for all tests
        - run CukesRunner.java class
    
2 - Cucumber BDD Framework has 2 layers;

        - Business Layer --> src > test > resources > features
        - Implementation Layer --> src > > test > java > com > intel471 > step_defs
3 - Automated UI case on <ins>amazon.com</ins>.

4 - <ins>Relevant credentials</ins> are located in configuration.properties file.

5 - Followed  <ins>Page Object Model</ins> & <ins>Singleton Design Pattern.</ins>

6 - Achieved <ins>Data Driven Testing</ins> by configuration.properties and Cucumber Expressions.

7 - Implemented <ins>Gherkin Language</ins> in feature files.

8 - Utilized <ins>Hooks</ins> class for taking screenshots if a test fails.


