Feature: Quikr smoke
  Learning ropes of mobile test automation

  Scenario: Login with unverified email
  User has registered already but did not verify email

    Given I launch Quikr app
    When I login with email bob@gmail.com
    And I enter the password "123"
    Then I get an error message
