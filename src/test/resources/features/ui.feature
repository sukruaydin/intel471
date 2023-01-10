@ui
Feature:

  @1
  Scenario: User can add product(s) to the cart
    Given User navigates amazon.com
    When User clicks sign in bar
    And User logs in
    Then User searches "selenium", adds the first product to cart
    Then User searches "turkish coffee", adds the first product to cart

  @2
  Scenario: User can remove product(s) from cart
    Given User navigates amazon.com
    When User clicks sign in bar
    And User logs in
    Then User removes a product from cart
    Then User removes a product from cart

  @3
  Scenario: User can NOT remove product(s) from empty cart
    Given User navigates amazon.com
    When User clicks sign in bar
    And User logs in
    And User removes all products from cart
    Then User removes a product from cart
