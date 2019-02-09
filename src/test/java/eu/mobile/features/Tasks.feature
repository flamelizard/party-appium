Feature: Smoke android app - Tasks
  Learning ropes of mobile automation testing

  @tasks
  Scenario: Create new list
    Given I launch app
    When I create list
    Then I delete active list