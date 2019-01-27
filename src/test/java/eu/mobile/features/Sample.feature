Feature: Ropes of mobile testing
  Native apps under test - Quikr (unreliable), Tasks

  Scenario: Login with unverified email
  User has registered already but did not verify email

    Given I launch Quikr app
    When I login with email bob@gmail.com
    And I enter the password "123"
    Then I get an error message

  @debug
  Scenario: SauceLabs - Login
    Given SauceLabs - I launch Quikr app
    And SauceLabs - I log in

  @tasks
  Scenario: Create new list
    Given I launch app
    When I create list
    Then I delete active list