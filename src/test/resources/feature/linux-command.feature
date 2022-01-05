@linux
Feature: Linux command Test

  @date
  Scenario: Change Date
    Given run docker and container
    When date change +5 days
    And set up the CURRENT date