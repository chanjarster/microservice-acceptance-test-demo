Feature: List product information with price

  Scenario: Product Price Service throws exception when being queried
    Given Product Service is up and running
    And Product Price Service is up and running

    Given Install the byteman script product_price_exception.btm to Product Price Service

    When User query product list

    Then Get following products

      | id       | name | description            | price |
      | animal-1 | dog  | woof woof              |       |
      | animal-2 | duck | quack quack            |       |
      | animal-3 | fox  | what does the fox say? |       |
