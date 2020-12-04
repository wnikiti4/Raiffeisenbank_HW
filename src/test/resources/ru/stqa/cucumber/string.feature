Feature: adding items to the cart and removing items from the cart
  Scenario: adding items to the cart and removing items from the cart
    Given Open site
    When adding item to cart
    And choose setting item
    Then item counter has changed
    When removing items to cart
    Then items removed from the cart