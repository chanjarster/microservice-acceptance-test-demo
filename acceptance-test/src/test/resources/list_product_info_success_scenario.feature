Feature: List product information with price

  Scenario: Everything is good
    Given Product Service is up and running
    And Product Price Service is up and running

    When User query product list

    Then Get following products

      | id       | name | description            | price |
      | animal-1 | dog  | woof woof              | 1000  |
      | animal-2 | duck | quack quack            | 40    |
      | animal-3 | fox  | what does the fox say? | 5000  |
