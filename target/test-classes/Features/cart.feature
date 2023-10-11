Feature: Cart Page functionality

  Background: User is on Home page
    Given the user is on Home page

  @cart
  Scenario Outline: Verify the cart
    And user clicks Shop link
    Then user is on shop page
    And the user adds the following items to the cart and gets total Price of all items
      | Product        | Quantity |
      | Stuffed Frog   |        2 |
      | Fluffy Bunny   |        5 |
      | Valentine Bear |        3 |
    And user clicks Cart link
    And verifies cart total quantity
    And user verifies the product total is equal to the sum of subtotals

   