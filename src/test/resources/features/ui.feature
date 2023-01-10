@ui @all
Feature:

  # Background keyword could have been used for the first 3 steps of the scenarios.
  # In case you would ask me to automate sth more and also to make it read easier I didn't use it.

  @a
  Scenario: User can add product(s) to the cart
    Given User navigates amazon.com
    When User clicks sign in bar
    And User logs in
    Then User searches "selenium", adds the first product to cart
    Then User searches "turkish coffee", adds the first product to cart
    Then User searches "statue of liberty", adds the first product to cart

  @b
  Scenario: User can remove product(s) from cart
    Given User navigates amazon.com
    When User clicks sign in bar
    And User logs in
    Then User removes a product from cart
    Then User removes a product from cart

  @c
  Scenario: User can NOT remove product(s) from empty cart
    Given User navigates amazon.com
    When User clicks sign in bar
    And User logs in
    And User removes all products from cart
    Then User removes a product from cart
