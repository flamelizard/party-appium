Feature: Smoke mobile web page - RelocateMe
#  Background:

  Scenario: Main page basics
    Given I open page "http://relocate.me"
    When I search for job "blaze" and place "chicago"
