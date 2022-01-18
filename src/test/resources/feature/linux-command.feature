@linux
Feature: Linux command Test

  @date
  Scenario: Change Date
    Given run docker and container
    When date change +5 days
    And set up the CURRENT date

  @logs
  Scenario: Execute .sh
    When executed linux-logs.sh file

  @logs
  Scenario: Search logs
    When executed search-linux-logs.sh file
    Then log "Key type blacklist registered" wos found