Feature: adding items to the cart and removing items from the cart
  Scenario: adding items to the cart and removing items from the cart
    Given Open site
    When adding '3' item to cart
    Then item counter has changed
    When removing items to cart
    Then items removed from the cart
    Then out site and close driver