@ui @all
Feature: Homework UI

  # Using any public e-commerce(dummy) site provide a test framework that tests the following:

      # @a --> successfully add two products to cart and assert that the operation is successful
      # @b --> remove successfully one of them and assert that the result is correct
      # @b --> remove successfully the remaining one and assert that the result is correct
      # @c --> try to remove again one of the products expecting a failed test in this case

  Background:
    Given User navigates amazon.com
    When User clicks sign in bar
    And User logs in

  @a
  Scenario: User can add product(s) to the cart
    Then User searches "selenium", adds the first product to cart
    Then User searches "turkish coffee", adds the first product to cart
    Then User searches "statue of liberty", adds the first product to cart

  @b
  Scenario: User can remove product(s) from cart
    Then User removes a product from cart
    Then User removes a product from cart

  @c
  Scenario: User can NOT remove product(s) from empty cart
    And User removes all products from cart
    Then User removes a product from cart
    # since it was stated in the assessment instruction that this case should fail, I let this case to fail
    # It throws RuntimeException
